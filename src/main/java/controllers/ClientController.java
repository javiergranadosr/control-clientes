/*
 * Servlet cliente (controlador)
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import models.Client;
import models.ClientDAO;

@WebServlet(value = "ClientController", urlPatterns = "/clients")
public class ClientController extends HttpServlet {

    /**
     * Redireccionar al index, pagina principal
     *
     * @param req
     * @param rep
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse rep)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "update":
                    this.clientUpdate(req, rep);
                    break;
                case "delete":
                    this.delete(req, rep);
                    break;
                default:
                    this.index(req, rep);
            }

        } else {
            this.index(req, rep);
        }

    }

    /**
     * Agregar, actualizar e eliminar cliente
     *
     * @param req
     * @param rep
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    this.create(req, rep);
                    break;
                case "clientUpdate":
                    this.updateClientAdd(req, rep);
                    break;
                default:
                    this.index(req, rep);
            }

        } else {
            this.index(req, rep);
        }
    }

    /**
     * Vista listado de clientes
     *
     * @param req
     * @param rep
     * @throws ServletException
     * @throws IOException
     */
    private void index(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        List<Client> clients = new ClientDAO().fetchAll();
        System.out.println("Clientes: " + clients);

        HttpSession session = req.getSession();
        session.setAttribute("clients", clients);

        // Total de clientes
        session.setAttribute("clientTotal", clients.size());
        // Saldo total
        session.setAttribute("balanceTotal", this.getTotalBalance(clients));
        // req.getRequestDispatcher("views/clients.jsp").forward(req, rep);
        rep.sendRedirect("views/clients.jsp");
    }

    /**
     * Crear un nuevo cliente
     *
     * @param req
     * @param rep
     */
    private void create(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        // Obtener valores del formulario
        String name = req.getParameter("nameClient");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        double balance = 0;
        String balanceString = req.getParameter("balance");

        if (balanceString != null && !"".equals(balanceString)) {
            balance = Double.parseDouble(balanceString);
        }

        // Creamos el objeto de cliente (modelo)
        Client client = new Client(name, surname, email, phone, balance);
        // Guardarlo en la base de datos
        int updateRegisters = new ClientDAO().create(client);
        System.out.println("Registros modificados: " + updateRegisters);

        // Redirigimos al index de la pagina
        this.index(req, rep);

    }

    /**
     * Obtener el total del saldo
     *
     * @param clients
     * @return
     */
    private double getTotalBalance(List<Client> clients) {
        double totalBalance = 0;
        for (Client client : clients) {
            totalBalance += client.getBalance();
        }
        return totalBalance;
    }

    /**
     * Actualizar un cliente
     *
     * @param req
     * @param rep
     * @throws ServletException
     * @throws IOException
     */
    private void clientUpdate(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        // Recuperar el id cliente
        int id = Integer.parseInt(req.getParameter("id"));

        // Buscar al cliente en la base de datos
        Client client = new ClientDAO().fetch(new Client(id));
        req.setAttribute("client", client);
        String jspUpdate = "/WEB-INF/client/clientUpdate.jsp";
        req.getRequestDispatcher(jspUpdate).forward(req, rep);

    }

    /**
     * Crear un nuevo cliente
     *
     * @param req
     * @param rep
     */
    private void updateClientAdd(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        // Obtener valores del formulario editar cliente

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nameClient");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        double balance = 0;
        String balanceString = req.getParameter("balance");

        if (balanceString != null && !"".equals(balanceString)) {
            balance = Double.parseDouble(balanceString);
        }

        // Actualizamos el objeto de cliente (modelo)
        Client client = new Client(id, name, surname, email, phone, balance);
        // Guardarlo en la base de datos
        int updateRegisters = new ClientDAO().update(client);
        System.out.println("Registros modificados: " + updateRegisters);

        // Redirigimos al index de la pagina
        this.index(req, rep);
    }

    /**
     * Eliminar un cliente
     *
     * @param req
     * @param rep
     * @throws ServletException
     * @throws IOException
     */
    private void delete(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        // Eliminamos cliente
        Client client = new Client(id);
        System.out.println("CLIENTE ELIMINADO: " + client.toString());
        int updateRegisters = new ClientDAO().delete(client);
        System.out.println("Registros modificados: " + updateRegisters);
        // Redirigimos al index de la pagina
        this.index(req, rep);

    }

}
