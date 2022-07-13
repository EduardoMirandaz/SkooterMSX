package Modelo;

public class BlocoQuebravel extends Bloco{
    public static Integer posicoesBlocosQuebraveis[][];

    public BlocoQuebravel(String sNomeImagePNG) {
        super(sNomeImagePNG);
    }

    public Integer[][] getPosicoesBlocosQuebraveis() {
        return posicoesBlocosQuebraveis;
    }

    public void setPosicoesBlocosQuebraveis(Integer[][] posicoesBlocosQuebraveis) {
        this.posicoesBlocosQuebraveis = posicoesBlocosQuebraveis;
    }
}
