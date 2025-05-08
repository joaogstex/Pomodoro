package com.pomodorotimer;

import java.io.IOException;

import com.pomodorotimer.view.TimerUI;

public class Main {
    public static void main(String[] args) throws IOException {
        TimerUI timerUI = new TimerUI();
        timerUI.timerScreen();
    }
}