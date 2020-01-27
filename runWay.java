package Lesson_1;

public class runWay implements obstacle{
    private int distance;

    public runWay(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(toDo participant) {
        participant.run(distance);
    }
}
