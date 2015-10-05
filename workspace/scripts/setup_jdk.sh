#!/usr/bin/env bash

tarball="jdk-7u79-linux-x64.tar.gz"

if [ ! -f "$tarball" ]; then
    echo "jdk tar.gz file not found, exit"
    exit
fi

echo "extrcting jdk"
sudo mkdir /usr/lib/jvm
sudo tar zxvf ${tarball}  -C /usr/lib/jvm
cd /usr/lib/jvm
sudo mv jdk1.7.0_79/ java-7-sun

echo "Add env"
sed -i '$ a\'"export JAVA_HOME=/usr/lib/jvm/java-7-sun" ~/.bashrc
sed -i '$ a\'"export JRE_HOME=\${JAVA_HOME}/jre " ~/.bashrc
sed -i '$ a\'"export PATH=\${JAVA_HOME}/bin:\$PATH" ~/.bashrc

source ~/.bashrc

echo "Update alternatives"
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-7-sun/bin/java 300
sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-7-sun/bin/javac 300
sudo update-alternatives --install /usr/bin/jar jar /usr/lib/jvm/java-7-sun/bin/jar 300
sudo update-alternatives --install /usr/bin/javah javah /usr/lib/jvm/java-7-sun/bin/javah 300
sudo update-alternatives --install /usr/bin/javap javap /usr/lib/jvm/java-7-sun/bin/javap 300

echo "now java is working"
java -version