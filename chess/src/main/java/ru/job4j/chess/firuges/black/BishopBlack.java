package ru.job4j.chess.firuges.black;

import javafx.scene.control.skin.CellSkinBase;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        if (!isDiagonal(source, dest)) {
            throw new IllegalStateException(
                    String.format("Could not way by diagonal from %s to %s", source, dest)
            );
        }
        int size = Math.abs(source.x - dest.x);
        Cell[] steps = new Cell[size];
        int deltaX = ( source.x - dest.x < 0 ) ? 1 : -1;
        int deltaY = ( source.y - dest.y < 0 ) ? 1 : -1;
        Cell temp = source;
        for (int index = 0; index < size; index++) {
            for (Cell cell : Cell.values()) {
                if (cell.x == temp.x + deltaX && cell.y == temp.y + deltaY) {
                    steps[index] = cell;
                    temp = cell;
                    break;
                }
            }
        }

       return steps;

    }

    public boolean isDiagonal(Cell source, Cell dest) {
        //TODO check diagonal
        return (Math.abs(source.x - dest.x) == Math.abs(source.y - dest.y)) ? true : false;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
