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
import com.infomind2.model.Celula;
import java.util.ArrayList;
import java.util.List;
import com.infomind2.model.Posse;
import com.infomind2.model.Membresia;
import com.infomind2.model.Responsabilidade;
import com.infomind2.model.Discipulado;
import com.infomind2.model.Membro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Lucas
 */
public class MembroJpaController implements Serializable {

    public MembroJpaController() {
        this.emf = Persistence.createEntityManagerFactory("InfoMind");;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Membro membro) {
        if (membro.getCelulaList() == null) {
            membro.setCelulaList(new ArrayList<Celula>());
        }
        if (membro.getPosseList() == null) {
            membro.setPosseList(new ArrayList<Posse>());
        }
        if (membro.getMembresiaList() == null) {
            membro.setMembresiaList(new ArrayList<Membresia>());
        }
        if (membro.getResponsabilidadeList() == null) {
            membro.setResponsabilidadeList(new ArrayList<Responsabilidade>());
        }
        if (membro.getDiscipuladoList() == null) {
            membro.setDiscipuladoList(new ArrayList<Discipulado>());
        }
        if (membro.getDiscipuladoList1() == null) {
            membro.setDiscipuladoList1(new ArrayList<Discipulado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Celula> attachedCelulaList = new ArrayList<Celula>();
            for (Celula celulaListCelulaToAttach : membro.getCelulaList()) {
                celulaListCelulaToAttach = em.getReference(celulaListCelulaToAttach.getClass(), celulaListCelulaToAttach.getIdCelula());
                attachedCelulaList.add(celulaListCelulaToAttach);
            }
            membro.setCelulaList(attachedCelulaList);
            List<Posse> attachedPosseList = new ArrayList<Posse>();
            for (Posse posseListPosseToAttach : membro.getPosseList()) {
                posseListPosseToAttach = em.getReference(posseListPosseToAttach.getClass(), posseListPosseToAttach.getPossePK());
                attachedPosseList.add(posseListPosseToAttach);
            }
            membro.setPosseList(attachedPosseList);
            List<Membresia> attachedMembresiaList = new ArrayList<Membresia>();
            for (Membresia membresiaListMembresiaToAttach : membro.getMembresiaList()) {
                membresiaListMembresiaToAttach = em.getReference(membresiaListMembresiaToAttach.getClass(), membresiaListMembresiaToAttach.getMembresiaPK());
                attachedMembresiaList.add(membresiaListMembresiaToAttach);
            }
            membro.setMembresiaList(attachedMembresiaList);
            List<Responsabilidade> attachedResponsabilidadeList = new ArrayList<Responsabilidade>();
            for (Responsabilidade responsabilidadeListResponsabilidadeToAttach : membro.getResponsabilidadeList()) {
                responsabilidadeListResponsabilidadeToAttach = em.getReference(responsabilidadeListResponsabilidadeToAttach.getClass(), responsabilidadeListResponsabilidadeToAttach.getResponsabilidadePK());
                attachedResponsabilidadeList.add(responsabilidadeListResponsabilidadeToAttach);
            }
            membro.setResponsabilidadeList(attachedResponsabilidadeList);
            List<Discipulado> attachedDiscipuladoList = new ArrayList<Discipulado>();
            for (Discipulado discipuladoListDiscipuladoToAttach : membro.getDiscipuladoList()) {
                discipuladoListDiscipuladoToAttach = em.getReference(discipuladoListDiscipuladoToAttach.getClass(), discipuladoListDiscipuladoToAttach.getDiscipuladoPK());
                attachedDiscipuladoList.add(discipuladoListDiscipuladoToAttach);
            }
            membro.setDiscipuladoList(attachedDiscipuladoList);
            List<Discipulado> attachedDiscipuladoList1 = new ArrayList<Discipulado>();
            for (Discipulado discipuladoList1DiscipuladoToAttach : membro.getDiscipuladoList1()) {
                discipuladoList1DiscipuladoToAttach = em.getReference(discipuladoList1DiscipuladoToAttach.getClass(), discipuladoList1DiscipuladoToAttach.getDiscipuladoPK());
                attachedDiscipuladoList1.add(discipuladoList1DiscipuladoToAttach);
            }
            membro.setDiscipuladoList1(attachedDiscipuladoList1);
            em.persist(membro);
            for (Celula celulaListCelula : membro.getCelulaList()) {
                Membro oldIdMembroLiderOfCelulaListCelula = celulaListCelula.getIdMembroLider();
                celulaListCelula.setIdMembroLider(membro);
                celulaListCelula = em.merge(celulaListCelula);
                if (oldIdMembroLiderOfCelulaListCelula != null) {
                    oldIdMembroLiderOfCelulaListCelula.getCelulaList().remove(celulaListCelula);
                    oldIdMembroLiderOfCelulaListCelula = em.merge(oldIdMembroLiderOfCelulaListCelula);
                }
            }
            for (Posse posseListPosse : membro.getPosseList()) {
                Membro oldMembroOfPosseListPosse = posseListPosse.getMembro();
                posseListPosse.setMembro(membro);
                posseListPosse = em.merge(posseListPosse);
                if (oldMembroOfPosseListPosse != null) {
                    oldMembroOfPosseListPosse.getPosseList().remove(posseListPosse);
                    oldMembroOfPosseListPosse = em.merge(oldMembroOfPosseListPosse);
                }
            }
            for (Membresia membresiaListMembresia : membro.getMembresiaList()) {
                Membro oldMembroOfMembresiaListMembresia = membresiaListMembresia.getMembro();
                membresiaListMembresia.setMembro(membro);
                membresiaListMembresia = em.merge(membresiaListMembresia);
                if (oldMembroOfMembresiaListMembresia != null) {
                    oldMembroOfMembresiaListMembresia.getMembresiaList().remove(membresiaListMembresia);
                    oldMembroOfMembresiaListMembresia = em.merge(oldMembroOfMembresiaListMembresia);
                }
            }
            for (Responsabilidade responsabilidadeListResponsabilidade : membro.getResponsabilidadeList()) {
                Membro oldMembroOfResponsabilidadeListResponsabilidade = responsabilidadeListResponsabilidade.getMembro();
                responsabilidadeListResponsabilidade.setMembro(membro);
                responsabilidadeListResponsabilidade = em.merge(responsabilidadeListResponsabilidade);
                if (oldMembroOfResponsabilidadeListResponsabilidade != null) {
                    oldMembroOfResponsabilidadeListResponsabilidade.getResponsabilidadeList().remove(responsabilidadeListResponsabilidade);
                    oldMembroOfResponsabilidadeListResponsabilidade = em.merge(oldMembroOfResponsabilidadeListResponsabilidade);
                }
            }
            for (Discipulado discipuladoListDiscipulado : membro.getDiscipuladoList()) {
                Membro oldMembroOfDiscipuladoListDiscipulado = discipuladoListDiscipulado.getMembro();
                discipuladoListDiscipulado.setMembro(membro);
                discipuladoListDiscipulado = em.merge(discipuladoListDiscipulado);
                if (oldMembroOfDiscipuladoListDiscipulado != null) {
                    oldMembroOfDiscipuladoListDiscipulado.getDiscipuladoList().remove(discipuladoListDiscipulado);
                    oldMembroOfDiscipuladoListDiscipulado = em.merge(oldMembroOfDiscipuladoListDiscipulado);
                }
            }
            for (Discipulado discipuladoList1Discipulado : membro.getDiscipuladoList1()) {
                Membro oldMembro1OfDiscipuladoList1Discipulado = discipuladoList1Discipulado.getMembro1();
                discipuladoList1Discipulado.setMembro1(membro);
                discipuladoList1Discipulado = em.merge(discipuladoList1Discipulado);
                if (oldMembro1OfDiscipuladoList1Discipulado != null) {
                    oldMembro1OfDiscipuladoList1Discipulado.getDiscipuladoList1().remove(discipuladoList1Discipulado);
                    oldMembro1OfDiscipuladoList1Discipulado = em.merge(oldMembro1OfDiscipuladoList1Discipulado);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Membro membro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Membro persistentMembro = em.find(Membro.class, membro.getIdMembro());
            List<Celula> celulaListOld = persistentMembro.getCelulaList();
            List<Celula> celulaListNew = membro.getCelulaList();
            List<Posse> posseListOld = persistentMembro.getPosseList();
            List<Posse> posseListNew = membro.getPosseList();
            List<Membresia> membresiaListOld = persistentMembro.getMembresiaList();
            List<Membresia> membresiaListNew = membro.getMembresiaList();
            List<Responsabilidade> responsabilidadeListOld = persistentMembro.getResponsabilidadeList();
            List<Responsabilidade> responsabilidadeListNew = membro.getResponsabilidadeList();
            List<Discipulado> discipuladoListOld = persistentMembro.getDiscipuladoList();
            List<Discipulado> discipuladoListNew = membro.getDiscipuladoList();
            List<Discipulado> discipuladoList1Old = persistentMembro.getDiscipuladoList1();
            List<Discipulado> discipuladoList1New = membro.getDiscipuladoList1();
            List<String> illegalOrphanMessages = null;
            for (Celula celulaListOldCelula : celulaListOld) {
                if (!celulaListNew.contains(celulaListOldCelula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Celula " + celulaListOldCelula + " since its idMembroLider field is not nullable.");
                }
            }
            for (Posse posseListOldPosse : posseListOld) {
                if (!posseListNew.contains(posseListOldPosse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Posse " + posseListOldPosse + " since its membro field is not nullable.");
                }
            }
            for (Membresia membresiaListOldMembresia : membresiaListOld) {
                if (!membresiaListNew.contains(membresiaListOldMembresia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Membresia " + membresiaListOldMembresia + " since its membro field is not nullable.");
                }
            }
            for (Responsabilidade responsabilidadeListOldResponsabilidade : responsabilidadeListOld) {
                if (!responsabilidadeListNew.contains(responsabilidadeListOldResponsabilidade)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Responsabilidade " + responsabilidadeListOldResponsabilidade + " since its membro field is not nullable.");
                }
            }
            for (Discipulado discipuladoListOldDiscipulado : discipuladoListOld) {
                if (!discipuladoListNew.contains(discipuladoListOldDiscipulado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Discipulado " + discipuladoListOldDiscipulado + " since its membro field is not nullable.");
                }
            }
            for (Discipulado discipuladoList1OldDiscipulado : discipuladoList1Old) {
                if (!discipuladoList1New.contains(discipuladoList1OldDiscipulado)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Discipulado " + discipuladoList1OldDiscipulado + " since its membro1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Celula> attachedCelulaListNew = new ArrayList<Celula>();
            for (Celula celulaListNewCelulaToAttach : celulaListNew) {
                celulaListNewCelulaToAttach = em.getReference(celulaListNewCelulaToAttach.getClass(), celulaListNewCelulaToAttach.getIdCelula());
                attachedCelulaListNew.add(celulaListNewCelulaToAttach);
            }
            celulaListNew = attachedCelulaListNew;
            membro.setCelulaList(celulaListNew);
            List<Posse> attachedPosseListNew = new ArrayList<Posse>();
            for (Posse posseListNewPosseToAttach : posseListNew) {
                posseListNewPosseToAttach = em.getReference(posseListNewPosseToAttach.getClass(), posseListNewPosseToAttach.getPossePK());
                attachedPosseListNew.add(posseListNewPosseToAttach);
            }
            posseListNew = attachedPosseListNew;
            membro.setPosseList(posseListNew);
            List<Membresia> attachedMembresiaListNew = new ArrayList<Membresia>();
            for (Membresia membresiaListNewMembresiaToAttach : membresiaListNew) {
                membresiaListNewMembresiaToAttach = em.getReference(membresiaListNewMembresiaToAttach.getClass(), membresiaListNewMembresiaToAttach.getMembresiaPK());
                attachedMembresiaListNew.add(membresiaListNewMembresiaToAttach);
            }
            membresiaListNew = attachedMembresiaListNew;
            membro.setMembresiaList(membresiaListNew);
            List<Responsabilidade> attachedResponsabilidadeListNew = new ArrayList<Responsabilidade>();
            for (Responsabilidade responsabilidadeListNewResponsabilidadeToAttach : responsabilidadeListNew) {
                responsabilidadeListNewResponsabilidadeToAttach = em.getReference(responsabilidadeListNewResponsabilidadeToAttach.getClass(), responsabilidadeListNewResponsabilidadeToAttach.getResponsabilidadePK());
                attachedResponsabilidadeListNew.add(responsabilidadeListNewResponsabilidadeToAttach);
            }
            responsabilidadeListNew = attachedResponsabilidadeListNew;
            membro.setResponsabilidadeList(responsabilidadeListNew);
            List<Discipulado> attachedDiscipuladoListNew = new ArrayList<Discipulado>();
            for (Discipulado discipuladoListNewDiscipuladoToAttach : discipuladoListNew) {
                discipuladoListNewDiscipuladoToAttach = em.getReference(discipuladoListNewDiscipuladoToAttach.getClass(), discipuladoListNewDiscipuladoToAttach.getDiscipuladoPK());
                attachedDiscipuladoListNew.add(discipuladoListNewDiscipuladoToAttach);
            }
            discipuladoListNew = attachedDiscipuladoListNew;
            membro.setDiscipuladoList(discipuladoListNew);
            List<Discipulado> attachedDiscipuladoList1New = new ArrayList<Discipulado>();
            for (Discipulado discipuladoList1NewDiscipuladoToAttach : discipuladoList1New) {
                discipuladoList1NewDiscipuladoToAttach = em.getReference(discipuladoList1NewDiscipuladoToAttach.getClass(), discipuladoList1NewDiscipuladoToAttach.getDiscipuladoPK());
                attachedDiscipuladoList1New.add(discipuladoList1NewDiscipuladoToAttach);
            }
            discipuladoList1New = attachedDiscipuladoList1New;
            membro.setDiscipuladoList1(discipuladoList1New);
            membro = em.merge(membro);
            for (Celula celulaListNewCelula : celulaListNew) {
                if (!celulaListOld.contains(celulaListNewCelula)) {
                    Membro oldIdMembroLiderOfCelulaListNewCelula = celulaListNewCelula.getIdMembroLider();
                    celulaListNewCelula.setIdMembroLider(membro);
                    celulaListNewCelula = em.merge(celulaListNewCelula);
                    if (oldIdMembroLiderOfCelulaListNewCelula != null && !oldIdMembroLiderOfCelulaListNewCelula.equals(membro)) {
                        oldIdMembroLiderOfCelulaListNewCelula.getCelulaList().remove(celulaListNewCelula);
                        oldIdMembroLiderOfCelulaListNewCelula = em.merge(oldIdMembroLiderOfCelulaListNewCelula);
                    }
                }
            }
            for (Posse posseListNewPosse : posseListNew) {
                if (!posseListOld.contains(posseListNewPosse)) {
                    Membro oldMembroOfPosseListNewPosse = posseListNewPosse.getMembro();
                    posseListNewPosse.setMembro(membro);
                    posseListNewPosse = em.merge(posseListNewPosse);
                    if (oldMembroOfPosseListNewPosse != null && !oldMembroOfPosseListNewPosse.equals(membro)) {
                        oldMembroOfPosseListNewPosse.getPosseList().remove(posseListNewPosse);
                        oldMembroOfPosseListNewPosse = em.merge(oldMembroOfPosseListNewPosse);
                    }
                }
            }
            for (Membresia membresiaListNewMembresia : membresiaListNew) {
                if (!membresiaListOld.contains(membresiaListNewMembresia)) {
                    Membro oldMembroOfMembresiaListNewMembresia = membresiaListNewMembresia.getMembro();
                    membresiaListNewMembresia.setMembro(membro);
                    membresiaListNewMembresia = em.merge(membresiaListNewMembresia);
                    if (oldMembroOfMembresiaListNewMembresia != null && !oldMembroOfMembresiaListNewMembresia.equals(membro)) {
                        oldMembroOfMembresiaListNewMembresia.getMembresiaList().remove(membresiaListNewMembresia);
                        oldMembroOfMembresiaListNewMembresia = em.merge(oldMembroOfMembresiaListNewMembresia);
                    }
                }
            }
            for (Responsabilidade responsabilidadeListNewResponsabilidade : responsabilidadeListNew) {
                if (!responsabilidadeListOld.contains(responsabilidadeListNewResponsabilidade)) {
                    Membro oldMembroOfResponsabilidadeListNewResponsabilidade = responsabilidadeListNewResponsabilidade.getMembro();
                    responsabilidadeListNewResponsabilidade.setMembro(membro);
                    responsabilidadeListNewResponsabilidade = em.merge(responsabilidadeListNewResponsabilidade);
                    if (oldMembroOfResponsabilidadeListNewResponsabilidade != null && !oldMembroOfResponsabilidadeListNewResponsabilidade.equals(membro)) {
                        oldMembroOfResponsabilidadeListNewResponsabilidade.getResponsabilidadeList().remove(responsabilidadeListNewResponsabilidade);
                        oldMembroOfResponsabilidadeListNewResponsabilidade = em.merge(oldMembroOfResponsabilidadeListNewResponsabilidade);
                    }
                }
            }
            for (Discipulado discipuladoListNewDiscipulado : discipuladoListNew) {
                if (!discipuladoListOld.contains(discipuladoListNewDiscipulado)) {
                    Membro oldMembroOfDiscipuladoListNewDiscipulado = discipuladoListNewDiscipulado.getMembro();
                    discipuladoListNewDiscipulado.setMembro(membro);
                    discipuladoListNewDiscipulado = em.merge(discipuladoListNewDiscipulado);
                    if (oldMembroOfDiscipuladoListNewDiscipulado != null && !oldMembroOfDiscipuladoListNewDiscipulado.equals(membro)) {
                        oldMembroOfDiscipuladoListNewDiscipulado.getDiscipuladoList().remove(discipuladoListNewDiscipulado);
                        oldMembroOfDiscipuladoListNewDiscipulado = em.merge(oldMembroOfDiscipuladoListNewDiscipulado);
                    }
                }
            }
            for (Discipulado discipuladoList1NewDiscipulado : discipuladoList1New) {
                if (!discipuladoList1Old.contains(discipuladoList1NewDiscipulado)) {
                    Membro oldMembro1OfDiscipuladoList1NewDiscipulado = discipuladoList1NewDiscipulado.getMembro1();
                    discipuladoList1NewDiscipulado.setMembro1(membro);
                    discipuladoList1NewDiscipulado = em.merge(discipuladoList1NewDiscipulado);
                    if (oldMembro1OfDiscipuladoList1NewDiscipulado != null && !oldMembro1OfDiscipuladoList1NewDiscipulado.equals(membro)) {
                        oldMembro1OfDiscipuladoList1NewDiscipulado.getDiscipuladoList1().remove(discipuladoList1NewDiscipulado);
                        oldMembro1OfDiscipuladoList1NewDiscipulado = em.merge(oldMembro1OfDiscipuladoList1NewDiscipulado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = membro.getIdMembro();
                if (findMembro(id) == null) {
                    throw new NonexistentEntityException("The membro with id " + id + " no longer exists.");
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
            Membro membro;
            try {
                membro = em.getReference(Membro.class, id);
                membro.getIdMembro();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The membro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Celula> celulaListOrphanCheck = membro.getCelulaList();
            for (Celula celulaListOrphanCheckCelula : celulaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Membro (" + membro + ") cannot be destroyed since the Celula " + celulaListOrphanCheckCelula + " in its celulaList field has a non-nullable idMembroLider field.");
            }
            List<Posse> posseListOrphanCheck = membro.getPosseList();
            for (Posse posseListOrphanCheckPosse : posseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Membro (" + membro + ") cannot be destroyed since the Posse " + posseListOrphanCheckPosse + " in its posseList field has a non-nullable membro field.");
            }
            List<Membresia> membresiaListOrphanCheck = membro.getMembresiaList();
            for (Membresia membresiaListOrphanCheckMembresia : membresiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Membro (" + membro + ") cannot be destroyed since the Membresia " + membresiaListOrphanCheckMembresia + " in its membresiaList field has a non-nullable membro field.");
            }
            List<Responsabilidade> responsabilidadeListOrphanCheck = membro.getResponsabilidadeList();
            for (Responsabilidade responsabilidadeListOrphanCheckResponsabilidade : responsabilidadeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Membro (" + membro + ") cannot be destroyed since the Responsabilidade " + responsabilidadeListOrphanCheckResponsabilidade + " in its responsabilidadeList field has a non-nullable membro field.");
            }
            List<Discipulado> discipuladoListOrphanCheck = membro.getDiscipuladoList();
            for (Discipulado discipuladoListOrphanCheckDiscipulado : discipuladoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Membro (" + membro + ") cannot be destroyed since the Discipulado " + discipuladoListOrphanCheckDiscipulado + " in its discipuladoList field has a non-nullable membro field.");
            }
            List<Discipulado> discipuladoList1OrphanCheck = membro.getDiscipuladoList1();
            for (Discipulado discipuladoList1OrphanCheckDiscipulado : discipuladoList1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Membro (" + membro + ") cannot be destroyed since the Discipulado " + discipuladoList1OrphanCheckDiscipulado + " in its discipuladoList1 field has a non-nullable membro1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(membro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Membro> findMembroEntities() {
        return findMembroEntities(true, -1, -1);
    }

    public List<Membro> findMembroEntities(int maxResults, int firstResult) {
        return findMembroEntities(false, maxResults, firstResult);
    }

    private List<Membro> findMembroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Membro.class));
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

    public Membro findMembro(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Membro.class, id);
        } finally {
            em.close();
        }
    }

    public int getMembroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Membro> rt = cq.from(Membro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Membro> listaLideres() {
        List<Membro> lideres = null;
        EntityManager em = getEntityManager();
        try {
            lideres = em.createQuery("select m from Membro m where m.isLider = :isLider").setParameter("isLider", "SIM").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return lideres;
    }

    public List<Membro> findConsultaGeral(String nomeMembro, String cidadeMembro, String estadoMembro, String statusMembro) {

        List<Membro> membros = null;
        EntityManager em = getEntityManager();
        try {
            membros = em.createQuery("SELECT m FROM Membro m WHERE m.nome LIKE :nomeMembro AND  m.cidadeMembro LIKE :cidadeMembro AND m.estadoMembro = :estadoMembro AND m.status = :statusMembro")
                    .setParameter("nomeMembro", nomeMembro).setParameter("cidadeMembro", cidadeMembro).setParameter("estadoMembro", estadoMembro).setParameter("statusMembro", statusMembro).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        System.out.println(membros.toString());
        return membros;
    }
}
