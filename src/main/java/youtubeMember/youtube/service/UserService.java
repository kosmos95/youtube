package youtubeMember.youtube.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import youtubeMember.youtube.model.User;
import youtubeMember.youtube.repository.MemberRepository2;
import youtubeMember.youtube.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {

    private final MemberRepository2 memberRepository2;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = userRepository.findByNickName(name);

        if (user == null) {
            throw new UsernameNotFoundException(name);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getNickName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }

//    public Long join(User user) {
//
//        user.setChannelId(getFileNameFromURL(user.getChannelId()));
//        validateDuplicateChannel(user);
//        memberRepository2.save(user);
//        return user.getId();
//    }
//
//    private void validateDuplicateChannel(User user) {
//        List<User> findUsers = memberRepository2.findByChannelId(user.getChannelId());
//        if (!findUsers.isEmpty()) {
//            throw new IllegalStateException("이미 존재하는 채널입니다");
//        }
//    }

    /**
     * 유튜브 채널 이름 추출
     */
    private String getFileNameFromURL(String channelId) {
       return channelId.substring(channelId.lastIndexOf('/') + 1, channelId.length());
    }


    public User saveMember(User user) {
        validateDuplicateMember(user);
        return userRepository.save(user);
    }

    private void validateDuplicateMember(User user) {
        User findUser = userRepository.findByNickName(user.getNickName());
        if (findUser != null) {
            throw new IllegalStateException("이미 있는 아이디입니다.");
        }
    }


}
