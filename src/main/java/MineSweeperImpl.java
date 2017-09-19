import java.util.Arrays;

public class MineSweeperImpl implements MineSweeper {
    private char[][] mineField;

    public char[][] getMineField() {
        return mineField;
    }

    public void setMineField(String mineField) throws IllegalArgumentException {
        if (mineField == null || mineField.equals("")) {
            throw new IllegalArgumentException("Cannot process empty string and null!");
        }
        String[] rows = mineField.split("\n");

        if (Arrays.stream(rows)
                .anyMatch(s -> s.length() != rows[0].length())) {
            throw new IllegalArgumentException("Two rows cannot have different length!");
        }
        this.mineField = new char[rows.length][rows[0].length()];

        for (int i = 0; i < rows.length; i++) {
            this.mineField[i] = rows[i].toCharArray();
        }
    }

    public String getHintField() throws IllegalStateException {
        if (mineField == null) {
            throw new IllegalStateException("mine-field has not been initialised!");
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.mineField.length; i++) {
            for (int j = 0; j < this.mineField[i].length; j++) {
                if (this.mineField[i][j] == '*') {
                    result.append("*");
                    continue;
                }
                result.append(getAdjacentMines(i, j));
            }
            result.append('\n');
        }

        return result.deleteCharAt(result.length()-1).toString();
    }

    private int getAdjacentMines(int i, int j) {
        int adjacentMines = 0;
        for (int k = -1; k < 2; k++) {
            if (i + k < 0 || i + k > this.mineField.length - 1) {
                continue;
            }
            for (int l = -1; l < 2; l++) {
                if (j + l < 0 || j + l > this.mineField[i].length - 1) {
                    continue;
                }
                if (this.mineField[i + k][j + l] == '*') {
                    adjacentMines++;
                }
            }
        }
        return adjacentMines;
    }
}
