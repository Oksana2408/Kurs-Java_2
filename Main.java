/**
 * Домашнее задание ко 2 уроку 2 курса Java
 * Оксана Марковская
 * 20.12.2019
 *
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого
 * размера необходимо бросить исключение MyArraySizeException.
 *
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то
 * элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть
 * брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
 *
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
 * MyArrayDataException и вывести результат расчета.
 * */

package Lesson_2;

public class Main {

    public static void main(String[] args) {

        String[][] brr = new String[][]{
                {"18", "20", "58", "16"},
                {"24", "8", "4n5", "65"},
                {"25", "12", "85", "13"},
                {"12", "55", "48", "9"}
        };
        try {
            method(brr);
        }catch (MySizeArrayException e){
            e.printStackTrace();
        }catch (MyArrayDataException e){
            e.printStackTrace();
        }
    }

    private static void method(String[][] arr) {
        int sum = 0;
        if (arr.length != 4) {
            throw new MySizeArrayException();
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                try {int n = Integer.parseInt(arr[i][j]);
                    sum += n;
                }catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        System.out.println(sum);
    }
}

