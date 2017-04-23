/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.cotrol;

import com.infomind2.cotrol.exceptions.IllegalOrphanException;
import com.infomind2.cotrol.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.infomind2.model.Posse;
import java.util.ArrayList;
import java.util.List;
import com.infomind2.model.Membresia;
import com.infomind2.model.Porte;
import com.infomind2.model.Responsabilidade;
import com.infomind2.model.Culto;
import com.infomind2.model.Igreja;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas
 */
public class IgrejaJpaController implements Serializable {

    public IgrejaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InfoMind");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Igreja igreja) {
        if (igreja.getPosseList() == null) {
            igreja.setPosseList(new ArrayList<Posse>());
        }
        if (igreja.getMembresiaList() == null) {
            igreja.setMembresiaList(new ArrayList<Membresia>());
        }
        if (igreja.getPorteList() == null) {
            igreja.setPorteList(new ArrayList<Porte>());
        }
        if (igreja.getResponsabilidadeList() == null) {
            igreja.setResponsabilidadeList(new ArrayList<Responsabilidade>());
        }
        if (igreja.getCultoList() == null) {
            igreja.setCultoList(new ArrayList<Culto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Posse> attachedPosseList = new ArrayList<Posse>();
            for (Posse posseListPosseToAttach : igreja.getPosseList()) {
                posseListPosseToAttach = em.getReference(posseListPosseToAttach.getClass(), posseListPosseToAttach.getPossePK());
                attachedPosseList.add(posseListPosseToAttach);
            }
            igreja.setPosseList(attachedPosseList);
            List<Membresia> attachedMembresiaList = new ArrayList<Membresia>();
            for (Membresia membresiaListMembresiaToAttach : igreja.getMembresiaList()) {
                membresiaListMembresiaToAttach = em.getReference(membresiaListMembresiaToAttach.getClass(), membresiaListMembresiaToAttach.getMembresiaPK());
                attachedMembresiaList.add(membresiaListMembresiaToAttach);
            }
            igreja.setMembresiaList(attachedMembresiaList);
            List<Porte> attachedPorteList = new ArrayList<Porte>();
            for (Porte porteListPorteToAttach : igreja.getPorteList()) {
                porteListPorteToAttach = em.getReference(porteListPorteToAttach.getClass(), porteListPorteToAttach.getPortePK());
                attachedPorteList.add(porteListPorteToAttach);
            }
            igreja.setPorteList(attachedPorteList);
            List<Responsabilidade> attachedResponsabilidadeList = new ArrayList<Responsabilidade>();
            for (Responsabilidade responsabilidadeListResponsabilidadeToAttach : igreja.getResponsabilidadeList()) {
                responsabilidadeListResponsabilidadeToAttach = em.getReference(responsabilidadeListResponsabilidadeToAttach.getClass(), responsabilidadeListResponsabilidadeToAttach.getResponsabilidadePK());
                attachedResponsabilidadeList.add(responsabilidadeListResponsabilidadeToAttach);
            }
            igreja.setResponsabilidadeList(attachedResponsabilidadeList);
            List<Culto> attachedCultoList = new ArrayList<Culto>();
            for (Culto cultoListCultoToAttach : igreja.getCultoList()) {
                cultoListCultoToAttach = em.getReference(cultoListCultoToAttach.getClass(), cultoListCultoToAttach.getIdCulto());
                attachedCultoList.add(cultoListCultoToAttach);
            }
            igreja.setCultoList(attachedCultoList);
            em.persist(igreja);
            for (Posse posseListPosse : igreja.getPosseList()) {
                Igreja oldIdIgrejaPosseOfPosseListPosse = posseListPosse.getIdIgrejaPosse();
                posseListPosse.setIdIgrejaPosse(igreja);
                posseListPosse = em.merge(posseListPosse);
                if (oldIdIgrejaPosseOfPosseListPosse != null) {
                    oldIdIgrejaPosseOfPosseListPosse.getPosseList().remove(posseListPosse);
                    oldIdIgrejaPosseOfPosseListPosse = em.merge(oldIdIgrejaPosseOfPosseListPosse);
                }
            }
            for (Membresia membresiaListMembresia : igreja.getMembresiaList()) {
                Igreja oldIgrejaOfMembresiaListMembresia = membresiaListMembresia.getIgreja();
                membresiaListMembresia.setIgreja(igreja);
                membresiaListMembresia = em.merge(membresiaListMembresia);
                if (oldIgrejaOfMembresiaListMembresia != null) {
                    oldIgrejaOfMembresiaListMembresia.getMembresiaList().remove(membresiaListMembresia);
                    oldIgrejaOfMembresiaListMembresia = em.merge(oldIgrejaOfMembresiaListMembresia);
                }
            }
            for (Porte porteListPorte : igreja.getPorteList()) {
                Igreja oldIgrejaOfPorteListPorte = porteListPorte.getIgreja();
                porteListPorte.setIgreja(igreja);
                porteListPorte = em.merge(porteListPorte);
                if (oldIgrejaOfPorteListPorte != null) {
                    oldIgrejaOfPorteListPorte.getPorteList().remove(porteListPorte);
                    oldIgrejaOfPorteListPorte = em.merge(oldIgrejaOfPorteListPorte);
                }
            }
            for (Responsabilidade responsabilidadeListResponsabilidade : igreja.getResponsabilidadeList()) {
                Igreja oldIgrejaOfResponsabilidadeListResponsabilidade = responsabilidadeListResponsabilidade.getIgreja();
                responsabilidadeListResponsabilidade.setIgreja(igreja);
                responsabilidadeListResponsabilidade = em.merge(responsabilidadeListResponsabilidade);
                if (oldIgrejaOfResponsabilidadeListResponsabilidade != null) {
                    oldIgrejaOfResponsabilidadeListResponsabilidade.getResponsabilidadeList().remove(responsabilidadeListResponsabilidade);
                    oldIgrejaOfResponsabilidadeListResponsabilidade = em.merge(oldIgrejaOfResponsabilidadeListResponsabilidade);
                }
            }
            for (Culto cultoListCulto : igreja.getCultoList()) {
                Igreja oldIdIgrejaCultoOfCultoListCulto = cultoListCulto.getIdIgrejaCulto();
                cultoListCulto.setIdIgrejaCulto(igreja);
                cultoListCulto = em.merge(cultoListCulto);
                if (oldIdIgrejaCultoOfCultoListCulto != null) {
                    oldIdIgrejaCultoOfCultoListCulto.getCultoList().remove(cultoListCulto);
                    oldIdIgrejaCultoOfCultoListCulto = em.merge(oldIdIgrejaCultoOfCultoListCulto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Igreja igreja) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Igreja persistentIgreja = em.find(Igreja.class, igreja.getIdIgreja());
            List<Posse> posseListOld = persistentIgreja.getPosseList();
            List<Posse> posseListNew = igreja.getPosseList();
            List<Membresia> membresiaListOld = persistentIgreja.getMembresiaList();
            List<Membresia> membresiaListNew = igreja.getMembresiaList();
            List<Porte> porteListOld = persistentIgreja.getPorteList();
            List<Porte> porteListNew = igreja.getPorteList();
            List<Responsabilidade> responsabilidadeListOld = persistentIgreja.getResponsabilidadeList();
            List<Responsabilidade> responsabilidadeListNew = igreja.getResponsabilidadeList();
            List<Culto> cultoListOld = persistentIgreja.getCultoList();
            List<Culto> cultoListNew = igreja.getCultoList();
            List<String> illegalOrphanMessages = null;
            for (Posse posseListOldPosse : posseListOld) {
                if (!posseListNew.contains(posseListOldPosse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Posse " + posseListOldPosse + " since its idIgrejaPosse field is not nullable.");
                }
            }
            for (Membresia membresiaListOldMembresia : membresiaListOld) {
                if (!membresiaListNew.contains(membresiaListOldMembresia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Membresia " + membresiaListOldMembresia + " since its igreja field is not nullable.");
                }
            }
            for (Porte porteListOldPorte : porteListOld) {
                if (!porteListNew.contains(porteListOldPorte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Porte " + porteListOldPorte + " since its igreja field is not nullable.");
                }
            }
            for (Responsabilidade responsabilidadeListOldResponsabilidade : responsabilidadeListOld) {
                if (!responsabilidadeListNew.contains(responsabilidadeListOldResponsabilidade)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Responsabilidade " + responsabilidadeListOldResponsabilidade + " since its igreja field is not nullable.");
                }
            }
            for (Culto cultoListOldCulto : cultoListOld) {
                if (!cultoListNew.contains(cultoListOldCulto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Culto " + cultoListOldCulto + " since its idIgrejaCulto field is not nullable.");
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
            igreja.setPosseList(posseListNew);
            List<Membresia> attachedMembresiaListNew = new ArrayList<Membresia>();
            for (Membresia membresiaListNewMembresiaToAttach : membresiaListNew) {
                membresiaListNewMembresiaToAttach = em.getReference(membresiaListNewMembresiaToAttach.getClass(), membresiaListNewMembresiaToAttach.getMembresiaPK());
                attachedMembresiaListNew.add(membresiaListNewMembresiaToAttach);
            }
            membresiaListNew = attachedMembresiaListNew;
            igreja.setMembresiaList(membresiaListNew);
            List<Porte> attachedPorteListNew = new ArrayList<Porte>();
            for (Porte porteListNewPorteToAttach : porteListNew) {
                porteListNewPorteToAttach = em.getReference(porteListNewPorteToAttach.getClass(), porteListNewPorteToAttach.getPortePK());
                attachedPorteListNew.add(porteListNewPorteToAttach);
            }
            porteListNew = attachedPorteListNew;
            igreja.setPorteList(porteListNew);
            List<Responsabilidade> attachedResponsabilidadeListNew = new ArrayList<Responsabilidade>();
            for (Responsabilidade responsabilidadeListNewResponsabilidadeToAttach : responsabilidadeListNew) {
                responsabilidadeListNewResponsabilidadeToAttach = em.getReference(responsabilidadeListNewResponsabilidadeToAttach.getClass(), responsabilidadeListNewResponsabilidadeToAttach.getResponsabilidadePK());
                attachedResponsabilidadeListNew.add(responsabilidadeListNewResponsabilidadeToAttach);
            }
            responsabilidadeListNew = attachedResponsabilidadeListNew;
            igreja.setResponsabilidadeList(responsabilidadeListNew);
            List<Culto> attachedCultoListNew = new ArrayList<Culto>();
            for (Culto cultoListNewCultoToAttach : cultoListNew) {
                cultoListNewCultoToAttach = em.getReference(cultoListNewCultoToAttach.getClass(), cultoListNewCultoToAttach.getIdCulto());
                attachedCultoListNew.add(cultoListNewCultoToAttach);
            }
            cultoListNew = attachedCultoListNew;
            igreja.setCultoList(cultoListNew);
            igreja = em.merge(igreja);
            for (Posse posseListNewPosse : posseListNew) {
                if (!posseListOld.contains(posseListNewPosse)) {
                    Igreja oldIdIgrejaPosseOfPosseListNewPosse = posseListNewPosse.getIdIgrejaPosse();
                    posseListNewPosse.setIdIgrejaPosse(igreja);
                    posseListNewPosse = em.merge(posseListNewPosse);
                    if (oldIdIgrejaPosseOfPosseListNewPosse != null && !oldIdIgrejaPosseOfPosseListNewPosse.equals(igreja)) {
                        oldIdIgrejaPosseOfPosseListNewPosse.getPosseList().remove(posseListNewPosse);
                        oldIdIgrejaPosseOfPosseListNewPosse = em.merge(oldIdIgrejaPosseOfPosseListNewPosse);
                    }
                }
            }
            for (Membresia membresiaListNewMembresia : membresiaListNew) {
                if (!membresiaListOld.contains(membresiaListNewMembresia)) {
                    Igreja oldIgrejaOfMembresiaListNewMembresia = membresiaListNewMembresia.getIgreja();
                    membresiaListNewMembresia.setIgreja(igreja);
                    membresiaListNewMembresia = em.merge(membresiaListNewMembresia);
                    if (oldIgrejaOfMembresiaListNewMembresia != null && !oldIgrejaOfMembresiaListNewMembresia.equals(igreja)) {
                        oldIgrejaOfMembresiaListNewMembresia.getMembresiaList().remove(membresiaListNewMembresia);
                        oldIgrejaOfMembresiaListNewMembresia = em.merge(oldIgrejaOfMembresiaListNewMembresia);
                    }
                }
            }
            for (Porte porteListNewPorte : porteListNew) {
                if (!porteListOld.contains(porteListNewPorte)) {
                    Igreja oldIgrejaOfPorteListNewPorte = porteListNewPorte.getIgreja();
                    porteListNewPorte.setIgreja(igreja);
                    porteListNewPorte = em.merge(porteListNewPorte);
                    if (oldIgrejaOfPorteListNewPorte != null && !oldIgrejaOfPorteListNewPorte.equals(igreja)) {
                        oldIgrejaOfPorteListNewPorte.getPorteList().remove(porteListNewPorte);
                        oldIgrejaOfPorteListNewPorte = em.merge(oldIgrejaOfPorteListNewPorte);
                    }
                }
            }
            for (Responsabilidade responsabilidadeListNewResponsabilidade : responsabilidadeListNew) {
                if (!responsabilidadeListOld.contains(responsabilidadeListNewResponsabilidade)) {
                    Igreja oldIgrejaOfResponsabilidadeListNewResponsabilidade = responsabilidadeListNewResponsabilidade.getIgreja();
                    responsabilidadeListNewResponsabilidade.setIgreja(igreja);
                    responsabilidadeListNewResponsabilidade = em.merge(responsabilidadeListNewResponsabilidade);
                    if (oldIgrejaOfResponsabilidadeListNewResponsabilidade != null && !oldIgrejaOfResponsabilidadeListNewResponsabilidade.equals(igreja)) {
                        oldIgrejaOfResponsabilidadeListNewResponsabilidade.getResponsabilidadeList().remove(responsabilidadeListNewResponsabilidade);
                        oldIgrejaOfResponsabilidadeListNewResponsabilidade = em.merge(oldIgrejaOfResponsabilidadeListNewResponsabilidade);
                    }
                }
            }
            for (Culto cultoListNewCulto : cultoListNew) {
                if (!cultoListOld.contains(cultoListNewCulto)) {
                    Igreja oldIdIgrejaCultoOfCultoListNewCulto = cultoListNewCulto.getIdIgrejaCulto();
                    cultoListNewCulto.setIdIgrejaCulto(igreja);
                    cultoListNewCulto = em.merge(cultoListNewCulto);
                    if (oldIdIgrejaCultoOfCultoListNewCulto != null && !oldIdIgrejaCultoOfCultoListNewCulto.equals(igreja)) {
                        oldIdIgrejaCultoOfCultoListNewCulto.getCultoList().remove(cultoListNewCulto);
                        oldIdIgrejaCultoOfCultoListNewCulto = em.merge(oldIdIgrejaCultoOfCultoListNewCulto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = igreja.getIdIgreja();
                if (findIgreja(id) == null) {
                    throw new NonexistentEntityException("The igreja with id " + id + " no longer exists.");
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
            Igreja igreja;
            try {
                igreja = em.getReference(Igreja.class, id);
                igreja.getIdIgreja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The igreja with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Posse> posseListOrphanCheck = igreja.getPosseList();
            for (Posse posseListOrphanCheckPosse : posseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Igreja (" + igreja + ") cannot be destroyed since the Posse " + posseListOrphanCheckPosse + " in its posseList field has a non-nullable idIgrejaPosse field.");
            }
            List<Membresia> membresiaListOrphanCheck = igreja.getMembresiaList();
            for (Membresia membresiaListOrphanCheckMembresia : membresiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Igreja (" + igreja + ") cannot be destroyed since the Membresia " + membresiaListOrphanCheckMembresia + " in its membresiaList field has a non-nullable igreja field.");
            }
            List<Porte> porteListOrphanCheck = igreja.getPorteList();
            for (Porte porteListOrphanCheckPorte : porteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Igreja (" + igreja + ") cannot be destroyed since the Porte " + porteListOrphanCheckPorte + " in its porteList field has a non-nullable igreja field.");
            }
            List<Responsabilidade> responsabilidadeListOrphanCheck = igreja.getResponsabilidadeList();
            for (Responsabilidade responsabilidadeListOrphanCheckResponsabilidade : responsabilidadeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Igreja (" + igreja + ") cannot be destroyed since the Responsabilidade " + responsabilidadeListOrphanCheckResponsabilidade + " in its responsabilidadeList field has a non-nullable igreja field.");
            }
            List<Culto> cultoListOrphanCheck = igreja.getCultoList();
            for (Culto cultoListOrphanCheckCulto : cultoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Igreja (" + igreja + ") cannot be destroyed since the Culto " + cultoListOrphanCheckCulto + " in its cultoList field has a non-nullable idIgrejaCulto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(igreja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Igreja> findIgrejaEntities() {
        return findIgrejaEntities(true, -1, -1);
    }

    public List<Igreja> findIgrejaEntities(int maxResults, int firstResult) {
        return findIgrejaEntities(false, maxResults, firstResult);
    }

    private List<Igreja> findIgrejaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Igreja.class));
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

    public Igreja findIgreja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Igreja.class, id);
        } finally {
            em.close();
        }
    }

    public int getIgrejaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Igreja> rt = cq.from(Igreja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Igreja> findConsultaGeral(String cidadeIgreja, String estadoIgreja, String statusIgreja) {
        List<Igreja> igrejas = null;
        EntityManager em = getEntityManager();
        try {
            igrejas = em.createQuery("select i from Igreja i WHERE i.cidadeIgreja like :cidadeIgreja and i.estadoIgreja = :estadoIgreja and i.status = :statusIgreja")
                    .setParameter("cidadeIgreja", cidadeIgreja).setParameter("estadoIgreja", estadoIgreja).setParameter("statusIgreja", statusIgreja).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return igrejas;
    }
}
