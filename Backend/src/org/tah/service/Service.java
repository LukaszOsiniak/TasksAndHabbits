package org.tah.service;

import org.tah.persistence.DbConnection;

public class Service {
    public static void main(String[] args) {
        DbConnection  dbConnect = new DbConnection();
        dbConnect.getConnection();
    }
}
