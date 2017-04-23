/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.cotrol;

import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.model.Culto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infomind2.model.Igreja;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas
 */
public class CultoJpaController implements Serializable {

    public CultoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InfoMind");;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Culto culto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Igreja idIgrejaCulto = culto.getIdIgrejaCulto();
            if (idIgrejaCulto != null) {
                idIgrejaCulto = em.getReference(idIgrejaCulto.getClass(), idIgrejaCulto.getIdIgreja());
                culto.setIdIgrejaCulto(idIgrejaCulto);
            }
            em.persist(culto);
            if (idIgrejaCulto != null) {
                idIgrejaCulto.getCultoList().add(culto);
                idIgrejaCulto = em.merge(idIgrejaCulto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Culto culto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Culto persistentCulto = em.find(Culto.class, culto.getIdCulto());
            Igreja idIgrejaCultoOld = persistentCulto.getIdIgrejaCulto();
            Igreja idIgrejaCultoNew = culto.getIdIgrejaCulto();
            if (idIgrejaCultoNew != null) {
                idIgrejaCultoNew = em.getReference(idIgrejaCultoNew.getClass(), idIgrejaCultoNew.getIdIgreja());
                culto.setIdIgrejaCulto(idIgrejaCultoNew);
            }
            culto = em.merge(culto);
            if (idIgrejaCultoOld != null && !idIgrejaCultoOld.equals(idIgrejaCultoNew)) {
                idIgrejaCultoOld.getCultoList().remove(culto);
                idIgrejaCultoOld = em.merge(idIgrejaCultoOld);
            }
            if (idIgrejaCultoNew != null && !idIgrejaCultoNew.equals(idIgrejaCultoOld)) {
                idIgrejaCultoNew.getCultoList().add(culto);
                idIgrejaCultoNew = em.merge(idIgrejaCultoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = culto.getIdCulto();
                if (findCulto(id) == null) {
                    throw new NonexistentEntityException("The culto with id " + id + " no longer exists.");
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
            Culto culto;
            try {
                culto = em.getReference(Culto.class, id);
                culto.getIdCulto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The culto with id " + id + " no longer exists.", enfe);
            }
            Igreja idIgrejaCulto = culto.getIdIgrejaCulto();
            if (idIgrejaCulto != null) {
                idIgrejaCulto.getCultoList().remove(culto);
                idIgrejaCulto = em.merge(idIgrejaCulto);
            }
            em.remove(culto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Culto> findCultoEntities() {
        return findCultoEntities(true, -1, -1);
    }

    public List<Culto> findCultoEntities(int maxResults, int firstResult) {
        return findCultoEntities(false, maxResults, firstResult);
    }

    private List<Culto> findCultoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Culto.class));
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

    public Culto findCulto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Culto.class, id);
        } finally {
            em.close();
        }
    }

    public int getCultoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Culto> rt = cq.from(Culto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Culto> findConsultaGeral(Date dataCelebracao, Date horario) {
        List<Culto> cultos = null;
        EntityManager em = getEntityManager();
        try {
            cultos = em.createQuery("SELECT cu FROM Culto cu WHERE cu.dataCelebracao = :dataCelebracao AND cu.horario = :horario")
                    .setParameter("dataCelebracao", dataCelebracao).setParameter("horario", horario).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            em.close();
        }
        return cultos;
    }
}
