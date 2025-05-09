package com.pomodorotimer.model;

import java.awt.Color;
import javax.swing.JLabel;

public class TimerImpl {
    /* 
     * Declara os minutos e segundos
     * Cria um label chamado timeLabel. Aqui será inserida a String time 
     * para ser atualizada na classe TimerController
     */
    private int seconds = 5;
    private int minutes = 0;

    private JLabel timeLabel = new JLabel();

    public void formattedTime() {
        /* 
         * Formata os minutos e segundos para ter 0 a esquerda
         * Print apenas para debug
         * Depois declara a cor do texto e então adiciona o texto como time
         */
        String time = String.format("%02d:%02d", minutes, seconds);
        //System.out.println(time);
        if (timeLabel != null) {
            timeLabel.setForeground(new Color(0, 0, 0, 200));
            timeLabel.setText(time);
        }
    }

    public void reset() {
        this.minutes = 0;
        this.seconds = 5;
        formattedTime();
    }

    //sem usar esse método por conta de testes
    public int getInitialMinutes() {
        return 15; 
    }
    /* 
     * Código porco de getters e setters para acessar os métodos no TimerUI
     */

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
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
