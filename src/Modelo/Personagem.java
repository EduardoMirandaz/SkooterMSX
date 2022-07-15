package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.MatrizObjetos;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;



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
            iFrente = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.CAMINHO_IMAGENS + imagemFrente);
            Image imgF = iFrente.getImage();
            BufferedImage biF = new BufferedImage(Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, BufferedImage.TYPE_INT_ARGB);
            Graphics gF = biF.createGraphics();
            gF.drawImage(imgF, 0, 0, Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, null);
            iFrente = new ImageIcon(biF);

            iTras = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.CAMINHO_IMAGENS + imagemTras);
            Image imgT = iTras.getImage();
            BufferedImage biT = new BufferedImage(Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, BufferedImage.TYPE_INT_ARGB);
            Graphics gT = biT.createGraphics();
            gT.drawImage(imgT, 0, 0, Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, null);
            iTras = new ImageIcon(biT);

            iDireita = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.CAMINHO_IMAGENS + imagemDireita);
            Image imgD = iDireita.getImage();
            BufferedImage biD = new BufferedImage(Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, BufferedImage.TYPE_INT_ARGB);
            Graphics gD = biD.createGraphics();
            gD.drawImage(imgD, 0, 0, Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, null);
            iDireita = new ImageIcon(biD);

            iEsquerda = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.CAMINHO_IMAGENS + imagemEsquerda);
            Image imgE = iEsquerda.getImage();
            BufferedImage biE = new BufferedImage(Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, BufferedImage.TYPE_INT_ARGB);
            Graphics gE = biE.createGraphics();
            gE.drawImage(imgE, 0, 0, Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, null);
            iEsquerda = new ImageIcon(biE);

            iImage = iFrente;

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setiImage(String caminho) {
        try {
            ImageIcon imagemAux = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.CAMINHO_IMAGENS + caminho);
            Image imgF = imagemAux.getImage();
            BufferedImage biF = new BufferedImage(Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, BufferedImage.TYPE_INT_ARGB);
            Graphics gF = biF.createGraphics();
            gF.drawImage(imgF, 0, 0, Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, null);
            this.iImage = new ImageIcon(biF);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    protected Personagem(String image) {
        this.pPosicao = new Posicao(0, 0);
        this.bTransponivel = true;
        this.bMortal = false;
        try {
            iFrente = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.CAMINHO_IMAGENS + image);
            Image imgF = iFrente.getImage();
            BufferedImage biF = new BufferedImage(Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, BufferedImage.TYPE_INT_ARGB);
            Graphics gF = biF.createGraphics();
            gF.drawImage(imgF, 0, 0, Consts.TAMANHO_CELULA, Consts.TAMANHO_CELULA, null);
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
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.movimentarParaCima();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;
    }

    public boolean moveDown() {
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.movimentarParaBaixo();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;
    }

    public boolean moveRight() {
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.movimentarParaDireita();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;    }

    public boolean moveLeft() {
        int linhaAtual = this.pPosicao.getLinha();
        int colunaAtual = this.pPosicao.getColuna();
        boolean moveu = this.pPosicao.movimentarParaEsquerda();
        if(moveu){
            MatrizObjetos.setMatrizDeObjetos(linhaAtual,colunaAtual, null);
            MatrizObjetos.setMatrizDeObjetos(this.pPosicao.getLinha(),this.pPosicao.getColuna(), this);
        }
        return moveu;    }

    public boolean breakBlock() {
        return this.pPosicao.quebrarBloco();
    }
}
