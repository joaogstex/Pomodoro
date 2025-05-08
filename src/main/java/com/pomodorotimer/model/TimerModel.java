package com.pomodorotimer.model;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.pomodorotimer.service.AudioService;
import com.pomodorotimer.service.impl.TimerServiceImpl;

public class TimerModel {

    private TimerServiceImpl timerServiceImpl = new TimerServiceImpl();
    private AudioService audioService = new AudioService();
    private Boolean started;
    private Boolean paused;

    public TimerModel(TimerServiceImpl timerServiceImpl, AudioService audioService) {
        this.timerServiceImpl = timerServiceImpl;
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
        timerServiceImpl.reset();
        audioService.audio();
    }

    public void resetTimeOnly() {
        paused = false;
        started = true;
        timerServiceImpl.reset();
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
