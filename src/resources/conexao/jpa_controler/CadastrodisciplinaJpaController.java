package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Cadastrodisciplina;
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

public class CadastrodisciplinaJpaController implements Serializable {

    public CadastrodisciplinaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CadastrodisciplinaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<String> getNomeDasDisci(int codGrade) {
        EntityManager em;
        em = getEntityManager();
        String sql = "select s.descricao from Cadastrodisciplina s WHERE s.nomeGrade.codGrade = " + codGrade + " order by s.nomeDisciplina.disciplina";
        TypedQuery<String> qry = em.createQuery(sql, String.class);
        return qry.getResultList();
    }

    public Cadastrodisciplina getObjetoCodGrade(int codGrade, String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Cadastrodisciplina s WHERE s.nomeGrade.codGrade = " + codGrade + " AND s.disciplina = '" + pesq + "'";
        System.out.println(ssql);
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(ssql, Cadastrodisciplina.class);
        return qry.getSingleResult();
    }

    public Cadastrodisciplina getObjetoCodGradeByDescricao(int codGrade, String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Cadastrodisciplina s WHERE s.nomeGrade.codGrade = " + codGrade + " AND s.descricao = '" + pesq + "'";
        System.out.println(ssql);
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(ssql, Cadastrodisciplina.class);
        return qry.getSingleResult();
    }

    // método para recuperar a disciplina dentro da grade.
    // Le grade, cadastrodisciplina e professor - não pode haver duplicatas
    public Cadastrodisciplina getDisUnica(int codGrade, int codDisciplina, int codProf1) {
        EntityManager em = null;
        em = getEntityManager();
        String sql = "select s from Cadastrodisciplina s WHERE s.nomeGrade.codGrade = " + codGrade
                + " AND s.nomeDisciplina.disciplinaId = " + codDisciplina
                + " AND s.nomeProfessor1.funcionarioId = " + codProf1;
        System.out.println(sql);
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(sql, Cadastrodisciplina.class);
        return qry.getSingleResult();
    }

    public List<Cadastrodisciplina> getVerifJaExiste(Integer codGrade, Integer cad_dis) {
        EntityManager em = getEntityManager();
        String ssql = "select s from Cadastrodisciplina s WHERE s.cadastroDisciplinaId = " + cad_dis + " AND s.nomeGrade.codGrade = " + codGrade;
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(ssql, Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public List<Cadastrodisciplina> getJaTemDescri(String descricao, Integer codGrade) {
        EntityManager em = getEntityManager();
        String ssql = "select s from Cadastrodisciplina s WHERE s.nomeDisciplina.descricao = '" + descricao + "'" + " AND s.nomeGrade.codGrade = " + codGrade;
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(ssql, Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public void create(Cadastrodisciplina dis) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(dis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCadastrodisciplina(dis.getCadastroDisciplinaId()) != null) {
                throw new PreexistingEntityException("Cadastrodisciplina " + dis + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cadastrodisciplina> getMeuCadDis() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cadastrodisciplina> qry = em.createQuery("select s from Cadastrodisciplina s", Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public List<Cadastrodisciplina> getMeuCadDisPes(int codDisc, String filtro) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Cadastrodisciplina s WHERE s.nomeDisciplina.disciplinaId = " + codDisc + filtro;
        System.out.println("\n" + ssql + "\n");
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(ssql, Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public List<Cadastrodisciplina> getMeuCadDisFil(String filtro) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Cadastrodisciplina s " + filtro;
        System.out.println("\n" + ssql + "\n");
        TypedQuery<Cadastrodisciplina> qry = em.createQuery(ssql, Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public List<Cadastrodisciplina> getCadastrodisciplinaPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cadastrodisciplina> qry = em.createQuery("select s from Cadastrodisciplina s WHERE s.nomeDisciplina.disciplina LIKE '%" + pesq + "%'", Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public List<Cadastrodisciplina> getCadastrodisciplinaPesqExac(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Cadastrodisciplina> qry = em.createQuery("select s from Cadastrodisciplina s WHERE s.nomeDisciplina.disciplina = '" + pesq + "'", Cadastrodisciplina.class);
        return qry.getResultList();
    }

    public List<Integer> getAnosDistinct(int pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Integer> qry = em.createQuery("SELECT DISTINCT s.anoLetivo FROM Cadastrodisciplina s WHERE s.nomeDisciplina.disciplinaId = " + pesq + " ORDER BY s.anoLetivo", Integer.class);
        return qry.getResultList();
    }

    public void edit(Cadastrodisciplina dis) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            dis = em.merge(dis);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = dis.getCadastroDisciplinaId();
                if (findCadastrodisciplina(id) == null) {
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

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cadastrodisciplina dis;
            try {
                dis = em.getReference(Cadastrodisciplina.class, id);
                dis.getCadastroDisciplinaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplina with id " + id + " no longer exists.", enfe);
            }
            em.remove(dis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cadastrodisciplina> findCadastrodisciplinaEntities() {
        return findCadastrodisciplinaEntities(true, -1, -1);
    }

    public List<Cadastrodisciplina> findCadastrodisciplinaEntities(int maxResults, int firstResult) {
        return findCadastrodisciplinaEntities(false, maxResults, firstResult);
    }

    private List<Cadastrodisciplina> findCadastrodisciplinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cadastrodisciplina.class));
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

    public Cadastrodisciplina findCadastrodisciplina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cadastrodisciplina.class, id);
        } finally {
            em.close();
        }
    }

    public int getCadastrodisciplinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cadastrodisciplina> rt = cq.from(Cadastrodisciplina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
