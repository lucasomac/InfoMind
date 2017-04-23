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
import com.infomind2.model.Cargo;
import com.infomind2.model.Igreja;
import com.infomind2.model.Membro;
import com.infomind2.model.Posse;
import com.infomind2.model.PossePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lucas
 */
public class PosseJpaController implements Serializable {

    public PosseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Posse posse) throws PreexistingEntityException, Exception {
        if (posse.getPossePK() == null) {
            posse.setPossePK(new PossePK());
        }
        posse.getPossePK().setIdMembroPosse(posse.getMembro().getIdMembro());
        posse.getPossePK().setIdCargoPosse(posse.getCargo().getIdCargo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo = posse.getCargo();
            if (cargo != null) {
                cargo = em.getReference(cargo.getClass(), cargo.getIdCargo());
                posse.setCargo(cargo);
            }
            Igreja idIgrejaPosse = posse.getIdIgrejaPosse();
            if (idIgrejaPosse != null) {
                idIgrejaPosse = em.getReference(idIgrejaPosse.getClass(), idIgrejaPosse.getIdIgreja());
                posse.setIdIgrejaPosse(idIgrejaPosse);
            }
            Membro membro = posse.getMembro();
            if (membro != null) {
                membro = em.getReference(membro.getClass(), membro.getIdMembro());
                posse.setMembro(membro);
            }
            em.persist(posse);
            if (cargo != null) {
                cargo.getPosseList().add(posse);
                cargo = em.merge(cargo);
            }
            if (idIgrejaPosse != null) {
                idIgrejaPosse.getPosseList().add(posse);
                idIgrejaPosse = em.merge(idIgrejaPosse);
            }
            if (membro != null) {
                membro.getPosseList().add(posse);
                membro = em.merge(membro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPosse(posse.getPossePK()) != null) {
                throw new PreexistingEntityException("Posse " + posse + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Posse posse) throws NonexistentEntityException, Exception {
        posse.getPossePK().setIdMembroPosse(posse.getMembro().getIdMembro());
        posse.getPossePK().setIdCargoPosse(posse.getCargo().getIdCargo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posse persistentPosse = em.find(Posse.class, posse.getPossePK());
            Cargo cargoOld = persistentPosse.getCargo();
            Cargo cargoNew = posse.getCargo();
            Igreja idIgrejaPosseOld = persistentPosse.getIdIgrejaPosse();
            Igreja idIgrejaPosseNew = posse.getIdIgrejaPosse();
            Membro membroOld = persistentPosse.getMembro();
            Membro membroNew = posse.getMembro();
            if (cargoNew != null) {
                cargoNew = em.getReference(cargoNew.getClass(), cargoNew.getIdCargo());
                posse.setCargo(cargoNew);
            }
            if (idIgrejaPosseNew != null) {
                idIgrejaPosseNew = em.getReference(idIgrejaPosseNew.getClass(), idIgrejaPosseNew.getIdIgreja());
                posse.setIdIgrejaPosse(idIgrejaPosseNew);
            }
            if (membroNew != null) {
                membroNew = em.getReference(membroNew.getClass(), membroNew.getIdMembro());
                posse.setMembro(membroNew);
            }
            posse = em.merge(posse);
            if (cargoOld != null && !cargoOld.equals(cargoNew)) {
                cargoOld.getPosseList().remove(posse);
                cargoOld = em.merge(cargoOld);
            }
            if (cargoNew != null && !cargoNew.equals(cargoOld)) {
                cargoNew.getPosseList().add(posse);
                cargoNew = em.merge(cargoNew);
            }
            if (idIgrejaPosseOld != null && !idIgrejaPosseOld.equals(idIgrejaPosseNew)) {
                idIgrejaPosseOld.getPosseList().remove(posse);
                idIgrejaPosseOld = em.merge(idIgrejaPosseOld);
            }
            if (idIgrejaPosseNew != null && !idIgrejaPosseNew.equals(idIgrejaPosseOld)) {
                idIgrejaPosseNew.getPosseList().add(posse);
                idIgrejaPosseNew = em.merge(idIgrejaPosseNew);
            }
            if (membroOld != null && !membroOld.equals(membroNew)) {
                membroOld.getPosseList().remove(posse);
                membroOld = em.merge(membroOld);
            }
            if (membroNew != null && !membroNew.equals(membroOld)) {
                membroNew.getPosseList().add(posse);
                membroNew = em.merge(membroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PossePK id = posse.getPossePK();
                if (findPosse(id) == null) {
                    throw new NonexistentEntityException("The posse with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PossePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Posse posse;
            try {
                posse = em.getReference(Posse.class, id);
                posse.getPossePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The posse with id " + id + " no longer exists.", enfe);
            }
            Cargo cargo = posse.getCargo();
            if (cargo != null) {
                cargo.getPosseList().remove(posse);
                cargo = em.merge(cargo);
            }
            Igreja idIgrejaPosse = posse.getIdIgrejaPosse();
            if (idIgrejaPosse != null) {
                idIgrejaPosse.getPosseList().remove(posse);
                idIgrejaPosse = em.merge(idIgrejaPosse);
            }
            Membro membro = posse.getMembro();
            if (membro != null) {
                membro.getPosseList().remove(posse);
                membro = em.merge(membro);
            }
            em.remove(posse);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Posse> findPosseEntities() {
        return findPosseEntities(true, -1, -1);
    }

    public List<Posse> findPosseEntities(int maxResults, int firstResult) {
        return findPosseEntities(false, maxResults, firstResult);
    }

    private List<Posse> findPosseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Posse.class));
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

    public Posse findPosse(PossePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Posse.class, id);
        } finally {
            em.close();
        }
    }

    public int getPosseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Posse> rt = cq.from(Posse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
