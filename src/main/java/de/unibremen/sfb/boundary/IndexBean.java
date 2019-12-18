package de.unibremen.sfb.boundary;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class IndexBean {

    @Getter
    @Setter
    String username;

    public String login(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username",username);
        switch (username.toLowerCase()){
            case "admin":
                return "index?faces-redirect-true";
            case "pkadmin":
                break;
            case "transport":
                break;
            case "technologe":
                break;
            case "logistiker":
                break;
            default:
                break;
        }
        return null;
    }
}
