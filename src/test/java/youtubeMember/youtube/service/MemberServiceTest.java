package youtubeMember.youtube.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.model.Member;
import youtubeMember.youtube.repository.MemberRepository2;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository2 memberRepository2;

    @Test
    @Rollback
    public void 회원가입() {

        //given
        Member member = new Member();
        member.setChannelId("UC0Mvu4HD8IuGiaVxrmph88g");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository2.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {

        //given
//        Member member1 = new Member();
//        member1.setChannelId("https://www.youtube.com/channel/UC0Mvu4HD8IuGiaVxrmph88g");
//
//        Member member2 = new Member();
//        member2.setChannelId("https://www.youtube.com/channel/UC0Mvu4HD8IuGiaVxrmph88g");

        Member member1 = new Member();
        Member member2 = new Member();

        member1.setChannelId("UC0Mvu4HD8IuGiaVxrmph88g");


        member2.setChannelId("UC0Mvu4HD8IuGiaVxrmph88g");
        //when
        memberService.join(member1);
        memberService.join(member2);

        //then
        fail("예외가 발생해야 한다.");

    }
}
