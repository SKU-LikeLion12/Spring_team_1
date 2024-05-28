package likelion.helloworld.controller;

import likelion.helloworld.DTO.MemberDTO;
import likelion.helloworld.domain.Member;
import likelion.helloworld.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/member/add")
    public String signUp(@RequestBody MemberDTO.MemberCreateRequest request){
        Member member = memberService.signUp(request.getUserId(), request.getPassword(), request.getNickname());
        if(member == null) return "dsdsd";
        return memberService.login(request.getUserId(),request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDTO.MemberLoginRequest request){
        return memberService.login(request.getUserId(), request.getPassword());
    }

    @GetMapping("/member/{userId}")
    public MemberDTO.MemberResponse getMember(@PathVariable("userId") String userId){
        Member member = memberService.findByUserId(userId);
        return new MemberDTO.MemberResponse(member.getUserID(), member.getNickName());
    }

    @PutMapping("/member")
    public MemberDTO.MemberResponse changeMemberName(@RequestBody MemberDTO.MemberUpdateRequest request){
        Member findMember = memberService.changeName(request.getToken(), request.getNickname());
        return new MemberDTO.MemberResponse(findMember.getUserID(), findMember.getNickName());
    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestBody MemberDTO.MemberDeleteRequest request){
        memberService.deleteMember(request.getToken());
    }






}