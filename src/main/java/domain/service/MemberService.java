package domain.service;

import domain.db.DbException;
import domain.db.MemberDB;
import domain.db.MemberDBSQL;
import domain.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberService {
    private MemberDB db = new MemberDBSQL();

    public MemberService() {
        Member administrator = new Member("admin", "admin@dcoptimi.be", "t", "Admin", "DCOptimi");
        add(administrator);
    }

    public Member get(String userid){
        return db.get(userid);
    }

    public List<Member> getAll(){
        return db.getAll();
    }

    public void add(Member member){
        db.add(member);
    }

    public void delete(String userid){
        db.delete(userid);
    }

}
