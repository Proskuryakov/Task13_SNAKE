package ru.vsu.cs.vvp.eproskuryakov.snake.game;

import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.Cell;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.Direction;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

public class GameController {

    private final Game game;

    public GameController(Game game) {
        this.game = game;
    }

    public List<Cell> getCellToDraw() {
        return Stream.concat(
            Stream.of(game.getFood()),
            game.getSnake().stream()
        ).toList();
    }

    public void updateDirection(Direction direction) {
        game.setDirection(direction);
    }

    public Color getCellColor(Cell cell) {
        return switch (cell.getType()) {
            case FOOD -> Color.RED;
            case BODY -> Color.GREEN;
            case HEAD -> Color.BLUE;
            default -> Color.BLACK;
        };
    }

    public void changeGameState() {
        if (game.isStart()) {
            game.stop();
        } else {
            game.start();
        }
    }

    public void start() {
        game.start();
    }

    public void stop() {
        game.stop();
    }

    public void restart() {
        game.stop();
        game.restart();
    }

    public boolean isPlaying() {
        return game.isStart();
    }

    public boolean nextStep() {
        if (isPlaying()) {
            return game.move();
        }
        return true;
    }

}
