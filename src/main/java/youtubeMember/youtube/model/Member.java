package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

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
public class  Member {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "office_Id")
    private Office office;

    @Column(name = "member_name")
    private String name;

    @Column(name = "authorChannel_id")
    private String channelId;

    private Boolean leader;

    public void setOffice(Office office) {
        this.office=office;
        office.getMembers().add(this);
    }

    public Member() {
    }

        public Member(Office office, String name, String channelId, Boolean leader) {
        this.office = office;
        this.name = name;
        this.channelId = channelId;
        this.leader = leader;
    }

}
