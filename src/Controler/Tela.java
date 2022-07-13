/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tela.java
 *
 * Created on 03/03/2011, 18:28:20
 */
package Controler;

import Modelo.*;
import Modelo.Skooter;
import Auxiliar.Consts;
import Auxiliar.Desenho;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static Controler.MatrizObjetos.matrizDeObjetos;
import static Modelo.BlocoFixo.posicoesBlocosFixos;
import static Modelo.BlocoQuebravel.posicoesBlocosQuebraveis;

/**
 *
 * @author junio
 */
public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private ControleDeJogo controleDeJogo = new ControleDeJogo();

    private Skooter skooter;
    private Graphics graphics_1;
    public static Personagem[][] estadoInicialFase;

    /**
     * Creates new form tabuleiro
     */
    public Tela() {

        MatrizObjetos matrizObjetos = new MatrizObjetos();

        Desenho.setCenario(this);
        initComponents();

        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize((Consts.RESOLUCAO +Consts.LARGURA_MENU) * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RESOLUCAO * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);


        /*Cria e adiciona personagens*/

        MatrizObjetos.setMatrizDeObjetos(0,7, new Skooter("skooter/skooterFrente.png", "skooter/skooterTras.png", "skooter/skooterDireita.png", "skooter/skooterEsquerda.png"));
        MatrizObjetos.getMatrizDeObjetos()[0][7].setPosicao(0,7);
        skooter = (Skooter) MatrizObjetos.getMatrizDeObjetos()[0][7];
//        this.addPersonagem(MatrizObjetos.getMatrizDeObjetos()[0][7]);


        Inimigo inimigoAmarelo = new Inimigo("inimigos/inimigoAmareloFrente.png","inimigos/inimigoAmareloTras.png","inimigos/inimigoAmareloDireita.png","inimigos/inimigoAmareloEsquerda.png");
        inimigoAmarelo.setPosicao(1, 0);
//        this.addPersonagem(inimigoAmarelo);
        MatrizObjetos.setMatrizDeObjetos(1,0, inimigoAmarelo);

        Inimigo inimigoRosa = new Inimigo("inimigos/inimigoRosaFrente.png","inimigos/inimigoRosaTras.png","inimigos/inimigoRosaDireita.png","inimigos/inimigoRosaEsquerda.png");
        inimigoRosa.setPosicao(0, 9);
//        this.addPersonagem(inimigoRosa);
        MatrizObjetos.setMatrizDeObjetos(0,9, inimigoRosa);


        Inimigo inimigoAzul = new Inimigo("inimigos/inimigoAzulFrente.png","inimigos/inimigoAzulTras.png","inimigos/inimigoAzulDireita.png","inimigos/inimigoAzulEsquerda.png");
        inimigoAzul.setPosicao(10, 1);
//        this.addPersonagem(inimigoAzul);
        MatrizObjetos.setMatrizDeObjetos(10,1, inimigoAzul);

        Inimigo inimigoVerde = new Inimigo("inimigos/inimigoVerdeFrente.png","inimigos/inimigoVerdeTras.png","inimigos/inimigoVerdeDireita.png","inimigos/inimigoVerdeEsquerda.png");
        inimigoVerde.setPosicao(10, 9);
//        this.addPersonagem(inimigoVerde);
        MatrizObjetos.setMatrizDeObjetos(10,9, inimigoVerde);


        MatrizObjetos.setMatrizDeObjetos(0,0, new Coletavel("coletaveis/uva.png"));
        MatrizObjetos.getMatrizDeObjetos()[0][0].setPosicao(0,0);


        MatrizObjetos.setMatrizDeObjetos(0,10, new Coletavel("coletaveis/limao.png"));
        MatrizObjetos.getMatrizDeObjetos()[0][10].setPosicao(0,10);

        MatrizObjetos.setMatrizDeObjetos(10,0, new Coletavel("coletaveis/morango.png"));
        MatrizObjetos.getMatrizDeObjetos()[10][0].setPosicao(10,0);

        MatrizObjetos.setMatrizDeObjetos(10,10, new Coletavel("coletaveis/cereja.png"));
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

        estadoInicialFase = new Personagem[Consts.RESOLUCAO][Consts.RESOLUCAO];
        for (int i = 0; i < Consts.RESOLUCAO; i++){
            System.arraycopy(matrizDeObjetos[i], 0, estadoInicialFase[i], 0, Consts.RESOLUCAO);
        }
        System.out.println("\n\n\n\n\nprintando estado inicial fase");
        for(int i = 0; i < 11; i++){
            System.out.println(Arrays.toString(estadoInicialFase[i]));
        }
        System.out.println("\n\n\n\n\n");
    }

    public static void setEstadoInicialFase(Personagem[][] estadoInicialFase) {
        Tela.estadoInicialFase = estadoInicialFase;
    }

    public Graphics getGraphicsBuffer(){
        return graphics_1;
    }
    public void paint(Graphics gOld) {
        Graphics graphics_2 = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        graphics_1 = graphics_2.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*************Desenha cenário de fundo**************/
        for (int i = 0; i < Consts.RESOLUCAO + Consts.LARGURA_MENU; i++) {
            for (int j = 0; j < Consts.RESOLUCAO; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "fundo/fundo.png");
                    graphics_1.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        for(int i = 0; i < 11; i++){
            System.out.println(Arrays.toString(MatrizObjetos.getMatrizDeObjetos()[i]));
        }


        if (!MatrizObjetos.isEmpty(MatrizObjetos.getMatrizDeObjetos())) {
            ArrayList<Personagem> personagens = new ArrayList<>();
            personagens = MatrizObjetos.getListaDePersonagens();

//            System.out.println(personagens);
//            for(Personagem p: personagens){
//                System.out.println(p.toString() + " -- " +p.getPosicao().getLinha() + " " + p.getPosicao().getColuna());
//                System.out.println();
//            }
//            System.out.println(personagens);
            this.controleDeJogo.desenhaTudo(personagens);
            this.controleDeJogo.processaTudo(personagens);
        }


        graphics_2.dispose();
        graphics_1.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public static Personagem[][] getEstadoInicialFase() {
        return estadoInicialFase;
    }

    public void removePersonagem(Personagem p){
        MatrizObjetos.setMatrizDeObjetos(p.getPosicao().getLinha(), p.getPosicao().getColuna(), null);
    }
    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            MatrizObjetos.getListaDePersonagens().clear();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            try {
                File tanque = new File("c:\\temp\\POO.zip");
                FileInputStream canoOut = new FileInputStream(tanque);
                GZIPInputStream compactador = new GZIPInputStream(canoOut);
                ObjectInputStream serializador = new ObjectInputStream(compactador);
                MatrizObjetos.setMatrizDeObjetos((Personagem[][])serializador.readObject());
                serializador.close();
            } catch (Exception ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            try {
                File tanque = new File("c:\\temp\\POO.zip");
                tanque.createNewFile();
                FileOutputStream canoOut = new FileOutputStream(tanque);
                GZIPOutputStream compactador = new GZIPOutputStream(canoOut);
                ObjectOutputStream serializador = new ObjectOutputStream(compactador);
                serializador.writeObject(matrizDeObjetos);
                serializador.flush();
                serializador.close();
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            skooter.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            skooter.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            skooter.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            skooter.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            skooter.breakBlock();
        }

        this.setTitle("-> Cell: " + (skooter.getPosicao().getColuna()) + ", "
                + (skooter.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado
         int x = e.getX();
         int y = e.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
         this.lSkooter.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         */
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2015-1 - Adventures of lolo");
        setAutoRequestFocus(false);
        setPreferredSize(new java.awt.Dimension(500, 500));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
