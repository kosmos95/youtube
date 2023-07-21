package youtubeMember.youtube.repository;

import org.springframework.stereotype.Repository;
import youtubeMember.youtube.doamin.Member;
import youtubeMember.youtube.doamin.Office;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Spliterator;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByChannelId(String channelId) {
        return em.createQuery("select m from Member m where m.channelId = :channelId", Member.class)
                .setParameter("channelId", channelId)
                .getResultList();
    }
}
