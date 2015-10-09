# Config environment

Code and dev environment setup scripts

NOTE: the `Vagrantfile` does NOT config provision
to install dependencies

## Config java

- Download jdk7

`curl -LO 'http://download.oracle.com/otn-pub/java/jdk/7u79-b15/jdk-7u79-linux-x64.tar.gz' -H 'Cookie: oraclelicense=accept-securebackup-cookie'`

- extract and set java home

````
echo "extrcting jdk"
sudo mkdir /usr/lib/jvm
sudo tar zxvf ./jdk-7u79-linux-x64.tar.gz  -C /usr/lib/jvm
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
````

You can try the `setup_jdk.sh` in scripts folder

## Config hadoop

- `wget http://mirrors.cnnic.cn/apache/hadoop/common/stable/hadoop-2.7.1.tar.gz`
- change `hadoop-env.sh`, use absolute path for JAVA_HOME
- change `core-site.xml`
- change `hdfs-site.xml`
- format namenode `bin/hadoop namenode -format`

### FAQ

- change `hadoop-env.sh` JAVA_HOME should be hard coded, otherwise when ssh to localhost, it will lost. http://stackoverflow.com/questions/20628093/java-home-is-not-set-in-hadoop
- `java.lang.IllegalArgumentException: Invalid URI for NameNode address` forgot to set `core-site.xml` and `hdfs-site.xml` http://blog.csdn.net/xiaolang85/article/details/7975618
- `org.apache.hadoop.hdfs.server.common.InconsistentFSStateException: Directory /tmp/hadoop-vagrant/dfs/name is in an inconsistent state: storage directory does not exist or is not accessible.` http://www.linuxidc.com/Linux/2012-03/56348.htm

If you see `log`, it means error has occurred, `out` is the normal log suffix