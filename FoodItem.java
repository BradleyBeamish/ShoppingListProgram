import java.util.Formatter;
import java.util.Scanner;

/**
 * Super class for every food group
 */

public class FoodItem implements Comparable<FoodItem> {

    /**
     * Type of food item
     */
    private String itemType;

    /**
     * Food item code
     */
    private int itemCode;

    /**
     * Food item name
     */
    private String itemName;

    /**
     * Food item price
     */
    private float itemPrice;

    /**
     * Food item quantity
     */
    private int itemQuantity;

    /**
     * No parameter constructor
     */
    public FoodItem() {

    }

    /**
     * One parameter constructor
     * @param itemCode int
     */
    public FoodItem(int itemCode) {

        this.itemCode = itemCode;       // Set the item code

    }

    /**
     * Adds all FoodItem data members to a string
     * @return String
     */
    public String toString() {

        return String.format("Name: %s, Code: %d, Price: $%.2f, Quantity: %d", itemName, itemCode, itemPrice, itemQuantity);

    }

    /**
     * Updates the quantity of an item
     * @param amount int
     * @return boolean
     */
    public boolean updateItem(int amount) {

        int sum = itemQuantity - amount;     // Ensures sum is above 0 before doing calculations

        if (sum >= 0) {

            itemQuantity -= amount;      // Subtracts from quantity
            return true;

        }

        return false;

    }

    /**
     * Checks if two item codes are equal
     * @param item FoodItem
     * @return boolean
     */
    public boolean isEqual(FoodItem item) {

        return item.itemCode == this.itemCode;      // Compares FoodItem passed in to a FoodItem from the array

    }

    /**
     * Stores all data members for FoodItem
     * @param scanner Scanner
     * @return boolean
     */
    public boolean addItem(Scanner scanner) {

        boolean inputIsValid = false;

        System.out.print("Enter the name for the item: ");

        scanner.nextLine();     // Prevents scanner bugs
        itemName = scanner.nextLine();      // Store string value


        while (!inputIsValid) {

            System.out.print("Enter the quantity for the item: ");

            if (scanner.hasNextInt()) {     // Ensures input is a integer

                itemQuantity = scanner.nextInt();

                if (itemQuantity > 0) {

                    inputIsValid = true;

                } else {

                    System.out.println("Invalid Quantity");

                }

            } else {

                System.out.println("Invalid Quantity");
                scanner.next();

            }

        }

        inputIsValid = false;       // Reset variable for next usage

        while (!inputIsValid) {

            System.out.print("Enter the sales price of the item: ");

            if (scanner.hasNextFloat()) {

                itemPrice = scanner.nextFloat();

                if (itemPrice > 0) {

                    inputIsValid = true;

                } else {

                    System.out.println("Invalid Sales Price");

                }

            } else {

                System.out.println("Invalid Sales Price");
                scanner.next();

            }

        }

        return true;

    }

    /**
     * Receives item code from user
     * @param scanner Scanner
     * @return boolean
     */
    public boolean inputCode(Scanner scanner) {

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

        return true;

    }

    public String getItemName() {

        return this.itemName;

    }

    /**
     * Sets the item type
     * @param itemType char
     */
    public void setItemType(String itemType) {

        this.itemType = itemType;

    }

    /**
     * Puts all data into a format that can be written to a file
     * @param writer Formatter
     */
    public void outputItem(Formatter writer) {

        writer.format("%s, Code: %d, Name: %s, Quantity: %d%.2f", itemType, itemCode, itemName, itemQuantity, itemPrice);

    }

    /**
     * Compares itemCode of two FoodItem objects
     * @param o the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(FoodItem o) {

        return this.itemCode - o.itemCode;      // Compares two items, if 0 then equal

    }
}
