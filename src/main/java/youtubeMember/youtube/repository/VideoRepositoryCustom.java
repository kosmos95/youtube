package youtubeMember.youtube.repository;

import youtubeMember.youtube.dto.video.VideoCountDto;

public interface VideoRepositoryCustom {
    VideoCountDto getLatestVideoStats();
}
