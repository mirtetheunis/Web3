package ui.controller;

import domain.db.DbException;
import domain.model.Member;
import domain.model.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Add extends RequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, NotAuthorizedException, ServletException {
        Role[] roles = {Role.ADMIN};
        Utility.checkRole(request, roles);

        List<String> errors = new ArrayList<>();
        System.out.println("Add");

        Member member = new Member();
        setMemberUserid(member, request, errors);
        setMemberFirstName(member, request, errors);
        setMemberLastName(member, request, errors);
        setMemberEmail(member, request, errors);
        setMemberPassword(member, request, errors);
        setMemberRole(member, request, errors);
        System.out.println(errors);
        if (errors.size() == 0) {
            try {
                service.add(member);
                String gelukt = "Member succesfully registered.";
                request.setAttribute("gelukt", gelukt);
                request.getRequestDispatcher("Controller?command=Overview").forward(request, response);
                //response.sendRedirect("Controller?command=Overview");
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        } else {
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("Controller?command=Register").forward(request, response);
        }
    }

    private void setMemberRole(Member member, HttpServletRequest request, List<String> errors) {
        String roleAsString = request.getParameter("role").trim();
        try {
            Role role = Role.valueOf(roleAsString.toUpperCase());
            member.setRole(role);
            request.setAttribute("rolVorige", roleAsString);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setMemberUserid(Member Member, HttpServletRequest request, List<String> errors) {
        String userId = request.getParameter("userid").trim();
        try {
            Member.setUserid(userId);
            request.setAttribute("idVorige", userId);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setMemberFirstName(Member Member, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName").trim();
        try {
            Member.setFirstName(firstName);
            request.setAttribute("voornaamVorige", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setMemberLastName(Member Member, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName").trim();
        try {
            Member.setLastName(lastName);
            request.setAttribute("naamVorige", lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setMemberEmail(Member Member, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try {
            Member.setEmail(email);
            request.setAttribute("emailVorige", email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setMemberPassword(Member Member, HttpServletRequest request, List<String> errors) {
        String password = request.getParameter("password").trim();
        try {
            Member.setPasswordHashed(password);
            request.setAttribute("passwordVorige", password);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
