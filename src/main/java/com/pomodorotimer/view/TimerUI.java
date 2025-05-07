package com.pomodorotimer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.pomodorotimer.service.impl.TimerServiceImpl;

public class TimerUI {

    /* 
     * Cria o frame, painel, label
     * Acessa a classe TimerServiceImpl
     * Pega os valores de minutos e segundos
     */
    private JFrame frame;
    private JPanel panel;
    private TimerServiceImpl timerService = new TimerServiceImpl();
    private Timer timer;
    private JLabel timeLabel = new JLabel();
    Integer minutes = timerService.getMinutes();
    Integer seconds = timerService.getSeconds();

    public void timerScreen() {
        /*
         * Método que cria a tela do Pomodoro
         */
        frame = new JFrame();
        frame.setTitle("Pomodoro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,600);

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        timeLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setVerticalAlignment(SwingConstants.CENTER);
        timerService.setTimeLabel(timeLabel);
        
        panel.add(timerService.getTimeLabel(), BorderLayout.CENTER);
        panel.setBackground(Color.BLACK);

        frame.add(panel);
    
        ActionListener taskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timerService.setMinutes(minutes);
                timerService.setSeconds(seconds);
                timerService.formattedTime();
                if (minutes == 0 && seconds == 0) {
                    timer.stop();
                }
                if (seconds <= 0) {
                    minutes--;
                    seconds = 59;
                } else {
                    seconds--;
                }
            } 
        };
        timer = new Timer(1000, taskListener);
        timer.start();
        frame.setVisible(true);
    }
    
}
