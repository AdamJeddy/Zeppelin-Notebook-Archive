import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter; 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class FileSystemPut {
    public static void main(String[] args) throws Exception {
        String localStr = args[0];
        String hdfsStr = args[1];
        Configuration conf = new Configuration();
        FileSystem local = FileSystem.getLocal(conf);
        FileSystem hdfs = FileSystem.get(URI.create(hdfsStr), conf);
        Path localFile = new Path(localStr);
        Path hdfsFile = new Path(hdfsStr);
        FSDataInputStream in = local.open(localFile);
        FSDataOutputStream out = hdfs.create(hdfsFile);
        IOUtils.copyBytes(in, out, 4096, true);
    }
}
