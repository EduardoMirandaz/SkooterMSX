package Auxiliar;

import Controler.MatrizObjetos;
import Modelo.Inimigo;
import Modelo.Skooter;

import java.io.Serializable;

import static Auxiliar.Util.*;
import static Controler.MatrizObjetos.matrizDeObjetos;

public class Posicao implements Serializable{
    private int	linha;
    private int coluna;
    private int direcao;


    public Posicao(int linha, int coluna){
        this.direcao = BAIXO;
        this.setPosicao(linha,coluna);
    }

    public boolean setPosicao(int linha, int coluna){
        if(linha < 0 || linha >= Auxiliar.Consts.RES ||
          (coluna < 0 || coluna >= Auxiliar.Consts.RES)){
            return false;
        }

        if(matrizDeObjetos[linha][coluna] != null && !(matrizDeObjetos[linha][coluna] instanceof Skooter)){
            return false;
        }

        this.linha = linha;
        this.coluna = coluna;
        return true;
    }

    public int getLinha(){
        return linha;
    }

    public int getColuna(){
        return coluna;
    }

    public boolean igual(Posicao posicao){
        return (linha == posicao.getLinha() && coluna == posicao.getColuna());
    }
    public boolean copia(Posicao posicao){
        return this.setPosicao(posicao.getLinha(),posicao.getColuna());
    }


    public boolean moveUp(){
        this.direcao = CIMA;
        return this.setPosicao(this.getLinha()-1, this.getColuna());
    }
    public boolean moveDown(){
        this.direcao = BAIXO;
        return this.setPosicao(this.getLinha()+1, this.getColuna());
    }
    public boolean moveRight(){
        this.direcao = DIREITA;
        return this.setPosicao(this.getLinha(), this.getColuna()+1);
    }
    public boolean moveLeft(){
        this.direcao = ESQUERDA;
        return this.setPosicao(this.getLinha(), this.getColuna()-1);
    }

    public boolean breakBlock(){
        int linhaDeletada = this.linha;
        int colunaDeletada = this.coluna;
        if(this.direcao == CIMA) linhaDeletada -= 1;
        if(this.direcao == BAIXO) linhaDeletada += 1;
        if(this.direcao == DIREITA) colunaDeletada += 1;
        if(this.direcao == ESQUERDA) colunaDeletada -= 1;
        return MatrizObjetos.delete(linhaDeletada,colunaDeletada);
    }
}
