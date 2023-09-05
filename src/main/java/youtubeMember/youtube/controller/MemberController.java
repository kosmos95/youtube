package youtubeMember.youtube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youtubeMember.youtube.dto.ChannelFormDto;
import youtubeMember.youtube.dto.MemberFormDto;
import youtubeMember.youtube.dto.OfficeFormDto;
import youtubeMember.youtube.model.Member;
import youtubeMember.youtube.model.Office;
import youtubeMember.youtube.service.MemberService;
import youtubeMember.youtube.service.OfficeService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final OfficeService officeService;

    @GetMapping(value = "members/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "members/memberForm";
    }

    @PostMapping(value = "members/new")
    public String memberForm(MemberFormDto memberFormDto) {
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberService.saveMember(member);
        log.info("회원가입 완료");

        return "members/main";
    }

    @GetMapping(value = "/channel")
    public String creteForm(Model model) {

        List<Office> offices = officeService.findOffices();
        model.addAttribute("memberForm", new ChannelFormDto());
        model.addAttribute("offices", offices);


        return "members/createMemberForm";
    }

    @PostMapping(value = "/channel")
    public String create(@Valid ChannelFormDto form, OfficeFormDto officeForm, BindingResult result, @RequestParam("officeId") Long officeId, Model model) {

        List<Office> offices = officeService.findOffices();
        
        if (result.hasErrors()) {
            //에러가 발생하면 도장 목록을 다시 읽어온다.
            model.addAttribute("offices", offices);
            return "members/createMemberForm";
        }

        officeForm.setId(officeId);
        Office office = officeService.findOffice(officeForm.getId());

        Member member = new Member(office, form.getName(), form.getChannelId(), form.getLeader());
        member.setNickName(form.getName());
        member.setChannelId(form.getChannelId());
        member.setLeader(form.getLeader());
        member.setOffice(office);


        memberService.join(member);

        return "redirect:/";
    }

}
