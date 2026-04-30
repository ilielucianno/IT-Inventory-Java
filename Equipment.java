// Clasa Equipment - tine info despre un echipament IT
import java.io.*;

public class Equipment {
    // variabilele pentru un obiect
    private String type;
    private String brand;
    private String description;
    
    // constructor - ruleaza cand fac un obiect nou
    public Equipment(String type, String brand, String description) {
        this.type = type;
        this.brand = brand;
        this.description = description;
    }
    
    // metode ca sa iau valorile
    public String getType() { return type; }
    public String getBrand() { return brand; }
    public String getDescription() { return description; }
    
    // afiseaza detaliile obiectului
    public String toString() {
        return "Tip: " + type + " | Brand: " + brand + " | Desc: " + description;
    }
    
    // format pentru salvat in fisier
    public String toFileString() {
        return type + "," + brand + "," + description;
    }
    
    // creeaza un obiect dintr-o linie citita din fisier
    public static Equipment fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 3) {
            return new Equipment(parts[0], parts[1], parts[2]);
        }
        return null;
    }
}
