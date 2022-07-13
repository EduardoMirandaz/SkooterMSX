package Auxiliar;

import java.io.Serializable;
import java.util.ArrayList;

import static Controler.MatrizObjetos.matrizDeObjetos;

public class Posicao implements Serializable{
    private int	linha;
    private int coluna;

    public Posicao(int linha, int coluna){
        this.setPosicao(linha,coluna);
    }

    public boolean setPosicao(int linha, int coluna){
        System.out.println("Estou querendo ir para: " + linha +" "+ coluna);
        if(linha < 0 || linha >= Auxiliar.Consts.RES ||
          (coluna < 0 || coluna >= Auxiliar.Consts.RES)){
            System.out.println("nao movi, borda");
            return false;

        }

        if(matrizDeObjetos[linha][coluna] != null){
            System.out.println("nao movi, parede!");
            return false;
        }

        System.out.println("movi");

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
}
