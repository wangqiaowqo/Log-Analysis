package com.shadowinlife.app.Split;

import java.io.Serializable;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;

import com.shadowinlife.app.SQLModelFactory.BaseBean;

import scala.Tuple2;

public class SaveParquet<T extends BaseBean> implements Serializable{

    private static final long serialVersionUID = 1L;
    private T t;

    public void set(T object) {
        this.t = object;
    }

    public T get() {
        return t;
    }

    public void LogToParquet(SQLContext sqlContext, JavaPairRDD<String, String[]> hadoopFile,
            String tableName, String dst, final String GameId, final String AccountType, final String WorldId) {
        try {
            final String name = tableName;
            JavaRDD<String[]> tempRDD = hadoopFile.filter(
                    new Function<Tuple2<String, String[]>, Boolean>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public Boolean call(Tuple2<String, String[]> f) throws Exception {
                            if (f._1().equalsIgnoreCase(name)) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    }).values();
            ;
            JavaRDD<T> tempLogs = tempRDD.map(new Function<String[], T>() {

                private static final long serialVersionUID = -8639862064479870422L;

                
                @Override
                public T call(String[] line) {
                   
                    return (T) t.parseFromLogFile(line, GameId, AccountType, WorldId);

                }

            });

            DataFrame schemaRDD = sqlContext.createDataFrame(tempLogs, t.getClass());
            schemaRDD.saveAsParquetFile(dst + "/" + tableName + ".parquet");
            schemaRDD.unpersist();
            tempLogs.unpersist();
            tempRDD.unpersist();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
