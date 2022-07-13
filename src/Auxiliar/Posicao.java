package Auxiliar;

import Controler.MatrizObjetos;

import java.io.Serializable;

import static Auxiliar.Util.*;

public class Posicao implements Serializable{
    private int	linha;
    private int coluna;
    private int direcao;


    public Posicao(int linha, int coluna){
        this.direcao = BAIXO;
        this.setPosicao(linha,coluna);
    }


    public boolean setPosicao(int linha, int coluna){
        if(linha < 0 || linha >= Auxiliar.Consts.RESOLUCAO ||
          (coluna < 0 || coluna >= Auxiliar.Consts.RESOLUCAO)){
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

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public boolean igual(Posicao posicao){
        return (linha == posicao.getLinha() && coluna == posicao.getColuna());
    }
    public boolean copia(Posicao posicao){
        return this.setPosicao(posicao.getLinha(),posicao.getColuna());
    }


    public boolean moveUp(){
        return this.setPosicao(this.getLinha()-1, this.getColuna());
    }
    public boolean moveDown(){
        return this.setPosicao(this.getLinha()+1, this.getColuna());
    }
    public boolean moveRight(){
        return this.setPosicao(this.getLinha(), this.getColuna()+1);
    }
    public boolean moveLeft(){
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
