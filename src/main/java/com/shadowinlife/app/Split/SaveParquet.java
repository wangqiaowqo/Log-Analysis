package com.shadowinlife.app.Split;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;

import scala.Tuple2;

public class SaveParquet {

    public static void LogToParquet(SQLContext sqlContext,
            JavaPairRDD<String, String[]> hadoopFile, final String tableName,
            final String[] schemaNames, final String[] schemaTypes, String dst,
            final String GameId, final String AccountType, final String WorldId) {
        try {
            // 获取表名
            final String name = tableName;

            // 过滤出以表名作为key的value形成一个RDD
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

            // 初始化表结构
            List<StructField> columnFields = new ArrayList<StructField>();

            // 初始化每一列的类型
            for (int i = 0; i < schemaNames.length; i++) {
                switch (schemaTypes[i]) {
                case "bigint":
                    columnFields.add(DataTypes.createStructField(schemaNames[i],
                            DataTypes.LongType, true));
                    break;
                case "varchar":
                    columnFields.add(DataTypes.createStructField(schemaNames[i],
                            DataTypes.StringType, true));
                    break;
                case "Map":
                    columnFields
                            .add(DataTypes.createStructField(schemaNames[i], DataTypes
                                    .createMapType(DataTypes.StringType, DataTypes.LongType), true));
                    break;
                case "Array":
                    columnFields.add(DataTypes.createStructField(schemaNames[i],
                            DataTypes.createArrayType(DataTypes.LongType), true));
                    break;
                case "ArrayMap":
                    columnFields.add(DataTypes.createStructField(schemaNames[i], DataTypes
                            .createArrayType(DataTypes.createMapType(DataTypes.StringType,
                                    DataTypes.LongType)), true));
                    break;
                case "datetime":
                    columnFields.add(DataTypes.createStructField(schemaNames[i],
                            DataTypes.TimestampType, true));
                    break;
                default:
                    System.out
                            .println(schemaTypes[i]
                                    + "Column Types Error, Only Long--String--Map--Array--ArrayMap--TimeStamp is legal type");
                }
            }

            // 创建表结构
            StructType stTableStruct = DataTypes.createStructType(columnFields);

            stTableStruct.printTreeString();
            // 将value中的每行数据格式化成一个Row
            JavaRDD<Row> rowRDD = tempRDD.map(new Function<String[], Row>() {

                private static final long serialVersionUID = 1L;
                private Long iEventId;
                private String vVersionId;
                private String vUin;
                private Long iRoleId;
                private String vRoleName;
                private Long iRoleJob;
                private Long iRoleGender;
                private Long iRoleLevel;
                private Long iRoleVipLevel;
                private Long iRoleReputationLevel;
                private String vRoleElse1;
                private String vRoleElse2;
                private String vClientIp;
                private String vZoneId;
                private Long iExp;
                private Long iReputation;
                private Long iEnergy;
                private Long lOnlineTotalTime;
                private String vLoginWay;

                @Override
                public Row call(String[] values) {
                    
                    // 初始化json解析器
                    JsonParserFactory factory = JsonParserFactory.getInstance();
                    JSONParser parser = factory.newJsonParser();
                    // 对每一列进行类型转换
                    /**
                     * for (String Types : schemaTypes) { switch (Types) { case
                     * "bigint":
                     * columns_array.add(Long.valueOf(values[column_index]));
                     * break;
                     * 
                     * case "varchar": columns_array.add(values[column_index]);
                     * break;
                     * 
                     * case "Map":
                     * columns_array.add(parser.parseJson(values[column_index
                     * ])); break; case "Array": Map jsonData =
                     * parser.parseJson(values[column_index]);
                     * columns_array.add((List<Long>) jsonData.get("root"));
                     * break;
                     * 
                     * case "ArrayMap": Map jsonData2 =
                     * parser.parseJson(values[column_index]);
                     * columns_array.add((List<Map<String, Long>>)
                     * jsonData2.get("root")); break;
                     * 
                     * case "datetime":
                     * columns_array.add(Timestamp.valueOf(values
                     * [column_index])); break;
                     * 
                     * default: break; }
                     */

                    Long iGameId = Long.valueOf(GameId);
                    Long iAccountType = Long.valueOf(AccountType);
                    Long iWorldId = Long.valueOf(WorldId);
                    Timestamp dtEventTime = Timestamp.valueOf(values[1]);
                    this.iEventId = Long.valueOf(values[2]);
                    this.vVersionId = String.valueOf(values[3]);
                    this.vUin = String.valueOf(values[4]);
                    this.iRoleId = Long.valueOf(values[5]);
                    this.vRoleName = String.valueOf(values[6]);
                    this.iRoleJob = Long.valueOf(values[7]);
                    this.iRoleGender = Long.valueOf(values[8]);
                    this.iRoleLevel = Long.valueOf(values[9]);
                    this.iRoleVipLevel = Long.valueOf(values[10]);
                    this.iRoleReputationLevel = Long.valueOf(values[11]);
                    this.vRoleElse1 = String.valueOf(values[12]);
                    this.vRoleElse2 = String.valueOf(values[13]);
                    this.vClientIp = String.valueOf(values[14]);
                    this.vZoneId = String.valueOf(values[15]);
                    this.iExp = Long.valueOf(values[16]);
                    this.iReputation = Long.valueOf(values[17]);
                    this.iEnergy = Long.valueOf(values[18]);
                    Map<String, Long> jMoney = parser.parseJson(values[19]);
                    Timestamp dtCreateTime = Timestamp.valueOf(values[20]);
                    this.lOnlineTotalTime = Long.valueOf(values[21]);
                    this.vLoginWay = String.valueOf(values[22]);

                    return RowFactory.create(dtEventTime, iEventId,
                            vVersionId, vUin, iRoleId, vRoleName, iRoleJob, iRoleGender,
                            iRoleLevel, iRoleVipLevel, iRoleReputationLevel, vRoleElse1,
                            vRoleElse2, vClientIp, vZoneId, iExp, iReputation, iEnergy, jMoney,
                            dtCreateTime, lOnlineTotalTime, vLoginWay);

                }
            });
            for (Row r : rowRDD.collect())
                System.out.println(r.mkString(" | "));
            DataFrame schemaRDD = sqlContext.createDataFrame(rowRDD, stTableStruct);
            schemaRDD.saveAsParquetFile("/test.parquet");
            schemaRDD.unpersist();
            tempRDD.unpersist();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
