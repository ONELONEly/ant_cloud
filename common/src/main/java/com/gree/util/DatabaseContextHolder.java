package com.gree.util;

public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static void removeDatabase(){
        contextHolder.set(null);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}
