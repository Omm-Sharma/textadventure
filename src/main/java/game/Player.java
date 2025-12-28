package game;
import java.util.*;

public class Player {
    private List<String> inventory;
    private int health = 100;

    public Player() {
        inventory = new ArrayList<>();
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public boolean hasInInventory(String item) {
        return inventory.contains(item);
    }

    public String removeFromInventory(String item) {
        if (inventory.remove(item)) {
            return item;
        }
        return null;
    }

    public List<String> getInventory() {
        return new ArrayList<>(inventory);
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }
}
