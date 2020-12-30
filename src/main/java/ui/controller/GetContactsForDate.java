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

public class GetContactsForDate extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        try {
            String fromAsString = request.getParameter("from");
            String untilAsString = request.getParameter("until");
            Timestamp from = Timestamp.valueOf(fromAsString + " 00:00:00");
            Timestamp until = Timestamp.valueOf(untilAsString + " 00:00:00");
            List<Contact> contacts = new ArrayList<>();

            String personid = request.getParameter("member");
            if(personid == null || personid.trim().isEmpty()) {
                contacts = service.getAllContactsForDate(from, until);
            } else {
                contacts = service.getAllContactsForDateFromMember(personid, from, until);
            }

            String gelukt = "Contacts are filtered.";
            request.setAttribute("gelukt", gelukt);
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
