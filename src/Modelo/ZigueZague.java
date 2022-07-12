/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Auxiliar.Desenho;
import java.util.Random;

/**
 *
 * @author Rodrigues
 */
public class ZigueZague extends Personagem{
    
    public ZigueZague(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public void autoDesenho(){
        Random rand = new Random();
        int iDirecao = rand.nextInt(4);
        if(iDirecao == 1)
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
        else if(iDirecao == 2)
            this.setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
        else if(iDirecao == 3)
            this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
        else if(iDirecao == 4)
            this.setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
        
        super.autoDesenho();
    }    
}
