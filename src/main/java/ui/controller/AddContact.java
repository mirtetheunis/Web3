package ui.controller;

import domain.db.DbException;
import domain.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AddContact extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = new ArrayList<>();

        Contact contact = new Contact();

        setContactFirstName(contact, request, errors);
        setContactLastName(contact, request, errors);
        setContactDate(contact, request, errors);
        setContactGsm(contact, request, errors);
        setContactEmail(contact, request, errors);

        if (errors.size() == 0) {
            try {
                service.addContact(contact);
                clearPreviousValues(request);
                response.sendRedirect("Controller?command=ContactOverview");
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        } else {
            try {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Controller?command=ContactOverview").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
    }

    private void clearPreviousValues(HttpServletRequest request) {
        request.removeAttribute("voornaamVorige");
        request.removeAttribute("naamVorige");
        request.removeAttribute("datumVorige");
        request.removeAttribute("uurVorige");
        request.removeAttribute("gsmVorige");
        request.removeAttribute("emailVorige");
    }

    private void setContactFirstName(Contact contact, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName").trim();
        try {
            contact.setFirstName(firstName);
            request.setAttribute("voornaamVorige", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactLastName(Contact contact, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName").trim();
        try {
            contact.setLastName(lastName);
            request.setAttribute("naamVorige", lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactDate(Contact contact, HttpServletRequest request, List<String> errors) {
        String datum = request.getParameter("date").trim();
        String uur = request.getParameter("hour").trim();
        try {
            Timestamp date = Timestamp.valueOf(datum + " " + uur + ":00");
            contact.setDate(date);
            request.setAttribute("datumVorige", datum);
            request.setAttribute("uurVorige", uur);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactGsm(Contact contact, HttpServletRequest request, List<String> errors) {
        String gsm = request.getParameter("gsm").trim();
        try {
            contact.setPhonenumber(gsm);
            request.setAttribute("gsmVorige", gsm);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setContactEmail(Contact contact, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try {
            contact.setEmail(email);
            request.setAttribute("emailVorige", email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
