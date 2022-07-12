package Modelo;

        import Auxiliar.Consts;
        import Auxiliar.Desenho;
        import Controler.Tela;
        import java.awt.Graphics;
        import java.io.Serializable;

public class Bloco extends Personagem implements Serializable {

    public Bloco(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
}