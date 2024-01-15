package youtubeMember.youtube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.dto.video.VideoChartDto;
import youtubeMember.youtube.dto.video.VideoCountDto;
import youtubeMember.youtube.repository.VideoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VideoService {

    private final VideoRepository videoRepository;

    public VideoCountDto getLatestVideoStats() {
        return videoRepository.getLatestVideoStats();
    }

    public List<VideoChartDto> getChartVideoStats() {
        return videoRepository.getChartVideoStats();
    }
}
