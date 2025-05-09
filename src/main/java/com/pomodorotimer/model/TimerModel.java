package com.pomodorotimer.model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.pomodorotimer.service.AudioService;

public class TimerModel {

    private TimerImpl timerImpl = new TimerImpl();
    private AudioService audioService = new AudioService();
    private Boolean started;
    private Boolean paused;

    public TimerModel(TimerImpl timerImpl, AudioService audioService) {
        this.timerImpl = timerImpl;
        this.audioService = audioService;
    }

    public TimerModel() {}

    public void start() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        started = true;
        paused = false;
        audioService.audio();
    }

    public void pause() {
        paused = true;
        audioService.pauseAudio();
    }

    public void unpause() {
        paused = false;
        audioService.resumeAudio();
    }

    public void restart() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        paused = false;
        started = true;
        timerImpl.reset();
        audioService.audio();
    }

    public void resetTimeOnly() {
        paused = false;
        started = true;
        timerImpl.reset();
    }

    public void stopAudio() {
        audioService.stop(); 
    }

    public Boolean isStarted() {
        return started;
    }

    public Boolean isPaused() {
        return paused;
    }
}
