# Tree-index

This project must came from the professor's publication ... http://link.springer.com/chapter/10.1007/978-3-642-41230-1_28

## Data

From the paper, they say they use https://www.openstreetmap.org/export 

## Paper summary

Page 1-2

- Hive's compact index and bitmap index are column that has few values, like gender, which only have two values. it won't
work well for ranged query, like age > 16
- C-index == Cloud index :)
- partition table and have sub-index for each partition
- a meta table store the partition and other meta data for sub-index ( This table could use MySQL or Redis)
- cache 

Page 3-4
- Ref2 Existing distributed B+ index, 1. not good at range query. 2. need to query other node http://www.vldb.org/pvldb/1/1453922.pdf
- Ref17 a built-in block-based index structure on HDFS http://ieeexplore.ieee.org/xpls/abs_all.jsp?arnumber=5575646&tag=1
- Architecture, data loader **sort and partition**, indexer, build index in parallel, meta table and client api

### Problems in the paper

- Page4, the data loader sort and partition based on key, so it may only have one key? what if it want multiple keys

### Unfamiliar terms in the paper

- one and multi dimensional queries, what does dimensional means ? seems to be `where age = 16 and school = sjtu` Faceted Search?

### Interesting in the paper 

- Page3 mentioned Sinfonia: a new paradigm for building scalable distributed systems http://dl.acm.org/citation.cfm?id=1294278 I never heard of it 
before ... 
- Page4 mentioned multi dimensional queries, seems to be `Faceted Search`
