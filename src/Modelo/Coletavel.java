package Modelo;
        import java.io.Serializable;

public class Coletavel extends Personagem  implements Serializable {

    private Integer valorEmPontos;
    private Integer indiceMenu;

    public Coletavel(String imagem, Integer indeceMenu) {
        super(imagem);
        this.indiceMenu = indeceMenu;
        switch (indeceMenu){
            case 1 -> valorEmPontos = 10;
            case 2 -> valorEmPontos = 15;
            case 3 -> valorEmPontos = 20;
            case 4 -> valorEmPontos = 25 ;
            default -> valorEmPontos = 0;
        }
    }

    public void autoDesenho(){
        super.autoDesenho();
    }

    public Integer getValorEmPontos() {
        return valorEmPontos;
    }

    public Integer getIndiceMenu() {
        return indiceMenu;
    }
}
