/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.cotrol;

import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.cotrol.exceptions.PreexistingEntityException;
import com.infomind2.model.Discipulado;
import com.infomind2.model.DiscipuladoPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infomind2.model.Membro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lucas
 */
public class DiscipuladoJpaController implements Serializable {

    public DiscipuladoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Discipulado discipulado) throws PreexistingEntityException, Exception {
        if (discipulado.getDiscipuladoPK() == null) {
            discipulado.setDiscipuladoPK(new DiscipuladoPK());
        }
        discipulado.getDiscipuladoPK().setIdMembroDiscipulador(discipulado.getMembro().getIdMembro());
        discipulado.getDiscipuladoPK().setIdMembroDiscipulo(discipulado.getMembro1().getIdMembro());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membro membro = discipulado.getMembro();
            if (membro != null) {
                membro = em.getReference(membro.getClass(), membro.getIdMembro());
                discipulado.setMembro(membro);
            }
            Membro membro1 = discipulado.getMembro1();
            if (membro1 != null) {
                membro1 = em.getReference(membro1.getClass(), membro1.getIdMembro());
                discipulado.setMembro1(membro1);
            }
            em.persist(discipulado);
            if (membro != null) {
                membro.getDiscipuladoList().add(discipulado);
                membro = em.merge(membro);
            }
            if (membro1 != null) {
                membro1.getDiscipuladoList().add(discipulado);
                membro1 = em.merge(membro1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiscipulado(discipulado.getDiscipuladoPK()) != null) {
                throw new PreexistingEntityException("Discipulado " + discipulado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Discipulado discipulado) throws NonexistentEntityException, Exception {
        discipulado.getDiscipuladoPK().setIdMembroDiscipulador(discipulado.getMembro().getIdMembro());
        discipulado.getDiscipuladoPK().setIdMembroDiscipulo(discipulado.getMembro1().getIdMembro());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Discipulado persistentDiscipulado = em.find(Discipulado.class, discipulado.getDiscipuladoPK());
            Membro membroOld = persistentDiscipulado.getMembro();
            Membro membroNew = discipulado.getMembro();
            Membro membro1Old = persistentDiscipulado.getMembro1();
            Membro membro1New = discipulado.getMembro1();
            if (membroNew != null) {
                membroNew = em.getReference(membroNew.getClass(), membroNew.getIdMembro());
                discipulado.setMembro(membroNew);
            }
            if (membro1New != null) {
                membro1New = em.getReference(membro1New.getClass(), membro1New.getIdMembro());
                discipulado.setMembro1(membro1New);
            }
            discipulado = em.merge(discipulado);
            if (membroOld != null && !membroOld.equals(membroNew)) {
                membroOld.getDiscipuladoList().remove(discipulado);
                membroOld = em.merge(membroOld);
            }
            if (membroNew != null && !membroNew.equals(membroOld)) {
                membroNew.getDiscipuladoList().add(discipulado);
                membroNew = em.merge(membroNew);
            }
            if (membro1Old != null && !membro1Old.equals(membro1New)) {
                membro1Old.getDiscipuladoList().remove(discipulado);
                membro1Old = em.merge(membro1Old);
            }
            if (membro1New != null && !membro1New.equals(membro1Old)) {
                membro1New.getDiscipuladoList().add(discipulado);
                membro1New = em.merge(membro1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DiscipuladoPK id = discipulado.getDiscipuladoPK();
                if (findDiscipulado(id) == null) {
                    throw new NonexistentEntityException("The discipulado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DiscipuladoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Discipulado discipulado;
            try {
                discipulado = em.getReference(Discipulado.class, id);
                discipulado.getDiscipuladoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The discipulado with id " + id + " no longer exists.", enfe);
            }
            Membro membro = discipulado.getMembro();
            if (membro != null) {
                membro.getDiscipuladoList().remove(discipulado);
                membro = em.merge(membro);
            }
            Membro membro1 = discipulado.getMembro1();
            if (membro1 != null) {
                membro1.getDiscipuladoList().remove(discipulado);
                membro1 = em.merge(membro1);
            }
            em.remove(discipulado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Discipulado> findDiscipuladoEntities() {
        return findDiscipuladoEntities(true, -1, -1);
    }

    public List<Discipulado> findDiscipuladoEntities(int maxResults, int firstResult) {
        return findDiscipuladoEntities(false, maxResults, firstResult);
    }

    private List<Discipulado> findDiscipuladoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Discipulado.class));
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

    public Discipulado findDiscipulado(DiscipuladoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Discipulado.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiscipuladoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Discipulado> rt = cq.from(Discipulado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
