package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(schema = "youtube", name = "YOUTUBE_COMMENT")
@Entity
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "COMMENT_SEQ_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @Column(name = "Channel_id")
    private String channelId;

    @Lob
    @Column(name = "comment_text")
    private String comments;

    @Column(name = "author")
    private String author;

    @Column(name = "write_time")
    private LocalDateTime writeTime;

    @Column(name = "like_count")
    private Long likeCount;

    public void setVideo(Video video) {
        this.video=video;
        video.getComments().add(this);
    }

}
