package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Estadocivil;
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

public class EstadocivilJpaController implements Serializable {


    public EstadocivilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EstadocivilJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public Estadocivil getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Estadocivil> qry = em.createQuery("select s from Estadocivil s WHERE s.estadoCivil = '" + pesq + "'", Estadocivil.class);
        return qry.getSingleResult();
    }
    

    public void create(Estadocivil estadocivil) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadocivil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadocivil(estadocivil.getEstadoCivilId()) != null) {
                throw new PreexistingEntityException("Estadocivil " + estadocivil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadocivil> getEstadocivilPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Estadocivil> qry = em.createQuery("select s from Estadocivil s WHERE s.estadoCivil LIKE '%" + pesq + "%'", Estadocivil.class);
        return qry.getResultList();
    }
    
    public List<Estadocivil> getEstadocivilPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Estadocivil> qry = em.createQuery("select s from Estadocivil s WHERE s.estadoCivil = '" + pesq + "'", Estadocivil.class);
        return qry.getResultList();
    }

    
    public void edit(Estadocivil estadocivil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadocivil = em.merge(estadocivil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = estadocivil.getEstadoCivilId();
                if (findEstadocivil(id) == null) {
                    throw new NonexistentEntityException("The estadocivil with id " + id + " no longer exists.");
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
            Estadocivil estadocivil;
            try {
                estadocivil = em.getReference(Estadocivil.class, id);
                estadocivil.getEstadoCivilId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadocivil with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadocivil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Estadocivil getEstCivUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Estadocivil> qry = em.createQuery("select s from Estadocivil s WHERE s.estadoCivil = '" + pesq + "'", Estadocivil.class);
        return qry.getSingleResult();
    }
    
    public List<String> getNomeDosEstadosCivis() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.estadoCivil from Estadocivil s order by s.estadoCivil", String.class);
        return qry.getResultList();
    }

    public List<Estadocivil> findEstadocivilEntities() {
        return findEstadocivilEntities(true, -1, -1);
    }

    public List<Estadocivil> findEstadocivilEntities(int maxResults, int firstResult) {
        return findEstadocivilEntities(false, maxResults, firstResult);
    }

    private List<Estadocivil> findEstadocivilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadocivil.class));
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

    public Estadocivil findEstadocivil(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadocivil.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadocivilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadocivil> rt = cq.from(Estadocivil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
