package job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true;
        for (int index = 0; index != this.table.length; index++) {
            Figure3T cell = this.table[startX][startY];
            startX += deltaX;
            startY += deltaY;
            if (!predicate.test(cell)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isWinnerX() {
        return this.isWin(Figure3T::hasMarkX);
    }

    public boolean isWinnerO() {
        return this.isWin(Figure3T::hasMarkO);
    }
    public boolean isWin(Predicate<Figure3T> condition) {
        return this.fillBy(condition, 0, 0, 1, 0) ||
                this.fillBy(condition, 0, 0, 0, 1) ||
                this.fillBy(condition, 0,0, 1, 1) ||
                this.fillBy(condition, this.table.length - 1 , 0, -1, 1) ||
                this.fillBy(condition, 0 , this.table.length - 1, 1, 0) ||
                this.fillBy(condition, this.table.length - 1, 0, 0, 1) ||
                this.fillBy(condition, 0, 1, 1, 0) ||
                this.fillBy(condition, 1, 0, 0, 1);
    }

    public boolean hasGap() {
        return Arrays.stream(this.table).flatMap(Arrays::stream).anyMatch(n -> n.hasMarkO() == false &&
                n.hasMarkX() == false);
    }
}
