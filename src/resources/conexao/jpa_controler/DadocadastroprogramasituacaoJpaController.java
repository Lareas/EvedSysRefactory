package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Dadocadastroprogramasituacao;
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

public class DadocadastroprogramasituacaoJpaController implements Serializable {

    public DadocadastroprogramasituacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DadocadastroprogramasituacaoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public Dadocadastroprogramasituacao getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastroprogramasituacao> qry = em.createQuery("select s from Dadocadastroprogramasituacao s WHERE s.dadoCadastroProgramaSituacao = '" + pesq + "'", Dadocadastroprogramasituacao.class);
        return qry.getSingleResult();
    }

    public void create(Dadocadastroprogramasituacao dadocadastroprogramasituacao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dadocadastroprogramasituacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDadocadastroprogramasituacao(dadocadastroprogramasituacao.getDadoCadastroProgramaSituacaoId()) != null) {
                throw new PreexistingEntityException("Dadocadastroprogramasituacao " + dadocadastroprogramasituacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dadocadastroprogramasituacao dadocadastroprogramasituacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dadocadastroprogramasituacao = em.merge(dadocadastroprogramasituacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dadocadastroprogramasituacao.getDadoCadastroProgramaSituacaoId();
                if (findDadocadastroprogramasituacao(id) == null) {
                    throw new NonexistentEntityException("The dadocadastroprogramasituacao with id " + id + " no longer exists.");
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
            Dadocadastroprogramasituacao dadocadastroprogramasituacao;
            try {
                dadocadastroprogramasituacao = em.getReference(Dadocadastroprogramasituacao.class, id);
                dadocadastroprogramasituacao.getDadoCadastroProgramaSituacaoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dadocadastroprogramasituacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(dadocadastroprogramasituacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<String> getNomeDasSituacoes() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.dadoCadastroProgramaSituacao from Dadocadastroprogramasituacao s order by s.dadoCadastroProgramaSituacao", String.class);
        return qry.getResultList();
    }

    
    public List<Dadocadastroprogramasituacao> findDadocadastroprogramasituacaoEntities() {
        return findDadocadastroprogramasituacaoEntities(true, -1, -1);
    }

    public List<Dadocadastroprogramasituacao> findDadocadastroprogramasituacaoEntities(int maxResults, int firstResult) {
        return findDadocadastroprogramasituacaoEntities(false, maxResults, firstResult);
    }

    private List<Dadocadastroprogramasituacao> findDadocadastroprogramasituacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dadocadastroprogramasituacao.class));
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

    public List<Dadocadastroprogramasituacao> getDadocadastroprogramasituacaoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastroprogramasituacao> qry = em.createQuery("select s from Dadocadastroprogramasituacao s WHERE s.dadoCadastroProgramaSituacao LIKE '%" + pesq + "%'", Dadocadastroprogramasituacao.class);
        return qry.getResultList();
    }

    public List<Dadocadastroprogramasituacao> getDadocadastroprogramasituacaoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastroprogramasituacao> qry = em.createQuery("select s from Dadocadastroprogramasituacao s WHERE s.dadoCadastroProgramaSituacao = '" + pesq + "'", Dadocadastroprogramasituacao.class);
        return qry.getResultList();
    }

    public Dadocadastroprogramasituacao findDadocadastroprogramasituacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dadocadastroprogramasituacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getDadocadastroprogramasituacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dadocadastroprogramasituacao> rt = cq.from(Dadocadastroprogramasituacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
