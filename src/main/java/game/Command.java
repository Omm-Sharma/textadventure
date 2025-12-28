package game;
public class Command {
    private String commandWord;
    private String secondWord;

    public Command(String inputLine) {
        String[] words = inputLine.toLowerCase().trim().split("\\s+");
        if (words.length > 0) {
            commandWord = words[0];
            secondWord = (words.length > 1) ? words[1] : null;
        }
    }

    public String getCommandWord() {
        return commandWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public boolean isUnknown() {
        return commandWord == null;
    }
}
