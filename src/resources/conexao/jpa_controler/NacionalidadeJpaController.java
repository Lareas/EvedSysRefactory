package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Nacionalidade;
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

public class NacionalidadeJpaController implements Serializable {

    public NacionalidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public NacionalidadeJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public Nacionalidade getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Nacionalidade> qry = em.createQuery("select s from Nacionalidade s WHERE s.nacionalidade = '" + pesq + "'", Nacionalidade.class);
        return qry.getSingleResult();
    }
    
    public List<String> getNomeDasNacionalidades() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nacionalidade from Nacionalidade s order by s.nacionalidade", String.class);
        return qry.getResultList();
    }
    
    public List<Nacionalidade> getNacionalidadePesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Nacionalidade> qry = em.createQuery("select s from Nacionalidade s WHERE s.nacionalidade LIKE '%" + pesq +"%'", Nacionalidade.class);
        return qry.getResultList();
    }
    
    public List<Nacionalidade> getNacionalidadePesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Nacionalidade> qry = em.createQuery("select s from Nacionalidade s WHERE s.nacionalidade = '" + pesq +"'", Nacionalidade.class);
        return qry.getResultList();
    }
    
    public Nacionalidade getNacioUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Nacionalidade> qry = em.createQuery("select s from Nacionalidade s WHERE s.nacionalidade = '" + pesq + "'", Nacionalidade.class);
        return qry.getSingleResult();
    }

    public void create(Nacionalidade nacionalidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nacionalidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findNacionalidade(nacionalidade.getNacionalidadeId()) != null) {
                throw new PreexistingEntityException("Nacionalidade " + nacionalidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nacionalidade nacionalidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nacionalidade = em.merge(nacionalidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = nacionalidade.getNacionalidadeId();
                if (findNacionalidade(id) == null) {
                    throw new NonexistentEntityException("The nacionalidade with id " + id + " no longer exists.");
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
            Nacionalidade nacionalidade;
            try {
                nacionalidade = em.getReference(Nacionalidade.class, id);
                nacionalidade.getNacionalidadeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nacionalidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(nacionalidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nacionalidade> findNacionalidadeEntities() {
        return findNacionalidadeEntities(true, -1, -1);
    }

    public List<Nacionalidade> findNacionalidadeEntities(int maxResults, int firstResult) {
        return findNacionalidadeEntities(false, maxResults, firstResult);
    }

    private List<Nacionalidade> findNacionalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nacionalidade.class));
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

    public Nacionalidade findNacionalidade(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nacionalidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getNacionalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nacionalidade> rt = cq.from(Nacionalidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
