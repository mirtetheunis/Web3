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

        Member member = new Member();
        setStudentUserid(member, request, errors);
        setStudentFirstName(member, request, errors);
        setStudentLastName(member, request, errors);
        setStudentEmail(member, request, errors);
        setStudentPassword(member, request, errors);

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

    private void setStudentUserid(Member Member, HttpServletRequest request, List<String> errors) {
        String userId = request.getParameter("userId").trim();
        try {
            Member.setUserid(userId);
            request.setAttribute("prevUserid", userId);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setStudentFirstName(Member Member, HttpServletRequest request, List<String> errors) {
        String firstName = request.getParameter("firstName").trim();
        try {
            Member.setFirstName(firstName);
            request.setAttribute("prevFirstname", firstName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setStudentLastName(Member Member, HttpServletRequest request, List<String> errors) {
        String lastName = request.getParameter("lastName").trim();
        try {
            Member.setLastName(lastName);
            request.setAttribute("prevLastname", lastName);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setStudentEmail(Member Member, HttpServletRequest request, List<String> errors) {
        String email = request.getParameter("email").trim();
        try {
            Member.setEmail(email);
            request.setAttribute("prevEmail", email);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }

    private void setStudentPassword(Member Member, HttpServletRequest request, List<String> errors) {
        String password = request.getParameter("password").trim();
        try {
            Member.setPassword(password);
            request.setAttribute("prevPassword", password);
        } catch (Exception e) {
            errors.add(e.getMessage());
        }
    }
}
