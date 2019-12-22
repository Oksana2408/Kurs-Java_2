package Lesson_2;

public class MySizeArrayException extends RuntimeException {

    public MySizeArrayException() {
        super(String.format("Заданный массив имеет неправильный размер"));
    }
}
