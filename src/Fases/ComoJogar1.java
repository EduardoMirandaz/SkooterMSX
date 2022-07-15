package Fases;

import Controler.ControleDeJogo;
import Controler.MatrizObjetos;
import Modelo.ParteMenu;

public class ComoJogar1 {
    public static void setMatrizParaComoJogar1() {

        for(int i = 10; i >= 0; i--){
            for(int j = 14; j >= 0; j--){
                MatrizObjetos.setMatrizDeObjetos(10-i,14-j, new ParteMenu("comoJogar1/"+((15*i+j)+1)+".png"));
                ((ParteMenu)(MatrizObjetos.getMatrizDeObjetos()[10-i][14-j])).setPosicaoInMenu(10-i,14-j);
            }
        }
    }
}
