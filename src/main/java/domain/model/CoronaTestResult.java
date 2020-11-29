package domain.model;

public class CoronaTestResult {
    private int id;
    private String date;
    private String personid;

    public CoronaTestResult(String date) {
        setDate(date);
    }
    public CoronaTestResult() {};
    public CoronaTestResult(int id, String date, String personid) {
        setId(id);
        setDate(date);
        setPersonid(personid);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        if(date.isEmpty()){
            throw new DomainException("No date given.");
        }
        this.date = date;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }
}
