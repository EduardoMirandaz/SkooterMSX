/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import Auxiliar.Consts;
import Controler.ControleDeJogo;
import Controler.MatrizObjetos;
import Controler.SomController;
import Controler.Tela;
import Fases.Fase2;
import Fases.Fase3;
import Fases.TelaFinal;

import java.io.Serializable;



public class Skooter extends Personagem implements Serializable{
    private Integer vidas;
    private boolean flagEasterEgg;
    private static Integer pontos;
    private Integer multiplicadorDePontos;
    public Integer telaFlag;

    public Skooter(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        super(imagemFrente, imagemTras, imagemDireita, imagemEsquerda);
        flagEasterEgg = false;
        vidas = 3;
        pontos = 0;
        multiplicadorDePontos = 1;
        telaFlag = 0;
    }

    public Integer getTelaFlag() {
        return telaFlag;
    }

    public Integer getVidas() {
        return vidas;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setFlagEasterEgg(boolean flagEasterEgg) {
        this.flagEasterEgg = flagEasterEgg;
    }

    public boolean isFlagEasterEgg() {
        return flagEasterEgg;
    }

    public Integer getMultiplicadorDePontos() {
        return multiplicadorDePontos;
    }



    public void setMultiplicadorDePontos(Integer multiplicadorDePontos) {
        this.multiplicadorDePontos = multiplicadorDePontos;
    }

    public boolean removerVida(){
        this.vidas-=1;
        if(this.vidas >= 0){
            ControleDeJogo.atualizarVidas(this.vidas);
            return true;
        }
        this.vidas+=1;
        return false;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

    public static void adicionarPontos(Integer novosPontos){
        pontos += novosPontos;
        ControleDeJogo.atualizarPlacar(pontos);
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
            Skooter.adicionarPontos(((Coletavel) personagemDePossivelConflito).getValorEmPontos() * this.multiplicadorDePontos);
            Integer indexColetavel = ((Coletavel) personagemDePossivelConflito).getIndexMenu();
            MatrizObjetos.getMatrizDeObjetos()[6+indexColetavel][13].setiImage("menu/menuX"+multiplicadorDePontos+".png");
            this.multiplicadorDePontos += 1;
            SomController.tocarAudio("coletar.wav");
            if(this.multiplicadorDePontos == 5){
                SomController.tocarAudio("proximaFase.wav");
                this.telaFlag+=1;
                if(this.telaFlag == 2){
                    Fase2.setMatrizParaFase2(this);
                }
                if(this.telaFlag == 3){
                    Fase3.setMatrizParaFase3(this);
                }
                if(this.telaFlag >= 4){
                    TelaFinal.setMatrizParaTelaFinal(this);
                }
                // se não é a tela final, atualizo os pontos
                else{
                    ControleDeJogo.atualizarPlacar(pontos);
                }
            }
        }
        if (personagemDePossivelConflito instanceof Inimigo){
            SomController.tocarAudio("dano.wav");
            if(this.removerVida()){
                ControleDeJogo.reiniciarFase(this);
            }
            else{
                SomController.tocarAudio("gameOver.wav");
                ControleDeJogo.gameOver(this);
            }
            return false;

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
