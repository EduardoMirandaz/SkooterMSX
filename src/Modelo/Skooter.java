/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

public class Skooter extends Personagem implements Serializable{
    public Skooter(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
}
