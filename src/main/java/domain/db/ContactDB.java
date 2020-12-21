package domain.db;

import domain.model.Contact;

import java.sql.Timestamp;
import java.util.List;

public interface ContactDB {
    void add(Contact contact);

    List<Contact> getAll();

    Contact get(String id);

    void delete(int id);

    List<Contact> getAllFromMember(String personid);

    List<Contact> getAllContactsForDate(Timestamp from, Timestamp until);

    List<Contact> getAllContactsForDateFromMember(String personid, Timestamp from, Timestamp until);
}
