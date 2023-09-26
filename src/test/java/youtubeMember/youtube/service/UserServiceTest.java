package youtubeMember.youtube.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.dto.MemberFormDto;
import youtubeMember.youtube.model.User;
import youtubeMember.youtube.repository.MemberRepository2;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    MemberRepository2 memberRepository2;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Test
    @Rollback
    public void 회원가입() {

        //given
        User user = new User();
        user.setChannelId("UC0Mvu4HD8IuGiaVxrmph88g");

        //when
        Long savedId = userService.join(user);

        //then
        assertEquals(user, memberRepository2.findOne(savedId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() {

        //given
//        Member member1 = new Member();
//        member1.setChannelId("https://www.youtube.com/channel/UC0Mvu4HD8IuGiaVxrmph88g");
//
//        Member member2 = new Member();
//        member2.setChannelId("https://www.youtube.com/channel/UC0Mvu4HD8IuGiaVxrmph88g");

        User user1 = new User();
        User user2 = new User();

        user1.setChannelId("UC0Mvu4HD8IuGiaVxrmph88g");


        user2.setChannelId("UC0Mvu4HD8IuGiaVxrmph88g");
        //when
        userService.join(user1);
        userService.join(user2);

        //then
        fail("예외가 발생해야 한다.");

    }

    public User createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("홍길동");
        memberFormDto.setPassword("1234");
        return User.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        User user = createMember();
        User savedUser = userService.saveMember(user);

        assertEquals(user.getNickName(), savedUser.getNickName());
    }
}
