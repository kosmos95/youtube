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

    @Column(name = "channel_title")
    private String channelTitle;

    @Column(name = "youtube_video_id")
    private String youtubeVideoId;

    @Column(name = "video_title")
    private String videoTitle;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "video_upload_date")
    private LocalDateTime videoUploadDate;

    @Column(name = "play_list")
    private String playList;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "comment_count")
    private Long commentCount;

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id")
    private Category category;

    public void setCategory(Category category) {
        this.category=category;
        category.getVideos().add(this);
    }
}
