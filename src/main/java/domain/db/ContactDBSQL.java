package domain.db;

import domain.model.Contact;
import domain.model.Member;
import util.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB{
    private Connection connection;
    private String schema;

    public ContactDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
        System.out.println(this.schema);
    }


    @Override
    public void add(Contact contact) {
        if(contact == null) throw new DbException("Nothing to add.");
        String sql = String.format("INSERT INTO %s.contact (firstname, lastname, date, gsm, email) VALUES (?, ?, ?, ?, ?) RETURNING id", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setObject(3, contact.getDate());
            statementSQL.setString(4, contact.getPhonenumber());
            statementSQL.setString(5, contact.getEmail());
            ResultSet result = statementSQL.executeQuery();
            result.next();
            contact.setId(result.getInt(1));
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAll() {
        List<Contact> contacts = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.contact", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                Timestamp date = result.getObject("date", Timestamp.class);
                String gsm = result.getString("gsm");
                String email = result.getString("email");
                Contact contact = new Contact(id, firstname, lastname, date, gsm, email);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacts;
    }

    @Override
    public Contact get(String id) {
        String sql = String.format("SELECT * from %s.contact WHERE id = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, id);

            ResultSet result = statementSql.executeQuery();
            int contactId = result.getInt("id");
            String firstname = result.getString("firstname");
            String lastname = result.getString("lastname");
            Timestamp date = result.getObject("date", Timestamp.class);
            String gsm = result.getString("gsm");
            String email = result.getString("email");
            Contact contact = new Contact(contactId, firstname, lastname, date, gsm, email);
            return contact;
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = String.format("DELETE FROM %s.contact WHERE id = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setInt(1, id);
            statementSql.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
