package ru.job4j;

import org.junit.Test;
import ru.job4j.chess.Logic;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BishopBlackTest {
    @Test
    public void whenTestPosition() {
        BishopBlack bishop = new BishopBlack(Cell.A1);
        Cell cell = bishop.position();

        assertThat(
                Cell.A1,
                is(cell)
        );
    }

    @Test
    public void whenCheckCopyFigure() {
        BishopBlack bishop = new BishopBlack(Cell.F6);
        Figure copyBishop = bishop.copy(Cell.E5);

        assertThat(
                Cell.E5,
                is(copyBishop.position())
        );
    }

    @Test
    public void whenChekWay() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        Cell[] way = bishop.way(Cell.C1, Cell.G5);
        Cell[] expected = new Cell[] {Cell.D2, Cell.E3, Cell.F4, Cell.G5};

        assertThat(
                way,
                is(expected)
        );
    }

    @Test
    public void whenCheckIsDiagonal() {
        BishopBlack bishop = new BishopBlack(Cell.G8);
        Boolean isDiagonal = bishop.isDiagonal(Cell.G8, Cell.A2);

        assertThat(
                isDiagonal,
                is(true)
        );
    }
    @Test
    public void whenCheckIsNotDiagonal() {
        BishopBlack bishop = new BishopBlack(Cell.C1);
        try {
            Cell[] way = bishop.way(Cell.C1, Cell.C2);
        } catch (IllegalStateException ex) {
            assertThat(
                    ex.getMessage(),
                    is(String.format("Could not way by diagonal from %s to %s", Cell.C1, Cell.C2))
            );
        }
    }

    @Test
    public void whenCheckWayIsFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        logic.add(new BishopBlack(Cell.D2));
        boolean expected = logic.move(Cell.C1, Cell.G5);

        assertThat(
                expected,
                is(false)
        );
    }

    @Test
    public void whenCheckWayIsTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        boolean expected = logic.move(Cell.C1, Cell.G5);

        assertThat(
                expected,
                is(true)
        );
    }

}
