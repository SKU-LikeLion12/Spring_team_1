package likelion.helloworld.service;

import likelion.helloworld.domain.Member;
import likelion.helloworld.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtility jwtutility;

    public Member tokenToMember(String token){
        return memberRepository.findByUserId(jwtutility.validateToken(token).getSubject());

    }


    public Member changeName(String token, String nickname){
        Member member = tokenToMember(token);
        if(member == null) return null;
        member.setNickName(nickname);
        return member;
    }


    public Member signUp(String userId, String password, String nickname){
        Member  member = memberRepository.findByUserId(userId);
        if(member !=null)return null;
        return memberRepository.save(new Member(userId, password, nickname));
    }

    public Member findMemberById(Long id){
        return memberRepository.findById(id);
    }

    public String login(String userId, String password){
        Member member = memberRepository.findByUserId(userId);
        if(member == null && member.checkPassword(password)) {
            return jwtutility.generateToken(member.getUserID());
        };
        return null;
    }



    public Member findByUserId(String userId){return memberRepository.findByUserId(userId);}
    public List<Member> findByName(String name){return memberRepository.findByName(name);}
    public List<Member> findAll(){return memberRepository.findAll();}

    public boolean deleteMember(String token){
        Member member = tokenToMember(token);
        if (member == null) return false;
        memberRepository.deleteMember(member);
        return true;
    }

}