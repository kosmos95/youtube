package youtubeMember.youtube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import youtubeMember.youtube.dto.ChannelFormDto;
import youtubeMember.youtube.dto.UserFormDto;
import youtubeMember.youtube.model.User;
import youtubeMember.youtube.model.Office;
import youtubeMember.youtube.service.UserService;
import youtubeMember.youtube.service.OfficeService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OfficeService officeService;

    @GetMapping(value = "users/new")
    public String userForm(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());

        return "users/userForm";
    }

    @PostMapping(value = "users/new")
    public String userForm(UserFormDto userFormDto) {
        User user = User.createMember(userFormDto, passwordEncoder);
        userService.saveUser(user);
        log.info("회원가입 완료");

        return "main";
    }

    @GetMapping(value = "/users/login")
    public String login(Model model) {
        model.addAttribute("userFormDto", new UserFormDto());
        return "users/login";
    }

    @GetMapping(value = "/channel")
    public String creteForm(Model model) {

        List<Office> offices = officeService.findOffices();
        model.addAttribute("memberForm", new ChannelFormDto());
        model.addAttribute("offices", offices);


        return "users/createMemberForm";
    }

//    @PostMapping(value = "/channel")
//    public String create(@Valid ChannelFormDto form, OfficeFormDto officeForm, BindingResult result, @RequestParam("officeId") Long officeId, Model model) {
//
//        List<Office> offices = officeService.findOffices();
//
//        if (result.hasErrors()) {
//            //에러가 발생하면 도장 목록을 다시 읽어온다.
//            model.addAttribute("offices", offices);
//            return "members/createMemberForm";
//        }
//
//        officeForm.setId(officeId);
//        Office office = officeService.findOffice(officeForm.getId());
//
//        User user = new User(office, form.getName(), form.getChannelId(), form.getLeader());
//        user.setNickName(form.getName());
//        user.setChannelId(form.getChannelId());
//        user.setLeader(form.getLeader());
//        user.setOffice(office);
//
//
//        userService.join(user);
//
//        return "redirect:/";
//    }

}
