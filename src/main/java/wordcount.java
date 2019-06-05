import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class wordcount {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = new Job(); // 创建一个作业对象
        job.setJarByClass(wordcount.class); // 设置运行/处理该作业的类
        job.setJobName("WordCount");

        FileInputFormat.addInputPath(job, new Path(args[0]));//设置这个作业输入数据的路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));//设置这个作业输出结果的路径

        job.setMapperClass(mapper.class);//设置实现了Map步的类
        job.setReducerClass(reducer.class);//设置实现了Reduce步的类

        job.setOutputKeyClass(Text.class);//设置输出结果key的类型
        job.setOutputValueClass(IntWritable.class);//设置输出结果value的类型

        System.exit(job.waitForCompletion(true) ? 0 : 1);//执行作业
    }
}
