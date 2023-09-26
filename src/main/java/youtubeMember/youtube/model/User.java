package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import youtubeMember.youtube.constant.Role;
import youtubeMember.youtube.dto.UserFormDto;


import javax.persistence.*;

@Table(schema = "youtube", name = "USERS")
@Entity
@SequenceGenerator(
        name = "USERS_SEQ_GENERATOR",
        sequenceName = "USERS_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "USERS_SEQ_GENERATOR")
    private Long id;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "nick_name")
    private String nickName;

    public User() {
    }

    public static User createMember(UserFormDto userFormDto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setNickName(userFormDto.getName());
        String password = passwordEncoder.encode(userFormDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.USER);

        return user;
    }

}
