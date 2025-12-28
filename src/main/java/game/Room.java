package game;
import java.util.*;

public class Room {
    public String description;  // public for look() access
    public Map<String, Room> exits;  // public for look() access
    private List<String> items;

    public Room(String description) {
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public boolean hasExit(String direction) {
        return exits.containsKey(direction);
    }

    public void addItem(String item) {
        items.add(item);
    }

    public boolean hasItem(String item) {
        return items.contains(item);
    }

    public String removeItem(String item) {
        if (items.remove(item)) {
            return item;
        }
        return null;
    }

    public List<String> getItems() {
        return new ArrayList<>(items);
    }
}
