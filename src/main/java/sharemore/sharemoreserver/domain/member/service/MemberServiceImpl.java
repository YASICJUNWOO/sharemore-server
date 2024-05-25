package sharemore.sharemoreserver.domain.member.service;

import sharemore.sharemoreserver.domain.member.Member;

public interface MemberServiceImpl {

    Member join(Member member);

    Member findByEmail(String testEmail);

    boolean login(String testEmail, String testPassword);
}
