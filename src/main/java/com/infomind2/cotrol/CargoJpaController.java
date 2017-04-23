/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.cotrol;

import com.infomind2.cotrol.exceptions.IllegalOrphanException;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.model.Cargo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infomind2.model.Posse;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas
 */
public class CargoJpaController implements Serializable {

    public CargoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InfoMind");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cargo cargo) {
        if (cargo.getPosseList() == null) {
            cargo.setPosseList(new ArrayList<Posse>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Posse> attachedPosseList = new ArrayList<Posse>();
            for (Posse posseListPosseToAttach : cargo.getPosseList()) {
                posseListPosseToAttach = em.getReference(posseListPosseToAttach.getClass(), posseListPosseToAttach.getPossePK());
                attachedPosseList.add(posseListPosseToAttach);
            }
            cargo.setPosseList(attachedPosseList);
            em.persist(cargo);
            for (Posse posseListPosse : cargo.getPosseList()) {
                Cargo oldCargoOfPosseListPosse = posseListPosse.getCargo();
                posseListPosse.setCargo(cargo);
                posseListPosse = em.merge(posseListPosse);
                if (oldCargoOfPosseListPosse != null) {
                    oldCargoOfPosseListPosse.getPosseList().remove(posseListPosse);
                    oldCargoOfPosseListPosse = em.merge(oldCargoOfPosseListPosse);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cargo cargo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo persistentCargo = em.find(Cargo.class, cargo.getIdCargo());
            List<Posse> posseListOld = persistentCargo.getPosseList();
            List<Posse> posseListNew = cargo.getPosseList();
            List<String> illegalOrphanMessages = null;
            for (Posse posseListOldPosse : posseListOld) {
                if (!posseListNew.contains(posseListOldPosse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Posse " + posseListOldPosse + " since its cargo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Posse> attachedPosseListNew = new ArrayList<Posse>();
            for (Posse posseListNewPosseToAttach : posseListNew) {
                posseListNewPosseToAttach = em.getReference(posseListNewPosseToAttach.getClass(), posseListNewPosseToAttach.getPossePK());
                attachedPosseListNew.add(posseListNewPosseToAttach);
            }
            posseListNew = attachedPosseListNew;
            cargo.setPosseList(posseListNew);
            cargo = em.merge(cargo);
            for (Posse posseListNewPosse : posseListNew) {
                if (!posseListOld.contains(posseListNewPosse)) {
                    Cargo oldCargoOfPosseListNewPosse = posseListNewPosse.getCargo();
                    posseListNewPosse.setCargo(cargo);
                    posseListNewPosse = em.merge(posseListNewPosse);
                    if (oldCargoOfPosseListNewPosse != null && !oldCargoOfPosseListNewPosse.equals(cargo)) {
                        oldCargoOfPosseListNewPosse.getPosseList().remove(posseListNewPosse);
                        oldCargoOfPosseListNewPosse = em.merge(oldCargoOfPosseListNewPosse);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cargo.getIdCargo();
                if (findCargo(id) == null) {
                    throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cargo cargo;
            try {
                cargo = em.getReference(Cargo.class, id);
                cargo.getIdCargo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cargo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Posse> posseListOrphanCheck = cargo.getPosseList();
            for (Posse posseListOrphanCheckPosse : posseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cargo (" + cargo + ") cannot be destroyed since the Posse " + posseListOrphanCheckPosse + " in its posseList field has a non-nullable cargo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(cargo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cargo> findCargoEntities() {
        return findCargoEntities(true, -1, -1);
    }

    public List<Cargo> findCargoEntities(int maxResults, int firstResult) {
        return findCargoEntities(false, maxResults, firstResult);
    }

    private List<Cargo> findCargoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cargo.class));
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

    public Cargo findCargo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cargo.class, id);
        } finally {
            em.close();
        }
    }

    public int getCargoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cargo> rt = cq.from(Cargo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Cargo> buscaNome(String nome) {
        List<Cargo> cargos = null;
        EntityManager em = getEntityManager();
        try {
            cargos = em.createNamedQuery("Cargo.findByNome").setParameter("nome", nome).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return cargos;
    }
}
