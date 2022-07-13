package Controler;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.BlocoQuebravel;
import Modelo.Personagem;
import Modelo.Skooter;

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

    public static void setMatrizDeObjetos(Personagem[][] matriz) {
        matrizDeObjetos = matriz;
    }

    public static boolean delete(int linha, int coluna){
        if(linha < 0 || linha >= Auxiliar.Consts.RESOLUCAO ||
                (coluna < 0 || coluna >= Auxiliar.Consts.RESOLUCAO)){
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

    public static boolean isEmpty(Personagem[][] p){
        for (Personagem[] personagems : p) {
            for (int j = 0; j < p.length; j++) {
                if (personagems[j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<Personagem> getListaDePersonagens(){
        ArrayList<Personagem> personagens = new ArrayList<>();
        boolean achouSkooter = false;
        for(int i = 0; i < matrizDeObjetos.length && !achouSkooter; i++){
            for (int j = 0; j < matrizDeObjetos.length; j++){
                if(matrizDeObjetos[i][j] instanceof Skooter){
                    personagens.add(matrizDeObjetos[i][j]);
                    achouSkooter = true;
                    break;
                }
            }
        }
        if(!achouSkooter){
            for (int i = 0; i < Consts.RESOLUCAO; i++){
                System.arraycopy(Tela.estadoInicialFase[i], 0, matrizDeObjetos[i], 0, Consts.RESOLUCAO);
            }
            for(int i = 0; i < 11; i++){
                System.out.println(Arrays.toString(Tela.estadoInicialFase[i]));
            }
            for(int i = 0; i < matrizDeObjetos.length && !achouSkooter; i++){
                for (int j = 0; j < matrizDeObjetos.length; j++){
                    if(matrizDeObjetos[i][j] instanceof Skooter){
                        personagens.add(matrizDeObjetos[i][j]);
                        achouSkooter = true;
                        break;
                    }
                }
            }
        }
        for (Personagem[] matrizDeObjeto : matrizDeObjetos) {
            for (int j = 0; j < matrizDeObjetos.length; j++) {
                if (matrizDeObjeto[j] != null && !(matrizDeObjeto[j] instanceof Skooter)) {
                    personagens.add(matrizDeObjeto[j]);
                }
            }
        }
        return personagens;
    }

}
