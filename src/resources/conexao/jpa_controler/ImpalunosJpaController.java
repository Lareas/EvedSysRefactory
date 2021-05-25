/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Impalunos;
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

/**
 *
 * @author luiza
 */
public class ImpalunosJpaController implements Serializable {

    public ImpalunosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ImpalunosJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Impalunos impalunos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(impalunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<String> getDescricoes() {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.descricao from Impalunos s order by s.codimpressao", String.class);
        return qry.getResultList();
    }
    
    public Impalunos getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Impalunos> qry = em.createQuery("select s from Impalunos s WHERE s.descricao = '" + pesq + "'", Impalunos.class);
        return qry.getSingleResult();
    }

    public void edit(Impalunos impalunos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            impalunos = em.merge(impalunos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = impalunos.getCodimpressao();
                if (findImpalunos(id) == null) {
                    throw new NonexistentEntityException("The impalunos with id " + id + " no longer exists.");
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
            Impalunos impalunos;
            try {
                impalunos = em.getReference(Impalunos.class, id);
                impalunos.getCodimpressao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The impalunos with id " + id + " no longer exists.", enfe);
            }
            em.remove(impalunos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Impalunos> findImpalunosEntities() {
        return findImpalunosEntities(true, -1, -1);
    }

    public List<Impalunos> findImpalunosEntities(int maxResults, int firstResult) {
        return findImpalunosEntities(false, maxResults, firstResult);
    }

    private List<Impalunos> findImpalunosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Impalunos.class));
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

    public Impalunos findImpalunos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Impalunos.class, id);
        } finally {
            em.close();
        }
    }

    public int getImpalunosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Impalunos> rt = cq.from(Impalunos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
