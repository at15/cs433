# Build tree-index for Hive

- Related issue https://github.com/at15/cs433/issues/8
- As the final course project ... it's just a implementation for the teacher's published paper http://link.springer.com/chapter/10.1007/978-3-642-41230-1_28

## B+ tree

already forgot most data structure I have to say ... 

https://en.wikipedia.org/wiki/B%2B_tree

- difference with b tree

> A B+ tree can be viewed as a B-tree in which each node contains only keys (not key-value pairs), 
and to which an additional level is added at the bottom with linked leaves.

a interactive b+ tree http://www.cs.usfca.edu/~galles/visualization/BPlusTree.html

### Problems

- Implement a B+ tree that support bulk loading and can be serialize & deserialize. 
- How to handle duplicate in key? may just treat it as one key and store other information in value

### Implementations

- http://www.conquex.com/b-tree-javascript-implementation/
- https://gist.github.com/mikelikesbikes/4742901
- https://github.com/jankotek/mapdb/blob/master/src/main/java/org/mapdb/BTreeMap.java

- http://algs4.cs.princeton.edu/code/edu/princeton/cs/algs4/BTree.java.html