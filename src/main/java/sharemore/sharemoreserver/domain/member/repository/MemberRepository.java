package sharemore.sharemoreserver.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharemore.sharemoreserver.domain.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
