package game;

import java.util.*;

public class Game {
    private Player player;
    private Room currentRoom;
    private Room treasure;
    private boolean isRunning = true;
    private Scanner scanner;
    
    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String BLUE = "\u001B[34m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";
    private static final String CYAN = "\u001B[36m";
    private static final String BOLD = "\u001B[1m";
    private static final String PURPLE = "\u001B[35m";

    public Game() {
        scanner = new Scanner(System.in);
        createRooms();
        player = new Player();
    }

    private void createRooms() {
        Room entrance = new Room("🏰 " + BOLD + "Entrance Hall" + RESET + "\nA grand hall with dusty chandeliers. Exits: north, east.");
        Room kitchen = new Room("🍲 " + BOLD + "Kitchen" + RESET + "\nPots simmering on stoves. A " + GREEN + "shiny key" + RESET + " on the table. Exits: south, west.");
        Room library = new Room("📚 " + BOLD + "Library" + RESET + "\nBookshelves tower above. A locked door north requires a key. Exits: west, east, north.");
        treasure = new Room("💎 " + BOLD + "Treasure Room" + RESET + "\nGold coins sparkle everywhere! Victory!");

        entrance.setExit("north", kitchen);
        entrance.setExit("east", library);
        kitchen.setExit("south", entrance);
        kitchen.setExit("west", library);
        library.setExit("west", entrance);
        library.setExit("east", kitchen);
        library.setExit("north", treasure);
        treasure.setExit("south", library);

        kitchen.addItem("key");
        currentRoom = entrance;
    }

    public void play() {
        clearScreen();
        printTitle();
        printGameInfo();  // Game description & rules (NO MAP)
        
        System.out.println("\n" + YELLOW + BOLD + "Press Enter to start your adventure..." + RESET);
        scanner.nextLine();
        
        clearScreen();
        printTitle();
        printWelcome();
        
        while (isRunning) {
            printStatusBar();
            Command command = new Command(getInput());
            processCommand(command);
        }
        printGameOver();
        scanner.close();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
    }

    private void printTitle() {
        System.out.println(CYAN + BOLD + "╔══════════════════════════════════════╗" + RESET);
        System.out.println("║        🗡️  TEXT ADVENTURE GAME  🗡️       ║");
        System.out.println("║           Java CLI Edition            ║");
        System.out.println(CYAN + "╚══════════════════════════════════════╝" + RESET);
    }

    // UPDATED: Game Info WITHOUT map
    private void printGameInfo() {
        System.out.println("\n" + PURPLE + BOLD + "📜 GAME STORY" + RESET);
        System.out.println(GREEN + "You are an explorer who discovered an ancient castle filled with treasures.");
        System.out.println("Your mission: Find the legendary " + YELLOW + "Golden Treasure" + GREEN + " hidden deep within!");
        System.out.println();
        
        System.out.println(PURPLE + BOLD + "🎯 OBJECTIVE" + RESET);
        System.out.println("- Explore interconnected rooms");
        System.out.println("- Find the " + YELLOW + "shiny key" + RESET + " (hint: check the kitchen)");
        System.out.println("- Use the key to unlock the treasure room");
        System.out.println("- " + GREEN + BOLD + "Reach treasure room WITH KEY = VICTORY!" + RESET);
        
        System.out.println("\n" + PURPLE + BOLD + "📋 GAME RULES" + RESET);
        System.out.println("• Type commands in lowercase");
        System.out.println("• Health starts at 100");
        System.out.println("• Carry multiple items in inventory");
        System.out.println("• Type " + BOLD + "'help'" + RESET + " anytime for commands");
    }

    private void printWelcome() {
        System.out.println(YELLOW + "Welcome, brave adventurer!" + RESET);
        System.out.println(GREEN + "Type 'help' for commands. Good luck!" + RESET);
        System.out.println();
        look();
    }

    private void printStatusBar() {
        System.out.print(BLUE + "┌─ " + RESET);
        System.out.print(YELLOW + "❤️ HP: " + RESET + player.getHealth() + " ");
        System.out.print(GREEN + "🎒 Inv: " + RESET + player.getInventory().size() + " ");
        System.out.print(CYAN + currentRoom.description.split("\n")[0].trim() + RESET);
        System.out.println(BLUE + " ─┐" + RESET);
    }

    private String getInput() {
        System.out.print(CYAN + BOLD + "> " + RESET);
        return scanner.nextLine();
    }

    private void processCommand(Command command) {
        String word1 = command.getCommandWord();
        String word2 = command.getSecondWord();

        if (word1 == null) {
            printError("I don't understand...");
            return;
        }

        clearScreen();
        printTitle();
        printStatusBar();

        switch (word1) {
            case "quit":
            case "q":
                isRunning = false;
                break;
            case "look":
            case "l":
                look();
                break;
            case "help":
            case "h":
                printHelp();
                break;
            case "go":
                if (word2 == null) {
                    printError("Go where?");
                } else {
                    goRoom(word2);
                }
                break;
            case "take":
            case "t":
                if (word2 == null) {
                    printError("Take what?");
                } else {
                    takeItem(word2);
                }
                break;
            case "drop":
            case "d":
                if (word2 == null) {
                    printError("Drop what?");
                } else {
                    dropItem(word2);
                }
                break;
            case "inventory":
            case "i":
                showInventory();
                break;
            default:
                printError("Unknown command: '" + word1 + "'. Type 'help'.");
                break;
        }
    }

    private void printHelp() {
        System.out.println(GREEN + BOLD + "📋 COMMANDS" + RESET);
        System.out.println("  " + CYAN + "go north/south/east/west" + RESET + "   " + CYAN + "look/l" + RESET);
        System.out.println("  " + CYAN + "take/t key" + RESET + "          " + CYAN + "drop/d key" + RESET);
        System.out.println("  " + CYAN + "inventory/i" + RESET + "       " + CYAN + "help/h, quit/q" + RESET);
        System.out.println(YELLOW + "\n💡 Kitchen has the key you need!" + RESET + "\n");
    }

    private void look() {
        System.out.println("\n" + currentRoom.description);
        
        List<String> items = currentRoom.getItems();
        if (!items.isEmpty()) {
            System.out.print(GREEN + "📦 Items: " + RESET);
            for (String item : items) {
                System.out.print(YELLOW + item + RESET + " ");
            }
            System.out.println();
        }
        
        System.out.print(BLUE + "🚪 Exits: " + RESET);
        for (String dir : currentRoom.exits.keySet()) {
            System.out.print(dir + " ");
        }
        System.out.println("\n");
    }

    private void goRoom(String direction) {
        if (currentRoom.hasExit(direction)) {
            currentRoom = currentRoom.getExit(direction);
            look();
            
            if (currentRoom == treasure && player.hasInInventory("key")) {
                printVictory();
                isRunning = false;
            }
        } else {
            printError("You can't go that way.");
        }
    }

    private void takeItem(String item) {
        if (currentRoom.hasItem(item)) {
            String taken = currentRoom.removeItem(item);
            player.addToInventory(taken);
            System.out.println(GREEN + "✓ Taken: " + YELLOW + item + RESET + "\n");
        } else {
            printError("No such item here.");
        }
    }

    private void dropItem(String item) {
        if (player.hasInInventory(item)) {
            String dropped = player.removeFromInventory(item);
            currentRoom.addItem(dropped);
            System.out.println(RED + "✗ Dropped: " + YELLOW + item + RESET + "\n");
        } else {
            printError("You don't have that item.");
        }
    }

    private void showInventory() {
        List<String> inv = player.getInventory();
        if (inv.isEmpty()) {
            System.out.println(YELLOW + "👜 Inventory empty.\n");
        } else {
            System.out.print(GREEN + "👜 Inventory: " + RESET);
            for (String item : inv) {
                System.out.print(YELLOW + "[" + item + "] " + RESET);
            }
            System.out.println("\n");
        }
    }

    private void printError(String message) {
        System.out.println(RED + "❌ " + message + RESET + "\n");
    }

    private void printVictory() {
        System.out.println(GREEN + BOLD + "\n" + 
                          "╔══════════════════════════════════════╗\n" +
                          "║           🎉 VICTORY! 🎉              ║\n" +
                          "║    Treasure found with the key!      ║\n" + 
                          "╚══════════════════════════════════════╝" + RESET);
    }

    private void printGameOver() {
        System.out.println(YELLOW + BOLD + "Thanks for playing!" + RESET);
    }

    public static void main(String[] args) {
        new Game().play();
    }
}
