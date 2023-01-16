package youtubeMember.youtube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youtubeMember.youtube.doamin.Member;
import youtubeMember.youtube.doamin.Office;
import youtubeMember.youtube.form.MemberForm;
import youtubeMember.youtube.repository.OfficeRepository;
import youtubeMember.youtube.service.MemberService;
import youtubeMember.youtube.service.OfficeService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final OfficeRepository officeRepository;
    private final MemberService memberService;

    private final OfficeService officeService;

    @GetMapping(value = "/")
    public String creteForm(Model model) {

        List<Office> offices = officeService.findOffices();

        model.addAttribute("memberForm", new MemberForm());
        model.addAttribute("offices", offices);

        return "members/createMemberForm";
    }

    @PostMapping(value = "/")
    public String create(@Valid MemberForm form, BindingResult result, @RequestParam("officeId") Long officeId) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Office office = officeService.findOffice(officeId);

        Member member = new Member(office, form.getName(), form.getChannelId(), form.getLeader());
        member.setName(form.getName());
        member.setChannelId(form.getChannelId());
        member.setLeader(form.getLeader());
        member.setOffice(office);

        memberService.join(member);

        return "redirect:/";
    }

}
