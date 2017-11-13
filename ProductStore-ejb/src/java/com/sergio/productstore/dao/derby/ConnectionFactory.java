/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.productstore.dao.derby;

import com.sergio.productstore.dao.derby.DerbyConnection;
import java.sql.Connection;

/**
 *
 * @author sergio
 */
public class ConnectionFactory {
    public static Connection getConnection() {
        return DerbyConnection.getConnection();
    }
}
