package youtubeMember.youtube.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import youtubeMember.youtube.dto.video.VideoChartDto;
import youtubeMember.youtube.dto.video.VideoCountDto;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

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
                .select(video.videoUploadDate.max())
                .from(video)
                .fetchOne();


        VideoCountDto result = queryFactory
                .select(Projections.constructor(VideoCountDto.class,
                        video.commentCount,
                        video.likeCount,
                        video.viewCount
                ))
                .from(video)
                .where(video.videoUploadDate.eq(maxUploadDate))
                .fetchOne();

        return result;
    }

    @Override
    public List<VideoChartDto> getChartVideoStats() {

        return queryFactory
                .select(Projections.constructor(
                        VideoChartDto.class,
                        video.commentCount,
                        video.likeCount,
                        video.viewCount,
                        video.videoUploadDate
                ))
                .from(video)
                .where(video.videoTitle.ne("비공개 영상"))
                .orderBy(video.videoUploadDate.desc())
                .limit(7)
                .fetch();

    }
}
