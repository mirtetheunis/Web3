package ui.controller;

import domain.service.ContactTracingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ContactTracingService service = new ContactTracingService();
    private HandlerFactory handlerFactory = new HandlerFactory();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if(command == null || command.isEmpty())
            command = "Home";
        RequestHandler handler = handlerFactory.getHandler(command, service);

            try {
                handler.handleRequest(request, response);
                System.out.println("Controller");
            } catch (NotAuthorizedException e) {
                request.setAttribute("notAutorized", "You have insufficient rights to have a look at this page.");
                handlerFactory.getHandler("Home", service).handleRequest(request,response);
            }
        }
}
