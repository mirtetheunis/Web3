package ui.controller;

import domain.model.Contact;
import domain.model.DomainException;
import domain.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

public class DeleteContact extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Contact> contacts = service.getAllContacts();
        String firstname = request.getParameter("firstName");
        String lastname = request.getParameter("lastName");
        String datum = request.getParameter("date");
        String uur = request.getParameter("hour");
        Timestamp date = Timestamp.valueOf(datum + uur);

        for (Contact c : contacts) {
            if(c.getFirstName().equals(firstname) && c.getLastName().equals(lastname)
                && c.getDate().equals(date)) {
                int contactId = c.getId();
                service.deleteContact(contactId);
                return "Controller?command=ContactOverview";
            }
        }
        throw new DomainException("Dit contact bestaat niet.");
    }
}
