package sharemore.sharemoreserver.domain.member.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.domain.member.Member;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Rollback
    @Test
    void 회원가입() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        //when
        Member savedMember = memberService.join(member);

        //then
        assertThat(member).isEqualTo(savedMember);

    }

    @Test
    public void 이메일로_회원찾기() {
        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        //when
        Member savedMember = memberService.join(member);
        Member findMember = memberService.findByEmail("testEmail");

        //then
        assertThat(findMember).isEqualTo(savedMember);
    }

}