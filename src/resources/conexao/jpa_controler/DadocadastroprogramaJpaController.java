package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Dadocadastroprograma;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;
import jpa_controler.exceptions.PreexistingEntityException;

public class DadocadastroprogramaJpaController implements Serializable {

    public DadocadastroprogramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public DadocadastroprogramaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<Dadocadastroprograma> getCursosExistentes(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Dadocadastroprograma> qry = em.createQuery("select s from Dadocadastroprograma s WHERE s.nomeCurso.curso LIKE '%" + pesq + "%'", Dadocadastroprograma.class);
        return qry.getResultList();
    }

    public List<Dadocadastroprograma> getProgramasAluno(String programa, int codALuno) {
        EntityManager em = null;
        em = getEntityManager();
        String sql = "select s from Dadocadastroprograma s WHERE s.nomeCurso.curso = '" + programa
                + "' AND s.dadoCadastroGeralId = " + codALuno;
        System.out.println(sql);
        
        TypedQuery<Dadocadastroprograma> qry = em.createQuery(sql, Dadocadastroprograma.class);
        return qry.getResultList();
    }

    public List<Dadocadastroprograma> getCursoExato(String pesq, int codaluno) {

        EntityManager em = null;
        em = getEntityManager();
        String sql = "select s from Dadocadastroprograma s WHERE s.nomeCurso.curso = '" + pesq
                + "' AND s.dadoCadastroProgramaId = " + codaluno
                + " AND s.nomeSituacao.dadoCadastroProgramaSituacao != 'Ativo'";
        System.out.println(sql);
        TypedQuery<Dadocadastroprograma> qry = em.createQuery(sql, Dadocadastroprograma.class);
        return qry.getResultList();

    }

    public void create(Dadocadastroprograma dadocadastroprograma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dadocadastroprograma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

//    public void create(Dadocadastroprograma dadocadastroprograma) throws PreexistingEntityException, Exception {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            em.persist(dadocadastroprograma);
//            em.getTransaction().commit();
//        } catch (Exception ex) {
//            if (findDadocadastroprograma(dadocadastroprograma.getDadoCadastroProgramaId()) != null) {
//                throw new PreexistingEntityException("Dadocadastroprograma " + dadocadastroprograma + " already exists.", ex);
//            }
//            throw ex;
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
    public void edit(Dadocadastroprograma dadocadastroprograma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dadocadastroprograma = em.merge(dadocadastroprograma);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dadocadastroprograma.getDadoCadastroProgramaId();
                if (findDadocadastroprograma(id) == null) {
                    throw new NonexistentEntityException("The dadocadastroprograma with id " + id + " no longer exists.");
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
            Dadocadastroprograma dadocadastroprograma;

            try {
                dadocadastroprograma = em.getReference(Dadocadastroprograma.class,
                        id);
                dadocadastroprograma.getDadoCadastroProgramaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dadocadastroprograma with id " + id + " no longer exists.", enfe);
            }
            em.remove(dadocadastroprograma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dadocadastroprograma> findDadocadastroprogramaEntities() {
        return findDadocadastroprogramaEntities(true, -1, -1);
    }

    public List<Dadocadastroprograma> findDadocadastroprogramaEntities(int maxResults, int firstResult) {
        return findDadocadastroprogramaEntities(false, maxResults, firstResult);
    }

    private List<Dadocadastroprograma> findDadocadastroprogramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq
                    .select(cq.from(Dadocadastroprograma.class
                    ));
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

    public Dadocadastroprograma findDadocadastroprograma(Integer id) {
        EntityManager em = getEntityManager();

        try {
            return em.find(Dadocadastroprograma.class,
                    id);
        } finally {
            em.close();
        }
    }

    public int getDadocadastroprogramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dadocadastroprograma> rt = cq.from(Dadocadastroprograma.class
            );
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
