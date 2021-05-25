package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Especificidade;
import entities.Programa;
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

public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ProgramaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    
    public List<Programa> getProgramas() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Programa> qry = em.createQuery("select s from Programa s", Programa.class);
        return qry.getResultList();
    }
    
    public List<Programa> getProgramasOrd() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Programa> qry = em.createQuery("select s from Programa s order by s.programa", Programa.class);
        return qry.getResultList();
    }
    
    public List<String> getNomeDosProgramas() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.programa from Programa s order by s.programa", String.class);
        return qry.getResultList();
    }

    public List<Programa> getProgramasPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Programa> qry = em.createQuery("select s from Programa s WHERE s.programa LIKE '%" + pesq + "%'", Programa.class);
        return qry.getResultList();
    }

    public List<Programa> getProgramasPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Programa> qry = em.createQuery("select s from Programa s WHERE s.programa = '" + pesq + "'", Programa.class);
        return qry.getResultList();
    }
    
    public Programa getProgramasPesqUmExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Programa> qry = em.createQuery("select s from Programa s WHERE s.programa = '" + pesq + "'", Programa.class);
        return qry.getSingleResult();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(programa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getProgramaId()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            programa = em.merge(programa);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = programa.getProgramaId();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
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
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getProgramaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
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

    private List<Programa> minhaPesquisa(String pesq) {
        EntityManager em = getEntityManager();
        List<Programa> resultado = null;
        try {
            resultado = (List<Programa>) em.createNamedQuery("Programa.minhaPesquisa")
                    .setParameter("pesq", pesq)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public Programa findPrograma(Short id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Programa.class,
                    id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
