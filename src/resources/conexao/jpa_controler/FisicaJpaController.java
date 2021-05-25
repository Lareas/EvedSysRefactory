package jpa_controler;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Fisica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import jpa_controler.exceptions.NonexistentEntityException;
import jpa_controler.exceptions.PreexistingEntityException;

public class FisicaJpaController implements Serializable {

    public FisicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FisicaJpaController() {
        emf = Persistence.createEntityManagerFactory("persistence-sserver");
    }

    public List<Fisica> getFisicaPesq(String pesq) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Fisica> qry = em.createQuery("select s from Fisica s WHERE s.nome LIKE '%" + pesq + "%'", Fisica.class);
        return qry.getResultList();
    }
    
    

    public Long getContaObjetoViaCodext(String codExt) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select count(s) from Fisica s WHERE s.codext = '" + codExt + "'";
        TypedQuery<Long> qry = em.createQuery(ssql, Long.class);
        return qry.getSingleResult();
    }

    public Long getContaObjetoViaNome(String nome) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select count(s) from Fisica s WHERE s.nome = \'" + nome + "\'";
        TypedQuery<Long> qry = em.createQuery(ssql, Long.class);
        return qry.getSingleResult();
    }

    public Long getContaObjetoViaCpf(String cpf) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select count(s) from Fisica s WHERE s.cpf = '" + cpf + "'";
        TypedQuery<Long> qry = em.createQuery(ssql, Long.class);
        return qry.getSingleResult();
    }
    
    

    public Fisica getObjetoViaCodext(String codExt) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Fisica s WHERE s.codext = '" + codExt + "'";
        TypedQuery<Fisica> qry = em.createQuery(ssql, Fisica.class);
        return qry.getSingleResult();
    }
    
    public Fisica getObjetoViaNome(String nome) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Fisica s WHERE s.nome = '" + nome + "'";
        TypedQuery<Fisica> qry = em.createQuery(ssql, Fisica.class);
        return qry.getSingleResult();
    }
    
    public Fisica getObjetoViaCodCpf(String cpf) {
        EntityManager em = null;
        em = getEntityManager();
        String ssql = "select s from Fisica s WHERE s.cpf = '" + cpf + "'";
        TypedQuery<Fisica> qry = em.createQuery(ssql, Fisica.class);
        return qry.getSingleResult();
    }
    
    
    

    public List<Fisica> getListCodExt(String codExt) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Fisica> qry = em.createQuery("select s from Fisica s WHERE s.codext = '" + codExt + "'", Fisica.class);
        return qry.getResultList();
    }

    public List<Fisica> getListNome(String nome) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Fisica> qry = em.createQuery("select s from Fisica s WHERE s.nome = '" + nome + "'", Fisica.class);
        return qry.getResultList();
    }

    public List<Fisica> getListCpf(String cpf) {
        EntityManager em = null;
        em = getEntityManager();
        TypedQuery<Fisica> qry = em.createQuery("select s from Fisica s WHERE s.cpf = '" + cpf + "'", Fisica.class);
        return qry.getResultList();
    }

    
    
    
    public void create(Fisica fisica) throws PreexistingEntityException, Exception {
        if (fisica.getFisicaCollection() == null) {
            fisica.setFisicaCollection(new ArrayList<Fisica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fisica idfkFisicaEnderecoIgual = fisica.getIdfkFisicaEnderecoIgual();
            if (idfkFisicaEnderecoIgual != null) {
                idfkFisicaEnderecoIgual = em.getReference(idfkFisicaEnderecoIgual.getClass(), idfkFisicaEnderecoIgual.getCodigo());
                fisica.setIdfkFisicaEnderecoIgual(idfkFisicaEnderecoIgual);
            }
            Collection<Fisica> attachedFisicaCollection = new ArrayList<Fisica>();
            for (Fisica fisicaCollectionFisicaToAttach : fisica.getFisicaCollection()) {
                fisicaCollectionFisicaToAttach = em.getReference(fisicaCollectionFisicaToAttach.getClass(), fisicaCollectionFisicaToAttach.getCodigo());
                attachedFisicaCollection.add(fisicaCollectionFisicaToAttach);
            }
            fisica.setFisicaCollection(attachedFisicaCollection);
            em.persist(fisica);
            if (idfkFisicaEnderecoIgual != null) {
                idfkFisicaEnderecoIgual.getFisicaCollection().add(fisica);
                idfkFisicaEnderecoIgual = em.merge(idfkFisicaEnderecoIgual);
            }
            for (Fisica fisicaCollectionFisica : fisica.getFisicaCollection()) {
                Fisica oldIdfkFisicaEnderecoIgualOfFisicaCollectionFisica = fisicaCollectionFisica.getIdfkFisicaEnderecoIgual();
                fisicaCollectionFisica.setIdfkFisicaEnderecoIgual(fisica);
                fisicaCollectionFisica = em.merge(fisicaCollectionFisica);
                if (oldIdfkFisicaEnderecoIgualOfFisicaCollectionFisica != null) {
                    oldIdfkFisicaEnderecoIgualOfFisicaCollectionFisica.getFisicaCollection().remove(fisicaCollectionFisica);
                    oldIdfkFisicaEnderecoIgualOfFisicaCollectionFisica = em.merge(oldIdfkFisicaEnderecoIgualOfFisicaCollectionFisica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFisica(fisica.getCodigo()) != null) {
                throw new PreexistingEntityException("Fisica " + fisica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fisica fisica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fisica persistentFisica = em.find(Fisica.class, fisica.getCodigo());
            Fisica idfkFisicaEnderecoIgualOld = persistentFisica.getIdfkFisicaEnderecoIgual();
            Fisica idfkFisicaEnderecoIgualNew = fisica.getIdfkFisicaEnderecoIgual();
            Collection<Fisica> fisicaCollectionOld = persistentFisica.getFisicaCollection();
            Collection<Fisica> fisicaCollectionNew = fisica.getFisicaCollection();
            if (idfkFisicaEnderecoIgualNew != null) {
                idfkFisicaEnderecoIgualNew = em.getReference(idfkFisicaEnderecoIgualNew.getClass(), idfkFisicaEnderecoIgualNew.getCodigo());
                fisica.setIdfkFisicaEnderecoIgual(idfkFisicaEnderecoIgualNew);
            }
            Collection<Fisica> attachedFisicaCollectionNew = new ArrayList<Fisica>();
            for (Fisica fisicaCollectionNewFisicaToAttach : fisicaCollectionNew) {
                fisicaCollectionNewFisicaToAttach = em.getReference(fisicaCollectionNewFisicaToAttach.getClass(), fisicaCollectionNewFisicaToAttach.getCodigo());
                attachedFisicaCollectionNew.add(fisicaCollectionNewFisicaToAttach);
            }
            fisicaCollectionNew = attachedFisicaCollectionNew;
            fisica.setFisicaCollection(fisicaCollectionNew);
            fisica = em.merge(fisica);
            if (idfkFisicaEnderecoIgualOld != null && !idfkFisicaEnderecoIgualOld.equals(idfkFisicaEnderecoIgualNew)) {
                idfkFisicaEnderecoIgualOld.getFisicaCollection().remove(fisica);
                idfkFisicaEnderecoIgualOld = em.merge(idfkFisicaEnderecoIgualOld);
            }
            if (idfkFisicaEnderecoIgualNew != null && !idfkFisicaEnderecoIgualNew.equals(idfkFisicaEnderecoIgualOld)) {
                idfkFisicaEnderecoIgualNew.getFisicaCollection().add(fisica);
                idfkFisicaEnderecoIgualNew = em.merge(idfkFisicaEnderecoIgualNew);
            }
            for (Fisica fisicaCollectionOldFisica : fisicaCollectionOld) {
                if (!fisicaCollectionNew.contains(fisicaCollectionOldFisica)) {
                    fisicaCollectionOldFisica.setIdfkFisicaEnderecoIgual(null);
                    fisicaCollectionOldFisica = em.merge(fisicaCollectionOldFisica);
                }
            }
            for (Fisica fisicaCollectionNewFisica : fisicaCollectionNew) {
                if (!fisicaCollectionOld.contains(fisicaCollectionNewFisica)) {
                    Fisica oldIdfkFisicaEnderecoIgualOfFisicaCollectionNewFisica = fisicaCollectionNewFisica.getIdfkFisicaEnderecoIgual();
                    fisicaCollectionNewFisica.setIdfkFisicaEnderecoIgual(fisica);
                    fisicaCollectionNewFisica = em.merge(fisicaCollectionNewFisica);
                    if (oldIdfkFisicaEnderecoIgualOfFisicaCollectionNewFisica != null && !oldIdfkFisicaEnderecoIgualOfFisicaCollectionNewFisica.equals(fisica)) {
                        oldIdfkFisicaEnderecoIgualOfFisicaCollectionNewFisica.getFisicaCollection().remove(fisicaCollectionNewFisica);
                        oldIdfkFisicaEnderecoIgualOfFisicaCollectionNewFisica = em.merge(oldIdfkFisicaEnderecoIgualOfFisicaCollectionNewFisica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fisica.getCodigo();
                if (findFisica(id) == null) {
                    throw new NonexistentEntityException("The fisica with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

//    public void destroy(Integer id) throws NonexistentEntityException {
//        EntityManager em = null;
//        try {
//            em = getEntityManager();
//            em.getTransaction().begin();
//            Fisica fisica;
//            try {
//                fisica = em.getReference(Fisica.class, id);
//                fisica.getCodigo();
//            } catch (EntityNotFoundException enfe) {
//                throw new NonexistentEntityException("The fisica with id " + id + " no longer exists.", enfe);
//            }
//            Fisica idfkFisicaEnderecoIgual = fisica.getIdfkFisicaEnderecoIgual();
//            if (idfkFisicaEnderecoIgual != null) {
//                idfkFisicaEnderecoIgual.getFisicaCollection().remove(fisica);
//                idfkFisicaEnderecoIgual = em.merge(idfkFisicaEnderecoIgual);
//            }
//            Collection<Fisica> fisicaCollection = fisica.getFisicaCollection();
//            for (Fisica fisicaCollectionFisica : fisicaCollection) {
//                fisicaCollectionFisica.setIdfkFisicaEnderecoIgual(null);
//                fisicaCollectionFisica = em.merge(fisicaCollectionFisica);
//            }
//            em.remove(fisica);
//            em.getTransaction().commit();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
//    }
    public List<Fisica> findFisicaEntities() {
        return findFisicaEntities(true, -1, -1);
    }

    public List<Fisica> findFisicaEntities(int maxResults, int firstResult) {
        return findFisicaEntities(false, maxResults, firstResult);
    }

    private List<Fisica> findFisicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fisica.class));
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

    public Fisica findFisica(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fisica.class, id);
        } finally {
            em.close();
        }
    }

    public int getFisicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fisica> rt = cq.from(Fisica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
