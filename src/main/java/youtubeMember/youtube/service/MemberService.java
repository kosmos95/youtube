package youtubeMember.youtube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.model.Member;
import youtubeMember.youtube.repository.MemberRepository2;
import youtubeMember.youtube.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository2 memberRepository2;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member member = memberRepository.findByNickName(name);

        if (member == null) {
            throw new UsernameNotFoundException(name);
        }

        return User.builder()
                .username(member.getNickName())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

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
     * 유튜브 채널 이름 추출
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
