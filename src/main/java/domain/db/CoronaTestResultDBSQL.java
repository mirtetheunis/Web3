package domain.db;

import domain.model.Contact;
import domain.model.CoronaTestResult;
import util.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
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
    public List<CoronaTestResult> getAll() {
        List<CoronaTestResult> tests = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.coronatestresult", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String date = result.getString("date");
                String personid = result.getString("personid");
                CoronaTestResult test = new CoronaTestResult(id, date, personid);
                tests.add(test);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return tests;
    }

    @Override
    public CoronaTestResult getTest(String personid) {
        String sql = String.format("SELECT * from %s.coronatestresult WHERE personid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, personid);

            ResultSet result = statementSql.executeQuery();
            if(result.next() == false) {
                CoronaTestResult testEmpty = null;
                return testEmpty;
            } else {
                int id = result.getInt("id");
                String date = result.getString("date");
                String personID = result.getString("personid");
                CoronaTestResult test = new CoronaTestResult(id, date, personID);
                return test;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
