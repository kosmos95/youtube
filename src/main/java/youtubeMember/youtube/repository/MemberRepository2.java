package youtubeMember.youtube.repository;

import org.springframework.stereotype.Repository;
import youtubeMember.youtube.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository2 {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findOne(Long id) {
        return em.find(User.class, id);
    }

//    public List<User> findByChannelId(String channelId) {
//        return em.createQuery("select u from User u where u.channelId = :channelId", User.class)
//                .setParameter("channelId", channelId)
//                .getResultList();
//    }
}
