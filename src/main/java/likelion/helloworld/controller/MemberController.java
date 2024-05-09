package likelion.helloworld.controller;

import likelion.helloworld.DTO.MemberDTO;
import likelion.helloworld.domain.Member;
import likelion.helloworld.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/member/add")
    public String signUp(@RequestBody MemberDTO.MemberCreateRequest request){
        Member member = memberService.signUp(request.getUserId(), request.getPassword(), request.getNickname());
        if(member == null)return null;
        return memberService.login(request.getUserId(),request.getPassword());
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDTO.MemberLoginRequest request){
        return memberService.login(request.getUserId(), request.getPassword());
    }



}