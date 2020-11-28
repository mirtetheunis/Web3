package domain.db;

import domain.model.Contact;
import domain.model.CoronaTestResult;
import util.DBConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CoronaTestResultDBSQL implements CoronaTestResultDB {
    private Connection connection;
    private String schema;

    public CoronaTestResultDBSQL() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    @Override
    public void add(CoronaTestResult test) {
        if(test == null) throw new DbException("Nothing to add.");
        String sql = String.format("INSERT INTO %s.coronatestresult (date, personid) VALUES (?, ?) RETURNING id", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);

            statementSQL.setObject(1, test.getDate());;
            statementSQL.setString(2, test.getPersonid());
            ResultSet result = statementSQL.executeQuery();
            result.next();
            test.setId(result.getInt(1));
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Contact> getAll() {
        return null;
    }
}
