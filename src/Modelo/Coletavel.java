package Modelo;
        import java.io.Serializable;

public class Coletavel extends Personagem  implements Serializable {

    private Integer valorEmPontos;
    private Integer indiceMenu;

    public Coletavel(String imagem, Integer indeceMenu) {
        super(imagem);
        this.indiceMenu = indeceMenu;
        switch (indeceMenu) {
            case 1:
                this.valorEmPontos = 10;
                break;
            case 2:
                this.valorEmPontos = 15;
                break;
            case 3:
                this.valorEmPontos = 20;
                break;
            case 4:
                this.valorEmPontos = 25;
                break;
            default:
                this.valorEmPontos = 0;
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
