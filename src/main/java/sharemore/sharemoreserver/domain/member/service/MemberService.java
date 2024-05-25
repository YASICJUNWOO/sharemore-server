package sharemore.sharemoreserver.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharemore.sharemoreserver.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{
    private final MemberRepository memberRepository;
}
