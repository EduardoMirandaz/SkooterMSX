/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Controler.ControleDeJogo;
import Controler.MatrizObjetos;

import java.io.Serializable;


public class Skooter extends Personagem implements Serializable{
    private Integer vidas;
    private Integer pontos;
    private Integer multiplicadorDePontos;
    public Skooter(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        super(imagemFrente, imagemTras, imagemDireita, imagemEsquerda);
        vidas = 3;
        pontos = 0;
        multiplicadorDePontos = 1;
    }

    public Integer getVidas() {
        return vidas;
    }

    public Integer getPontos() {
        return pontos;
    }

    public Integer getMultiplicadorDePontos() {
        return multiplicadorDePontos;
    }



    public void setMultiplicadorDePontos(Integer multiplicadorDePontos) {
        this.multiplicadorDePontos = multiplicadorDePontos;
    }

    public Skooter(String imagem) {
        super(imagem);
    }
    @Override
    public boolean moveUp() {
        this.iImage = this.iTras;
        this.getPosicao().setDirecao(Consts.CIMA);
        int indexLinha = this.getPosicao().getLinha()-1;
        if(indexLinha < 0) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[indexLinha][this.getPosicao().getColuna()],
                Consts.CIMA
        );
    }

    @Override
    public boolean moveRight() {
        this.iImage = this.iDireita;
        this.getPosicao().setDirecao(Consts.DIREITA);
        int indexColuna = this.getPosicao().getColuna()+1;
        if(indexColuna >= Consts.RESOLUCAO) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[this.getPosicao().getLinha()][indexColuna],
                Consts.DIREITA
        );
    }
    @Override
    public boolean moveDown() {
        this.iImage = this.iFrente;
        this.getPosicao().setDirecao(Consts.BAIXO);
        int indexLinha = this.getPosicao().getLinha()+1;
        if(indexLinha >= Consts.RESOLUCAO) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[indexLinha][this.getPosicao().getColuna()],
                Consts.BAIXO
        );
    }

    @Override
    public boolean moveLeft() {
        this.iImage = this.iEsquerda;
        this.getPosicao().setDirecao(Consts.ESQUERDA);
        int indexColuna = this.getPosicao().getColuna()-1;
        if(indexColuna < 0) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[this.getPosicao().getLinha()][indexColuna],
                Consts.ESQUERDA
        );
    }

    public boolean verificarProximoPasso(Personagem personagemDePossivelConflito, int direcao) {
        if (personagemDePossivelConflito instanceof Bloco)
            return false;
        if (personagemDePossivelConflito instanceof Coletavel){
            this.pontos += ((Coletavel) personagemDePossivelConflito).getValorEmPontos() * this.multiplicadorDePontos;
            Integer indexColetavel = ((Coletavel) personagemDePossivelConflito).getIndexMenu();
            MatrizObjetos.getMatrizDeObjetos()[6+indexColetavel][13].setiImage("menu/menuX"+multiplicadorDePontos+".png");
            this.multiplicadorDePontos += 1;
            ControleDeJogo.atualizarPlacar(pontos);
        }


        switch (direcao){
            case Consts.CIMA -> {return super.moveUp();}
            case Consts.DIREITA -> {return super.moveRight();}
            case Consts.BAIXO -> {return super.moveDown();}
            case Consts.ESQUERDA -> {return super.moveLeft();}
            default -> {return false;}
        }
    }

}
