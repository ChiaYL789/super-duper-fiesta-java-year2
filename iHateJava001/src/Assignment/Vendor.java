package Assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Vendor {
    private static final String DATA_FILE = "food_menu.txt";

    public static void main(String[] args) {
        Vendor vendor = new Vendor();
        vendor.getFoodMenu();
    }

    public void getFoodMenu() {
        List<FoodItem> foodItems = loadFoodItems();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Food Menu Options:");
            System.out.println("1. Add Food Item");
            System.out.println("2. Show Food Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine();  // Consume the newline character
                    System.out.print("Enter food name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: $");
                    double price = scanner.nextDouble();

                    FoodItem foodItem = new FoodItem(name, quantity, price);
                    foodItems.add(foodItem);
                    saveFoodItems(foodItems);
                    System.out.println("Food item added to the menu.");
                    break;

                case 2:
                    System.out.println("Food Menu:");
                    for (int i = 0; i < foodItems.size(); i++) {
                        System.out.println((i + 1) + ". " + foodItems.get(i));
                    }
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private List<FoodItem> loadFoodItems() {
        List<FoodItem> foodItems = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    double price = Double.parseDouble(parts[2]);
                    foodItems.add(new FoodItem(name, quantity, price));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading data file: " + e.getMessage());
        }
        return foodItems;
    }
    
    private void saveFoodItems(List<FoodItem> foodItems) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (FoodItem item : foodItems) {
                writer.write(item.getName() + "," + item.getQuantity() + "," + item.getPrice());
                writer.newLine(); // Writes a platform-specific line separator
            }
    } catch (IOException e) {
        System.err.println("Error writing to data file.");
    }
}
}

class FoodItem {
    private String name;
    private int quantity;
    private double price;

    public FoodItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - Quantity: " + quantity + ", Price: $" + price;
    }
}