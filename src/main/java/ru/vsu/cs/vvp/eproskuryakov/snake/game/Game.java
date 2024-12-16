package ru.vsu.cs.vvp.eproskuryakov.snake.game;

import ru.vsu.cs.vvp.eproskuryakov.snake.Config;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.Cell;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.CellType;
import ru.vsu.cs.vvp.eproskuryakov.snake.game.model.Direction;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

public final class Game {

    private final Random random = new Random();

    private Cell food;
    private Deque<Cell> snake;
    private Direction direction;

    private boolean isStart = false;

    public Game() {
        initState();
    }

    public void start() {
        isStart = true;
    }

    public void restart() {
        initState();
    }

    public void stop() {
        isStart = false;
    }

    public boolean isStart() {
        return isStart;
    }

    private void initState() {
        initSnake();
        setFood();
        direction = Direction.RIGHT;
    }

    private void initSnake() {
        snake = new LinkedList<>();
        Cell head = new Cell(1, 0, CellType.HEAD);
        Cell tail = new Cell(0, 0, CellType.BODY);
        snake.add(head);
        snake.add(tail);
    }

    private void setFood() {
        int x = random.nextInt(Config.CELL_COUNT);
        int y = random.nextInt(Config.CELL_COUNT);

        food = new Cell(x, y, CellType.FOOD);
        if (snake.contains(food)) {
            setFood();
        }
    }

    public boolean move() {
        Cell head = snake.peekFirst();
        if (head == null) {
            throw new RuntimeException();
        }
        head.setType(CellType.BODY);

        Cell newHead = switch (direction) {
            case UP -> new Cell(head.getX(), head.getY() - 1);
            case DOWN -> new Cell(head.getX(), head.getY() + 1);
            case LEFT -> new Cell(head.getX() - 1, head.getY());
            case RIGHT -> new Cell(head.getX() + 1, head.getY());
        };
        newHead.setType(CellType.HEAD);

        if (snake.contains(newHead)) {
            return false;
        }

        snake.addFirst(newHead);

        if (newHead.equals(food)) {
            setFood();
        } else {
            snake.pollLast();
        }

        return true;
    }

    public void setDirection(Direction newDirection) {
        if (Direction.UP.equals(direction) && Direction.DOWN.equals(newDirection) ||
            Direction.DOWN.equals(direction) && Direction.UP.equals(newDirection) ||
            Direction.LEFT.equals(direction) && Direction.RIGHT.equals(newDirection) ||
            Direction.RIGHT.equals(direction) && Direction.LEFT.equals(newDirection)) {
            return;
        }

        this.direction = newDirection;
    }

    public Cell getFood() {
        return food;
    }

    public Deque<Cell> getSnake() {
        return snake;
    }

}
