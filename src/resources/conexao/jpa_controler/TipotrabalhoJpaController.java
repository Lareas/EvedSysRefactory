package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Tipotrabalho;
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


public class TipotrabalhoJpaController implements Serializable {

    public TipotrabalhoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public List<Tipotrabalho> getTipotrabalhoPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Tipotrabalho> qry = em.createQuery("select s from Tipotrabalho s WHERE s.tipoTrabalho LIKE '%" + pesq +"%'", Tipotrabalho.class);
        return qry.getResultList();
    }
    
    public List<Tipotrabalho> getTipotrabalhoPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Tipotrabalho> qry = em.createQuery("select s from Tipotrabalho s WHERE s.tipoTrabalho = '" + pesq +"'", Tipotrabalho.class);
        return qry.getResultList();
    }
    
    public TipotrabalhoJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public void create(Tipotrabalho tipotrabalho) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipotrabalho);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipotrabalho(tipotrabalho.getTipoTrabalhoId()) != null) {
                throw new PreexistingEntityException("Tipotrabalho " + tipotrabalho + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipotrabalho tipotrabalho) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipotrabalho = em.merge(tipotrabalho);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = tipotrabalho.getTipoTrabalhoId();
                if (findTipotrabalho(id) == null) {
                    throw new NonexistentEntityException("The tipotrabalho with id " + id + " no longer exists.");
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
            Tipotrabalho tipotrabalho;
            try {
                tipotrabalho = em.getReference(Tipotrabalho.class, id);
                tipotrabalho.getTipoTrabalhoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipotrabalho with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipotrabalho);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipotrabalho> findTipotrabalhoEntities() {
        return findTipotrabalhoEntities(true, -1, -1);
    }

    public List<Tipotrabalho> findTipotrabalhoEntities(int maxResults, int firstResult) {
        return findTipotrabalhoEntities(false, maxResults, firstResult);
    }

    private List<Tipotrabalho> findTipotrabalhoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipotrabalho.class));
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

    public Tipotrabalho findTipotrabalho(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipotrabalho.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipotrabalhoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipotrabalho> rt = cq.from(Tipotrabalho.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
