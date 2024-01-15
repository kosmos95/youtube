package youtubeMember.youtube.dto.video;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class VideoChartDto {

    private Long commentCount;

    private Long likeCount;

    private Long viewCount;

    private LocalDateTime videoUploadDate;

    public VideoChartDto(Long commentCount, Long likeCount, Long viewCount, LocalDateTime  videoUploadDate) {
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.videoUploadDate = videoUploadDate;
    }
}
