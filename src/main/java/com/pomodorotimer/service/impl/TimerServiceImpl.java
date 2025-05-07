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
    private Integer seconds = 0;
    private Integer minutes = 15;
    private JLabel timeLabel = new JLabel();

    @Override
    public void formattedTime() {
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
    public TimerServiceImpl(Integer seconds, Integer minutes, JLabel timeLabel) {
        this.seconds = seconds;
        this.minutes = minutes;
        this.timeLabel = timeLabel;
    }

    public TimerServiceImpl() {}

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public void setTimeLabel(JLabel timeLabel) {
        this.timeLabel = timeLabel;
    }
}
