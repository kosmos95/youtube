package youtubeMember.youtube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.model.Member;
import youtubeMember.youtube.repository.MemberRepository2;
import youtubeMember.youtube.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository2 memberRepository2;
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {

        member.setChannelId(getFileNameFromURL(member.getChannelId()));
        validateDuplicateChannel(member);
        memberRepository2.save(member);
        return member.getId();
    }

    private void validateDuplicateChannel(Member member) {
        List<Member> findMembers = memberRepository2.findByChannelId(member.getChannelId());
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

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByNickName(member.getNickName());
        if (findMember != null) {
            throw new IllegalStateException("이미 있는 아이디입니다.");
        }
    }

}
