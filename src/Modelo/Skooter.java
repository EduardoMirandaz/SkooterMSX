/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.Serializable;

public class Skooter extends Personagem implements Serializable{
    public Skooter(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        super(imagemFrente, imagemTras, imagemDireita, imagemEsquerda);
    }

    public Skooter(String imagem) {
        super(imagem);
    }
    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
}
