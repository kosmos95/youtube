package youtubeMember.youtube.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import youtubeMember.youtube.model.Office;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OfficeRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Office office) {
        em.persist(office);
    }

    public Office findOne(Long id) {
        return em.find(Office.class, id);
    }

    public List<Office> findAll() {
        return em.createQuery("select o from Office o", Office.class)
                .getResultList();
    }
    public List<Office> findId(String office) {
        return em.createQuery("select o from Office o where o.office =:office", Office.class)
                .setParameter("office", office)
                .getResultList();
    }

}

