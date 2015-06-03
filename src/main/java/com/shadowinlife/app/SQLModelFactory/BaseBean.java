package com.shadowinlife.app.SQLModelFactory;

public abstract class BaseBean {
    public abstract BaseBean parseFromLogFile(String[] array, String GameId, String AccountType, String WorldId);
    public BaseBean(){
       
    }
}
