import org.apache.spark.sql.SparkSession
object SimpleApp {
 def main(args: Array[String]) {
     val text = "/user/bigdata/README.md"
     val spark = SparkSession.builder
     .appName("Simple Application")
     .config("spark.master", "local[*]")
     .getOrCreate()
     val data = spark.read.textFile(text).cache()
     val numAs = data.filter(line => line.contains("a")).count()
     val numBs = data.filter(line => line.contains("b")).count()
     spark.sparkContext.setLogLevel("ERROR")
     println(s"Lines with a: $numAs, Lines with b: $numBs")
     spark.stop()
 }
}

