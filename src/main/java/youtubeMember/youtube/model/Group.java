package youtubeMember.youtube.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "youtube", name = "group")
@Entity
@SequenceGenerator(
        name = "GROUP_SEQ_GENERATOR",
        sequenceName = "GROUP_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "GROUP_SEQ_GENERATOR")
    @JoinColumn(name = "group_Id")
    private Long id;

    @Column(name = "group_name")
    private String groupName;

    @OneToMany(mappedBy = "group")
    private List<Category> categories = new ArrayList<>();
}
