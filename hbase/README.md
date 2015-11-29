# Hbase index

- use the hbase in `forum-search` bundle instead of vagrant

commands for create test table and input

````
# table contacts with column family info
create 'contacts', 'info'
# insert data
put 'contacts', '1', 'info:name', 'jack'
put 'contacts', '2', 'info:name', 'mary'
put 'contacts', '3', 'info:name', 'jason'
````