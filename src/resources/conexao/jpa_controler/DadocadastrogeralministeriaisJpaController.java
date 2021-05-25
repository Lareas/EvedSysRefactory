package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Dadocadastrogeralministeriais;
import funcoes.MyFunc;
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

public class DadocadastrogeralministeriaisJpaController implements Serializable {

    public DadocadastrogeralministeriaisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DadocadastrogeralministeriaisJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<Dadocadastrogeralministeriais> getDadocadastrogeralministeriaisPesqExac(Integer codAluno, String ministerio) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastrogeralministeriais> qry = em.createQuery("select s from Dadocadastrogeralministeriais s WHERE s.dadoCadastroGeralId = '" + codAluno + "' AND s.ministeriais = '" + ministerio + "'", Dadocadastrogeralministeriais.class);
        return qry.getResultList();
    }

    public Integer getCodDocMin(Integer codAluno, String ministerio) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s.dadoCadastroGeralMinisteriaisId from Dadocadastrogeralministeriais s WHERE s.dadoCadastroGeralId = '" + codAluno + "' AND s.ministeriais = '" + ministerio + "'";
        TypedQuery<Integer> qry = em.createQuery(ssql, Integer.class);
        return qry.getSingleResult();
    }

    public Integer apagaTemporario(Integer codAlunox) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Query query = em.createNativeQuery("DELETE FROM Dadocadastrogeralministeriais e WHERE e.dadoCadastroGeralId = :codAluno ");
            query.setParameter("codAluno", "5131");
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            return deletedCount;
        } catch (Exception ex) {
            MyFunc.mostraMsg("Erro 20 - Apagando temporários", "" + ex, 2);
            throw ex;
        } finally {
            if (em != null) {
                em.close();
                return 0;
            }
        }
    }

    public List<Dadocadastrogeralministeriais> get00MinNomes(int codAluno) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Dadocadastrogeralministeriais s WHERE s.dadoCadastroGeralId = " + codAluno;// + "'";
        //    System.out.println("\n"+ssql+"\n");
        TypedQuery<Dadocadastrogeralministeriais> qry = em.createQuery(ssql, Dadocadastrogeralministeriais.class);
        return qry.getResultList();
    }

    public void create(Dadocadastrogeralministeriais dadocadastrogeralministeriais) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dadocadastrogeralministeriais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDadocadastrogeralministeriais(dadocadastrogeralministeriais.getDadoCadastroGeralMinisteriaisId()) != null) {
                throw new PreexistingEntityException("Dadocadastrogeralministeriais " + dadocadastrogeralministeriais + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dadocadastrogeralministeriais dadocadastrogeralministeriais) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dadocadastrogeralministeriais = em.merge(dadocadastrogeralministeriais);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dadocadastrogeralministeriais.getDadoCadastroGeralMinisteriaisId();
                if (findDadocadastrogeralministeriais(id) == null) {
                    throw new NonexistentEntityException("The dadocadastrogeralministeriais with id " + id + " no longer exists.");
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
            Dadocadastrogeralministeriais dadocadastrogeralministeriais;
            try {
                dadocadastrogeralministeriais = em.getReference(Dadocadastrogeralministeriais.class, id);
                dadocadastrogeralministeriais.getDadoCadastroGeralMinisteriaisId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dadocadastrogeralministeriais with id " + id + " no longer exists.", enfe);
            }
            em.remove(dadocadastrogeralministeriais);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dadocadastrogeralministeriais> findDadocadastrogeralministeriaisEntities() {
        return findDadocadastrogeralministeriaisEntities(true, -1, -1);
    }

    public List<Dadocadastrogeralministeriais> findDadocadastrogeralministeriaisEntities(int maxResults, int firstResult) {
        return findDadocadastrogeralministeriaisEntities(false, maxResults, firstResult);
    }

    private List<Dadocadastrogeralministeriais> findDadocadastrogeralministeriaisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dadocadastrogeralministeriais.class));
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

    public Dadocadastrogeralministeriais findDadocadastrogeralministeriais(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dadocadastrogeralministeriais.class, id);
        } finally {
            em.close();
        }
    }

    public int getDadocadastrogeralministeriaisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dadocadastrogeralministeriais> rt = cq.from(Dadocadastrogeralministeriais.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

//    public List<Dadocadastrogeralministeriais> getNomeMeusMinisterios(int codAluno) {
//        EntityManager em = null;
//        em = getEntityManager();
//        TypedQuery<Dadocadastrogeralministeriais> qry = em.createQuery("select s.ministeriais from Dadocadastrogeralministeriais s WHERE s.dadoCadastroGeralId = " + codAluno + " order by s.ministeriais ", Dadocadastrogeralministeriais.class);
//        return qry.getResultList();
//    }
    public List<Dadocadastrogeralministeriais> getNomeMeusMinisterios(int codAluno) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastrogeralministeriais> qry = em.createQuery("select s.ministeriais from Dadocadastrogeralministeriais ", Dadocadastrogeralministeriais.class);
        return qry.getResultList();
    }

    public List<Dadocadastrogeralministeriais> get00Min(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastrogeralministeriais> qry = em.createQuery("select s from Dadocadastrogeralministeriais ", Dadocadastrogeralministeriais.class);
        return qry.getResultList();
    }

    public List<String> get00Min1() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.ministeriais from Dadocadastrogeralministeriais s order by s.ministeriais", String.class);
        return qry.getResultList();
    }

}
