package com.shadowinlife.app.ExtraData;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.FieldSchema;
import org.apache.hadoop.hive.metastore.api.MetaException;
import org.apache.hadoop.hive.metastore.api.UnknownDBException;
import org.apache.hadoop.hive.metastore.api.UnknownTableException;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.thrift.TException;

public class DeliveryHiveDB {
    public void DeliveryByDBNames(HiveContext sqlContext, String DataBaseName, String url,
            String strWhere) {

        try {

            HiveConf hiveConf = new HiveConf(this.getClass());
            HiveMetaStoreClient hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
            List<String> hiveTables = hiveMetaStoreClient.getAllTables(DataBaseName);
            DeliveryDataToMysql extraProcess = new DeliveryDataToMysql();
            while (hiveTables.iterator().hasNext()) {
                String strTable = hiveTables.iterator().next();
                List<FieldSchema> fieldSchemaList = hiveMetaStoreClient.getFields(DataBaseName,
                        strTable);
                List<String> columnList = new ArrayList<String>();
                while (fieldSchemaList.iterator().hasNext()) {
                    FieldSchema fSchema = fieldSchemaList.iterator().next();
                    if (!fSchema.getName().contains("index_")) {
                        columnList.add(fSchema.getName());
                    }
                }
                extraProcess.insertIntoMysql(sqlContext, url, strTable, columnList, strWhere);
            }
        } catch (MetaException e) {
            e.printStackTrace();
        } catch (UnknownTableException e) {
            e.printStackTrace();
        } catch (UnknownDBException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }

    public void DeliveryByTableName(HiveContext sqlContext, String DataBaseName, String tableName,
            String url, String strWhere) {

        try {
            HiveConf hiveConf = new HiveConf(this.getClass());
            HiveMetaStoreClient hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);

            DeliveryDataToMysql extraProcess = new DeliveryDataToMysql();

            List<FieldSchema> fieldSchemaList = hiveMetaStoreClient.getFields(DataBaseName,
                    tableName);
            List<String> columnList = new ArrayList<String>();
            while (fieldSchemaList.iterator().hasNext()) {
                FieldSchema fSchema = fieldSchemaList.iterator().next();
                if (!fSchema.getName().contains("index_")) {
                    columnList.add(fSchema.getName());
                }
            }
            extraProcess.insertIntoMysql(sqlContext, url, tableName, columnList, strWhere);

        } catch (MetaException e) {
            e.printStackTrace();
        } catch (UnknownTableException e) {
            e.printStackTrace();
        } catch (UnknownDBException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }
}
