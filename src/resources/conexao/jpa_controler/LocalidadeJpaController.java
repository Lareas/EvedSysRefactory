package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Localidade;
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


public class LocalidadeJpaController implements Serializable {

    public LocalidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public LocalidadeJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<String> getNomeDasLocalidades() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.localidade from Localidade s order by s.localidade", String.class);
        return qry.getResultList();
    }
    
    public List<Localidade> getLocalidadeExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Localidade s WHERE s.localidade = '" + pesq + "'";
        System.out.println("\n\n\n" + ssql + "\n\n\n");
        TypedQuery<Localidade> qry = em.createQuery(ssql, Localidade.class);
        return qry.getResultList();
    }
    
    public List<String> getNomeLocalidades() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.localidade from Localidade s order by s.localidade", String.class);
        return qry.getResultList();
    }
    
    public List<Localidade> getLocalidadePesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Localidade> qry = em.createQuery("select s from Localidade s WHERE s.localidade LIKE '%" + pesq +"%'", Localidade.class);
        return qry.getResultList();
    }
    
    public Localidade getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Localidade> qry = em.createQuery("select s from Localidade s WHERE s.localidade = '" + pesq +"'", Localidade.class);
        return qry.getSingleResult();
    }

    public void create(Localidade localidade) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(localidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLocalidade(localidade.getLocalidadeId()) != null) {
                throw new PreexistingEntityException("Localidade " + localidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Localidade localidade) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            localidade = em.merge(localidade);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = localidade.getLocalidadeId();
                if (findLocalidade(id) == null) {
                    throw new NonexistentEntityException("The localidade with id " + id + " no longer exists.");
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
            Localidade localidade;
            try {
                localidade = em.getReference(Localidade.class, id);
                localidade.getLocalidadeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localidade with id " + id + " no longer exists.", enfe);
            }
            em.remove(localidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Localidade> findLocalidadeEntities() {
        return findLocalidadeEntities(true, -1, -1);
    }

    public List<Localidade> findLocalidadeEntities(int maxResults, int firstResult) {
        return findLocalidadeEntities(false, maxResults, firstResult);
    }

    private List<Localidade> findLocalidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Localidade.class));
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

    public Localidade findLocalidade(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Localidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Localidade> rt = cq.from(Localidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
