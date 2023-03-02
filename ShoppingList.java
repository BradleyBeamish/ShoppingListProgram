import java.io.*;
import java.util.*;

/**
 * Interacts with all the different food items
 */
public class ShoppingList {

    /**
     * Array of food items
     */
    private final ArrayList<FoodItem> shoppingList;

    /**
     * Number of items in shopping list array
     */
    private int numItems;

    /**
     * Food item object
     */
    private FoodItem item = null;

    /**
     * No parameter constructor
     */
    public ShoppingList() {

        shoppingList = new ArrayList<>();       // Initialize arrayList with no capacity limit

    }

    /**
     * Uses string builder to create a String of each object in shoppingList[]
     * @return String
     */
    public String toString() {

        StringBuilder message = new StringBuilder();

        message.append("Shopping List: \n");

        for (int i = 0; i < numItems; i++) {        // Loops through every item in array

            message.append(shoppingList.get(i).toString()).append("\n");       // Adds every items toString into message

        }

        return message.toString();      // Return complete string with all items
    }

    /**
     * Compares 2 objects looking for a match with itemCode
     * @param item FoodItem
     * @return int
     */
    public int alreadyExists(FoodItem item) {

        int low = 0;
        int high = numItems - 1;

        sortInventory('i');

        while (low <= high) {       // BINARY SEARCH

            int mid = (low + high) / 2;
            int cmp = shoppingList.get(mid).compareTo(item);

            if (cmp < 0) {

                low = mid + 1;

            } else if (cmp > 0) {

                high = mid - 1;

            } else {

                return mid;

            }
        }

        return -1;

    }

    /**
     * Adds a new item to the shopping list array
     * @param scanner Scanner
     * @return boolean
     */
    public boolean addItem(Scanner scanner) {

        boolean validChoice = false;
        char userChoice;

        while (!validChoice) {      // Loops until user input is correct

            System.out.print("Do you wish to add a Fruit/Vegetable(v), Protein(p), Dairy(d), Grains(g), Fats/Oils(f) or Sweeteners/Spices(s): ");

            userChoice = scanner.next().charAt(0);      // Store user input

            switch (userChoice) {

                case 'v':
                    item = new FruitVegetable();     // Creates object for FoodItem child class Fruit
                    item.setItemType("Fruit / Vegetable");
                    validChoice = true;
                    break;

                case 'p':
                    item = new Protein();
                    item.setItemType("Protein");
                    validChoice = true;
                    break;

                case 'd':
                    item = new Dairy();
                    item.setItemType("Dairy");
                    validChoice = true;
                    break;

                case 'g':
                    item = new Grain();
                    item.setItemType("Grains");
                    validChoice = true;
                    break;

                case 'f':
                    item = new Fat();
                    item.setItemType("Fats and Oils");
                    validChoice = true;
                    break;

                case 's':
                    item = new Sweeteners();
                    item.setItemType("Sweeteners and Spices");
                    validChoice = true;
                    break;

                default:
                    System.out.println("Invalid Input");        // If input is invalid
                    break;

            }

        }

        boolean result = item.inputCode(scanner);       // Stores user input code for specified object

        if (result) {

            int exists = alreadyExists(item);

            if (exists == -1) {     // Item with same item code does not exist

                item.addItem(scanner);

                shoppingList.add(item);        // Add item to array List
                numItems++;

                return true;

            }

            System.out.println("An item with the code entered already exists");
            return false;

        }

        return false;

    }

    /**
     * Saves shopping list to a file
     * @param keyboard Scanner
     */
    public void saveToFile(Scanner keyboard) {

        File file;
        String filePath, fileName;

        keyboard.nextLine();

        System.out.print("Enter the file path to save to (Press Enter for Default Path or d for Desktop): ");
        filePath = keyboard.nextLine(); // Store user input for file path

        System.out.print("Enter the filename to save to: ");
        fileName = keyboard.nextLine(); // Store user input for file name

        if (filePath.trim().isEmpty()) {        // Default (Project Folder)

            file = new File(fileName);

        } else if (filePath.trim().equalsIgnoreCase("d")) {     // Desktop

            file = new File(System.getProperty("user.home") + "/Desktop/", fileName);

        } else {        // User specified

            file = new File(filePath, fileName);

        }

        try (Formatter formatter = new Formatter(file)) {

            for (FoodItem item : shoppingList) {

                item.outputItem(formatter);

            }

        } catch (FileNotFoundException e) {

            System.out.println("Error creating file: " + e.getMessage());

        }
    }

    /**
     * Allows user to add or remove quantity of an food item
     * @param scanner Scanner
     * @param buyOrSell boolean
     * @return boolean
     */
    public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {

        boolean validChoice = false, result = false, result2;       // Initializing variables
        int i, itemCode2 = 0, quantityBuy = 0, quantitySell = 0;

        while (!validChoice) {

            System.out.print("Enter valid item code: ");

            if (scanner.hasNextInt()) {     // Ensures user input is a integer

                itemCode2 = scanner.nextInt();
                validChoice = true;

            } else {

                System.out.println("Invalid Code");
                scanner.next();     // Stops infinite loop

            }
        }

        validChoice = false;        // Reset validChoice

        FoodItem itemCodeObject = new FoodItem(itemCode2);

        for (i = 0; i < numItems; i++) {

            result = itemCodeObject.isEqual(shoppingList.get(i));      // Checks if item code is valid

            if (result) {
                break;
            }

        }

        if (result) {

            if (buyOrSell) {        // Buy item

                while (!validChoice) {

                    System.out.print("Enter valid quantity to add: ");

                    if (scanner.hasNextInt()) {     // Ensures valid input

                        quantityBuy = scanner.nextInt();

                        if (quantityBuy > 0) {
                            validChoice = true;
                        } else {
                            System.out.println("Invalid Quantity");
                        }

                    } else {

                        System.out.println("Invalid Quantity");
                        scanner.next();

                    }
                }

                quantityBuy = -quantityBuy;     // Convert to negative for calculations

                result2 = shoppingList.get(i).updateItem(quantityBuy);

            } else {

                while (!validChoice) {      // Sell item

                    System.out.print("Enter valid quantity to remove: ");

                    if (scanner.hasNextInt()) {     // Ensures correct input

                        quantitySell = scanner.nextInt();

                        if (quantitySell > 0) {

                            validChoice = true;

                        } else {

                            System.out.println("Invalid Quantity");

                        }

                    } else {

                        System.out.println("Invalid Quantity");
                        scanner.next();

                    }
                }

                result2 = shoppingList.get(i).updateItem(quantitySell);

            }

            if (!result2) {     // Could not update quantity

                System.out.println("Invalid Quantity");
                return false;

            }

            return true;

        }

        return false;

    }

    /**
     * Searches shopping list array for an item with a specific item code
     * @param scanner Scanner
     */
    public void searchForItem(Scanner scanner) {

        int itemCode = 0;
        boolean inputIsValid = false;

        while (!inputIsValid) {

            System.out.print("Enter the code for the item: ");

            if (scanner.hasNextInt()) {     // Ensures input is a integer

                itemCode = scanner.nextInt();
                inputIsValid = true;

            } else {

                System.out.println("Invalid Code");
                scanner.next();     // Clears buffer

            }
        }

        FoodItem temp = new FoodItem(itemCode);     // Temporary food item with a itemCode

        int result = alreadyExists(temp);       // Compare temporary item with existing items

        if (result == -1) {
            System.out.println("Item code does not exist.");
        } else {
            System.out.println(shoppingList.get(result));      // Print item with matching itemCode
        }
    }

    /**
     * Sorts the shopping list
     * @param option char
     */
    public void sortInventory(char option) {

        switch (option) {

            case 'i':       // Sort by item code
                Collections.sort(shoppingList);
                break;

            case 'n':       // Sort by name
                shoppingList.sort(Comparator.comparing(FoodItem::getItemName));
                break;

            default:
                System.out.println("Error");
                break;
        }
    }
}