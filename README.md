# 🗡️ Text Adventure Game

A Java-based text adventure game where you explore an ancient castle to find the legendary Golden Treasure! This classic command-line game features colorful ANSI output, room navigation, item management, and puzzle-solving elements.

## 📖 Game Story

You are an explorer who discovered an ancient castle filled with treasures. Your mission is to navigate through interconnected rooms, find the shiny key, and unlock the treasure room to claim the legendary Golden Treasure!

## ✨ Features

- **Interactive CLI Interface**: Colorful ANSI text output with emojis
- **Multiple Rooms**: Explore different interconnected locations
  - 🏰 Entrance Hall
  - 🍲 Kitchen
  - 📚 Library
  - 💎 Treasure Room
- **Inventory System**: Pick up, carry, and drop items
- **Puzzle Mechanics**: Find the key to unlock the treasure room
- **Health System**: Player health tracking
- **Command Shortcuts**: Quick commands for faster gameplay

## 🎮 How to Play

### Prerequisites

- Java 17 or higher
- Maven (for building the project)

### Running the Game

1. Clone the repository:
```bash
git clone https://github.com/Omm-Sharma/textadventure.git
cd textadventure
```

2. Compile and run using Maven:
```bash
mvn compile
mvn exec:java -Dexec.mainClass="game.Game"
```

Or compile and run directly with Java:
```bash
javac -d target/classes src/main/java/game/*.java
java -cp target/classes game.Game
```

## 🎯 Game Commands

| Command | Shortcut | Description |
|---------|----------|-------------|
| `go <direction>` | - | Move in specified direction (north/south/east/west) |
| `look` | `l` | Examine current room |
| `take <item>` | `t <item>` | Pick up an item |
| `drop <item>` | `d <item>` | Drop an item from inventory |
| `inventory` | `i` | View items in your inventory |
| `help` | `h` | Display available commands |
| `quit` | `q` | Exit the game |

## 🎯 Objective

1. Explore the castle rooms
2. Find the shiny key in the kitchen
3. Navigate to the library
4. Use the key to unlock and enter the treasure room
5. **Victory**: Reach the treasure room WITH the key!

## 🏗️ Project Structure

```
textadventure/
├── src/main/java/game/
│   ├── Game.java       # Main game logic and controller
│   ├── Room.java       # Room class with exits and items
│   ├── Player.java     # Player class with inventory and health
│   └── Command.java    # Command parser
├── pom.xml             # Maven configuration
└── README.md           # This file
```

## 💻 Technical Details

- **Language**: Java 17
- **Build Tool**: Maven
- **Architecture**: Object-oriented design with separate classes for game logic, rooms, player, and commands
- **Features**: 
  - ANSI color codes for enhanced terminal output
  - Scanner-based input handling
  - HashMap-based room navigation system
  - ArrayList-based inventory management

## 🎨 Game Mechanics

- **Health**: Starts at 100 (currently display-only)
- **Inventory**: Can carry multiple items
- **Win Condition**: Enter treasure room while holding the key
- **Room Navigation**: Connected rooms with directional exits

## 📝 Example Gameplay

```
> look
🏰 Entrance Hall
A grand hall with dusty chandeliers. Exits: north, east.
🚪 Exits: north east

> go north
🍲 Kitchen
Pots simmering on stoves. A shiny key on the table. Exits: south, west.
📦 Items: key
🚪 Exits: south west

> take key
✓ Taken: key

> go west
📚 Library
Bookshelves tower above. A locked door north requires a key. Exits: west, east, north.
🚪 Exits: west east north

> go north
💎 Treasure Room
Gold coins sparkle everywhere! Victory!

╔══════════════════════════════════════╗
║         🎉 VICTORY! 🎉              ║
║   Treasure found with the key!      ║
╚══════════════════════════════════════╝
```

## 🤝 Contributing

Feel free to fork this project and submit pull requests with improvements or new features!

## 👤 Author

**Omm Sharma**
- GitHub: [@Omm-Sharma](https://github.com/Omm-Sharma)

## 📜 License

This project is open source and available for educational purposes.

---

**Have fun exploring the castle and finding the treasure!** 🏰✨
