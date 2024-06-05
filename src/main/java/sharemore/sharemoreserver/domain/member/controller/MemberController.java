package sharemore.sharemoreserver.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<Member> join(@RequestBody Member member) {
        Member savedMember = memberService.join(member);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @PostMapping("/login")
    public ResponseEntity<Member> login(@RequestBody Member member) {

        boolean isLogin = memberService.login(member.getEmail(), member.getPassword());

        if(isLogin) {
            return ResponseEntity.status(HttpStatus.OK).body(member);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}