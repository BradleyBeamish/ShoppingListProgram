import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Prints Menu
 * Calls other classes
 */
public class Menu {

    /**
     * Prints menu, provides options for tasks
     * @param args String[]
     */
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);     // Initializing variables
        boolean loop = true;
        boolean methodSuccess;
        ShoppingList shoppingList = new ShoppingList();

        while (loop) {      // Keeps looping until user stops it

            int userInput = 0;

            displayMenu();

            try {       // Ensures user input is a integer
                userInput = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();
            }

            switch (userInput) {

                case 1:     // Food groups explained
                    System.out.println("Food Groups:");
                    System.out.println("Fruits and vegetables: This group includes all types of fresh, frozen, or canned fruits and vegetables. Encourage users to choose a variety of colors to ensure they are getting a range of nutrients.");
                    System.out.println("Grains: This group includes all types of whole grains, such as brown rice, quinoa, whole wheat bread, and oatmeal.");
                    System.out.println("Dairy: This group includes milk, yogurt, and cheese. Encourage users to choose low-fat or fat-free options.");
                    System.out.println("Protein: This group includes meat, poultry, fish, beans, lentils, tofu, and other plant-based protein sources.");
                    System.out.println("Fats and oils: This group includes healthy fats, such as olive oil, avocado, and nuts.");
                    System.out.println("Sweeteners and spices: This group includes honey, maple syrup, and other natural sweeteners, as well as various herbs and spices.\n");
                    break;

                case 2:
                    methodSuccess = shoppingList.addItem(input);       // Add item to shopping list

                    if (!methodSuccess) {       // If item is not added
                        System.out.println("Item was not added");
                    }

                    break;

                case 3:
                    System.out.println(shoppingList);      // Prints shoppingList toString
                    break;

                case 4:
                    methodSuccess = shoppingList.updateQuantity(input, true);  // Add Quantities, updates shopping list

                    if (!methodSuccess) {
                        System.out.println("Error...could not add quantities");
                    }

                    break;

                case 5:
                    methodSuccess = shoppingList.updateQuantity(input, false);  // Remove Quantities, updates inventory

                    if (!methodSuccess) {
                        System.out.println("Error...could not remove quantities");
                    }

                    break;

                case 6:
                    shoppingList.searchForItem(input);     // Search array for item code
                    break;

                case 7:
                    shoppingList.saveToFile(input);        // Save shopping list to a file
                    break;

                case 8:
                    System.out.print("Sort By: Item Code(i) or Name(n): ");       // Sort shopping list
                    char option = input.next().charAt(0);
                    shoppingList.sortInventory(option);
                    break;

                case 9:
                    System.out.println("Exiting...");       // Exists loop
                    loop = false;
                    break;

                default:
                    System.out.println("Input must be an integer 1-5");     // User did not enter a integer
                    break;

            }
        }

        input.close();      // Close scanner

    }

    /**
     * Displays a main menu
     */
    public static void displayMenu() {

        // Prints menu
        System.out.println("Please select one of the following:");
        System.out.println("1: Food Groups Explained");
        System.out.println("2: Add Item to Shopping List");
        System.out.println("3: Display Shopping List");
        System.out.println("4: Add Quantities");
        System.out.println("5: Sell Quantities");
        System.out.println("6: Search for Item");
        System.out.println("7: Save Shopping List to File");
        System.out.println("8: Sort Shopping List");
        System.out.println("9: To Exit");
        System.out.print("> ");

    }
}