/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa_controler;

import static main.Login.gbDeOnde;
import entities.ListaAula;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import jpa_controler.exceptions.NonexistentEntityException;

/**
 *
 * @author ticoa
 */
public class ListaAulaJpaController implements Serializable {

    public ListaAulaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ListaAulaJpaController() {
        emf = Persistence.createEntityManagerFactory(gbDeOnde);
    }
    
    public List<ListaAula> getAulasLC(int codLista) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<ListaAula> qry = em.createQuery("select s from ListaAula s WHERE s.codLista = " + codLista + " ORDER BY s.codLau", ListaAula.class);
        return qry.getResultList();
    }
    
//    public List<ListaAula> getDataDia(Date dataAula) {
//    EntityManager emf = null;
//    emf.createQuery("SELECT s " +
//                   "FROM ListaAula s " +
//                   "WHERE s.dataAula = :datadia")
//      .setParameter("datadia", dataAula, TemporalType.DATE)
//      .getResultList();
//    }
//    
//    public List<ListaAula> getDataDia(Date dataAula) {
//        EntityManager em = null;
//        em = getEntityManager();
//        TypedQuery<ListaAula> qry = em.createQuery("select s from ListaAula s WHERE s.dataAula = " + dataAula, ListaAula.class);
//        return qry.getResultList();
//    }

    public void create(ListaAula listaAula) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaAula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaAula listaAula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaAula = em.merge(listaAula);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = listaAula.getCodLau();
                if (findListaAula(id) == null) {
                    throw new NonexistentEntityException("The listaAula with id " + id + " no longer exists.");
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
            ListaAula listaAula;
            try {
                listaAula = em.getReference(ListaAula.class, id);
                listaAula.getCodLau();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaAula with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaAula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaAula> findListaAulaEntities() {
        return findListaAulaEntities(true, -1, -1);
    }

    public List<ListaAula> findListaAulaEntities(int maxResults, int firstResult) {
        return findListaAulaEntities(false, maxResults, firstResult);
    }

    private List<ListaAula> findListaAulaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaAula.class));
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

    public ListaAula findListaAula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaAula.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaAulaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaAula> rt = cq.from(ListaAula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
