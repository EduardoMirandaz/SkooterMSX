/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Auxiliar.Consts;
import Fases.Fase1;
import Fases.Fase2;
import Fases.Fase3;
import Fases.TelaFinal;
import Modelo.Personagem;
import Modelo.Skooter;
import Auxiliar.Posicao;
import java.util.ArrayList;


public class ControleDeJogo {
    public void desenhaTudo(Personagem[][] matrizDePersonagens){
        for(int i = 0; i < Consts.RESOLUCAO; i++){
            for(int j = 0; j < Consts.RESOLUCAO + Consts.LARGURA_MENU; j++){
                if(matrizDePersonagens[i][j] != null){
                    matrizDePersonagens[i][j].autoDesenho();
                }
            }
        }
    }

    public static void atualizarPlacar(Integer pontos){
        System.out.println();
        char sPontos[] = ("000"+ pontos.toString()).toCharArray();

        MatrizObjetos.getMatrizDeObjetos()[1][14]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 1]+".png");
        MatrizObjetos.getMatrizDeObjetos()[1][13]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 2]+".png");
        MatrizObjetos.getMatrizDeObjetos()[1][12]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 3]+".png");
        MatrizObjetos.getMatrizDeObjetos()[1][11]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 4]+".png");
    }

    public static void atualizarRecord(Integer pontos){
        System.out.println();
        char sPontos[] = ("000"+ pontos.toString()).toCharArray();

        MatrizObjetos.getMatrizDeObjetos()[10][12]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 1]+".png");
        MatrizObjetos.getMatrizDeObjetos()[10][11]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 2]+".png");
        MatrizObjetos.getMatrizDeObjetos()[10][10]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 3]+".png");
        MatrizObjetos.getMatrizDeObjetos()[10][9]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 4]+".png");
    }

    public static void mostrarPontuacaoFinal(Integer pontos){
        System.out.println();
        char sPontos[] = ("000"+ pontos.toString()).toCharArray();

        MatrizObjetos.getMatrizDeObjetos()[4][12]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 1]+".png");
        MatrizObjetos.getMatrizDeObjetos()[4][11]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 2]+".png");
        MatrizObjetos.getMatrizDeObjetos()[4][10]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 3]+".png");
        MatrizObjetos.getMatrizDeObjetos()[4][9]
                .setiImage("menu/menuNum"+sPontos[sPontos.length - 4]+".png");
    }


    public static void atualizarVidas(Integer vidas){
        char[] sVidas = ("0"+ vidas.toString()).toCharArray();
        MatrizObjetos.getMatrizDeObjetos()[3][14]
                .setiImage("menu/menuNum"+sVidas[sVidas.length - 1]+".png");
        MatrizObjetos.getMatrizDeObjetos()[3][13]
                .setiImage("menu/menuNum"+sVidas[sVidas.length - 2]+".png");
    }

    public static void reiniciarFase(Skooter skooter){
        if(skooter.telaFlag == 1){
            Fase1.setMatrizParaFase1(skooter);
        }
        else if(skooter.telaFlag == 2){
            Fase2.setMatrizParaFase2(skooter);
        }
        else if(skooter.telaFlag == 3){
            Fase3.setMatrizParaFase3(skooter);
        }
        else{
            TelaFinal.setMatrizParaTelaFinal(skooter);
        }
    }

    public static void gameOver(Skooter skooter){
        TelaFinal.setMatrizParaTelaFinal(skooter);
    }


}
