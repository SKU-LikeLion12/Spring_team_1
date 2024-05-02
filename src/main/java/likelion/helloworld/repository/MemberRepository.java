package likelion.helloworld.repository;

import likelion.helloworld.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member mamber);

    Member findById(Long id);

    Member findByUserId(String userId);

    List<Member> findAll();

    void deleteMember(Member mamber);

    List<Member> findByName(String name);
}
