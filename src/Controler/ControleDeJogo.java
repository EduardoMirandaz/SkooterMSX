/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controler;

import Modelo.Personagem;
import Modelo.Skooter;
import Auxiliar.Posicao;
import java.util.ArrayList;


public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Personagem> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    public void processaTudo(ArrayList<Personagem> e){
        Skooter skooter = (Skooter)e.get(0);
        // verifica todos os personagens que nao sao skooter
        // e, se encontra um que é transponível, remove o mesmo;
        Personagem pTemp;
        for(int i = 1; i < e.size(); i++){
            pTemp = e.get(i);
            if(skooter.getPosicao().igual(pTemp.getPosicao()))
                if(pTemp.isbTransponivel())
                    e.remove(pTemp);
        }        
    }
    
    /*Retorna true se a posicao p é válida para Skooter com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Personagem> e, Posicao p){
        Personagem pTemp;
        for(int i = 1; i < e.size(); i++){
            pTemp = e.get(i);            
            if(!pTemp.isbTransponivel())
                if(pTemp.getPosicao().igual(p))
                    return false;
        }        
        return true;
    }
}
