package Controler;

import Auxiliar.Desenho;
import Modelo.Bloco;
import Modelo.BlocoQuebravel;
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

    public void autoDesenho() {
        for (int i = 0; i < MatrizObjetos.matrizDeObjetos.length; i++){
            for (Personagem p: MatrizObjetos.matrizDeObjetos[i]) {
                p.autoDesenho();
            }
        }
    }

        public static Personagem[][] getMatrizDeObjetos() {
        return matrizDeObjetos;
    }

    public static void setMatrizDeObjetos(int linha, int coluna, Personagem personagem) {
        matrizDeObjetos[linha][coluna] = personagem;
    }

    public static boolean delete(int linha, int coluna){
        if(linha < 0 || linha >= Auxiliar.Consts.RES ||
                (coluna < 0 || coluna >= Auxiliar.Consts.RES)){
            System.out.println("nao quebrei, borda");
            return false;
        }

        if(matrizDeObjetos[linha][coluna] == null){
            System.out.println("nao quebrei, vazio!");
            return false;
        }

        if(matrizDeObjetos[linha][coluna] instanceof BlocoQuebravel){
            Desenho.getCenario().removePersonagem(matrizDeObjetos[linha][coluna]);
            setMatrizDeObjetos(linha,coluna,null);
            return true;
        }

        return false;
    }
}
