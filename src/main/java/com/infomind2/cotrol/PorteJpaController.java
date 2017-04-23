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
import com.infomind2.model.Celula;
import com.infomind2.model.Igreja;
import com.infomind2.model.Porte;
import com.infomind2.model.PortePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Lucas
 */
public class PorteJpaController implements Serializable {

    public PorteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Porte porte) throws PreexistingEntityException, Exception {
        if (porte.getPortePK() == null) {
            porte.setPortePK(new PortePK());
        }
        porte.getPortePK().setIdCelulaPorte(porte.getCelula().getIdCelula());
        porte.getPortePK().setIdIgrejaPorte(porte.getIgreja().getIdIgreja());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Celula celula = porte.getCelula();
            if (celula != null) {
                celula = em.getReference(celula.getClass(), celula.getIdCelula());
                porte.setCelula(celula);
            }
            Igreja igreja = porte.getIgreja();
            if (igreja != null) {
                igreja = em.getReference(igreja.getClass(), igreja.getIdIgreja());
                porte.setIgreja(igreja);
            }
            em.persist(porte);
            if (celula != null) {
                celula.getPorteList().add(porte);
                celula = em.merge(celula);
            }
            if (igreja != null) {
                igreja.getPorteList().add(porte);
                igreja = em.merge(igreja);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPorte(porte.getPortePK()) != null) {
                throw new PreexistingEntityException("Porte " + porte + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Porte porte) throws NonexistentEntityException, Exception {
        porte.getPortePK().setIdCelulaPorte(porte.getCelula().getIdCelula());
        porte.getPortePK().setIdIgrejaPorte(porte.getIgreja().getIdIgreja());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Porte persistentPorte = em.find(Porte.class, porte.getPortePK());
            Celula celulaOld = persistentPorte.getCelula();
            Celula celulaNew = porte.getCelula();
            Igreja igrejaOld = persistentPorte.getIgreja();
            Igreja igrejaNew = porte.getIgreja();
            if (celulaNew != null) {
                celulaNew = em.getReference(celulaNew.getClass(), celulaNew.getIdCelula());
                porte.setCelula(celulaNew);
            }
            if (igrejaNew != null) {
                igrejaNew = em.getReference(igrejaNew.getClass(), igrejaNew.getIdIgreja());
                porte.setIgreja(igrejaNew);
            }
            porte = em.merge(porte);
            if (celulaOld != null && !celulaOld.equals(celulaNew)) {
                celulaOld.getPorteList().remove(porte);
                celulaOld = em.merge(celulaOld);
            }
            if (celulaNew != null && !celulaNew.equals(celulaOld)) {
                celulaNew.getPorteList().add(porte);
                celulaNew = em.merge(celulaNew);
            }
            if (igrejaOld != null && !igrejaOld.equals(igrejaNew)) {
                igrejaOld.getPorteList().remove(porte);
                igrejaOld = em.merge(igrejaOld);
            }
            if (igrejaNew != null && !igrejaNew.equals(igrejaOld)) {
                igrejaNew.getPorteList().add(porte);
                igrejaNew = em.merge(igrejaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PortePK id = porte.getPortePK();
                if (findPorte(id) == null) {
                    throw new NonexistentEntityException("The porte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PortePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Porte porte;
            try {
                porte = em.getReference(Porte.class, id);
                porte.getPortePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The porte with id " + id + " no longer exists.", enfe);
            }
            Celula celula = porte.getCelula();
            if (celula != null) {
                celula.getPorteList().remove(porte);
                celula = em.merge(celula);
            }
            Igreja igreja = porte.getIgreja();
            if (igreja != null) {
                igreja.getPorteList().remove(porte);
                igreja = em.merge(igreja);
            }
            em.remove(porte);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Porte> findPorteEntities() {
        return findPorteEntities(true, -1, -1);
    }

    public List<Porte> findPorteEntities(int maxResults, int firstResult) {
        return findPorteEntities(false, maxResults, firstResult);
    }

    private List<Porte> findPorteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Porte.class));
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

    public Porte findPorte(PortePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Porte.class, id);
        } finally {
            em.close();
        }
    }

    public int getPorteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Porte> rt = cq.from(Porte.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
