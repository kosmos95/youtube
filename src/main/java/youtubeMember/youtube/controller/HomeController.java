package youtubeMember.youtube.controller;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import youtubeMember.youtube.dto.video.VideoCountDto;
import youtubeMember.youtube.service.VideoService;

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
        log.info("main controller");

        VideoCountDto videoCountDto = videoService.getLatestVideoStats();
        model.addAttribute("videoStatus", videoCountDto);

        return "main";
    }

}
