import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public interface Card {
    String getName();
    String getType();
}

public class CardGame {
    public static void main(String[] args) {
        Map<String, Card> availableCards = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("cards_desc.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                String name = parts[0].trim();
                String type = parts[1].trim();
                Card card = CardFactory.createCard(name, type);
                availableCards.put(name, card);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            System.exit(1);
        }
    }
}
public class CardFactory {
    public static Card createCard(String name, String type) {
        switch (type.toLowerCase()) {
            case "monstruo":
                return new MonsterCard(name);
            case "hechizo":
                return new SpellCard(name);
            case "trampa":
                return new TrapCard(name);
            default:
                throw new IllegalArgumentException("Tipo de carta inválido: " + type);
        }
    }
}

public class MonsterCard implements Card {
    private String name;

    public MonsterCard(String name) {
        this.name = name;
    }

    public String getType() {
        return "Monstruo";
    }

    public String getName() {
        return name;
    }
}

public class SpellCard implements Card {
    private String name;

    public SpellCard(String name) {
        this.name = name;
    }

    public String getType() {
        return "Hechizo";
    }

    public String getName() {
        return name;
    }
}

public class TrapCard implements Card {
    private String name;

    public TrapCard(String name) {
        this.name = name;
    }

    public String getType() {
        return "Trampa";
    }

    public String getName() {
        return name;
    }
}