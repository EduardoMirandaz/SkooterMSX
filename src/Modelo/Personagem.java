/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.MatrizObjetos;
import Controler.Tela;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public abstract class Personagem implements Serializable {

    protected ImageIcon iImage;
    protected ImageIcon iFrente;
    protected ImageIcon iTras;
    protected ImageIcon iDireita;
    protected ImageIcon iEsquerda;
    protected Posicao pPosicao;
    protected boolean bTransponivel; /*Pode passar por cima?*/
    protected boolean bMortal;       /*Se encostar, o Bomberman morre?*/


    protected Personagem(String imagemFrente, String imagemTras, String imagemDireita, String imagemEsquerda) {
        this.pPosicao = new Posicao(0, 0);
        this.bTransponivel = true;
        this.bMortal = false;
        try {
            iFrente = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imagemFrente);
            Image imgF = iFrente.getImage();
            BufferedImage biF = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics gF = biF.createGraphics();
            gF.drawImage(imgF, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iFrente = new ImageIcon(biF);

            iTras = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imagemTras);
            Image imgT = iTras.getImage();
            BufferedImage biT = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics gT = biT.createGraphics();
            gT.drawImage(imgT, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iTras = new ImageIcon(biT);

            iDireita = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imagemDireita);
            Image imgD = iDireita.getImage();
            BufferedImage biD = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics gD = biD.createGraphics();
            gD.drawImage(imgD, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iDireita = new ImageIcon(biD);

            iEsquerda = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + imagemEsquerda);
            Image imgE = iEsquerda.getImage();
            BufferedImage biE = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics gE = biE.createGraphics();
            gE.drawImage(imgE, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iEsquerda = new ImageIcon(biE);

            iImage = iFrente;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected Personagem(String image) {
        this.pPosicao = new Posicao(0, 0);
        this.bTransponivel = true;
        this.bMortal = false;
        try {
            iFrente = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + image);
            Image imgF = iFrente.getImage();
            BufferedImage biF = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics gF = biF.createGraphics();
            gF.drawImage(imgF, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iFrente = new ImageIcon(biF);

            iImage = iFrente;
            iDireita = iFrente;
            iEsquerda = iFrente;
            iTras = iFrente;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Posicao getPosicao() {
        /*TODO: Retirar este método para que objetos externos nao possam operar
         diretamente sobre a posição do Personagem*/
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }

    public void autoDesenho(){
        Desenho.desenhar(this.iImage, pPosicao.getColuna(), pPosicao.getLinha());        
    }

    public boolean setPosicao(int linha, int coluna) {
        return pPosicao.setPosicao(linha, coluna);
    }

    public boolean moveUp() {
        this.iImage = this.iTras;
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.moveUp();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;
    }

    public boolean moveDown() {
        iImage = iFrente;
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.moveDown();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;
    }

    public boolean moveRight() {
        iImage = iDireita;
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.moveRight();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;    }

    public boolean moveLeft() {
        this.iImage = this.iEsquerda;
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.moveLeft();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;    }

    public boolean breakBlock() {
        return this.pPosicao.breakBlock();
    }
}
