package domain.db;

import domain.model.Contact;
import domain.model.CoronaTestResult;

import java.util.List;

public interface CoronaTestResultDB {
    void add(CoronaTestResult test);

    List<Contact> getAll();
}
