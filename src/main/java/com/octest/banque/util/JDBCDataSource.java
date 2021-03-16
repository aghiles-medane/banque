package com.octest.banque.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.octest.banque.exception.ApplicationException;





/**
 * JDBC DataSource is a Data Connection Pool
 Interface qui h�rite de  CommonDataSource et Wrapper Class. 
  Contient deux m�thodes getConnection () et getConnection (cha�ne nom d'utilisateur, cha�ne mot de passe)
  Une  alternative � DriverManager utilis�e pour cr�er une connexion au pool,
   d�finir une dur�e maximale ou minimale pour un pool.
   aidera � lire les donn�es du fichier "system.properties" et � cr�er un pool de connexion
 * 
 */

public class JDBCDataSource
{

	/**
     * JDBC Database connection pool ( DCP )
     */
    private static JDBCDataSource datasource;

    private JDBCDataSource() {
    }

    private ComboPooledDataSource cpds = null;

    /**
     * Create instance of Connection Pool
     *
     * @return
     */
    public static JDBCDataSource getInstance() {
        if (datasource == null) {

            ResourceBundle rb = ResourceBundle.getBundle("system");

            datasource = new JDBCDataSource();
            datasource.cpds = new ComboPooledDataSource();
            try {
                datasource.cpds.setDriverClass(rb.getString("driver"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            datasource.cpds.setJdbcUrl(rb.getString("url"));
            datasource.cpds.setUser(rb.getString("username"));
            datasource.cpds.setPassword(rb.getString("password"));
            datasource.cpds.setInitialPoolSize(new Integer((String) rb .getString("initialPoolSize")));
            datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireIncrement")));
            datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxPoolSize")));
            datasource.cpds.setMaxIdleTime(DataUtility.getInt(rb.getString("timeout")));
            datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minPoolSize")));
            

        }
        return datasource;
    }

    /**
     * Gets the connection from ComboPooledDataSource
     *
     * @return connection
     */
    public static Connection getConnection() throws Exception {
        return getInstance().cpds.getConnection();
    }

    /**
     * Closes a connection
     *
     * @param connection
     * @throws Exception
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
    }

    public static void trnRollback(Connection connection)
            throws ApplicationException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new ApplicationException(ex.toString());
            }
        }
    }
}
