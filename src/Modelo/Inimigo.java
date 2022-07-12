package Modelo;

        import Auxiliar.Consts;
        import Auxiliar.Desenho;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.geom.AffineTransform;
        import java.io.IOException;
        import java.io.Serializable;
        import java.util.Random;
        import javax.swing.ImageIcon;
        import javax.swing.JFrame;
        import javax.swing.JPanel;

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
                if(!this.moveUp()){
                   this.voltaAUltimaPosicao();
                }

            }
            else if(valorAleatorio == 1){
                if(!this.moveRight()){
                    this.voltaAUltimaPosicao();
                }
            }
            else if(valorAleatorio == 2){
                if(!this.moveDown()){
                    this.voltaAUltimaPosicao();
                }
            }
            else if(valorAleatorio == 3){
                if(!this.moveLeft()){
                    this.voltaAUltimaPosicao();
                }
            }
            this.acumuladorDeTempo = 0;
        }
        this.acumuladorDeTempo += 1;
        super.autoDesenho();
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
}

