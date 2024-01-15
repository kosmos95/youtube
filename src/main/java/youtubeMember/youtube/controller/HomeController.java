package youtubeMember.youtube.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import youtubeMember.youtube.dto.video.VideoChartDto;
import youtubeMember.youtube.dto.video.VideoCountDto;
import youtubeMember.youtube.service.VideoService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j //로그를 볼 수 있음
public class HomeController {

    //Logger log = LoggerFactory.getLogger(getClass()); ->로그찍어서 보는 코드

    private final VideoService videoService;

    @GetMapping("/")
    public String home() {
        log.info("home controller");
        return "users/login";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {


        VideoCountDto videoCountDto = videoService.getLatestVideoStats();
        List<VideoChartDto> videoChartDto = videoService.getChartVideoStats();

        List<Long> commentData = videoChartDto.stream()
                .map(VideoChartDto::getCommentCount)
                .collect(Collectors.toList());

        List<Long> likeData = videoChartDto.stream()
                .map(VideoChartDto::getLikeCount)
                .collect(Collectors.toList());

        List<Long> viewData = videoChartDto.stream()
                .map(VideoChartDto::getViewCount)
                .collect(Collectors.toList());

        List<String> xCategories = videoChartDto.stream()
                .map(dto -> dto.getVideoUploadDate().toString())
                .collect(Collectors.toList());


        System.out.println("videoChartDto ==" + videoChartDto);

        model.addAttribute("videoStatus", videoCountDto);
       // model.addAttribute("videoChartStats", videoChartDto);

        model.addAttribute("commentData", commentData);
        model.addAttribute("likeData", likeData);
        model.addAttribute("viewData", viewData);
        model.addAttribute("xCategories", xCategories);

        System.out.println("commentData =============" + commentData);
        System.out.println("xCategories =============" + xCategories);

        return "main";
    }

}
