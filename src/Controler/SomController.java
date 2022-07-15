package Controler;

import java.io.File;
import javax.sound.sampled.*;

public class SomController {

    public void AudioCore() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sons/music.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); //Para repetir o som.
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }

    public static void tocarAudio(String nomeDoAudio) {
        try {
            Thread.sleep(50);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sons/"+nomeDoAudio).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Erro ao executar SOM!");
            ex.printStackTrace();
        }
    }
}