/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.cotrol;

import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.cotrol.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infomind2.model.Igreja;
import com.infomind2.model.Membresia;
import com.infomind2.model.MembresiaPK;
import com.infomind2.model.Membro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lucas
 */
public class MembresiaJpaController implements Serializable {

    public MembresiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Membresia membresia) throws PreexistingEntityException, Exception {
        if (membresia.getMembresiaPK() == null) {
            membresia.setMembresiaPK(new MembresiaPK());
        }
        membresia.getMembresiaPK().setIdMembroIgreja(membresia.getMembro().getIdMembro());
        membresia.getMembresiaPK().setIdIgrejaMembro(membresia.getIgreja().getIdIgreja());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Igreja igreja = membresia.getIgreja();
            if (igreja != null) {
                igreja = em.getReference(igreja.getClass(), igreja.getIdIgreja());
                membresia.setIgreja(igreja);
            }
            Membro membro = membresia.getMembro();
            if (membro != null) {
                membro = em.getReference(membro.getClass(), membro.getIdMembro());
                membresia.setMembro(membro);
            }
            em.persist(membresia);
            if (igreja != null) {
                igreja.getMembresiaList().add(membresia);
                igreja = em.merge(igreja);
            }
            if (membro != null) {
                membro.getMembresiaList().add(membresia);
                membro = em.merge(membro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMembresia(membresia.getMembresiaPK()) != null) {
                throw new PreexistingEntityException("Membresia " + membresia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Membresia membresia) throws NonexistentEntityException, Exception {
        membresia.getMembresiaPK().setIdMembroIgreja(membresia.getMembro().getIdMembro());
        membresia.getMembresiaPK().setIdIgrejaMembro(membresia.getIgreja().getIdIgreja());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membresia persistentMembresia = em.find(Membresia.class, membresia.getMembresiaPK());
            Igreja igrejaOld = persistentMembresia.getIgreja();
            Igreja igrejaNew = membresia.getIgreja();
            Membro membroOld = persistentMembresia.getMembro();
            Membro membroNew = membresia.getMembro();
            if (igrejaNew != null) {
                igrejaNew = em.getReference(igrejaNew.getClass(), igrejaNew.getIdIgreja());
                membresia.setIgreja(igrejaNew);
            }
            if (membroNew != null) {
                membroNew = em.getReference(membroNew.getClass(), membroNew.getIdMembro());
                membresia.setMembro(membroNew);
            }
            membresia = em.merge(membresia);
            if (igrejaOld != null && !igrejaOld.equals(igrejaNew)) {
                igrejaOld.getMembresiaList().remove(membresia);
                igrejaOld = em.merge(igrejaOld);
            }
            if (igrejaNew != null && !igrejaNew.equals(igrejaOld)) {
                igrejaNew.getMembresiaList().add(membresia);
                igrejaNew = em.merge(igrejaNew);
            }
            if (membroOld != null && !membroOld.equals(membroNew)) {
                membroOld.getMembresiaList().remove(membresia);
                membroOld = em.merge(membroOld);
            }
            if (membroNew != null && !membroNew.equals(membroOld)) {
                membroNew.getMembresiaList().add(membresia);
                membroNew = em.merge(membroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MembresiaPK id = membresia.getMembresiaPK();
                if (findMembresia(id) == null) {
                    throw new NonexistentEntityException("The membresia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MembresiaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membresia membresia;
            try {
                membresia = em.getReference(Membresia.class, id);
                membresia.getMembresiaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The membresia with id " + id + " no longer exists.", enfe);
            }
            Igreja igreja = membresia.getIgreja();
            if (igreja != null) {
                igreja.getMembresiaList().remove(membresia);
                igreja = em.merge(igreja);
            }
            Membro membro = membresia.getMembro();
            if (membro != null) {
                membro.getMembresiaList().remove(membresia);
                membro = em.merge(membro);
            }
            em.remove(membresia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Membresia> findMembresiaEntities() {
        return findMembresiaEntities(true, -1, -1);
    }

    public List<Membresia> findMembresiaEntities(int maxResults, int firstResult) {
        return findMembresiaEntities(false, maxResults, firstResult);
    }

    private List<Membresia> findMembresiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Membresia.class));
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

    public Membresia findMembresia(MembresiaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Membresia.class, id);
        } finally {
            em.close();
        }
    }

    public int getMembresiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Membresia> rt = cq.from(Membresia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
