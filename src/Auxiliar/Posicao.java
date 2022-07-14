package Auxiliar;

import Controler.MatrizObjetos;

import java.io.Serializable;
import Auxiliar.Consts;
import Modelo.ParteMenu;

public class Posicao implements Serializable{
    private int	linha;
    private int coluna;
    private int direcao;


    public Posicao(int linha, int coluna){
        this.direcao = Consts.BAIXO;
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

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
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
        if(this.direcao == Consts.CIMA) linhaDeletada -= 1;
        if(this.direcao == Consts.BAIXO) linhaDeletada += 1;
        if(this.direcao == Consts.DIREITA) colunaDeletada += 1;
        if(this.direcao == Consts.ESQUERDA) colunaDeletada -= 1;
        return MatrizObjetos.delete(linhaDeletada,colunaDeletada);
    }

}
