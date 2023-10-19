package youtubeMember.youtube.dto.video;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class videoDto {

    private String channelTitle;

    private String videoTitle;

    private String videoUrl;

    private String youtubeVideoId;

    private String playList;

    private Long commentCount;

    private Long likeCount;

    private Long viewCount;

    private LocalDateTime videoUpload;


}
