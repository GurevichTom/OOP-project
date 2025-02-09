package features;

public class PrintUniqueUsernamesTwiceBackwardsCommand implements Command{
    private final UserNameFeatures uf;

    public PrintUniqueUsernamesTwiceBackwardsCommand(UserNameFeatures uf) {
        this.uf = uf;
    }

    @Override
    public void execute() {
        uf.printUniqueUsernamesTwiceBackwards();
    }
}
