package com.chatapplication.userservice.member;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;


    @PostMapping("/signup")
    public void signUp(@RequestBody MemberRequest member) {
        memberService.signUp(member);
    }

    @PostMapping("/login")
    public MemberResponse login(@RequestBody MemberRequest member) {
        return memberService.login(member);
    }

    @GetMapping("/{username}")
    public Member getMember(@PathVariable String username) {
        return memberService.getMember(username);
    }


}
