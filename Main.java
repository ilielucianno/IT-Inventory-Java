// Programul principal pentru gestiunea inventarului IT
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // lista sa tina toate echipamentele
        ArrayList<Equipment> inventory = new ArrayList<Equipment>();
        Scanner scanner = new Scanner(System.in);
        
        // incarca datele salvate din fisier
        loadFromFile(inventory);
        
        System.out.println("=== INVENTAR IT ===");
        System.out.println("Comenzi: add, list, search, exit");
        
        boolean pornit = true;
        while (pornit) {
            System.out.print("\nComanda: ");
            String cmd = scanner.nextLine().toLowerCase();
            
            if (cmd.equals("add")) {
                // ia detaliile de la utilizator
                System.out.print("Tip (cablu, router, switch, etc): ");
                String type = scanner.nextLine();
                System.out.print("Brand: ");
                String brand = scanner.nextLine();
                System.out.print("Descriere: ");
                String desc = scanner.nextLine();
                
                Equipment item = new Equipment(type, brand, desc);
                inventory.add(item);
                System.out.println("Produs adaugat.");
                
            } else if (cmd.equals("list")) {
                // arata toate produsele
                if (inventory.size() == 0) {
                    System.out.println("Nu sunt produse in inventar.");
                } else {
                    for (int i = 0; i < inventory.size(); i++) {
                        System.out.println((i+1) + ". " + inventory.get(i).toString());
                    }
                    System.out.println("Total: " + inventory.size() + " produse");
                }
                
            } else if (cmd.equals("search")) {
                // cauta dupa tip sau brand
                System.out.print("Cauta: ");
                String term = scanner.nextLine().toLowerCase();
                int gasit = 0;
                for (Equipment item : inventory) {
                    if (item.getType().toLowerCase().contains(term) || 
                        item.getBrand().toLowerCase().contains(term)) {
                        System.out.println(item.toString());
                        gasit++;
                    }
                }
                System.out.println("Gasit " + gasit + " produse.");
                
            } else if (cmd.equals("exit")) {
                // salveaza si iesi
                saveToFile(inventory);
                System.out.println("Inventar salvat. Pa!");
                pornit = false;
                
            } else {
                System.out.println("Comanda gresita. Foloseste add, list, search sau exit.");
            }
        }
        scanner.close();
    }
    
    // salveaza inventarul in fisier
    public static void saveToFile(ArrayList<Equipment> list) {
        try {
            FileWriter writer = new FileWriter("inventar.txt");
            for (Equipment item : list) {
                writer.write(item.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Eroare la salvare: " + e.getMessage());
        }
    }
    
    // incarca inventarul din fisier
    public static void loadFromFile(ArrayList<Equipment> list) {
        try {
            File file = new File("inventar.txt");
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    Equipment item = Equipment.fromFileString(line);
                    if (item != null) {
                        list.add(item);
                    }
                }
                fileScanner.close();
                System.out.println("Incarcate " + list.size() + " produse din fisier.");
            }
        } catch (IOException e) {
            System.out.println("Eroare la incarcare: " + e.getMessage());
        }
    }
}
