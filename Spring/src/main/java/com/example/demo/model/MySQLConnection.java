package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnection {

    private Connection _conn;

    MySQLConnection(String url, String user, String pass) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&user=root&password=why23Naya#";
        Connection conn = DriverManager.getConnection(connectionUrl);
//        ResultSet rs = conn.prepareStatement("show tables").executeQuery();
    }

    public void getData(String query) throws SQLException {
//        String query = "select * from cardetails";
        Statement st = this._conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        while (rs.next()) {
            //Print one row          
            for(int i = 1 ; i <= columnsNumber; i++){
                  System.out.print(rs.getString(i) + " ");
            }
            System.out.println();        
        }
        return;
    }

}
