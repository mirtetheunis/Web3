package ui.controller;

import domain.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContactOverview extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Contact> contacts = service.getAllContacts();
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
