package features;

public class Action2 implements IteratorObserver{
    @Override
    public void onIterationEnd(String message) {
        System.out.println("Action2 got: " + message);
    }
}
