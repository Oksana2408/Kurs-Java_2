package Lesson_2;

public class MyArrayDataException extends RuntimeException{
    private int row;
    private int column;

    public MyArrayDataException(int row, int column) {
        super(String.format("Ошибка в ячейке " + row + " " + column));
        this.row = row;
        this.column = column;
    }
}
