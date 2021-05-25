package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Cadastroalunodisciplina;
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

public class CadastroalunodisciplinaJpaController implements Serializable {

    public CadastroalunodisciplinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CadastroalunodisciplinaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<Cadastroalunodisciplina> getDiscMatriculadas(int codGrade, Short codAluno, int cadDis) {
        EntityManager em = null;
        em = getEntityManager();
        String sql = "select s from Cadastroalunodisciplina s " 
                + " WHERE s.codGrade = " + codGrade 
                + " AND s.nomeMatriSem.dadoCadastroGeralId = " + codAluno
                + " AND s.nomeCadDisciplina.cadastroDisciplinaId = " + cadDis;
            //    + " GROUP BY s.codgrade, s.nomeMatriSem.dadoCadastroGeralId, s.nomeCadDisciplina.nomeDisciplina.cadastroDisciplinaId ";
        System.out.println(sql);
        TypedQuery<Cadastroalunodisciplina> qry = em.createQuery(sql, Cadastroalunodisciplina.class);
        return qry.getResultList();
    }
    
    public Long contaGravacoes(int codAluno) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select count(s) from Cadastroalunodisciplina s JOIN Dadocadastroprograma d ON s.dadoCadastroProgramaId = d.dadoCadastroProgramaId  " 
                + " WHERE s.tipoRgOuv = 'G' AND d.dadoCadastroGeralId = " + codAluno;
        System.out.println(ssql);
        TypedQuery<Long> qry = em.createQuery(ssql, Long.class);
        return qry.getSingleResult();
    }
    
    public List<Cadastroalunodisciplina> getContaAlunosPup(int cadDis) {
        EntityManager em = null;
        em = getEntityManager();
        String sql = "select s from Cadastroalunodisciplina s " 
                + " WHERE s.nomeCadDisciplina.cadastroDisciplinaId = " + cadDis;
        System.out.println(sql);
        TypedQuery<Cadastroalunodisciplina> qry = em.createQuery(sql, Cadastroalunodisciplina.class);
        return qry.getResultList();
    }
    
    public void create(Cadastroalunodisciplina cadastroalunodisciplina) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cadastroalunodisciplina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCadastroalunodisciplina(cadastroalunodisciplina.getCadastroAlunoDisciplinaId()) != null) {
                throw new PreexistingEntityException("Cadastroalunodisciplina " + cadastroalunodisciplina + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cadastroalunodisciplina cadastroalunodisciplina) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            cadastroalunodisciplina = em.merge(cadastroalunodisciplina);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cadastroalunodisciplina.getCadastroAlunoDisciplinaId();
                if (findCadastroalunodisciplina(id) == null) {
                    throw new NonexistentEntityException("The cadastroalunodisciplina with id " + id + " no longer exists.");
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
            Cadastroalunodisciplina cadastroalunodisciplina;
            try {
                cadastroalunodisciplina = em.getReference(Cadastroalunodisciplina.class, id);
                cadastroalunodisciplina.getCadastroAlunoDisciplinaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cadastroalunodisciplina with id " + id + " no longer exists.", enfe);
            }
            em.remove(cadastroalunodisciplina);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cadastroalunodisciplina> findCadastroalunodisciplinaEntities() {
        return findCadastroalunodisciplinaEntities(true, -1, -1);
    }

    public List<Cadastroalunodisciplina> findCadastroalunodisciplinaEntities(int maxResults, int firstResult) {
        return findCadastroalunodisciplinaEntities(false, maxResults, firstResult);
    }

    private List<Cadastroalunodisciplina> findCadastroalunodisciplinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cadastroalunodisciplina.class));
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

    public Cadastroalunodisciplina findCadastroalunodisciplina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cadastroalunodisciplina.class, id);
        } finally {
            em.close();
        }
    }

    public int getCadastroalunodisciplinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cadastroalunodisciplina> rt = cq.from(Cadastroalunodisciplina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
