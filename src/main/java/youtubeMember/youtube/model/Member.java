package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
