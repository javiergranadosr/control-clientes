/*
 * Clase conexion a base de datos, utlizando pool de conexiones
 */
package config;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author javiergranadosr
 */
public class DBConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    private static BasicDataSource dataSource;

    /**
     * Configurando nuestro pool de conexiones basico de 50 conexiones
     * disponibles
     *
     * @return
     */
    public static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }

        return dataSource;
    }

    /**
     * Obtener nuestra conexion, a partir de nuestro pool de conexiones
     * configurado previamente en getDataSource();
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    /**
     * Cerrar objeto ResultSet
     *
     * @param rs
     */
    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Cerrar objeto PreparedStatement
     *
     * @param stmt
     */
    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Cerrar objeto conexion
     *
     * @param conn
     */
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
