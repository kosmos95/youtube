package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "youtube", name = "OFFICE")
@Entity
@SequenceGenerator(
        name = "OFFICE_SEQ_GENERATOR",
        sequenceName = "OFFICE_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFFICE_SEQ_GENERATOR")
    @Column(name = "office_id")
    private Long id;

    private Integer area;

    private String office;

    @OneToMany(mappedBy = "office")
    private List<Member> members = new ArrayList<>();

    public Office() {
    }
}
