package Auxiliar;
import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;
import Controler.Tela;

public class Desenho implements Serializable {
    static Tela jCenario;

    public static void setCenario(Tela umJCenario) {
        jCenario = umJCenario;
    }

    public static Tela getCenario() {
        return jCenario;
    }

    public static Graphics getGraphicsDaTela() {
        return jCenario.getGraphicsBuffer();
    }
    
    public static void desenhar(ImageIcon iImage, int iColuna, int iLinha) {
        iImage.paintIcon(jCenario,getGraphicsDaTela(),iColuna * Consts.TAMANHO_CELULA, iLinha * Consts.TAMANHO_CELULA);
    }
}
