package youtubeMember.youtube.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(schema = "youtube", name = "comment")
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

    @JoinColumn(name = "video_id")
    private String videoId;

    @Column(name = "authorChannel_id")
    private String channelId;

    @Lob
    @Column(name = "comments")
    private String comments;

    @Column(name = "author")
    private String author;

    @Column(name = "author")
    private String writeTime;

    @Column(name = "likeAccount")
    private Long likeAccount;

    @Column(name = "youtube_category")
    private String youtubeCategory;

}
