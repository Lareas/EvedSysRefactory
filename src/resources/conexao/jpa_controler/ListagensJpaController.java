/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.Listagens;
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
public class ListagensJpaController implements Serializable {

    public ListagensJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public ListagensJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Listagens listagens) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listagens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<String> getDescricoes(Integer pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<String> qry = em.createQuery("select s.descricao from Listagens s WHERE s.tipo = " + pesq + " order by s.codListagem ", String.class);
        return qry.getResultList();
    }
    
    public Listagens getObjeto(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Listagens> qry = em.createQuery("select s from Listagens s WHERE s.descricao = '" + pesq + "'", Listagens.class);
        return qry.getSingleResult();
    }

    public void edit(Listagens listagens) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listagens = em.merge(listagens);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = listagens.getCodListagem();
                if (findListagens(id) == null) {
                    throw new NonexistentEntityException("The listagens with id " + id + " no longer exists.");
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
            Listagens listagens;
            try {
                listagens = em.getReference(Listagens.class, id);
                listagens.getCodListagem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listagens with id " + id + " no longer exists.", enfe);
            }
            em.remove(listagens);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Listagens> findListagensEntities() {
        return findListagensEntities(true, -1, -1);
    }

    public List<Listagens> findListagensEntities(int maxResults, int firstResult) {
        return findListagensEntities(false, maxResults, firstResult);
    }

    private List<Listagens> findListagensEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Listagens.class));
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

    public Listagens findListagens(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Listagens.class, id);
        } finally {
            em.close();
        }
    }

    public int getListagensCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Listagens> rt = cq.from(Listagens.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
