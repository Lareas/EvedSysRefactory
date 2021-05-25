package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Docsalunos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;

public class DocsalunosJpaController implements Serializable {

    public DocsalunosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DocsalunosJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(Docsalunos docsalunos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(docsalunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docsalunos docsalunos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            docsalunos = em.merge(docsalunos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docsalunos.getCoddocaluno();
                if (findDocsalunos(id) == null) {
                    throw new NonexistentEntityException("The docsalunos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docsalunos docsalunos;
            try {
                docsalunos = em.getReference(Docsalunos.class, id);
                docsalunos.getCoddocaluno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docsalunos with id " + id + " no longer exists.", enfe);
            }
            em.remove(docsalunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Docsalunos jaTemArquivo(Short codAluno, String nomeLabel) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Docsalunos> qry = em.createQuery("select s from Docsalunos s WHERE s.dadoCadastroGeralId = " + codAluno + " AND s.labelDoc = '" + nomeLabel + "' ", Docsalunos.class);
        try {
            return qry.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Docsalunos> findDocsalunosEntities() {
        return findDocsalunosEntities(true, -1, -1);
    }

    public List<Docsalunos> findDocsalunosEntities(int maxResults, int firstResult) {
        return findDocsalunosEntities(false, maxResults, firstResult);
    }

    private List<Docsalunos> findDocsalunosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docsalunos.class));
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

    public Docsalunos findDocsalunos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docsalunos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocsalunosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docsalunos> rt = cq.from(Docsalunos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
