package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import youtubeMember.youtube.constant.Role;
import youtubeMember.youtube.dto.MemberFormDto;


import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Table(schema = "youtube", name = "USER")
@Entity
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "USER_SEQ_GENERATOR")
    private Long id;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



    @Column(name = "nick_name")
    private String nickName;





    public User() {
    }

    public static User createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setNickName(memberFormDto.getName());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.USER);

        return user;
    }

}
