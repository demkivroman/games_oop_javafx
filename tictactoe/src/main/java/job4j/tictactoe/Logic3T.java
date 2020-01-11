package job4j.tictactoe;

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
        return this.fillBy(Figure3T::hasMarkX, 0, 0, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 0, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0,0, 1, 1) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1 , 0, -1, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0 , this.table.length - 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, this.table.length - 1, 0, 0, 1) ||
                this.fillBy(Figure3T::hasMarkX, 0, 1, 1, 0) ||
                this.fillBy(Figure3T::hasMarkX, 1, 0, 0, 1);
    }
    /*
     Method - this method is made by loop, because matrix can be more in size,
     then 3*3
     */
    public boolean isWinnerO() {
        boolean rsl = false;
        for (int row = 0; row < this.table.length; row++) {
            for (int cell = 0; cell < this.table.length; cell++) {
                if (this.table.length - row == this.table.length) {
                    if (this.fillBy(Figure3T::hasMarkO, row, cell, 1, 0)) {
                        rsl = true;
                        break;
                    }
                }
                if (this.table.length - cell == this.table.length) {
                    if (this.fillBy(Figure3T::hasMarkO, row, cell, 0, 1)) {
                        rsl = true;
                        break;
                    }
                }
                if (this.table.length - row == this.table.length &&
                               this.table.length - cell == this.table.length) {
                    if (this.fillBy(Figure3T::hasMarkO, row, cell, 1, 1)) {
                        rsl = true;
                        break;
                    }
                }

            }
        }
        return rsl;
    }

    public boolean hasGap() {
        return true;
    }
}
