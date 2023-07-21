package youtubeMember.youtube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.doamin.Member;
import youtubeMember.youtube.doamin.Office;
import youtubeMember.youtube.form.MemberForm;
import youtubeMember.youtube.repository.MemberRepository;
import youtubeMember.youtube.repository.OfficeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {

        member.setChannelId(getFileNameFromURL(member.getChannelId()));
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByChannelId(member.getChannelId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 채널입니다");
        }
    }

    /**
     * 처음에는 모르고 controller 단에  getFileNameFromURL함수를 넣었다.
     * 하지만 객체지향의 특징을 생각하면 서비스 단에서 처리해야 하는게 맞다.
     */
    private String getFileNameFromURL(String channelId) {
       return channelId.substring(channelId.lastIndexOf('/') + 1, channelId.length());
    }

}
