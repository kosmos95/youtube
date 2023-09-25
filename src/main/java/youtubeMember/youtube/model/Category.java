package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "youtube", name = "category")
@Entity
@SequenceGenerator(
        name = "CATEGORY_SEQ_GENERATOR",
        sequenceName = "CATEGORY_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "CATEGORY_SEQ_GENERATOR")
    @Column(name = "category_id")
    private Long id;

    @Column(name = "category_name")
    private String CategoryName;

    @OneToMany(mappedBy = "category")
    private List<Video> videos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;
}
