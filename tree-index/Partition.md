# Partition

partition is used to sort and split into several small files for further processing

the default partition function is hash, which means, all the k-v pairs with the same key go to one 
reducer, but the order is not certain between reducers, that's why we use TotalOrderPartitioner

- have a function to determine which partition the k,v pair should go to, which is `getPartition` ??
- define number of partitions, this is done by defining number of reducers

in order to know how to partition, we can use sampler, which will generate a sequence file `_partition.lst`
to tell the partitioner, if key > keyvalue1 goto partition-1, etc. 

The sampler is taking sample from the input source ?? (seems to be, from http://mail-archives.apache.org/mod_mbox/hadoop-common-user/201001.mbox/%3Ca549c83b1001042339i1ae7dcdasae1aa7bc37c98b5@mail.gmail.com%3E)
so the input source key class should be the same as map output class in order to use it.

so for the default mapper, this is fine, we can use it to sort, like use the KeyValueTextFileInput and result in sort
and partition using the first world in each line (KeyValueTextFileInput split by tab, and take first word as key, rest as
value)

however, if the key should be treated as number instead of text, because text sort is different from number sort,
text 98 > 950, it's not what we want, but change the MapOutKeyClass to IntWritable and add a mapper to turn the key from
string to IntWritable won't work, because the sampler is using the input source key type for sample and partition, 
... TODO ... this part is still quite a mess

so the possible solutions for use number as key and get right sample are

1. use the custom k-v FileInput by modify the original class
this has one drawback, the k must be what you need to sort on, meaning, your mapper can only change the value,
key-in == key-out in your mapper, otherwise the result is unpredictable.

2. ~~use text input format because its key is long~~
this won't work, because it's dumb to sample and partition based on line number, which is not the data we want.

3. use sequence file as source
the key,value type is defined in sequence file itself. 

so one way to do the work is, you have a tab separated table file like

name age height
jack  18 180
marry 19 175

you want to sort based on age, first you have a mapper, use TextInput, and generate a sequence file which use 
age as key, and the row as value. Then you sample based on the sequence file, and everything will work, I guess
