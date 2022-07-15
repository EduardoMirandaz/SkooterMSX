package Fases;
import Controler.ControleDeJogo;
import Controler.MatrizObjetos;
import Modelo.ParteMenu;
import Modelo.Skooter;

public class TelaFinal {
    public static void setMatrizParaTelaFinal(Skooter skooter) {
        skooter.setFlagEasterEgg(true);
        for(int i = 10; i >= 0; i--){
            for(int j = 14; j >= 0; j--){
                MatrizObjetos.setMatrizDeObjetos(10-i,14-j, new ParteMenu("creditos/"+((15*i+j)+1)+".png"));
                ((ParteMenu)(MatrizObjetos.getMatrizDeObjetos()[10-i][14-j])).setPosicaoInMenu(10-i,14-j);
            }
        }

        Integer maxScore = ControleDeJogo.getMaxScore();
        ControleDeJogo.mostrarPontuacaoFinal(skooter.getPontos());
        if(skooter.getPontos() > maxScore){
            ControleDeJogo.mostrarRecordFinal(skooter.getPontos());
            ControleDeJogo.setMaxScore(skooter.getPontos());
        }
        else{
            ControleDeJogo.mostrarRecordFinal(maxScore);
        }

    }

}
