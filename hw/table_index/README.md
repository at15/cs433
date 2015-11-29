# TODO:desc

## Problems

- Must create table and right column family before insert
- column family name is case sensitive, index != INDEX

A working config

````xml
<configuration>
    <property>
        <!--<name>hbase.rootdir</name>-->
        <!--<value>file:///Users/gpl/repos/data/hbase</value>-->
        <name>hbase.rootdir</name>
        <value>hdfs://localhost:9000/hbase</value>
    </property>
    <!--<property>-->
        <!--<name>hbase.zookeeper.property.dataDir</name>-->
        <!--<value>/home/at15/workspace/data/zookeeper</value>-->
    <!--</property>-->
    <!--<property>-->
        <!--<name>hbase.cluster.distributed</name>-->
        <!--<value>true</value>-->
    <!--</property>-->
</configuration>

````