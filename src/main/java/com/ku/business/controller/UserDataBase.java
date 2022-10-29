package com.ku.business.controller;

public class UserDataBase {
        private final String USERNAME = "postgres";
        private final String PASSWORD = "postgres";
        private final String URL = "jdbc:postgresql://localhost:5432/postgres?characterEncoding=utf8";

        public String getUSERNAME() {
            return USERNAME;
        }

        public String getPASSWORD() {
            return PASSWORD;
        }

        public String getURL() {
            return URL;
        }
}
