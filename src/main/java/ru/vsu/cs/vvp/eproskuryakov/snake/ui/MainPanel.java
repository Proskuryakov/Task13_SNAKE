package ru.vsu.cs.vvp.eproskuryakov.snake.ui;

import ru.vsu.cs.vvp.eproskuryakov.snake.game.GameController;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static ru.vsu.cs.vvp.eproskuryakov.snake.Config.*;

public class MainPanel {

    private static final int COMPONENT_HEIGHT = 25;
    private static final int BUFFER_HEIGHT = 45;
    private static final int BUFFER_WIDTH = 14;

    private final GameController controller;
    private final GameFieldGenerator gameFieldGenerator;

    private int width;
    private int height;

    private JFrame frame;
    private JPanel panel;
    private JSlider delaySlider;
    private JButton startButton;
    private JButton restartButton;
    private JButton infoButton;
    private Timer timer;

    private static GraphicPanel graphicPanel;

    public MainPanel(GameController gameController, GameFieldGenerator gameFieldGenerator) {
        this.controller = gameController;
        this.gameFieldGenerator = gameFieldGenerator;

        calculateWindowSize();
        initComponent();
        initTimer();
        placeElements();
        setLookAndFeel();
        addListeners();


        frame.setVisible(true);
    }

    private void calculateWindowSize() {
        width = BUFFER_WIDTH + CELL_COUNT * CELL_SIZE;
        height = BUFFER_HEIGHT + CELL_COUNT * CELL_SIZE + COMPONENT_HEIGHT * 2;
    }

    private void initComponent() {
        frame = new JFrame(FRAME_TITLE_TEXT);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setFocusable(true);

        graphicPanel = new GraphicPanel(gameFieldGenerator);

        delaySlider = new JSlider(SLIDER_MIN, SLIDER_MAX);
        delaySlider.setValue(SLIDER_INIT);
        delaySlider.setSize(width, COMPONENT_HEIGHT);

        restartButton = new JButton(RESTART_BUTTON_TEXT);
        restartButton.setSize(width / 3, COMPONENT_HEIGHT);

        startButton = new JButton(START_BUTTON_TEXT);
        startButton.setSize(width / 3, COMPONENT_HEIGHT);

        infoButton = new JButton(INFO_BUTTON_TEXT);
        infoButton.setSize(width / 3, COMPONENT_HEIGHT);
    }

    private void initTimer() {
        timer = new Timer(getDelay(), e -> process());
        timer.start();
    }

    private void placeElements() {
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(restartButton);
        buttonPanel.add(infoButton);

        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(graphicPanel, BorderLayout.CENTER);
        panel.add(delaySlider, BorderLayout.SOUTH);

        frame.add(panel);
    }

    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void process() {
        boolean isGameOver = !controller.nextStep();
        if (isGameOver) {
            controller.stop();
            showGameOverWindow();
        }
        graphicPanel.repaint();
    }

    private void addListeners() {
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    controller.changeGameState();
                    updateStartButtonText();
                    panel.requestFocusInWindow();
                }

                if (controller.isPlaying()) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        controller.updateDirection(Direction.UP);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        controller.updateDirection(Direction.RIGHT);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        controller.updateDirection(Direction.DOWN);
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        controller.updateDirection(Direction.LEFT);
                    }
                }

                if (e.getKeyChar() == '=') {
                    delaySlider.setValue(delaySlider.getValue() + SLIDER_STEP);
                }
                if (e.getKeyChar() == '-') {
                    delaySlider.setValue(delaySlider.getValue() - SLIDER_STEP);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        delaySlider.addChangeListener(e -> {
            timer.setDelay(getDelay());
            panel.requestFocusInWindow();
        });

        infoButton.addActionListener(e -> {
            showInfoWindow();
            panel.requestFocusInWindow();
        });

        startButton.addActionListener(e -> {
            controller.changeGameState();
            updateStartButtonText();
            panel.requestFocusInWindow();
        });

        restartButton.addActionListener(e -> {
            controller.restart();
            graphicPanel.repaint();
            panel.requestFocusInWindow();
        });
    }

    private int getDelay() {
        return delaySlider.getMaximum() - delaySlider.getValue();
    }

    private void showInfoWindow() {
        controller.stop();
        JOptionPane.showOptionDialog(frame, INFO_WINDOW_CONTENT, INFO_WINDOW_TITLE,
            JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, null, null
        );
    }

    private void showGameOverWindow() {
        controller.stop();
        int request = JOptionPane.showOptionDialog(frame,
            GAME_OVER_WINDOW_CONTENT,
            GAME_OVER_WINDOW_TITLE,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, new String[]{"Да", "Нет"}, null

        );
        if (request == 0) {
            controller.restart();
        } else {
            frame.dispose();
            System.exit(0);
        }
    }

    private void updateStartButtonText() {
        startButton.setText(controller.isPlaying() ? STOP_BUTTON_TEXT : START_BUTTON_TEXT);
    }

}
