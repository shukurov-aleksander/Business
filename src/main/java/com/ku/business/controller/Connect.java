package com.ku.business.controller;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public class Connect {
    UserDataBase userDataBase = new UserDataBase();
    private static PGSimpleDataSource pgSimpleDataSource;
    public DataSource getConnection(){
        if (pgSimpleDataSource == null){
            pgSimpleDataSource = new PGSimpleDataSource();
            pgSimpleDataSource.setUrl(userDataBase.getURL());
            pgSimpleDataSource.setUser(userDataBase.getUSERNAME());
            pgSimpleDataSource.setPassword(userDataBase.getPASSWORD());

        }
        return pgSimpleDataSource;
    }

}
