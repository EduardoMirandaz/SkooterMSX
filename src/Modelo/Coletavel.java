package Modelo;
        import java.io.Serializable;

public class Coletavel extends Personagem  implements Serializable {

    public Coletavel(String imagem) {
        super(imagem);
    }

    public void autoDesenho(){
        super.autoDesenho();
    }
}
