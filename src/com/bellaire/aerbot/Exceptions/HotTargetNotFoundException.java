/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bellaire.aerbot.Exceptions;

/**
 *
 * @author cmurphey100
 */
public class HotTargetNotFoundException extends Exception{
    public String getMessage(){
        return "No hot target found";
    }
}