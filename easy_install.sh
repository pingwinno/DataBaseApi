#!/bin/bash
cd /tmp


### installing DataBaseApi.jar

FILE=/usr/local/StreamArchiveBackend/DataBaseApi.jar
if [ -f $FILE ]; then
  echo "tom installed. Updating...."
  git clone https://github.com/pingwinno/DataBaseApi.git
  cd ./StreamArchiveBackend
  mvn package
else
### installing dependencies
  echo -e "\033[36m Installing dependencies.\033[0m"
  apt update
  apt upgrade -y
  apt install -y maven git jsvc
  git clone https://github.com/pingwinno/DataBaseApi.git
  cd ./StreamArchiveBackend
  mvn package
  adduser --system --no-create-home --group db-daemon
  mkdir /usr/local/StreamArchiveBackend/
  mkdir /var/log/db/
  chown archive-daemon /var/log/db/
  chmod u+w /var/log/db/
   mkdir /etc/db/
    mv config.prop /etc/db/
fi


mv ./target/DataBaseApi.jar /usr/local/StreamArchiveBackend/
mv archive.sh /usr/local/bin/
chmod +x /usr/local/bin/db.sh


cp archive.service /etc/systemd/system/db.service
cd ../
rm -rf StreamArchiveBackend
chmod 644 /etc/systemd/system/db.service
systemctl daemon-reload
systemctl enable db.service

echo -e "\033[32m Installation completed.\033[0m"
