/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.ListaCabec;
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
public class ListaCabecJpaController implements Serializable {

    public ListaCabecJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ListaCabecJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    
    public List<ListaCabec> getCadDis(int cadDis) {
        EntityManager em;
        em = getEntityManager();
        String sql = "select s from ListaCabec s WHERE s.nomeCadDisId.cadastroDisciplinaId = " + cadDis ;
        System.out.println(sql);
        TypedQuery<ListaCabec> qry = em.createQuery(sql, ListaCabec.class);
        return qry.getResultList();
    }
    
    public void create(ListaCabec listaCabec) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaCabec);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaCabec listaCabec) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaCabec = em.merge(listaCabec);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = listaCabec.getCodLista();
                if (findListaCabec(id) == null) {
                    throw new NonexistentEntityException("The listaCabec with id " + id + " no longer exists.");
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
            ListaCabec listaCabec;
            try {
                listaCabec = em.getReference(ListaCabec.class, id);
                listaCabec.getCodLista();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaCabec with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaCabec);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaCabec> findListaCabecEntities() {
        return findListaCabecEntities(true, -1, -1);
    }

    public List<ListaCabec> findListaCabecEntities(int maxResults, int firstResult) {
        return findListaCabecEntities(false, maxResults, firstResult);
    }

    private List<ListaCabec> findListaCabecEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaCabec.class));
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

    public ListaCabec findListaCabec(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaCabec.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaCabecCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaCabec> rt = cq.from(ListaCabec.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
