package features;

public class SortByUsernameAndDisplayCommand implements Command{

    private final UserNameFeatures uf;

    public SortByUsernameAndDisplayCommand(UserNameFeatures uf) {
        this.uf = uf;
    }

    @Override
    public void execute() {
        uf.sortByUsernameAndDisplay();
    }
}
