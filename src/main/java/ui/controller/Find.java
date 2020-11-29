package ui.controller;

import domain.model.Contact;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Find extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Role[] roles = {Role.ADMIN, Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<Contact> contacts = service.getAllContacts();
        List<Contact> contactsAtTime = new ArrayList<>();

        String datum = request.getParameter("date");
        String uur = request.getParameter("hour");
        Timestamp date = Timestamp.valueOf(datum + " " + uur + ":00");

        for(Contact c : contacts) {
            if(c.getDate().equals(date)) {
                contactsAtTime.add(c);
            }
        }

        try {
            request.setAttribute("contacts", contactsAtTime);
            request.getRequestDispatcher("findResult.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
