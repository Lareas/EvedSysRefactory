package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Funcionario;
import entities.ProfessoresGrid;
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


public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FuncionarioJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<String> getNomeDosProfessores() {
        EntityManager em;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.nome from Funcionario s WHERE s.professor = 1 order by s.nome ", String.class);
        return qry.getResultList();
    }
    
    public List<Funcionario> getProfGrid() {
        EntityManager em;
        em = getEntityManager();
        System.out.println("select s from Funcionario s WHERE s.professor = 1 order by s.nome ");
        TypedQuery<Funcionario> qry = em.createQuery("select s.nome from Funcionario s WHERE s.professor = 1 order by s.nome ", Funcionario.class);
        return qry.getResultList();
    }
    
    public List<Funcionario> getProfsPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Funcionario> qry = em.createQuery("select s from Funcionario s WHERE s.nome LIKE '%" + pesq + "%' AND s.professor = 1", Funcionario.class);
        return qry.getResultList();
    }
    
    public Funcionario getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Funcionario s WHERE s.nome = '" + pesq + "'";
        TypedQuery<Funcionario> qry = em.createQuery(ssql, Funcionario.class);
        return qry.getSingleResult();
    }
    
    
    public void create(Funcionario funcionario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(funcionario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFuncionario(funcionario.getFuncionarioId()) != null) {
                throw new PreexistingEntityException("Funcionario " + funcionario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            funcionario = em.merge(funcionario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = funcionario.getFuncionarioId();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getFuncionarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
