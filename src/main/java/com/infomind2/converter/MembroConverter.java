/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infomind2.converter;

import com.infomind2.cotrol.MembroJpaController;
import com.infomind2.model.Membro;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Membro.class)
public class MembroConverter implements Converter {

    MembroJpaController membros = new MembroJpaController();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Chamei o metodo as Object");
        Membro membro = new Membro();
        if (value != null || !"".equals(value)) {
            membro = membros.findMembro(new Integer(value));
            System.out.println(membro.getNome() + membro.getIdMembro());
        }
        return membro;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent componet, Object value) {
        System.out.println("Chamei o metodo as String");
        if (value != null) {
            return ((Membro) value).getIdMembro().toString();
        }
        return null;
    }
}
