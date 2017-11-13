/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class DerbyConnection {
    
    private static final String URL = "jdbc:derby://localhost:1527/ProductStore";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public static Connection getConnection() {
        try {
            // Class.forName("org.apache.derby.jdbc.ClientDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DerbyConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("No se pudo conectar a la base de datos");
        }
    }
}
