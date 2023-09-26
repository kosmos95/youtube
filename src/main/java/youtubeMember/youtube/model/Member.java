package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Table(schema = "youtube", name = "MEMBER")
@Entity
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "authorChannel_id")
    private String channelId;

    private Boolean leader;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "office_Id")
    private Office office;

    public void setOffice(Office office) {
        this.office=office;
        office.getMembers().add(this);
    }
}
