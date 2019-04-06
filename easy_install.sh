#!/bin/bash
cd /tmp


### installing DataBaseApi.jar

FILE=/usr/local/DataBaseApi/DataBaseApi.jar
if [ -f $FILE ]; then
  echo "tom installed. Updating...."
  git clone https://github.com/pingwinno/DataBaseApi.git
  cd ./DataBaseApi
  mvn package
else
### installing dependencies
  echo -e "\033[36m Installing dependencies.\033[0m"
  apt update
  apt upgrade -y
  apt install -y maven git jsvc
  git clone https://github.com/pingwinno/DataBaseApi.git
  cd ./DataBaseApi
  mvn package
  adduser --system --no-create-home --group db-daemon
  mkdir /usr/local/DataBaseApi/
  mkdir /var/log/db/
  chown archive-daemon /var/log/db/
  chmod u+w /var/log/db/
   mkdir /etc/db/
    mv config.prop /etc/db/
fi


mv ./target/DataBaseApi.jar /usr/local/DataBaseApi/
mv archive.sh /usr/local/bin/
chmod +x /usr/local/bin/db.sh


cp archive.service /etc/systemd/system/db.service
cd ../
rm -rf DataBaseApi
chmod 644 /etc/systemd/system/db.service
systemctl daemon-reload
systemctl enable db.service

echo -e "\033[32m Installation completed.\033[0m"
