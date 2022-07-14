/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Auxiliar.Consts;
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
}
