package youtubeMember.youtube.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import youtubeMember.youtube.dto.video.VideoCountDto;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static youtubeMember.youtube.model.QVideo.*;

public class VideoRepositoryCustomImpl implements VideoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public VideoRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public VideoCountDto getLatestVideoStats() {

        //서브쿼리
        LocalDateTime maxUploadDate = queryFactory
                .select(video.videoUpload.max())
                .from(video)
                .fetchOne();


        VideoCountDto result = queryFactory
                .select(Projections.constructor(VideoCountDto.class,
                        video.commentCount,
                        video.likeCount,
                        video.viewCount
                ))
                .from(video)
                .where(video.videoUpload.eq(maxUploadDate))
                .fetchOne();

        return result;
    }
}
