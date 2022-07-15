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

import Fases.*;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static Controler.MatrizObjetos.matrizDeObjetos;

public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {

    private ControleDeJogo controleDeJogo = new ControleDeJogo();

    private Skooter skooter;

    private Graphics graphics_1;

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
        this.setSize((Consts.RESOLUCAO + Consts.LARGURA_MENU) * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RESOLUCAO * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);


        TelaInicial.setMatrizParaTelaInicial();
        skooter = MatrizObjetos.getSkooter();
    }

    public Graphics getGraphicsBuffer(){
        return graphics_1;
    }
    public void paint(Graphics gOld) {
        Graphics graphics_2 = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        graphics_1 = graphics_2.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*************Desenha cenário de fundo**************/
        for (int i = 0; i < Consts.RESOLUCAO; i++) {
            for (int j = 0; j < Consts.RESOLUCAO + Consts.LARGURA_MENU; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "fundo/fundo.png");
                    graphics_1.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        if (!MatrizObjetos.isEmpty(MatrizObjetos.getMatrizDeObjetos())) {
            this.controleDeJogo.desenhaTudo(matrizDeObjetos);
        }


        graphics_2.dispose();
        graphics_1.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
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
                File tanque = new File("c:\\temp\\SkooterMSX.zip");
                if(!tanque.exists() || tanque.isDirectory()){
                    return;
                }
                FileInputStream canoOut = new FileInputStream(tanque);
                GZIPInputStream compactador = new GZIPInputStream(canoOut);
                ObjectInputStream serializador = new ObjectInputStream(compactador);
                MatrizObjetos.setMatrizDeObjetos((Personagem[][])serializador.readObject());
                skooter= MatrizObjetos.getSkooter();
                serializador.close();
            } catch (Exception ex) {
                Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            try {
                File tanque = new File("c:\\temp\\SkooterMSX.zip");
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
            if(skooter != null && !skooter.isFlagEasterEgg())
                skooter.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(skooter != null && !skooter.isFlagEasterEgg())
                skooter.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(skooter != null && !skooter.isFlagEasterEgg())
                skooter.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(skooter != null && !skooter.isFlagEasterEgg())
                skooter.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (skooter == null){
                Fase1.setMatrizParaFase1();
                skooter = MatrizObjetos.getSkooter();
                skooter.telaFlag+=1;
            }
            else{
                skooter.breakBlock();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            skooter.setFlagEasterEgg(false);
            MatrizObjetos.setMatrizDeObjetos(10,0, skooter);
            MatrizObjetos.getMatrizDeObjetos()[10][0].setPosicao(10,0);
        } else if (e.getKeyCode() == KeyEvent.VK_R) {
            TelaInicial.setMatrizParaTelaInicial();
            skooter = MatrizObjetos.getSkooter();
        }

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
        setTitle("POO 2022-1 - Skooter MSX");
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
