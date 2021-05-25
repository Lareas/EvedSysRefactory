package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Disciplina;
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

public class DisciplinaJpaController implements Serializable {

    public DisciplinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DisciplinaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<String> getNomeDasDisci() {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.disciplina from Disciplina s order by s.disciplina", String.class);
        return qry.getResultList();
    }
    
    public Short getCodigoDisci(String nomeDis) {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<Short> qry = em.createQuery("select s.disciplinaId from Disciplina s WHERE s.disciplina = '" + nomeDis +"'", Short.class);
        return qry.getSingleResult();
    }
    
    public String getAreaDis(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s.especificidade from Disciplina s WHERE s.disciplina = '" + pesq + "'";
        TypedQuery<String> qry = em.createQuery(ssql, String.class);
        return qry.getSingleResult();
    }
    
    public Disciplina getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Disciplina s WHERE s.disciplina = '" + pesq + "'";
        TypedQuery<Disciplina> qry = em.createQuery(ssql, Disciplina.class);
        return qry.getSingleResult();
    }
    
    public void create(Disciplina disciplina) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(disciplina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDisciplina(disciplina.getDisciplinaId()) != null) {
                throw new PreexistingEntityException("Disciplina " + disciplina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Disciplina> getPesquisaPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Disciplina> qry = em.createQuery("select s from Disciplina s WHERE s.disciplina LIKE '%" + pesq +"%'", Disciplina.class);
        return qry.getResultList();
    }
    
    public List<Disciplina> getPesquisaPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Disciplina> qry = em.createQuery("select s from Disciplina s WHERE s.disciplina = '" + pesq +"'", Disciplina.class);
        return qry.getResultList();
    }
    

    public void edit(Disciplina disciplina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            disciplina = em.merge(disciplina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = disciplina.getDisciplinaId();
                if (findDisciplina(id) == null) {
                    throw new NonexistentEntityException("The disciplina with id " + id + " no longer exists.");
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
            Disciplina disciplina;
            try {
                disciplina = em.getReference(Disciplina.class, id);
                disciplina.getDisciplinaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplina with id " + id + " no longer exists.", enfe);
            }
            em.remove(disciplina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Disciplina> findDisciplinaEntities() {
        return findDisciplinaEntities(true, -1, -1);
    }

    public List<Disciplina> findDisciplinaEntities(int maxResults, int firstResult) {
        return findDisciplinaEntities(false, maxResults, firstResult);
    }

    private List<Disciplina> findDisciplinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Disciplina.class));
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

    public Disciplina findDisciplina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disciplina.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disciplina> rt = cq.from(Disciplina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
     

}
