package Modelo;

import java.util.ArrayList;

public class BlocoFixo extends Bloco{

    public static Integer posicoesBlocosFixos[][];

    public BlocoFixo(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public Integer[][] getPosicoesBlocosFixos() {
        return posicoesBlocosFixos;
    }

    public void setPosicoesBlocosFixos(Integer[][] posicoesBlocosFixos) {
        this.posicoesBlocosFixos = posicoesBlocosFixos;
    }
}
