package com.shadowinlife.app.LogAnalyse.SQLModelFactory;

import java.sql.Struct;
import java.util.List;


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
    public Struct structOfTable();

    /**
     * Get the columnNames of This SQL Table
     */
    public List<String> columnNames();

    /**
     * Get every class type of the column
     */
    public List<String> excuteSQL();
}
