package youtubeMember.youtube.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j //로그를 볼 수 있음
public class HomeController {

    //Logger log = LoggerFactory.getLogger(getClass()); ->로그찍어서 보는 코드

    @GetMapping("/")
    public String home() {
        log.info("home controller");
        return "members/login";
    }

    @GetMapping("/main")
    public String mainPage() {
        log.info("home controller");
        return "members/main";
    }

}
