package ui.controller;

import domain.db.DbException;
import domain.model.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class Add extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = new ArrayList<>();
        System.out.println("Add");

        Member member = new Member();
        setMemberUserid(member, request, errors);
        setMemberFirstName(member, request, errors);
        setMemberLastName(member, request, errors);
        setMemberEmail(member, request, errors);
        setMemberPassword(member, request, errors);
        System.out.println(errors);
        if (errors.size() == 0) {
            try {
                service.add(member);
                return "Controller?command=Overview";
            } catch (DbException e) {
                errors.add(e.getMessage());
            }
        }
        request.setAttribute("errors", errors);
        return "Controller?command=Register";
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
