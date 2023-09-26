package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import youtubeMember.youtube.constant.Role;
import youtubeMember.youtube.dto.MemberFormDto;


import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Table(schema = "youtube", name = "MEMBER_TESET")
@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "office_Id")
    private Office office;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "authorChannel_id")
    private String channelId;

    private Boolean leader;

    public void setOffice(Office office) {
        this.office=office;
        office.getUsers().add(this);
    }

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
