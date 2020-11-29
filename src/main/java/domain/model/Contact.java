package domain.model;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phonenumber;
    private Timestamp date;
    private String personid;

    public Contact(int id, String firstName, String lastName, Timestamp date, String phonenumber, String email, String personid) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setDate(date);
        setPhonenumber(phonenumber);
        setEmail(email);
        setPersonid(personid);
    }

    public Contact() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()) throw new IllegalArgumentException("No first name given.");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()) throw new IllegalArgumentException("No last name given.");
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isEmpty()){
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        if(phonenumber.isEmpty()) throw new IllegalArgumentException("No GSM given.");
        this.phonenumber = phonenumber;
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
        if(personid.isEmpty()) throw new IllegalArgumentException("Userid isn't correct.");
        this.personid = personid;
    }
}
