package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Formacao;
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

public class FormacaoJpaController implements Serializable {

    public FormacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FormacaoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public Formacao getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Formacao> qry = em.createQuery("select s from Formacao s WHERE s.nomeFormacao = '" + pesq + "'", Formacao.class);
        return qry.getSingleResult();
    }
    
    public List<Formacao> getFormacaoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Formacao> qry = em.createQuery("select s from Formacao s WHERE s.nomeFormacao LIKE '%" + pesq + "%'", Formacao.class);
        return qry.getResultList();
    }

    public List<Formacao> getFormacaoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Formacao> qry = em.createQuery("select s from Formacao s WHERE s.nomeFormacao = '" + pesq + "'", Formacao.class);
        return qry.getResultList();
    }

    public List<String> getNomeDasFormacoes() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nomeFormacao from Formacao s order by s.nomeFormacao", String.class);
        return qry.getResultList();
    }

    public Formacao getFormUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Formacao> qry = em.createQuery("select s from Formacao s WHERE s.nomeFormacao = '" + pesq + "'", Formacao.class);
        return qry.getSingleResult();
    }

    public void create(Formacao formacao) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(formacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formacao formacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            formacao = em.merge(formacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = formacao.getCodFormacao();
                if (findFormacao(id) == null) {
                    throw new NonexistentEntityException("The formacao with id " + id + " no longer exists.");
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
            Formacao formacao;
            try {
                formacao = em.getReference(Formacao.class, id);
                formacao.getCodFormacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(formacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formacao> findFormacaoEntities() {
        return findFormacaoEntities(true, -1, -1);
    }

    public List<Formacao> findFormacaoEntities(int maxResults, int firstResult) {
        return findFormacaoEntities(false, maxResults, firstResult);
    }

    private List<Formacao> findFormacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formacao.class));
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

    public Formacao findFormacao(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formacao> rt = cq.from(Formacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
