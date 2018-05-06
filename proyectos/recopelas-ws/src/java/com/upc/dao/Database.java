/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.dao;

import javax.ejb.Singleton;

/**
 *
 * @author leo
 */
@Singleton
public class Database {
    
    static final String USERNAME = "root";
    static final String PASSWORD = "";
    static final String URL = "jdbc:mysql://localhost:3306/recopelas";
    
}
