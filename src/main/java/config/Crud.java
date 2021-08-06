/*
 * Interfaz con los metodos principales de un CRUD
 */
package config;

import java.sql.SQLException;
import java.util.List;
import models.Client;

public interface Crud {
    public int create(Client client);
    public int update(Client client);
    public int delete(Client client);
    public  List<Client> fetchAll();
    public Client fetch(Client client);
}
