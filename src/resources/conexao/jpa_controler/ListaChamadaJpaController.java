/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.ListaChamada;
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
 * @author ticoa
 */
public class ListaChamadaJpaController implements Serializable {

    public ListaChamadaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ListaChamadaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }

    public List<ListaChamada> pegaChamadaDia(Integer codListaAula) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from ListaChamada s WHERE s.codListaAula = " + codListaAula;
        System.out.println(ssql);
        TypedQuery<ListaChamada> qry = em.createQuery(ssql, ListaChamada.class);
        return qry.getResultList();
    }
    
    public void create(ListaChamada listaChamada) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaChamada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaChamada listaChamada) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaChamada = em.merge(listaChamada);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = listaChamada.getCodListaChamada();
                if (findListaChamada(id) == null) {
                    throw new NonexistentEntityException("The listaChamada with id " + id + " no longer exists.");
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
            ListaChamada listaChamada;
            try {
                listaChamada = em.getReference(ListaChamada.class, id);
                listaChamada.getCodListaChamada();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaChamada with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaChamada);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaChamada> findListaChamadaEntities() {
        return findListaChamadaEntities(true, -1, -1);
    }

    public List<ListaChamada> findListaChamadaEntities(int maxResults, int firstResult) {
        return findListaChamadaEntities(false, maxResults, firstResult);
    }

    private List<ListaChamada> findListaChamadaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaChamada.class));
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

    public ListaChamada findListaChamada(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaChamada.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaChamadaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaChamada> rt = cq.from(ListaChamada.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
