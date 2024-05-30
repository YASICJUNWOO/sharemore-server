package sharemore.sharemoreserver.domain.member.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.domain.member.Member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


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

    @Rollback
    @Test
    void 회원가입_중복_예외() {

        //given
        Member member1 = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        Member member2 = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        //when
        memberService.join(member1);

        //then
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 존재하는 회원 이메일입니다.");
    }

    @Test
    @Rollback
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

    @Test
    @Rollback
    public void 로그인() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        //when
        memberService.join(member);
        boolean login = memberService.login("testEmail", "testPassword");

        //then
        assertThat(login).isTrue();
    }

    @Test
    @Rollback
    public void 로그인_실패() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        //when
        memberService.join(member);
        boolean login = memberService.login("testEmail", "wrongPassword");

        //then
        assertThat(login).isFalse();
    }

}