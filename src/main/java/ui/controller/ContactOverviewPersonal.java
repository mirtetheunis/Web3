package ui.controller;

import domain.model.Contact;
import domain.model.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContactOverviewPersonal extends RequestHandler{
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Member m = (Member) request.getSession().getAttribute("user");
        try {
            String personid = m.getUserid();
            List<Contact> contacts = service.getAllContactsFromMember(personid);
            request.setAttribute("contacts", contacts);
            request.getRequestDispatcher("contactsPersonal.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
