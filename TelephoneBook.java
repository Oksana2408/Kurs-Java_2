package Lesson_3;


import java.util.ArrayList;
import java.util.HashMap;

public class TelephoneBook{

   HashMap<String, ArrayList<String>> hm = new HashMap();

    void add(String name, String telNum){
        ArrayList<String> telList = hm.get(name);
        if (telList == null) telList = new ArrayList<>();
        telList.add(telNum);
        hm.put(name, telList);
    }
    ArrayList<String> get (String name){
       return hm.get(name);
    }
}
