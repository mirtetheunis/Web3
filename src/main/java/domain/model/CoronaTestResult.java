package domain.model;

import java.sql.Timestamp;

public class CoronaTestResult {
    private int id;
    private Timestamp date;
    private String personid;

    public CoronaTestResult(Timestamp date) {
        setDate(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }
}
