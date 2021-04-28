package com.roblesc.model;

import com.mysql-connector.*;
import java.sql.*;

public class Connect
{
    public static void main(String[] args) {
        String usuario = "roblesc";
        String clave = "robles123";
        String url = "jdbc:mysql:localhost:3306/quarkus";
        Connection con;
        Statement stmt;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("asdfñlasdkfjasñkfdj");
        } catch (SQLException ex) {
            Logger.getLogger(Mysqlconnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
