package sharemore.sharemoreserver.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{
    private final MemberRepository memberRepository;

    @Override
    public Member join(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findByEmail(String testEmail) {
        return memberRepository.findByEmail(testEmail);
    }

    @Override
    public boolean login(String testEmail, String testPassword) {
        Member member = memberRepository.findByEmail(testEmail);
        return member.getPassword().equals(testPassword);
    }
}
