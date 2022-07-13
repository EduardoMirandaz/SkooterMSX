package Modelo;

import java.io.Serializable;

public class ParteMenu extends Personagem  implements Serializable {
    public ParteMenu(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        super(imagemFrente, imagemTras, imagemDireita, imagemEsquerda);
    }

    public ParteMenu(String image) {
        super(image);
    }

    public boolean setPosicaoInMenu(int linha, int coluna){
        this.pPosicao.setLinha(linha);
        this.pPosicao.setColuna(coluna);
        return true;
    }
}
