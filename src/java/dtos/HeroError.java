/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class HeroError implements Serializable{
    private String usernameError,passwordError,retypePasswordError;
    
    public HeroError() {
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRetypePasswordError() {
        return retypePasswordError;
    }

    public void setRetypePasswordError(String retypePasswordError) {
        this.retypePasswordError = retypePasswordError;
    }

    
    
}
