package Fases;

import Auxiliar.Consts;
import Controler.ControleDeJogo;
import Controler.MatrizObjetos;
import Modelo.ParteMenu;
import Modelo.Personagem;

import java.io.*;
import java.util.Scanner;
import java.util.zip.GZIPInputStream;

public class TelaInicial {

    public static void getMaxScore(){
        try{
            String path = "src/Controler/MaxScore.txt";
            File tanque = new File(path);
            if(!tanque.exists() || tanque.isDirectory()){
                if(!tanque.createNewFile()){
                    return;
                }
            }
            //Fluxo de saida de um arquivo
            OutputStream os = new FileOutputStream(path); // nome do arquivo que será escrito
            Writer wr = new OutputStreamWriter(os); // criação de um escritor
            BufferedWriter br = new BufferedWriter(wr); // adiciono a um escritor de buffer
            br.write(0);
            //Fluxo de saida de um arquivo
            Scanner scanner = new Scanner(tanque);
            ControleDeJogo.atualizarRecord(scanner.nextInt());
            scanner.close();
        }catch (Exception e){
            e.getStackTrace();
        }


    }

    public static void setMatrizParaTelaInicial() {
        for(int i = 10; i >= 0; i--){
            for(int j = 14; j >= 0; j--){
                MatrizObjetos.setMatrizDeObjetos(10-i,14-j, new ParteMenu("inicial/"+((15*i+j)+1)+".png"));
                ((ParteMenu)(MatrizObjetos.getMatrizDeObjetos()[10-i][14-j])).setPosicaoInMenu(10-i,14-j);
            }
        }
        ControleDeJogo.atualizarRecord(0);
    }

}
