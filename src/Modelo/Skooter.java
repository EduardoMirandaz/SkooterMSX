/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Controler.MatrizObjetos;

import java.io.Serializable;

import static Auxiliar.Util.*;

public class Skooter extends Personagem implements Serializable{
    public Skooter(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        super(imagemFrente, imagemTras, imagemDireita, imagemEsquerda);
    }

    public Skooter(String imagem) {
        super(imagem);
    }
    @Override
    public boolean moveUp() {
        this.iImage = this.iTras;
        this.getPosicao().setDirecao(CIMA);
        int indexLinha = this.getPosicao().getLinha()-1;
        if(indexLinha < 0) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[indexLinha][this.getPosicao().getColuna()],
                CIMA
        );
    }

    @Override
    public boolean moveRight() {
        this.iImage = this.iDireita;
        this.getPosicao().setDirecao(DIREITA);
        int indexColuna = this.getPosicao().getColuna()+1;
        if(indexColuna >= Consts.RES) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[this.getPosicao().getLinha()][indexColuna],
                DIREITA
        );
    }
    @Override
    public boolean moveDown() {
        this.iImage = this.iFrente;
        this.getPosicao().setDirecao(BAIXO);
        int indexLinha = this.getPosicao().getLinha()+1;
        if(indexLinha >= Consts.RES) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[indexLinha][this.getPosicao().getColuna()],
                BAIXO
        );
    }

    @Override
    public boolean moveLeft() {
        this.iImage = this.iEsquerda;
        this.getPosicao().setDirecao(ESQUERDA);
        int indexColuna = this.getPosicao().getColuna()-1;
        if(indexColuna < 0) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[this.getPosicao().getLinha()][indexColuna],
                ESQUERDA
        );
    }

    public boolean verificarProximoPasso(Personagem personagemDePossivelConflito, int direcao){
        if(personagemDePossivelConflito instanceof Bloco)
            return false;

        switch (direcao){
            case CIMA -> {return super.moveUp();}
            case DIREITA -> {return super.moveRight();}
            case BAIXO -> {return super.moveDown();}
            case ESQUERDA -> {return super.moveLeft();}
            default -> {return false;}
        }
    }

}
