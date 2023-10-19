package youtubeMember.youtube.dto.video;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class VideoCountDto {

    private Long commentCount;

    private Long likeCount;

    private Long viewCount;

    public VideoCountDto(Long commentCount, Long likeCount, Long viewCount) {
        this.commentCount = commentCount;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
    }
}
