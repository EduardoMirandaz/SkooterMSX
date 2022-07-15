package Modelo;

import java.io.Serializable;

public abstract class Bloco extends Personagem implements Serializable {

    public Bloco(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
}