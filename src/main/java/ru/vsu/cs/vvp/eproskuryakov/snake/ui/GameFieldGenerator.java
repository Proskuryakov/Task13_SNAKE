package ru.vsu.cs.vvp.eproskuryakov.snake.ui;

import ru.vsu.cs.vvp.eproskuryakov.snake.Config;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.Cell;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.GameController;

import java.awt.*;
import java.awt.image.BufferedImage;


public class GameFieldGenerator {

    private static final int RECTANGLE_CORNER_ARC = 5;

    private final int width;
    private final int height;
    private final GameController controller;

    public GameFieldGenerator(GameController controller) {
        this.controller = controller;
        int side = Config.CELL_COUNT * Config.CELL_SIZE;
        this.width = side;
        this.height = side;
    }

    public Image generate() {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = (Graphics2D) image.getGraphics();

        paintGameNet(g2);
        fillGameNet(g2);

        return image;
    }

    private void paintGameNet(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, width - 1, height - 1);

        g2.setColor(Color.DARK_GRAY);
        g2.drawRect(0, 0, width - 1, height - 1);

        int x = 0, y = 0;

        for (int i = 0; i < Config.CELL_COUNT; i++) {
            g2.drawLine(x, 0, x, height);
            g2.drawLine(0, y, width, y);
            x += Config.CELL_SIZE;
            y += Config.CELL_SIZE;
        }
    }

    private void fillGameNet(Graphics2D g2) {
        for (Cell cell : controller.getCellToDraw()) {
            g2.setColor(controller.getCellColor(cell));
            g2.fillRoundRect(
                cell.getX() * Config.CELL_SIZE,
                cell.getY() * Config.CELL_SIZE,
                Config.CELL_SIZE, Config.CELL_SIZE,
                RECTANGLE_CORNER_ARC, RECTANGLE_CORNER_ARC
            );
        }
    }


}
