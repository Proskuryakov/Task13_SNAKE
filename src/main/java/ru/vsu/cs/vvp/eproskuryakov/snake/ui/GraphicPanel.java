package ru.vsu.cs.vvp.eproskuryakov.snake.ui;


import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel {

    private final GameFieldGenerator gameFieldGenerator;

    public GraphicPanel(GameFieldGenerator gameFieldGenerator) {
        this.gameFieldGenerator = gameFieldGenerator;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image gameField = gameFieldGenerator.generate();
        setSize(gameField.getWidth(null), gameField.getHeight(null));

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(gameField, 0, 0, null);
    }


}
