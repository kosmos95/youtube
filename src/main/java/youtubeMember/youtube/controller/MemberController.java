package youtubeMember.youtube.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import youtubeMember.youtube.model.Member;
import youtubeMember.youtube.model.Office;
import youtubeMember.youtube.form.MemberForm;
import youtubeMember.youtube.form.OfficeForm;
import youtubeMember.youtube.service.MemberService;
import youtubeMember.youtube.service.OfficeService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final OfficeService officeService;

    @GetMapping(value = "/member")
    public String creteForm(Model model) {

        List<Office> offices = officeService.findOffices();
        model.addAttribute("memberForm", new MemberForm());
        model.addAttribute("offices", offices);

        log.info("member controller");
        return "members/createMemberForm";
    }

    @PostMapping(value = "/member")
    public String create(@Valid MemberForm form,OfficeForm officeForm, BindingResult result, @RequestParam("officeId") Long officeId, Model model) {

        List<Office> offices = officeService.findOffices();
        
        if (result.hasErrors()) {
            //에러가 발생하면 도장 목록을 다시 읽어온다.
            model.addAttribute("offices", offices);
            return "members/createMemberForm";
        }

        officeForm.setId(officeId);
        Office office = officeService.findOffice(officeForm.getId());

        Member member = new Member(office, form.getName(), form.getChannelId(), form.getLeader());
        member.setName(form.getName());
        member.setChannelId(form.getChannelId());
        member.setLeader(form.getLeader());
        member.setOffice(office);


        memberService.join(member);

        return "redirect:/";
    }

}
