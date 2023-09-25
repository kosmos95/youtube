package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "youtube", name = "video")
@Entity
@SequenceGenerator(
        name = "VIDEO_SEQ_GENERATOR",
        sequenceName = "VIDEO_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter@Setter
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "VIDEO_SEQ_GENERATOR")
    private Long id;

    private String videoId;

    private String videoTitle;

    private LocalDateTime videoUpload;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

}
