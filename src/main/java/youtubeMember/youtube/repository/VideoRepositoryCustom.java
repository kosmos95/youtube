package youtubeMember.youtube.repository;

import youtubeMember.youtube.dto.video.VideoChartDto;
import youtubeMember.youtube.dto.video.VideoCountDto;

import java.util.List;

public interface VideoRepositoryCustom {
    VideoCountDto getLatestVideoStats();

    List<VideoChartDto> getChartVideoStats();
}
