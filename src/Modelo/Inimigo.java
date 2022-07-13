package Modelo;

        import Auxiliar.Consts;
        import Controler.MatrizObjetos;
        import Controler.Tela;

        import java.io.Serializable;
        import java.util.Random;

        import static Auxiliar.Util.*;
        import static Auxiliar.Util.ESQUERDA;

public class Inimigo extends Personagem  implements Serializable {
    private boolean bRight;

    //Acumulador que fará o inimigo se movimentar mais lentamente a cada periodo e meio
    private int acumuladorDeTempo;
    private final int limitadorDoAcumulador = 5;

    public Inimigo(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        super(imagemFrente, imagemTras, imagemDireita, imagemEsquerda);
        this.acumuladorDeTempo = 0;
    }

    public void autoDesenho(){
        if(this.acumuladorDeTempo % limitadorDoAcumulador == 0){
            Random r = new Random();
            int valorAleatorio = r.nextInt(4);
            if(valorAleatorio == 0){
                this.moveUp();
            }
            else if(valorAleatorio == 1){
                this.moveRight();
            }
            else if(valorAleatorio == 2){
                this.moveDown();
            }
            else if(valorAleatorio == 3){
                this.moveLeft();
            }
            this.acumuladorDeTempo = 0;
        }
        this.acumuladorDeTempo += 1;
        super.autoDesenho();
    }

    //TODO interface movimentacao, para colocar os metodos dos personagens que se movem

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
        if(indexColuna >= Consts.RESOLUCAO) return false;
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
        if(indexLinha >= Consts.RESOLUCAO) return false;
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
        if(personagemDePossivelConflito instanceof Coletavel)
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

