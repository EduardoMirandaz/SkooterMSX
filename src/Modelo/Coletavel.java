package Modelo;

        import Auxiliar.Consts;
        import Auxiliar.Desenho;
        import java.awt.Graphics;
        import java.awt.Graphics2D;
        import java.awt.geom.AffineTransform;
        import java.io.IOException;
        import java.io.Serializable;
        import javax.swing.ImageIcon;
        import javax.swing.JFrame;
        import javax.swing.JPanel;

public class Coletavel extends Personagem  implements Serializable {

    public Coletavel(String imagem) {
        super(imagem);
    }

    public void autoDesenho(){
        super.autoDesenho();
    }
}
