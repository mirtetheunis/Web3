package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Member {
    private String userid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public Member(String userid, String firstName, String lastName, String email, String password) {
        setUserid(userid);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password);

    }

    public Member() {

    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        if(userid.isEmpty()){
            throw new IllegalArgumentException("No userid given");
        }
        this.userid = userid;
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



    public String getEmail() {
        return email;
    }

    public String getPassword() {
        String hashedPassword = hashPassword(this.password);
        return hashedPassword;
    }

    private String hashPassword(String password) {
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);
            byte[] digest = crypt.digest();
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            return digestAsBigInteger.toString(16);
        } catch (NoSuchAlgorithmException e) {
            return e.getMessage();
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }

    public boolean isCorrectPassword(String password) {
        if(password.isEmpty()){
            throw new IllegalArgumentException("No password given");
        }
        String hashedPassword = hashPassword(password);
        return getPassword().equals(hashedPassword);
    }

    public void setPassword(String password) {
        if(password.isEmpty()){
            throw new IllegalArgumentException("No password given");
        }
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    @Override
    public String toString(){
        return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
    }
}
