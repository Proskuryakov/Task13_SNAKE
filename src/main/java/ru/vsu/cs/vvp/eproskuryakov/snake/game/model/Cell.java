package ru.vsu.cs.vvp.eproskuryakov.snake.game.model;

import ru.vsu.cs.vvp.eproskuryakov.snake.Config;

import java.util.Objects;

public class Cell {

    private final int x;
    private final int y;
    private CellType type;

    public Cell(int x, int y, CellType type) {
        this.x = normalize(x);
        this.y = normalize(y);
        this.type = type;
    }

    public Cell(int x, int y) {
        this(x, y, CellType.EMPTY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private int normalize(int coordinate) {
        return (coordinate + Config.CELL_COUNT) % Config.CELL_COUNT;
    }

}
