package com.pomodorotimer.service.impl;

import java.awt.Color;

import javax.swing.JLabel;

import com.pomodorotimer.service.TimerService;

public class TimerServiceImpl implements TimerService {

    /* 
     * Declara os minutos e segundos
     * Cria um label chamado timeLabel. Aqui será inserida a String time 
     * para ser atualizada na classe TimerController
     */
    static Integer seconds = 0;
    static Integer minutes = 15;
    static JLabel timeLabel = new JLabel();

    @Override
    public void countdown() {
        /* 
         * Formata os minutos e segundos para ter 0 a esquerda
         * Print apenas para debug
         * Depois declara a cor do texto e então adiciona o texto como time
         */
        String time = String.format("%02d:%02d", minutes, seconds);
        System.out.println(time);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setText(time);
    }

    /* 
     * Código porco de getters e setters para acessar os métodos no controller
     */
    public static Integer getSeconds() {
        return seconds;
    }

    public static void setSeconds(Integer seconds) {
        TimerServiceImpl.seconds = seconds;
    }

    public static Integer getMinutes() {
        return minutes;
    }

    public static void setMinutes(Integer minutes) {
        TimerServiceImpl.minutes = minutes;
    }

    public static JLabel getTimeLabel() {
        return timeLabel;
    }

    public static void setTimeLabel(JLabel timeLabel) {
        TimerServiceImpl.timeLabel = timeLabel;
    }
}
