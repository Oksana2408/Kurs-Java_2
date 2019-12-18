package Lesson_1;

public class Robot implements toDo{
    private String name;
    private int maxHeight;
    private int maxLength;

    public Robot(String name, int maxHeight, int maxLength) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    @Override
    public void run(int distance) {
        if(distance <= maxLength){
            System.out.println( name + " успешно пробежал " +  distance + " метров.");
        } else {
            System.out.println(name + " не смог пробежать " + distance + " метров.");
        }
    }
    @Override
    public void jump(int height) {
        if(height <= maxHeight){
            System.out.println(name + " успешно прыгнул на " + height + " метров.");
        }else{
            System.out.println(name + " не смог прыгнуть на " + height + " метров.");
        }
    }
}
