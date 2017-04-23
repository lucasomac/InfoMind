/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.cotrol;

import com.infomind2.cotrol.exceptions.IllegalOrphanException;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import com.infomind2.model.Celula;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infomind2.model.Membro;
import com.infomind2.model.Porte;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas
 */
public class CelulaJpaController implements Serializable {

    public CelulaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InfoMind");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Celula celula) {
        if (celula.getPorteList() == null) {
            celula.setPorteList(new ArrayList<Porte>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membro idMembroLider = celula.getIdMembroLider();
            if (idMembroLider != null) {
                idMembroLider = em.getReference(idMembroLider.getClass(), idMembroLider.getIdMembro());
                celula.setIdMembroLider(idMembroLider);
            }
            List<Porte> attachedPorteList = new ArrayList<Porte>();
            for (Porte porteListPorteToAttach : celula.getPorteList()) {
                porteListPorteToAttach = em.getReference(porteListPorteToAttach.getClass(), porteListPorteToAttach.getPortePK());
                attachedPorteList.add(porteListPorteToAttach);
            }
            celula.setPorteList(attachedPorteList);
            em.persist(celula);
            if (idMembroLider != null) {
                idMembroLider.getCelulaList().add(celula);
                idMembroLider = em.merge(idMembroLider);
            }
            for (Porte porteListPorte : celula.getPorteList()) {
                Celula oldCelulaOfPorteListPorte = porteListPorte.getCelula();
                porteListPorte.setCelula(celula);
                porteListPorte = em.merge(porteListPorte);
                if (oldCelulaOfPorteListPorte != null) {
                    oldCelulaOfPorteListPorte.getPorteList().remove(porteListPorte);
                    oldCelulaOfPorteListPorte = em.merge(oldCelulaOfPorteListPorte);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Celula celula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Celula persistentCelula = em.find(Celula.class, celula.getIdCelula());
            Membro idMembroLiderOld = persistentCelula.getIdMembroLider();
            Membro idMembroLiderNew = celula.getIdMembroLider();
            List<Porte> porteListOld = persistentCelula.getPorteList();
            List<Porte> porteListNew = celula.getPorteList();
            List<String> illegalOrphanMessages = null;
            for (Porte porteListOldPorte : porteListOld) {
                if (!porteListNew.contains(porteListOldPorte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Porte " + porteListOldPorte + " since its celula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idMembroLiderNew != null) {
                idMembroLiderNew = em.getReference(idMembroLiderNew.getClass(), idMembroLiderNew.getIdMembro());
                celula.setIdMembroLider(idMembroLiderNew);
            }
            List<Porte> attachedPorteListNew = new ArrayList<Porte>();
            for (Porte porteListNewPorteToAttach : porteListNew) {
                porteListNewPorteToAttach = em.getReference(porteListNewPorteToAttach.getClass(), porteListNewPorteToAttach.getPortePK());
                attachedPorteListNew.add(porteListNewPorteToAttach);
            }
            porteListNew = attachedPorteListNew;
            celula.setPorteList(porteListNew);
            celula = em.merge(celula);
            if (idMembroLiderOld != null && !idMembroLiderOld.equals(idMembroLiderNew)) {
                idMembroLiderOld.getCelulaList().remove(celula);
                idMembroLiderOld = em.merge(idMembroLiderOld);
            }
            if (idMembroLiderNew != null && !idMembroLiderNew.equals(idMembroLiderOld)) {
                idMembroLiderNew.getCelulaList().add(celula);
                idMembroLiderNew = em.merge(idMembroLiderNew);
            }
            for (Porte porteListNewPorte : porteListNew) {
                if (!porteListOld.contains(porteListNewPorte)) {
                    Celula oldCelulaOfPorteListNewPorte = porteListNewPorte.getCelula();
                    porteListNewPorte.setCelula(celula);
                    porteListNewPorte = em.merge(porteListNewPorte);
                    if (oldCelulaOfPorteListNewPorte != null && !oldCelulaOfPorteListNewPorte.equals(celula)) {
                        oldCelulaOfPorteListNewPorte.getPorteList().remove(porteListNewPorte);
                        oldCelulaOfPorteListNewPorte = em.merge(oldCelulaOfPorteListNewPorte);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = celula.getIdCelula();
                if (findCelula(id) == null) {
                    throw new NonexistentEntityException("The celula with id " + id + " no longer exists.");
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
            Celula celula;
            try {
                celula = em.getReference(Celula.class, id);
                celula.getIdCelula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The celula with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Porte> porteListOrphanCheck = celula.getPorteList();
            for (Porte porteListOrphanCheckPorte : porteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Celula (" + celula + ") cannot be destroyed since the Porte " + porteListOrphanCheckPorte + " in its porteList field has a non-nullable celula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Membro idMembroLider = celula.getIdMembroLider();
            if (idMembroLider != null) {
                idMembroLider.getCelulaList().remove(celula);
                idMembroLider = em.merge(idMembroLider);
            }
            em.remove(celula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Celula> findCelulaEntities() {
        return findCelulaEntities(true, -1, -1);
    }

    public List<Celula> findCelulaEntities(int maxResults, int firstResult) {
        return findCelulaEntities(false, maxResults, firstResult);
    }

    private List<Celula> findCelulaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Celula.class));
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

    public Celula findCelula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Celula.class, id);
        } finally {
            em.close();
        }
    }

    public int getCelulaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Celula> rt = cq.from(Celula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Celula> findConsultaGeral(String diaRealizacao, Date horario, String cidadeCelula, String estadoCelula, String status) {
        List<Celula> celulas = null;
        EntityManager em = getEntityManager();
        try {
            celulas = em.createQuery("SELECT ce FROM Celula ce WHERE ce.diaRealizacao like :diaRealizacao AND ce.horario = :horario AND ce.cidadeCelula like :cidadeCelula AND ce.estadoCelula = :estadoCelula and ce.status = :status")
                    .setParameter("diaRealizacao", diaRealizacao).setParameter("horario", horario).setParameter("cidadeCelula", cidadeCelula)
                    .setParameter("estadoCelula", estadoCelula).setParameter("status", status).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return celulas;
    }
}
