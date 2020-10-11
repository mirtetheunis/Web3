package domain.db;

import domain.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberService {
    private Map<String, Member> members = new HashMap<>();

    public MemberService() {
        Member administrator = new Member("admin", "admin@ucll.be", "t", "Admin", "UCLL");
        add(administrator);
    }

    public Member get(String personId){
        if(personId == null){
            throw new DbException("No id given");
        }
        return members.get(personId);
    }

    public List<Member> getAll(){
        return new ArrayList<Member>(members.values());
    }

    public void add(Member person){
        if(person == null){
            throw new DbException("No student given");
        }
        if (members.containsKey(person.getUserid())) {
            throw new DbException("Student already exists");
        }
        members.put(person.getUserid(), person); //test
    }

    public void update(Member person){
        if(person == null){
            throw new DbException("No student given");
        }
        if(!members.containsKey(person.getUserid())){
            throw new DbException("No student found");
        }
        members.put(person.getUserid(), person);
    }

    public void delete(String personId){
        if(personId == null){
            throw new DbException("No id given");
        }
        members.remove(personId);
    }

    public int getNumberOfPersons() {
        return members.size();
    }
}
