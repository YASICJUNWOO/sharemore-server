package sharemore.sharemoreserver.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/member")
public class MemberAdminController {

    private final MemberRepository memberRepository;

    @GetMapping("/all")
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

}
