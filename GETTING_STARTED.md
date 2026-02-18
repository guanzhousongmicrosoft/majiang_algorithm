# Getting Started with Majiang Algorithm

Welcome to the Majiang (Mahjong) Algorithm library! This guide will help you get started with using this library in your projects.

## Overview

This library provides efficient algorithms for:
- **Hu (Win) Detection**: Check if a hand can win with given cards and wildcards
- **Ting (Ready) Detection**: Check which cards can complete a winning hand
- **AI Card Playing**: Intelligent card selection for playing Mahjong

The algorithms use pre-computed lookup tables for optimal performance, especially when dealing with multiple wildcard (gui/鬼牌) scenarios.

## Prerequisites

Before you begin, ensure you have the following installed:
- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: Version 3.0 or higher (for building from source or as a dependency management tool)

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version
```

## Installation

### Option 1: Using Maven (Recommended)

Add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.github.esrrhs</groupId>
    <artifactId>majiang_algorithm</artifactId>
    <version>1.0.15</version>
</dependency>
```

### Option 2: Building from Source

1. **Clone the repository**:
   ```bash
   git clone https://github.com/guanzhousongmicrosoft/majiang_algorithm.git
   cd majiang_algorithm
   ```

2. **Build the project**:
   ```bash
   mvn clean compile
   ```

3. **Create JAR package**:
   ```bash
   mvn package
   ```
   
   The JAR file will be generated in the `target/` directory.

4. **Install to local Maven repository** (optional):
   ```bash
   mvn clean install
   ```

## Quick Start

### 1. Loading Lookup Tables

Before using the algorithms, you need to load the pre-computed lookup tables:

```java
import com.github.esrrhs.majiang_algorithm.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// Load Hu (win detection) tables
HuTable.load(Files.readAllLines(Paths.get("majiang_clien_normal.txt")));
HuTableFeng.load(Files.readAllLines(Paths.get("majiang_clien_feng.txt")));
HuTableJian.load(Files.readAllLines(Paths.get("majiang_clien_jian.txt")));

// Load AI tables (for intelligent play)
AITable.load(Files.readAllLines(Paths.get("majiang_ai_normal.txt")));
AITableFeng.load(Files.readAllLines(Paths.get("majiang_ai_feng.txt")));
AITableJian.load(Files.readAllLines(Paths.get("majiang_ai_jian.txt")));
```

**Note**: The table files (`.txt` files) are included in the repository root directory.

### 2. Check if a Hand Can Win (Hu)

```java
import java.util.ArrayList;
import java.util.List;

// Define your hand (card values according to MaJiangDef)
List<Integer> cards = new ArrayList<>();
cards.add(11); // 1 Wan
cards.add(12); // 2 Wan
cards.add(13); // 3 Wan
cards.add(21); // 1 Tong
cards.add(21); // 1 Tong
cards.add(21); // 1 Tong

// Define wildcard/gui cards
List<Integer> gui = new ArrayList<>();
gui.add(41); // White dragon as wildcard

// Check if the hand can win
boolean isHu = HuUtil.isHu(cards, gui);
System.out.println("Can win: " + isHu);
```

### 3. Check Which Cards Can Complete a Win (Ting)

```java
// Get list of cards that would complete a winning hand
List<Integer> tingCards = HuUtil.isTing(cards, gui);

System.out.println("Cards that complete a win:");
for (int card : tingCards) {
    System.out.println("Card: " + card);
}
```

### 4. AI Card Selection

```java
// Get the best card to discard
int cardToDiscard = AIUtil.outAI(cards, gui);
System.out.println("AI recommends discarding: " + cardToDiscard);

// Check if AI should Peng (claim a triplet)
int pengCard = 21; // Someone discarded this card
boolean shouldPeng = AIUtil.pengAI(cards, gui, pengCard, 0.0);
System.out.println("Should Peng: " + shouldPeng);

// Check if AI should Gang (declare a kong)
int gangCard = 21;
boolean shouldGang = AIUtil.gangAI(cards, gui, gangCard, 0.0);
System.out.println("Should Gang: " + shouldGang);
```

## Card Numbering System

The library uses the following numbering system (defined in `MaJiangDef`):

- **Wan (万/Characters)**: 11-19 (1-9 Wan)
- **Tong (筒/Circles)**: 21-29 (1-9 Tong)
- **Tiao (条/Bamboo)**: 31-39 (1-9 Tiao)
- **Feng (风/Winds)**: 
  - 51: East (东)
  - 52: South (南)
  - 53: West (西)
  - 54: North (北)
- **Jian (箭/Dragons)**:
  - 41: Red Dragon (中)
  - 42: Green Dragon (發)
  - 43: White Dragon (白)

## Understanding the Lookup Tables

The library includes three types of lookup tables for both client and server/AI use:

### For Hu Detection (Client Tables)
- `majiang_clien_normal.txt`: For Wan, Tong, Tiao suits
- `majiang_clien_feng.txt`: For Wind tiles
- `majiang_clien_jian.txt`: For Dragon tiles

### For AI Decision Making
- `majiang_ai_normal.txt`: For Wan, Tong, Tiao suits
- `majiang_ai_feng.txt`: For Wind tiles
- `majiang_ai_jian.txt`: For Dragon tiles

### Server Tables (Optional)
- Server tables (`majiang_server_*.txt`) can be used for server-side game logic

## Project Structure

```
majiang_algorithm/
├── src/
│   └── main/
│       └── java/
│           └── com/github/esrrhs/majiang_algorithm/
│               ├── HuUtil.java          # Win detection utilities
│               ├── HuTable.java         # Win lookup tables (normal suits)
│               ├── HuTableFeng.java     # Win lookup tables (winds)
│               ├── HuTableJian.java     # Win lookup tables (dragons)
│               ├── AIUtil.java          # AI utilities
│               ├── AITable.java         # AI lookup tables (normal suits)
│               ├── AITableFeng.java     # AI lookup tables (winds)
│               ├── AITableJian.java     # AI lookup tables (dragons)
│               └── MaJiangDef.java      # Card definitions
├── majiang_clien_*.txt                  # Client lookup tables
├── majiang_ai_*.txt                     # AI lookup tables
├── majiang_server_*.txt                 # Server lookup tables
├── hu.md                                # Detailed Hu algorithm documentation (Chinese)
├── ai.md                                # Detailed AI algorithm documentation (Chinese)
├── pom.xml                              # Maven configuration
└── GETTING_STARTED.md                   # This file
```

## Building and Testing

### Compile the Project
```bash
mvn clean compile
```

### Package as JAR
```bash
mvn package
```

The JAR file will be created at `target/majiang_algorithm-1.0.15.jar`

### Clean Build Artifacts
```bash
mvn clean
```

## Continuous Integration

This project uses GitHub Actions for continuous integration. The workflow:
- Automatically builds the project on every push
- Uses JDK 1.8 for compatibility
- Runs Maven package command

Check the build status: ![Build Status](https://img.shields.io/github/actions/workflow/status/esrrhs/majiang_algorithm/maven.yml?branch=master)

## Additional Resources

- **Algorithm Details**: 
  - [Hu Algorithm Documentation](./hu.md) (Chinese)
  - [AI Algorithm Documentation](./ai.md) (Chinese)
- **Original Repository**: https://github.com/esrrhs/majiang_algorithm
- **License**: Apache License 2.0

## Common Issues and Troubleshooting

### Table Files Not Found
Make sure the `.txt` table files are in the correct location when loading them. You can:
1. Use absolute paths
2. Package them as resources in your JAR
3. Place them in your project's working directory

### Java Version Compatibility
The project is configured for Java 8. If you're using a newer Java version, you might see warnings about bootstrap classpath, but the code will still work.

### Maven Build Warnings
You may see warnings about bootstrap class path when building. These are informational and don't affect functionality.

## Example Application

Here's a complete example:

```java
import com.github.esrrhs.majiang_algorithm.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MajiangExample {
    public static void main(String[] args) throws Exception {
        // Load tables
        HuTable.load(Files.readAllLines(Paths.get("majiang_clien_normal.txt")));
        HuTableFeng.load(Files.readAllLines(Paths.get("majiang_clien_feng.txt")));
        HuTableJian.load(Files.readAllLines(Paths.get("majiang_clien_jian.txt")));
        
        AITable.load(Files.readAllLines(Paths.get("majiang_ai_normal.txt")));
        AITableFeng.load(Files.readAllLines(Paths.get("majiang_ai_feng.txt")));
        AITableJian.load(Files.readAllLines(Paths.get("majiang_ai_jian.txt")));
        
        // Example hand
        List<Integer> cards = new ArrayList<>();
        cards.add(11); cards.add(12); cards.add(13);
        cards.add(21); cards.add(21); cards.add(21);
        cards.add(31); cards.add(32); cards.add(33);
        cards.add(41); cards.add(41); cards.add(41);
        cards.add(51); cards.add(51);
        
        List<Integer> gui = new ArrayList<>(); // No wildcards
        
        // Check if can win
        boolean canWin = HuUtil.isHu(cards, gui);
        System.out.println("Can win: " + canWin);
        
        // Get cards that complete a win
        List<Integer> ting = HuUtil.isTing(cards, gui);
        System.out.println("Ting cards: " + ting);
        
        // Get AI recommendation
        int discard = AIUtil.outAI(cards, gui);
        System.out.println("Discard card: " + discard);
    }
}
```

## Contributing

Contributions are welcome! Please feel free to submit issues or pull requests.

## Support

For questions or issues:
1. Check the [documentation](./hu.md) and [AI documentation](./ai.md)
2. Search existing GitHub issues
3. Create a new issue with details about your problem

---

Happy coding with Majiang Algorithm! 🀄
