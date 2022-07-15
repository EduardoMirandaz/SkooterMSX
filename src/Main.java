/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;

import Controler.ControleDeJogo;
import Controler.SomController;
import Controler.Tela;

public class Main {
    public static void main(String[] args) {
        SomController somController = new SomController();
        somController.AudioCore();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela tTela = new Tela();
                tTela.setVisible(true);
                tTela.createBufferStrategy(2);
                tTela.go();
            }
        });
    }
}

