package Fases;

import Auxiliar.Consts;
import Controler.ControleDeJogo;
import Controler.MatrizObjetos;
import Modelo.*;

import static Modelo.BlocoFixo.posicoesBlocosFixos;
import static Modelo.BlocoQuebravel.posicoesBlocosQuebraveis;

public class Fase2 {
    public static void setMatrizParaFase2(Skooter skooter) {

        MatrizObjetos.apagarObjetos();


        skooter.setMultiplicador(1);
        MatrizObjetos.setMatrizDeObjetos(0,5, skooter);
        MatrizObjetos.getMatrizDeObjetos()[0][5].setPosicao(0,5);



        String imagensIniciaisMenu[][] = {
                {"menu/menuScore1.png","menu/menuScore2.png","menu/menuScore3.png","menu/menuScore4.png"},
                {"menu/menuNum0.png","menu/menuNum0.png","menu/menuNum0.png","menu/menuNum0.png"},
                {"menu/menuVida1.png","menu/menuVida2.png","menu/menuVida3.png","menu/menuVida4.png"},
                {"menu/menuEmpty.png","menu/menuEmpty.png","menu/menuNum0.png","menu/menuNum3.png"},
                {"menu/menuFase1.png","menu/menuFase2.png","menu/menuFase3.png","menu/menuFase4.png"},
                {"menu/menuEmpty.png","menu/menuEmpty.png","menu/menuNum0.png","menu/menuNum1.png"},
                {"menu/menuEmpty.png","menu/menuEmpty.png","menu/menuEmpty.png","menu/menuEmpty.png"},
                {"menu/menuEmpty.png", "menu/menu10.png", "menu/menuEmpty.png", "menu/menuColetavel1.png"},
                {"menu/menuEmpty.png", "menu/menu15.png", "menu/menuEmpty.png", "menu/menuColetavel2.png"},
                {"menu/menuEmpty.png", "menu/menu20.png", "menu/menuEmpty.png", "menu/menuColetavel3.png"},
                {"menu/menuEmpty.png", "menu/menu25.png", "menu/menuEmpty.png", "menu/menuColetavel4.png"}
        };


        for(int i = 0; i < Consts.RESOLUCAO; i++){
            for(int j = 0; j < Consts.LARGURA_MENU; j++){
                MatrizObjetos.setMatrizDeObjetos(i,11+j, new ParteMenu(imagensIniciaisMenu[i][j]));
                ((ParteMenu)(MatrizObjetos.getMatrizDeObjetos()[i][11+j])).setPosicaoInMenu(i,11+j);
            }
        }

        ControleDeJogo.atualizarPlacar(skooter.getPontos());
        ControleDeJogo.atualizarVidas(skooter.getVidas());

        Inimigo inimigoAmarelo = new Inimigo("inimigos/inimigoAmareloFrente.png","inimigos/inimigoAmareloTras.png","inimigos/inimigoAmareloDireita.png","inimigos/inimigoAmareloEsquerda.png");
        inimigoAmarelo.setPosicao(0, 0);
        MatrizObjetos.setMatrizDeObjetos(0,0, inimigoAmarelo);

        Inimigo inimigoRosa = new Inimigo("inimigos/inimigoRosaFrente.png","inimigos/inimigoRosaTras.png","inimigos/inimigoRosaDireita.png","inimigos/inimigoRosaEsquerda.png");
        inimigoRosa.setPosicao(0, 10);
        MatrizObjetos.setMatrizDeObjetos(0,10, inimigoRosa);

        Inimigo inimigoAzul = new Inimigo("inimigos/inimigoAzulFrente.png","inimigos/inimigoAzulTras.png","inimigos/inimigoAzulDireita.png","inimigos/inimigoAzulEsquerda.png");
        inimigoAzul.setPosicao(10, 0);
        MatrizObjetos.setMatrizDeObjetos(10,0, inimigoAzul);

        Inimigo inimigoVerde = new Inimigo("inimigos/inimigoVerdeFrente.png","inimigos/inimigoVerdeTras.png","inimigos/inimigoVerdeDireita.png","inimigos/inimigoVerdeEsquerda.png");
        inimigoVerde.setPosicao(10, 10);
        MatrizObjetos.setMatrizDeObjetos(10,10, inimigoVerde);



        MatrizObjetos.setMatrizDeObjetos(3,9, new Coletavel("coletaveis/uva.png",4));
        MatrizObjetos.getMatrizDeObjetos()[3][9].setPosicao(3,9);

        MatrizObjetos.setMatrizDeObjetos(10,4, new Coletavel("coletaveis/limao.png",2));
        MatrizObjetos.getMatrizDeObjetos()[10][4].setPosicao(10,4);

        MatrizObjetos.setMatrizDeObjetos(10,6, new Coletavel("coletaveis/morango.png",1));
        MatrizObjetos.getMatrizDeObjetos()[10][6].setPosicao(10,6);

        MatrizObjetos.setMatrizDeObjetos(3,1, new Coletavel("coletaveis/cereja.png",3));
        MatrizObjetos.getMatrizDeObjetos()[3][1].setPosicao(3,1);



        posicoesBlocosFixos = new Integer[][]{
                {9, 2}, {9, 3}, {9, 4}, {9, 5}, {9, 6}, {9, 7}, {9, 8},
                {10,5}
        };
        for (Integer[] posicoesBlocosFixo : posicoesBlocosFixos) {
            BlocoFixo blocoFixo = new BlocoFixo("blocos/blocoVermelhoFixo.png");
            blocoFixo.setPosicao(posicoesBlocosFixo[0], posicoesBlocosFixo[1]);
            MatrizObjetos.setMatrizDeObjetos(posicoesBlocosFixo[0], posicoesBlocosFixo[1], blocoFixo);
        }



        posicoesBlocosQuebraveis = new Integer[][]{
                {2,0},{2,1},{2,2},{2,4},{2,5},{2,6},{2,8},{2,9},{2,10},
                {3,0},{3,2},{3,4},{3,6},{3,8},{3,10},
                {4,0},{4,1},{4,2},{4,4},{4,6},{4,8},{4,10},
                {5,0},{5,4},{5,6},{5,8},{5,10},
                {6,0},{6,4},{6,6},{6,8},{6,10},
                {7,0},{7,4},{7,5},{7,6},{7,8},{7,9},{7,10},
                {9, 0},{9, 1},{9, 9},{9, 10}
        };
        System.out.println(posicoesBlocosQuebraveis.length   );

        for (Integer[] posicoesBlocosQuebraveis : posicoesBlocosQuebraveis) {
            BlocoQuebravel blocoQuebravel = new BlocoQuebravel("blocos/blocoVerdeQuebravel.png");
            blocoQuebravel.setPosicao(posicoesBlocosQuebraveis[0], posicoesBlocosQuebraveis[1]);
            MatrizObjetos.setMatrizDeObjetos(posicoesBlocosQuebraveis[0], posicoesBlocosQuebraveis[1], blocoQuebravel);
        }
    }
}
