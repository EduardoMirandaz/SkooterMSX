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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static Modelo.BlocoFixo.posicoesBlocosFixos;
import static Modelo.BlocoQuebravel.posicoesBlocosQuebraveis;

/**
 *
 * @author junio
 */
public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private Skooter lSkooter;
    private ArrayList<Personagem> e;
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;

    public static ArrayList<Bloco> blocosFase1;

    /**
     * Creates new form tabuleiro
     */
    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        e = new ArrayList<Personagem>(121);

        /*Cria e adiciona personagens*/
        lSkooter = new Skooter("skooter/skooterFrente.png", "skooter/skooterTras.png", "skooter/skooterDireita.png", "skooter/skooterEsquerda.png");
        lSkooter.setPosicao(0, 7);
        this.addPersonagem(lSkooter);

        Inimigo inimigoAmarelo = new Inimigo("inimigos/inimigoAmareloFrente.png","inimigos/inimigoAmareloTras.png","inimigos/inimigoAmareloDireita.png","inimigos/inimigoAmareloEsquerda.png");
        inimigoAmarelo.setPosicao(2, 0);
        this.addPersonagem(inimigoAmarelo);

        Inimigo inimigoRosa = new Inimigo("inimigos/inimigoRosaFrente.png","inimigos/inimigoRosaTras.png","inimigos/inimigoRosaDireita.png","inimigos/inimigoRosaEsquerda.png");
        inimigoRosa.setPosicao(0, 9);
        this.addPersonagem(inimigoRosa);

        Inimigo inimigoAzul = new Inimigo("inimigos/inimigoAzulFrente.png","inimigos/inimigoAzulTras.png","inimigos/inimigoAzulDireita.png","inimigos/inimigoAzulEsquerda.png");
        inimigoAzul.setPosicao(10, 1);
        this.addPersonagem(inimigoAzul);

        Inimigo inimigoVerde = new Inimigo("inimigos/inimigoVerdeFrente.png","inimigos/inimigoVerdeTras.png","inimigos/inimigoVerdeDireita.png","inimigos/inimigoVerdeEsquerda.png");
        inimigoVerde.setPosicao(10, 9);
        this.addPersonagem(inimigoVerde);

        Coletavel uva = new Coletavel("coletaveis/uva.png");
        uva.setPosicao(0, 0);
        this.addPersonagem(uva);

        Coletavel limao = new Coletavel("coletaveis/limao.png");
        limao.setPosicao(0, 10);
        this.addPersonagem(limao);

        Coletavel morango = new Coletavel("coletaveis/morango.png");
        morango.setPosicao(10, 0);
        this.addPersonagem(morango);

        Coletavel cereja = new Coletavel("coletaveis/cereja.png");
        cereja.setPosicao(10, 10);
        this.addPersonagem(cereja);

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
            this.addPersonagem(blocoFixo);
        }

        posicoesBlocosQuebraveis = new Integer[][]{
                {0,1},{1,3},
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
            this.addPersonagem(blocoQuebravel);
        }

    }

    public void addPersonagem(Personagem umPersonagem) {
        e.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        e.remove(umPersonagem);
    }

    public Graphics getGraphicsBuffer(){
        return g2;
    }
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*************Desenha cenário de fundo**************/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "fundo/fundo.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!this.e.isEmpty()) {
            this.cj.desenhaTudo(e);
            this.cj.processaTudo(e);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
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
            this.e.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            try {
                File tanque = new File("c:\\temp\\POO.zip");
                FileInputStream canoOut = new FileInputStream(tanque);
                GZIPInputStream compactador = new GZIPInputStream(canoOut);
                ObjectInputStream serializador = new ObjectInputStream(compactador);
                this.e = (ArrayList<Personagem>)serializador.readObject();
                this.lSkooter = (Skooter)this.e.get(0);
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
                serializador.writeObject(this.e);
                serializador.flush();
                serializador.close();
            } catch (IOException ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            lSkooter.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            lSkooter.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            lSkooter.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            lSkooter.moveRight();
        }
        if (!cj.ehPosicaoValida(this.e, lSkooter.getPosicao())) {
            lSkooter.voltaAUltimaPosicao();
        }

        this.setTitle("-> Cell: " + (lSkooter.getPosicao().getColuna()) + ", "
                + (lSkooter.getPosicao().getLinha()));

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
