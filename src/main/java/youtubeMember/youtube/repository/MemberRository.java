package youtubeMember.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youtubeMember.youtube.model.Member;

public interface MemberRository extends JpaRepository<Member, Long> {

    Member findBy(String NickName);
}
