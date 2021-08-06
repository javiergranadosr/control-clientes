package models;

import config.Crud;
import config.DBConnection;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO implements Crud {

    // Consultas a realizar
    private static final String QUERY_SELECT = "SELECT id, nombre, apellido, email, telefono, saldo FROM cliente";
    private static final String QUERY_SELECT_BY_ID = "SELECT id, nombre, apellido, email, telefono, saldo FROM cliente WHERE id = ?";
    private static final String QUERY_CREATE = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUES (?,?,?,?,?)";
    private static final String QUERY_UPDATE = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE id = ?";
    private static final String QUERY_DELETE = "DELETE FROM cliente WHERE id = ?";

    /**
     * Crear un nuevo cliente
     *
     * @param client
     * @return
     */
    @Override
    public int create(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {

            // Inicializar conexion a la BD
            conn = DBConnection.getConnection();
            // Preparamos la consulta a realizar en la BD
            stmt = conn.prepareStatement(QUERY_CREATE);
            // Setiar valores
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getName());
            stmt.setString(4, client.getPhone());
            stmt.setDouble(5, client.getBalance());

            // Ejecutamos consulta preparada
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // Cerramos objeto PreparedStatement
            DBConnection.close(stmt);
            // Cerramos objeto Connection
            DBConnection.close(conn);

        }
        // Retornamos el numero de registros modificados, insertados
        return rows;
    }

    /**
     * Actualizar un cliente
     *
     * @param client
     * @return
     */
    @Override
    public int update(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            // Inicializar conexion a la BD
            conn = DBConnection.getConnection();
            // Preparamos la consulta a realizar en la BD
            stmt = conn.prepareStatement(QUERY_UPDATE);
            // Setiar valores
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getSurname());
            stmt.setString(3, client.getName());
            stmt.setString(4, client.getPhone());
            stmt.setDouble(5, client.getBalance());
            stmt.setInt(6, client.getId());
            // Ejecutamos consulta preparada
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // Cerramos objeto PreparedStatement
            DBConnection.close(stmt);
            // Cerramos objeto Connection
            DBConnection.close(conn);
        }
        // Retornamos el numero de registros modificados, actualizado
        return rows;
    }

    /**
     * Eliminar un cliente
     *
     * @param client
     * @return
     */
    @Override
    public int delete(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            // Inicializar conexion a la BD
            conn = DBConnection.getConnection();
            // Preparamos la consulta a realizar en la BD
            stmt = conn.prepareStatement(QUERY_DELETE);
            // Setiar valores
            stmt.setInt(1, client.getId());
            // Ejecutamos consulta preparada
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            // Cerramos objeto PreparedStatement
            DBConnection.close(stmt);
            // Cerramos objeto Connection
            DBConnection.close(conn);

        }
        // Retornamos el numero de registros modificados, eliminados
        return rows;
    }

    /**
     * Listar todos los clientes
     *
     * @return
     */
    @Override
    public List<Client> fetchAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Client client = null;
        List<Client> clients = new ArrayList<>();

        try {

            // Inicializar conexion a la BD
            conn = DBConnection.getConnection();
            // Preparamos la consulta a realizar en la BD
            stmt = conn.prepareStatement(QUERY_SELECT);
            // Ejecutamos la consulta
            rs = stmt.executeQuery();

            // Iterar los datos consultado en la BD, clientes a listar
            while (rs.next()) {
                // Creamos objeto cliente
                client = new Client(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("email"), rs.getString("telefono"), rs.getDouble("saldo"));
                // Agremos objeto cliente a la lista
                clients.add(client);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            // Cerramos objeto ResultSet
            DBConnection.close(rs);
            // Cerramos objeto PreparedStatement
            DBConnection.close(stmt);
            // Cerramos objeto Connection
            DBConnection.close(conn);

        }

        // Retornamos el listado de clientes
        return clients;
    }

    /**
     * Buscar cliente por ID
     *
     * @param client
     * @return
     */
    @Override
    public Client fetch(Client client) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // Inicializar conexion a la BD
            conn = DBConnection.getConnection();
            // Preparamos la consulta a realizar en la BD
            stmt = conn.prepareStatement(QUERY_SELECT_BY_ID);
            // Ejecutamos la consulta
            rs = stmt.executeQuery();
            // Ingresar parametros a la consulta preparada a realizar
            stmt.setInt(1, client.getId());
            // Nos posicionamos al registro que hemos consultado
            rs.absolute(1);

            // Setiar valores
            client.setName(rs.getString("nombre"));
            client.setSurname(rs.getString("apellido"));
            client.setEmail(rs.getString("email"));
            client.setPhone(rs.getString("telefono"));
            client.setBalance(rs.getDouble("saldo"));
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // Cerramos objeto ResultSet
            DBConnection.close(rs);
            // Cerramos objeto PreparedStatement
            DBConnection.close(stmt);
            // Cerramos objeto Connection
            DBConnection.close(conn);
        }
        // Retornamos el cliente consultado
        return client;
    }

}
