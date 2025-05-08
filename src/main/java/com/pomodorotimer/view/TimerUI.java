package com.pomodorotimer.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.pomodorotimer.model.TimerModel;
import com.pomodorotimer.service.AudioService;
import com.pomodorotimer.service.impl.TimerServiceImpl;

public class TimerUI {

    /*
     * Cria o frame, painel, label
     * Acessa a classe TimerServiceImpl
     * Pega os valores de minutos e segundos
     */

    private boolean start = false;
    private boolean paused = false;

    private JFrame frame;
    private JPanel panel;
    private Timer timer;
    private JButton startButton;
    private JButton pauseButton;
    private JButton unpauseButton;
    private JButton restartButton;
    private JPanel buttonPanel;
    private JLabel timeLabel;

    private Image icon;
    private BufferedImage originalImage;
    private Image scaledImage;

    private TimerServiceImpl timerService = new TimerServiceImpl();
    private TimerModel timerModel = new TimerModel(timerService, new AudioService());

    private ActionListener taskListener;
    
    public void timerScreen() throws IOException {
        System.out.println("Memória usada (MB): " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        /*
        * Método que cria a tela do Pomodoro
        * Insere ícone personalizado
        * Insere uma imagem de fundo reescalada para o tamanho do frame
        */
        frame = new JFrame();
        icon = ImageIO.read(new File("D:\\Downloads\\pomodoro-icon.png"));
        originalImage = ImageIO.read(new File("D:\\Downloads\\tomato.png"));
        scaledImage = originalImage.getScaledInstance(500, 600, Image.SCALE_SMOOTH);
        /*
         * Cria um JPanel com fundo customizado sobrescrevendo paintComponent.
         * O Graphics 'g' permite desenhar a imagem no painel como fundo.
         * A imagem é redimensionada para ocupar toda a área do painel, começando da esquerda
         * para a direita.
         */
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImage, 0, 0, this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        /*
        * Botões de
        * Start
        * Pause
        * Unpause
        * Restart
        */
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        unpauseButton = new JButton("Unpause");
        restartButton = new JButton("Restart");
        
        frame.setIconImage(icon);
        frame.setTitle("Pomodoro");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);

        // panel = new JPanel();
        //Adiciona a imagem ao painel para ser a imagem de fundo
        panel = backgroundPanel;
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BorderLayout());

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setVerticalAlignment(SwingConstants.CENTER);
        timerService.setTimeLabel(timeLabel);
        
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);
        
        startButton.addActionListener(e -> {
            try {

                if (timerService.getMinutes() == 0 && timerService.getSeconds() == 0) {
                    timerModel.resetTimeOnly(); // reseta o tempo E para som, se necessário
                    timerService.formattedTime();
                    start = false; // permite tocar som de novo na próxima execução
                }

                if (!start) {
                    timerModel.start(); // toca o som 1x
                    timer.start();
                    start = true;
                }

                paused = false;
                startButton.setEnabled(false);

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                JOptionPane.showMessageDialog(null, "Error playing audio: " + e1.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });
        pauseButton.addActionListener(e -> {

            timerModel.pause();
            paused = true;

        });
        unpauseButton.addActionListener(e -> {

            timerModel.unpause();
            paused = false;

        });
        restartButton.addActionListener(e -> {
            try {

                if (timer != null && timer.isRunning()) {
                    timer.stop();
                }
                timer = null;

                //timer.stop();
                timerModel.restart(); // reinicia a lógica interna
                timerModel.stopAudio();

                timerService.formattedTime();

                timer = new Timer(1000, taskListener);
                timer.start();

                start = true;
                paused = false;
                startButton.setEnabled(false);

                System.gc();
                System.out.println("Memória usada (MB): " + (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024);
            
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                JOptionPane.showMessageDialog(null, "Error playing audio: " + e1.getMessage(), "Error",
                JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 18);

        Color startColor = new Color(0x28a745); // Verde
        Color pauseColor = new Color(0xffc107); // Amarelo
        Color unpauseColor = new Color(0x17a2b8); // Azul claro
        Color restartColor = new Color(0xdc3545); // Vermelho
        
        Color textColor = Color.WHITE;
        
        JButton[] buttons = { startButton, pauseButton, unpauseButton, restartButton };
        Color[] colors = { startColor, pauseColor, unpauseColor, restartColor };
        
        for (int i = 0; i < buttons.length; i++) {
            JButton btn = buttons[i];
            Color originalColor = colors[i];
            btn.setFont(buttonFont); // aplica a fonte a cada botão
            btn.setBackground(originalColor); // cor de fundo
            btn.setForeground(textColor); // cor do texto
            btn.setFocusPainted(false); // Remove borda de foco/contorno azul ao clicar
            btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding interno
            btn.setOpaque(true); // Garante que o fundo seja colorido
            
            btn.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    btn.setBackground(originalColor.brighter()); // torna mais clara
                }
                
                @Override
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    btn.setBackground(originalColor); // volta ao normal
                }
            });
        }
        
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(unpauseButton);
        buttonPanel.add(restartButton);
        
        panel.add(timerService.getTimeLabel(), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.setBackground(Color.BLACK);
        
        frame.add(panel);
        
        taskListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (start && !paused) {
                    int minutes = timerService.getMinutes();
                    int seconds = timerService.getSeconds();
                    if (minutes == 0 && seconds == 0) {
                        timer.stop();
                        timerModel.stopAudio();
                        startButton.setEnabled(true);
                        return;
                    }
                    if (seconds == 0) {
                        minutes--;
                        seconds = 59;
                    } else {
                        seconds--;
                    }
                    timerService.setMinutes(minutes);
                    timerService.setSeconds(seconds);
                    timerService.formattedTime();
                }
            }
        };

        timer = new Timer(1000, taskListener);
        timer.start();
        timerService.formattedTime();
        frame.setVisible(true);
    }
}
