package domain.db;

import domain.model.Member;
import util.DBConnectionService;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDBSQL implements MemberDB{
    private Connection connection;
    private String schema;

    public MemberDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
        System.out.println(this.schema);
    }


    @Override
    public void add(Member member) {
        if(member == null) throw new DbException("Nothing to add.");
        String sql = String.format("INSERT INTO %s.person (userid, firstname, lastname, email, password) VALUES (?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, member.getUserid());
            statementSQL.setString(2, member.getFirstName());
            statementSQL.setString(3, member.getLastName());
            statementSQL.setString(4, member.getEmail());
            statementSQL.setString(5, member.getPassword());
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Member> getAll() {
        List<Member> members = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.person", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String email = result.getString("email");
                String password = result.getString("password");
                Member member = new Member(userid, firstname, lastname, email, password);
                members.add(member);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return members;
    }

    @Override
    public Member get(String userid) {
        String sql = String.format("SELECT * from %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);

            ResultSet result = statementSql.executeQuery();
            String id = result.getString("userid");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            String email = result.getString("email");
            String password = result.getString("password");
            Member member = new Member(id, firstname, lastname, email, password);
            return member;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String userid) {
        String sql = String.format("DELETE FROM %s.person WHERE userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            statementSql.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}