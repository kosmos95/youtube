package youtubeMember.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import youtubeMember.youtube.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByNickName(String NickName);
}
