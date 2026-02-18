[<img src="https://img.shields.io/github/license/esrrhs/majiang_algorithm">](https://github.com/esrrhs/majiang_algorithm)
[<img src="https://img.shields.io/github/languages/top/esrrhs/majiang_algorithm">](https://github.com/esrrhs/majiang_algorithm)
[<img src="https://img.shields.io/maven-central/v/com.github.esrrhs/majiang_algorithm">](https://github.com/esrrhs/majiang_algorithm)
[<img src="https://img.shields.io/github/actions/workflow/status/esrrhs/majiang_algorithm/maven.yml?branch=master">](https://github.com/esrrhs/majiang_algorithm/actions)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b75adb4b83884d35beeaa396ab76b597)](https://www.codacy.com/manual/esrrhs/majiang_algorithm?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=esrrhs/majiang_algorithm&amp;utm_campaign=Badge_Grade)

# Majiang (Mahjong) Algorithm

An efficient Java library for Mahjong game logic, including win detection (Hu), ready hand detection (Ting), and AI card playing algorithms.

## 📚 Documentation

- **[Getting Started Guide](./GETTING_STARTED.md)** - Complete guide for using this library (English)
- **[胡牌算法](./hu.md)** - Win detection algorithm documentation (Chinese)
- **[AI算法](./ai.md)** - AI algorithm documentation (Chinese)

## 🚀 Quick Start

### Maven Dependency
```xml
<dependency>
    <groupId>com.github.esrrhs</groupId>
    <artifactId>majiang_algorithm</artifactId>
    <version>1.0.15</version>
</dependency>
```

### Basic Usage
```java
// Load tables
HuTable.load(Files.readAllLines(Paths.get("majiang_clien_normal.txt")));
HuTableFeng.load(Files.readAllLines(Paths.get("majiang_clien_feng.txt")));
HuTableJian.load(Files.readAllLines(Paths.get("majiang_clien_jian.txt")));

// Check if hand can win
boolean isHu = HuUtil.isHu(cards, gui);

// Get cards that complete a win
List<Integer> tingCards = HuUtil.isTing(cards, gui);

// Get AI card recommendation
int card = AIUtil.outAI(cards, gui);
```

## ✨ Features

- ⚡ **Fast Performance**: Pre-computed lookup tables for optimal speed
- 🎴 **Multiple Wildcards**: Efficient handling of multiple wildcard (gui/鬼牌) scenarios
- 🤖 **AI Support**: Intelligent card selection algorithms
- 📦 **Easy Integration**: Simple Maven dependency
- 🔧 **Flexible**: Support for various Mahjong rules

## 🏗️ Building from Source

```bash
# Clone the repository
git clone https://github.com/guanzhousongmicrosoft/majiang_algorithm.git
cd majiang_algorithm

# Build the project
mvn clean compile

# Create JAR package
mvn package
```

## 📋 Requirements

- Java 8 or higher
- Maven 3.0 or higher

## 🎮 Related Projects
- <a href="https://github.com/esrrhs/texas_algorithm">德州算法 (Texas Hold'em Algorithm)</a>
- <a href="https://github.com/esrrhs/teenpatti_algorithm">印度炸金花算法 (Teen Patti Algorithm)</a>

## 📄 License

Apache License 2.0

## 🤝 Contributing

Contributions are welcome! Please read the [Getting Started Guide](./GETTING_STARTED.md) first.
