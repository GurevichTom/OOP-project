package features;

public class Action1 implements IteratorObserver{
    @Override
    public void onIterationEnd(String message) {
        System.out.println("Action1 got: " + message);
    }
}
