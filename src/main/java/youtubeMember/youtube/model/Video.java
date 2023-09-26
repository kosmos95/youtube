package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(schema = "youtube", name = "VIDEO")
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

    @Column(name = "youtube_video_id")
    private String youtubeVideoId;

    @Column(name = "video_title")
    private String videoTitle;

    @Column(name = "video_upload")
    private LocalDateTime videoUpload;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id")
    private Category category;
}
