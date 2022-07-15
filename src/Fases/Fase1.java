package Fases;

import Auxiliar.Consts;
import Controler.ControleDeJogo;
import Controler.MatrizObjetos;
import Modelo.*;

import static Modelo.BlocoFixo.posicoesBlocosFixos;
import static Modelo.BlocoQuebravel.posicoesBlocosQuebraveis;

public class Fase1 {
    public static void setMatrizParaFase1() {
        Skooter skooter = new Skooter("skooter/skooterFrente.png", "skooter/skooterTras.png", "skooter/skooterDireita.png", "skooter/skooterEsquerda.png");
        setMatrizParaFase1(skooter);
    }

    public static void setMatrizParaFase1(Skooter skooter) {

        MatrizObjetos.apagarObjetos();



        MatrizObjetos.setMatrizDeObjetos(0,4, skooter);
        MatrizObjetos.getMatrizDeObjetos()[0][4].setPosicao(0,4);
        skooter.setMultiplicadorDePontos(1);


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
        inimigoAmarelo.setPosicao(1, 0);
        MatrizObjetos.setMatrizDeObjetos(1,0, inimigoAmarelo);

        Inimigo inimigoRosa = new Inimigo("inimigos/inimigoRosaFrente.png","inimigos/inimigoRosaTras.png","inimigos/inimigoRosaDireita.png","inimigos/inimigoRosaEsquerda.png");
        inimigoRosa.setPosicao(0, 9);
        MatrizObjetos.setMatrizDeObjetos(0,9, inimigoRosa);

        Inimigo inimigoAzul = new Inimigo("inimigos/inimigoAzulFrente.png","inimigos/inimigoAzulTras.png","inimigos/inimigoAzulDireita.png","inimigos/inimigoAzulEsquerda.png");
        inimigoAzul.setPosicao(10, 1);
        MatrizObjetos.setMatrizDeObjetos(10,1, inimigoAzul);

        Inimigo inimigoVerde = new Inimigo("inimigos/inimigoVerdeFrente.png","inimigos/inimigoVerdeTras.png","inimigos/inimigoVerdeDireita.png","inimigos/inimigoVerdeEsquerda.png");
        inimigoVerde.setPosicao(10, 9);
        MatrizObjetos.setMatrizDeObjetos(10,9, inimigoVerde);



        MatrizObjetos.setMatrizDeObjetos(0,0, new Coletavel("coletaveis/uva.png",4));
        MatrizObjetos.getMatrizDeObjetos()[0][0].setPosicao(0,0);

        MatrizObjetos.setMatrizDeObjetos(0,10, new Coletavel("coletaveis/limao.png",2));
        MatrizObjetos.getMatrizDeObjetos()[0][10].setPosicao(0,10);

        MatrizObjetos.setMatrizDeObjetos(10,0, new Coletavel("coletaveis/morango.png",1));
        MatrizObjetos.getMatrizDeObjetos()[10][0].setPosicao(10,0);

        MatrizObjetos.setMatrizDeObjetos(10,10, new Coletavel("coletaveis/cereja.png",3));
        MatrizObjetos.getMatrizDeObjetos()[10][10].setPosicao(10,10);



        posicoesBlocosFixos = new Integer[][]{
                {1, 1}, {1, 3}, {1, 5}, {1, 7}, {1, 9},
                {3, 1}, {3, 3}, {3, 5}, {3, 7}, {3, 9},
                {5, 1}, {5, 3}, {5, 5}, {5, 7}, {5, 9},
                {7, 1}, {7, 3}, {7, 5}, {7, 7}, {7, 9},
                {9, 1}, {9, 3}, {9, 5}, {9, 7}, {9, 9}
        };
        for (Integer[] posicoesBlocosFixo : posicoesBlocosFixos) {
            BlocoFixo blocoFixo = new BlocoFixo("blocos/blocoVermelhoFixo.png");
            blocoFixo.setPosicao(posicoesBlocosFixo[0], posicoesBlocosFixo[1]);
            MatrizObjetos.setMatrizDeObjetos(posicoesBlocosFixo[0], posicoesBlocosFixo[1], blocoFixo);
        }



        posicoesBlocosQuebraveis = new Integer[][]{
                {0,1},
                {1,2},{1,8},{1,10},
                {2,1},{2,5},
                {3,0},{3,8},
                {4,1},{4,9},
                {5,2},{5,6},
                {6,5},{6,7},
                {7,8},{7,10},
                {8,3},{8,9},
                {9,0},{9,2},{9,6},{9,8},
                {10,7}
        };
        for (Integer[] posicoesBlocosQuebraveis : posicoesBlocosQuebraveis) {
            BlocoQuebravel blocoQuebravel = new BlocoQuebravel("blocos/blocoVerdeQuebravel.png");
            blocoQuebravel.setPosicao(posicoesBlocosQuebraveis[0], posicoesBlocosQuebraveis[1]);
            MatrizObjetos.setMatrizDeObjetos(posicoesBlocosQuebraveis[0], posicoesBlocosQuebraveis[1], blocoQuebravel);
        }
    }
}
