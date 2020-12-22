package ui.controller;

import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class DeleteContactMember extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.CUSTOMER};
        Utility.checkRole(request, roles);

        List<Contact> contacts = service.getAllContacts();
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String datum = request.getParameter("date");
        Timestamp date = Timestamp.valueOf(datum);

        for (Contact c : contacts) {
            if(c.getFirstName().equals(firstname) && c.getLastName().equals(lastname)
                    && c.getDate().equals(date)) {
                int contactId = c.getId();
                service.deleteContact(contactId);
                String gelukt = "Contact successfully deleted.";
                request.setAttribute("gelukt", gelukt);
                request.getRequestDispatcher("Controller?command=ContactOverviewPersonal").forward(request, response);
                // response.sendRedirect("Controller?command=ContactOverview");
            }
        }
        throw new DomainException("Dit contact bestaat niet.");
    }
}
