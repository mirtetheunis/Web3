package domain.model;

import domain.db.MemberService;

import java.util.List;

public class ContactTracingService {
    private MemberService personDb = new MemberService();

    public ContactTracingService() {
    }

    public Member getPerson(String personId) {
        return getPersonDb().get(personId);
    }

    public List<Member> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Member person) {
        getPersonDb().add(person);
    }

    public void updatePersons(Member person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private MemberService getPersonDb() {
        return personDb;
    }
}
