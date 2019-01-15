#!/bin/bash
cd /tmp


### installing Twitch-o-matic

FILE=/usr/local/twitch-o-matic/twitch-o-matic.jar
if [ -f $FILE ]; then
  echo "tom installed. Updating...."
  git clone https://github.com/TwitchStreamhub/StreamArchiveBackend.git
  cd ./StreamArchiveBackend
  mvn package
else
### installing dependencies
  echo -e "\033[36m Installing dependencies.\033[0m"
  apt update
  apt upgrade -y
  apt install -y maven git jsvc
  git clone https://github.com/TwitchStreamhub/StreamArchiveBackend.git
  cd ./StreamArchiveBackend
  mvn package
  adduser --system --no-create-home --group archive-daemon
  mkdir /usr/local/StreamArchiveBackend/
  mkdir /var/log/archive/
  chown archive-daemon /var/log/archive/
  chmod u+w /var/log/archive/
fi


mv ./target/ArchivePortal.jar /usr/local/StreamArchiveBackend/
mv archive.sh /usr/local/bin/
chmod +x /usr/local/bin/archive.sh


cp archive.service /etc/systemd/system/archive.service
cd ../
rm -rf StreamArchiveBackend
chmod 644 /etc/systemd/system/archive.service
systemctl daemon-reload
systemctl enable archive.service

echo -e "\033[32m Installation completed.\033[0m"
