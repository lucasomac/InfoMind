package com.infomind2.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class GrowlView {

//    private String message;
//    public String getMessage() {
//        return message;
//    }
//    public void setMessage(String message) {
//        this.message = message;
//    }
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
//         
//        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        context.addMessage(null, new FacesMessage("Cadastrado com sucesso!", "Cadastro Realizado!"));
    }

    public void editMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
//         
//        context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
        context.addMessage(null, new FacesMessage("Atualizado com sucesso!", "Cadastro Atualizado!"));
    }

}
