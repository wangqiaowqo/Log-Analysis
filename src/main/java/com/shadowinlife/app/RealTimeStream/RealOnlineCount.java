package com.shadowinlife.app.RealTimeStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.flume.FlumeUtils;
import org.apache.spark.streaming.flume.SparkFlumeEvent;

import scala.Tuple2;

import com.shadowinlife.app.LogAnalyse.SQLModelFactory.RealOnline;

public class RealOnlineCount {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: JavaFlumeEventRealCount <host> <port>");
            System.exit(1);
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        Duration batchInterval = new Duration(1000 * 60 * 5);
        SparkConf sparkConf = new SparkConf().setAppName("JavaFlumeEventRealCount");
        JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, batchInterval);
        JavaDStream<SparkFlumeEvent> flumeStream = FlumeUtils.createStream(ssc, host, port);

        JavaPairDStream<String,RealOnline> tmpRDD = flumeStream.mapToPair(new PairFunction<SparkFlumeEvent, String, RealOnline>() {

            @Override
            public Tuple2<String, RealOnline> call(SparkFlumeEvent arg) throws Exception {
                Tuple2<String,RealOnline> tuple = new Tuple2<String,RealOnline>();
               
                String bodyString = new String(arg.event().getBody().array(), "UTF-8");
                System.out.println(bodyString.split("\n").length);
                for (int i = 0; i < loglines.length; i++) {
                    RealOnline ro = RealOnline.parseFromLog(loglines[i]);
                    if(Math.abs(ro.getDtEventTime().getTime()-now)<300000) {
                        sum= sum + ro.getUserCount();
                        valueNum++;
                    }
                }
                
            }
        }).window(batchInterval);

        tmpRDD.foreach(new Function<JavaRDD<Long>,Void>(){

            @Override
            public Void call(JavaRDD<Long> avgRDD) throws Exception {
               List<Long> l = avgRDD.collect();
               long avg = 0l;
               for(int i =0;i<l.size();i++) {
                   avg = avg+l.get(i);
               }
               Connection connect = DriverManager
                       .getConnection("jdbc:mysql://10-4-28-24:3306/dbDJOssResult?"
                               + "user=oss&password=oss");

              
               Statement statement = connect.createStatement();
               String sql = 
               statement.execute(sql);
               statement.close();
               connect.close();
               return null;
               
            }
            
        });

        ssc.start();
        ssc.awaitTermination();
    }
}
