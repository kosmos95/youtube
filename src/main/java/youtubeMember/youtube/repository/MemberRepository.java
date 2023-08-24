package youtubeMember.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youtubeMember.youtube.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickName(String NickName);
}
