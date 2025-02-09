package features;

public class PrintNamesCommand implements Command{
    private final UserNameFeatures uf;

    public PrintNamesCommand(UserNameFeatures uf) {
        this.uf = uf;
    }

    @Override
    public void execute() {
        uf.printNames();
    }
}
