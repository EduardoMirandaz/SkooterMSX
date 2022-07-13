package Controler;

import Modelo.Personagem;

import java.util.ArrayList;
import java.util.Arrays;

public class MatrizObjetos {
    public static Personagem[][] matrizDeObjetos;

    public MatrizObjetos() {
        matrizDeObjetos = new Personagem[11][11];
        for(int i = 0; i < 11; i++) Arrays.fill(matrizDeObjetos[i], null);
        for(int i = 0; i < 11; i++){
            System.out.println(Arrays.toString(matrizDeObjetos[i]));
        }

    }

    public Personagem[][] getMatrizDeObjetos() {
        return matrizDeObjetos;
    }

    public void setMatrizDeObjetos(int linha, int coluna, Personagem personagem) {
        matrizDeObjetos[linha][coluna] = personagem;
    }

}
