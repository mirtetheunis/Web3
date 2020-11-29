package domain.service;

import domain.db.*;
import domain.model.Contact;
import domain.model.CoronaTestResult;
import domain.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactTracingService {
    private MemberDB db = new MemberDBSQL();
    private ContactDB contactDB = new ContactDBSQL();
    private CoronaTestResultDB testDB = new CoronaTestResultDBSQL();

    public ContactTracingService() {

    }

    public Member get(String userid){
        System.out.println("service Member get");
        return db.get(userid);
    }

    public List<Member> getAll(){
        System.out.println("getAll");
        return db.getAll();
    }

    public void add(Member member){
        db.add(member);
    }

    public void delete(String userid){
        db.delete(userid);
    }

    public List<Contact> getAllContacts() {
        return contactDB.getAll();
    }

    public void addContact(Contact c) {
        contactDB.add(c);
    }

    public void deleteContact(int id) {
        contactDB.delete(id);
    }

    public Contact getContact(String id) {
        return contactDB.get(id);
    }

    public List<Contact> getAllContactsFromMember(String personid) {
        return contactDB.getAllFromMember(personid);
    }

    public void addTestResult(CoronaTestResult test) {
        testDB.add(test);
    }

}
