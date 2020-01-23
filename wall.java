package Lesson_1;

public class wall implements obstacle{
    private int height;

    public wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt( toDo participant) {
        participant.jump(height);
    }

}
