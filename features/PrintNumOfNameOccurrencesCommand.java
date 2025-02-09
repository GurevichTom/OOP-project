package features;

public class PrintNumOfNameOccurrencesCommand implements Command{
    private final UserNameFeatures uf;

    public PrintNumOfNameOccurrencesCommand(UserNameFeatures uf) {
        this.uf = uf;
    }

    @Override
    public void execute() {
        uf.printNumOfNameOccurrences();
    }
}
