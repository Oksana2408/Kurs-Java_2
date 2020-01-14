/**
 * Домашнее задание к 3 уроку 2 курса Java
 * 08.01.2020
 * Оксана Марковская
 *
 * 1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся). Найти и вывести
 * список уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое
 * слово.
 * 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. В этот
 * телефонный справочник с помощью метода add() можно добавлять записи. С помощью метода get() искать номер телефона
 * по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев), тогда
 * при запросе такой фамилии должны выводиться все телефоны.
 * */

package Lesson_3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainHW {
    public static void main(String[] args) {
        String [] name = new String [] {"Anna", "Tom", "Lena", "Anna", "Alex", "Tom", "Anna", "Lisa", "Tom", "Nik"};

        Set <String> set = new HashSet<>();
        for (int i = 0; i < name.length ; i++) {
            set.add(name[i]);
        }
        System.out.println(set);

        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < name.length ; i++) {
            String getName = name[i];
            Integer current = hm.get(getName);
            hm.put(getName, current == null ? 1 : current + 1);
            }
        System.out.println(hm);

        TelephoneBook tb = new TelephoneBook();
        tb.add("Иванов", "05551 25356");
        tb.add("Сидоров", "05551 236154");
        tb.add("Иванов", "05551 687258");

        System.out.println(tb.get("Иванов"));
    }
}