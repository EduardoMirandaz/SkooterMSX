package Modelo;
        import java.io.Serializable;

public class Coletavel extends Personagem  implements Serializable {

    private Integer valorEmPontos;
    private Integer indexMenu;

    public Coletavel(String imagem, Integer indexMenu) {
        super(imagem);
        this.indexMenu = indexMenu;
        switch (indexMenu){
            case 1 -> valorEmPontos = 100;
            case 2 -> valorEmPontos = 150;
            case 3 -> valorEmPontos = 200;
            case 4 -> valorEmPontos = 250;
            default -> valorEmPontos = 0;
        }
    }

    public void autoDesenho(){
        super.autoDesenho();
    }

    public Integer getValorEmPontos() {
        return valorEmPontos;
    }

    public Integer getIndexMenu() {
        return indexMenu;
    }
}
