package Modelo;

        import Auxiliar.Consts;
        import Controler.ControleDeJogo;
        import Controler.MatrizObjetos;
        import Controler.SomController;

        import java.io.Serializable;
        import java.util.Random;

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
                this.movimentarParaCima();
            }
            else if(valorAleatorio == 1){
                this.movimentarParaDireita();
            }
            else if(valorAleatorio == 2){
                this.movimentarParaBaixo();
            }
            else if(valorAleatorio == 3){
                this.movimentarParaEsquerda();
            }
            this.acumuladorDeTempo = 0;
        }
        this.acumuladorDeTempo += 1;
        super.autoDesenho();
    }

    @Override
    public boolean movimentarParaCima() {
        this.iImage = this.iTras;
        this.getPosicao().setDirecao(Consts.CIMA);
        int indiceLinha = this.getPosicao().getLinha()-1;
        if(indiceLinha < 0) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[indiceLinha][this.getPosicao().getColuna()],
                Consts.CIMA
        );
    }

    @Override
    public boolean movimentarParaDireita() {
        this.iImage = this.iDireita;
        this.getPosicao().setDirecao(Consts.DIREITA);
        int jindiceColuna = this.getPosicao().getColuna()+1;
        if(jindiceColuna >= Consts.RESOLUCAO) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[this.getPosicao().getLinha()][jindiceColuna],
                Consts.DIREITA
        );
    }
    @Override
    public boolean movimentarParaBaixo() {
        this.iImage = this.iFrente;
        this.getPosicao().setDirecao(Consts.BAIXO);
        int indiceLinha = this.getPosicao().getLinha()+1;
        if(indiceLinha >= Consts.RESOLUCAO) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[indiceLinha][this.getPosicao().getColuna()],
                Consts.BAIXO
        );
    }

    @Override
    public boolean movimentarParaEsquerda() {
        this.iImage = this.iEsquerda;
        this.getPosicao().setDirecao(Consts.ESQUERDA);
        int jindiceColuna = this.getPosicao().getColuna()-1;
        if(jindiceColuna < 0) return false;
        return verificarProximoPasso(
                MatrizObjetos.getMatrizDeObjetos()[this.getPosicao().getLinha()][jindiceColuna],
                Consts.ESQUERDA
        );
    }

    public boolean verificarProximoPasso(Personagem personagemDePossivelConflito, int direcao){
        if(personagemDePossivelConflito instanceof Bloco)
            return false;
        if(personagemDePossivelConflito instanceof Coletavel)
            return false;
        if(personagemDePossivelConflito instanceof Inimigo)
            return false;
        if(personagemDePossivelConflito instanceof Skooter){
            SomController.tocarAudio("dano.wav");
            MatrizObjetos.delete(this.getPosicao().getLinha(),this.getPosicao().getColuna());
            if(((Skooter) personagemDePossivelConflito).removerVida()){
                ControleDeJogo.reiniciarFase((Skooter)personagemDePossivelConflito);
            }
            else{
                SomController.tocarAudio("gameOver.wav");
                ControleDeJogo.gameOver((Skooter)personagemDePossivelConflito);
            }
            return false;

        }
        switch (direcao){
            case Consts.CIMA -> {return super.movimentarParaCima();}
            case Consts.DIREITA -> {return super.movimentarParaDireita();}
            case Consts.BAIXO -> {return super.movimentarParaBaixo();}
            case Consts.ESQUERDA -> {return super.movimentarParaEsquerda();}
            default -> {return false;}
        }
    }
}

