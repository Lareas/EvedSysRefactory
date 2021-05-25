package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Dadocadastrogeral;
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


public class DadocadastrogeralJpaController implements Serializable {

    public DadocadastrogeralJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DadocadastrogeralJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(Dadocadastrogeral dadocadastrogeral) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dadocadastrogeral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Dadocadastrogeral> getGeralalunoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastrogeral> qry = em.createQuery("select s from Dadocadastrogeral s WHERE s.dadoCadastroGeralId LIKE '%" + pesq + "%'", Dadocadastrogeral.class);
        return qry.getResultList();
    }
    
    public List<Dadocadastrogeral> getGeralalunoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Dadocadastrogeral s WHERE s.nome = '" + pesq + "'";
        System.out.println("\n\n\n" + ssql + "\n\n\n");
        TypedQuery<Dadocadastrogeral> qry = em.createQuery(ssql, Dadocadastrogeral.class);
        return qry.getResultList();
    }
    
    public List<Dadocadastrogeral> getMatriqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Dadocadastrogeral s WHERE s.matricula = '" + pesq + "'";
        System.out.println("\n\n\n" + ssql + "\n\n\n");
        TypedQuery<Dadocadastrogeral> qry = em.createQuery(ssql, Dadocadastrogeral.class);
        return qry.getResultList();
    }
    
    public List<Dadocadastrogeral> getCpfExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Dadocadastrogeral s WHERE s.cpf = '" + pesq + "'";
        System.out.println("\n\n\n" + ssql + "\n\n\n");
        TypedQuery<Dadocadastrogeral> qry = em.createQuery(ssql, Dadocadastrogeral.class);
        return qry.getResultList();
    }

    public void edit(Dadocadastrogeral dadocadastrogeral) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dadocadastrogeral = em.merge(dadocadastrogeral);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dadocadastrogeral.getDadoCadastroGeralId();
                if (findDadocadastrogeral(id) == null) {
                    throw new NonexistentEntityException("The dadocadastrogeral with id " + id + " no longer exists.");
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
            Dadocadastrogeral dadocadastrogeral;
            try {
                dadocadastrogeral = em.getReference(Dadocadastrogeral.class, id);
                dadocadastrogeral.getDadoCadastroGeralId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dadocadastrogeral with id " + id + " no longer exists.", enfe);
            }
            em.remove(dadocadastrogeral);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dadocadastrogeral> findDadocadastrogeralEntities() {
        return findDadocadastrogeralEntities(true, -1, -1);
    }

    public List<Dadocadastrogeral> findDadocadastrogeralEntities(int maxResults, int firstResult) {
        return findDadocadastrogeralEntities(false, maxResults, firstResult);
    }

    private List<Dadocadastrogeral> findDadocadastrogeralEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dadocadastrogeral.class));
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

    public Dadocadastrogeral findDadocadastrogeral(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dadocadastrogeral.class, id);
        } finally {
            em.close();
        }
    }

    public int getDadocadastrogeralCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dadocadastrogeral> rt = cq.from(Dadocadastrogeral.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
