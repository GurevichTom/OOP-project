package features;

public class PrintNamesCountedDuplicatesCommand implements Command{
    private final UserNameFeatures uf;

    public PrintNamesCountedDuplicatesCommand(UserNameFeatures uf) {
        this.uf = uf;
    }

    @Override
    public void execute() {
        uf.printNamesCountedDuplicates();
    }
}
