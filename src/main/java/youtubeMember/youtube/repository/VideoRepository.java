package youtubeMember.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import youtubeMember.youtube.model.Video;


public interface VideoRepository extends JpaRepository<Video, Long>, QuerydslPredicateExecutor<Video>, VideoRepositoryCustom {
}
