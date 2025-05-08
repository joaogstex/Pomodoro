package com.pomodorotimer.service;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioService {
    
    private Clip clip;
    private long clipPosition = 0;
    
    public void audio() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (clip != null && clip.isRunning()) {
            clip.stop(); //garante que o anterior pare, precisamente para o restart
        }
        //cria um novo som, zera a posição e toca
        File audioFile = new File("D:\\Downloads\\lofi-music.wav");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        clip = AudioSystem.getClip(); //cria um objeto de som reutilizável
        clip.open(audioInputStream); //carrega o som
        clip.setMicrosecondPosition(0); //começa do início
        clip.start();
    }

    public void pauseAudio() {
        if (clip != null && clip.isRunning()) {
            clipPosition = clip.getMicrosecondPosition(); //salva o ponto atual
            clip.stop(); //pausa nessa parte
        }
    }

    public void resumeAudio() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(clipPosition); //volta de onde parou
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.setFramePosition(0); // volta ao início
            clip.flush();
            clip.close();
            clip = null;
        }
    }
}
