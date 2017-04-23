package com.infomind2.converter;

import com.infomind2.cotrol.IgrejaJpaController;
import com.infomind2.model.Igreja;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Igreja.class)
public class IgrejaConverter implements Converter {

    IgrejaJpaController igrejas = new IgrejaJpaController();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("Chamei o metodo as Object da igreja");
        Igreja igreja = new Igreja();
        if (value != null || !"".equals(value)) {
            System.out.println("A igreja vai ser pesquisada.");
            igreja = igrejas.findIgreja(new Integer(value));
            System.out.println("A igreja foi instaurada.");
            System.out.println(igreja.getBairroIgreja() + igreja.getCidadeIgreja() + igreja.getEstadoIgreja());
        }
        return igreja;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent componet, Object value) {
        System.out.println("Chamei o metodo as String");
        if (value != null) {
            return ((Igreja) value).getIdIgreja().toString();
        }
        return null;
    }
}
