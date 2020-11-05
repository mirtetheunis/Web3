package domain.db;

import domain.model.Member;

import java.util.List;

public interface MemberDB {
    void add(Member member);

    List<Member> getAll();

    Member get(String userid);

    void delete(String userid);
}
