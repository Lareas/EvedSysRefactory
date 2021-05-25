package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Especificidade;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;
import jpa_controler.exceptions.PreexistingEntityException;

public class EspecificidadeJpaController implements Serializable {

    public EspecificidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EspecificidadeJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public Short pegaCodEspec(String area) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s.especificidadeId from Especificidade s WHERE s.especificidade = '" + area + "'";
        System.out.println(ssql);
        TypedQuery<Short> qry = em.createQuery(ssql, Short.class);
        return qry.getSingleResult();
    }

    public List<String> getNomeEsp() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.especificidade from Especificidade s order by s.especificidade", String.class);
        return qry.getResultList();
    }

    public Especificidade getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Especificidade> qry = em.createQuery("select s from Especificidade s WHERE s.especificidade = '" + pesq + "'", Especificidade.class);
        return qry.getSingleResult();
    }

    public List<Especificidade> getEspecificidades() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Especificidade> qry = em.createQuery("select s from Especificidade s", Especificidade.class);
        return qry.getResultList();
    }

    public List<Especificidade> getEspecificidadesPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Especificidade> qry = em.createQuery("select s from Especificidade s WHERE s.especificidade LIKE '%" + pesq + "%'", Especificidade.class);
        return qry.getResultList();
    }

    public List<Especificidade> getEspecificidadesPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Especificidade> qry = em.createQuery("select s from Especificidade s WHERE s.especificidade = '" + pesq + "'", Especificidade.class);
        return qry.getResultList();
    }

    public Especificidade getEspecificidadesPesqUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Especificidade> qry = em.createQuery("select s from Especificidade s WHERE s.especificidade = '" + pesq + "'", Especificidade.class);
        return qry.getSingleResult();
    }

    public void create(Especificidade especificidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(especificidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEspecificidade(especificidade.getEspecificidadeId()) != null) {
                throw new PreexistingEntityException("Especificidade " + especificidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Especificidade especificidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            especificidade = em.merge(especificidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = especificidade.getEspecificidadeId();
                if (findEspecificidade(id) == null) {
                    throw new NonexistentEntityException("The especificidade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Especificidade especificidade;
            try {
                especificidade = em.getReference(Especificidade.class, id);
                especificidade.getEspecificidadeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The especificidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(especificidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<String> getNomeDasEspecificidades() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.especificidade from Especificidade s order by s.especificidade", String.class);
        return qry.getResultList();
    }

    public List<Especificidade> findEspecificidadeEntities() {
        return findEspecificidadeEntities(true, -1, -1);
    }

    public List<Especificidade> findEspecificidadeEntities(int maxResults, int firstResult) {
        return findEspecificidadeEntities(false, maxResults, firstResult);
    }

    private List<Especificidade> findEspecificidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Especificidade.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Especificidade findEspecificidade(Short id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Especificidade.class,
                    id);
        } finally {
            em.close();
        }
    }

    public int getEspecificidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Especificidade> rt = cq.from(Especificidade.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
