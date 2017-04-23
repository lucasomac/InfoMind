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
import com.infomind2.model.Membro;
import com.infomind2.model.Responsabilidade;
import com.infomind2.model.ResponsabilidadePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lucas
 */
public class ResponsabilidadeJpaController implements Serializable {

    public ResponsabilidadeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Responsabilidade responsabilidade) throws PreexistingEntityException, Exception {
        if (responsabilidade.getResponsabilidadePK() == null) {
            responsabilidade.setResponsabilidadePK(new ResponsabilidadePK());
        }
        responsabilidade.getResponsabilidadePK().setIdIgrejaResp(responsabilidade.getIgreja().getIdIgreja());
        responsabilidade.getResponsabilidadePK().setIdMembroResp(responsabilidade.getMembro().getIdMembro());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Igreja igreja = responsabilidade.getIgreja();
            if (igreja != null) {
                igreja = em.getReference(igreja.getClass(), igreja.getIdIgreja());
                responsabilidade.setIgreja(igreja);
            }
            Membro membro = responsabilidade.getMembro();
            if (membro != null) {
                membro = em.getReference(membro.getClass(), membro.getIdMembro());
                responsabilidade.setMembro(membro);
            }
            em.persist(responsabilidade);
            if (igreja != null) {
                igreja.getResponsabilidadeList().add(responsabilidade);
                igreja = em.merge(igreja);
            }
            if (membro != null) {
                membro.getResponsabilidadeList().add(responsabilidade);
                membro = em.merge(membro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResponsabilidade(responsabilidade.getResponsabilidadePK()) != null) {
                throw new PreexistingEntityException("Responsabilidade " + responsabilidade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Responsabilidade responsabilidade) throws NonexistentEntityException, Exception {
        responsabilidade.getResponsabilidadePK().setIdIgrejaResp(responsabilidade.getIgreja().getIdIgreja());
        responsabilidade.getResponsabilidadePK().setIdMembroResp(responsabilidade.getMembro().getIdMembro());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsabilidade persistentResponsabilidade = em.find(Responsabilidade.class, responsabilidade.getResponsabilidadePK());
            Igreja igrejaOld = persistentResponsabilidade.getIgreja();
            Igreja igrejaNew = responsabilidade.getIgreja();
            Membro membroOld = persistentResponsabilidade.getMembro();
            Membro membroNew = responsabilidade.getMembro();
            if (igrejaNew != null) {
                igrejaNew = em.getReference(igrejaNew.getClass(), igrejaNew.getIdIgreja());
                responsabilidade.setIgreja(igrejaNew);
            }
            if (membroNew != null) {
                membroNew = em.getReference(membroNew.getClass(), membroNew.getIdMembro());
                responsabilidade.setMembro(membroNew);
            }
            responsabilidade = em.merge(responsabilidade);
            if (igrejaOld != null && !igrejaOld.equals(igrejaNew)) {
                igrejaOld.getResponsabilidadeList().remove(responsabilidade);
                igrejaOld = em.merge(igrejaOld);
            }
            if (igrejaNew != null && !igrejaNew.equals(igrejaOld)) {
                igrejaNew.getResponsabilidadeList().add(responsabilidade);
                igrejaNew = em.merge(igrejaNew);
            }
            if (membroOld != null && !membroOld.equals(membroNew)) {
                membroOld.getResponsabilidadeList().remove(responsabilidade);
                membroOld = em.merge(membroOld);
            }
            if (membroNew != null && !membroNew.equals(membroOld)) {
                membroNew.getResponsabilidadeList().add(responsabilidade);
                membroNew = em.merge(membroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ResponsabilidadePK id = responsabilidade.getResponsabilidadePK();
                if (findResponsabilidade(id) == null) {
                    throw new NonexistentEntityException("The responsabilidade with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ResponsabilidadePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsabilidade responsabilidade;
            try {
                responsabilidade = em.getReference(Responsabilidade.class, id);
                responsabilidade.getResponsabilidadePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The responsabilidade with id " + id + " no longer exists.", enfe);
            }
            Igreja igreja = responsabilidade.getIgreja();
            if (igreja != null) {
                igreja.getResponsabilidadeList().remove(responsabilidade);
                igreja = em.merge(igreja);
            }
            Membro membro = responsabilidade.getMembro();
            if (membro != null) {
                membro.getResponsabilidadeList().remove(responsabilidade);
                membro = em.merge(membro);
            }
            em.remove(responsabilidade);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Responsabilidade> findResponsabilidadeEntities() {
        return findResponsabilidadeEntities(true, -1, -1);
    }

    public List<Responsabilidade> findResponsabilidadeEntities(int maxResults, int firstResult) {
        return findResponsabilidadeEntities(false, maxResults, firstResult);
    }

    private List<Responsabilidade> findResponsabilidadeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Responsabilidade.class));
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

    public Responsabilidade findResponsabilidade(ResponsabilidadePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Responsabilidade.class, id);
        } finally {
            em.close();
        }
    }

    public int getResponsabilidadeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Responsabilidade> rt = cq.from(Responsabilidade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
