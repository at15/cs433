java.lang.Exception: java.io.IOException: wrong value class: class org.apache.hadoop.io.Text is not class org.apache.hadoop.io.IntWritable
	at org.apache.hadoop.mapred.LocalJobRunner$Job.runTasks(LocalJobRunner.java:462)
	at org.apache.hadoop.mapred.LocalJobRunner$Job.run(LocalJobRunner.java:522)
Caused by: java.io.IOException: wrong value class: class org.apache.hadoop.io.Text is not class org.apache.hadoop.io.IntWritable
	at org.apache.hadoop.mapred.IFile$Writer.append(IFile.java:194)
	at org.apache.hadoop.mapred.Task$CombineOutputCollector.collect(Task.java:1350)
	at cn.edu.sjtu.stu.at15.text.invertedindex.Combine.reduce(Combine.java:30)
	at cn.edu.sjtu.stu.at15.text.invertedindex.Combine.reduce(Combine.java:16)
	at org.apache.hadoop.mapred.Task$OldCombinerRunner.combine(Task.java:1615)
	at org.apache.hadoop.mapred.MapTask$MapOutputBuffer.sortAndSpill(MapTask.java:1637)
	at org.apache.hadoop.mapred.MapTask$MapOutputBuffer.flush(MapTask.java:1489)
	at org.apache.hadoop.mapred.MapTask.runOldMapper(MapTask.java:460)
	at org.apache.hadoop.mapred.MapTask.run(MapTask.java:343)
	at org.apache.hadoop.mapred.LocalJobRunner$Job$MapTaskRunnable.run(LocalJobRunner.java:243)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:471)
	at java.util.concurrent.FutureTask.run(FutureTask.java:262)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
	at java.lang.Thread.run(Thread.java:745)
	
http://stackoverflow.com/questions/30546957/wrong-value-class-class-org-apache-hadoop-io-text-is-not-class-org-apache-hadoop

Output types of a combiner must match output types of a mapper. Hadoop makes no guarantees on how many times the combiner is applied, or that it is even applied at all. And that's what happens in your case.

Values from map (<Text, IntWritable>) go directly to the reduce where types <Text, Text> are expected.