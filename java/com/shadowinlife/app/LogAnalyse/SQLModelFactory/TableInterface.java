package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.util.List;

import org.apache.spark.sql.types.StructType;

/**
 * @author shadowinlife
 * @since 2014-04-03
 * 
 * TBD Used For Dynamic Create SQLSchemaRDD
 */

public interface TableInterface {
    /**
     * Construct a SQL Table From configure file
     */
    public StructType structOfTable();

    /**
     * Get the columnNames of This SQL Table
     */
    public List<String> columnNames();

    /**
     * Get every class type of the column
     */
    public List<String> excuteSQL();
}
