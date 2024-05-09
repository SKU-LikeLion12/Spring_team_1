package likelion.helloworld.repository;

import likelion.helloworld.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> local = new HashMap<>();

    @Override
    public Member save(Member member) {

        local.put(member.getId(), member);
        return member;

    }

    @Override
    public Member findById(Long id) {
        return local.get(id);
    }

    @Override
    public Member findByUserId(String userId) {
        for(Member member : local.values()) {
            if (member.getUserID().equals(userId)){
                return member;
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(local.values());
    }

    @Override
    public void deleteMember(Member member) {
        Long delId = member.getId();

        local.remove(delId);

    }

    @Override
    public List<Member> findByName(String name) {
        List<Member> memberList = new ArrayList<>();

        for (Member member : local.values()) {
            if ( member.getNickName().equals(name)){
                memberList.add(member);

            }
        }

        return memberList;
    }
}
