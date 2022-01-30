package com.example.cinema.model.connectionpool;

import com.example.cinema.model.dao.UserDao;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool(){
        //private constructor
    }

    private static ConnectionPool instance = null;
    private static Logger log = Logger.getLogger(ConnectionPool.class);

    public static ConnectionPool getInstance(){
        if (instance==null)
            instance = new ConnectionPool();
        return instance;
    }

    public Connection getConnection(){
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource)context.lookup("java:comp/env/jdbc/connectionPool");
            connection = ds.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        log.info("Connection was opened");
        return connection;
    }

}
