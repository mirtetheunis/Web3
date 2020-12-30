package domain.model;

public class CoronaTestResult {
    private String date;
    private String personid;

    public CoronaTestResult(String date) {
        setDate(date);
    }
    public CoronaTestResult() {};
    public CoronaTestResult(String date, String personid) {
        setDate(date);
        setPersonid(personid);
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
