package com.chatapplication.userservice.member;


import com.chatapplication.userservice.exception.ConlfictException;
import com.chatapplication.userservice.exception.ResourceNotFoundException;
import com.chatapplication.userservice.jwt.JwtService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public HttpStatus signUp(MemberRequest memberRequest) {
        if (memberRepository.existsByUsername(memberRequest.getUsername())) {
            log.info("User signed up with a existing username = {}", memberRequest.getUsername());
            throw new ConlfictException("User already exists");
        }

        Member member = new Member();
        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword()));

        BeanUtils.copyProperties(memberRequest, member);
        memberRepository.save(member);
        return HttpStatus.OK;
    }

    public MemberResponse login(MemberRequest memberRequest) {

        authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                memberRequest.getUsername(), memberRequest.getPassword()
                        )
                );

        UserDetails user = userDetailsService.loadUserByUsername(memberRequest.getUsername());
        String token = jwtService.generateToken(user);
        Member member = memberRepository.findByUsername(user.getUsername()).get();

        return MemberResponse.builder().member(member).token(token).build();
    }

    public Member getMember(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username doesnt exists"));
    }
}
