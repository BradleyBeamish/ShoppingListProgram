import java.util.Formatter;
import java.util.Scanner;

/**
 * Child class of FoodItem
 */
public class FruitVegetable extends FoodItem {

    /**
     * Fruit orchard name
     */
    private String measurement;

    /**
     * No parameter constructor
     */
    public FruitVegetable() {

    }

    /**
     * Stores all data members into a string
     * @return String
     */
    @Override
    public String toString() {

        return super.toString() + measurement;

    }

    /**
     * Adds Fruit specific data member to shopping list
     * @param scanner Scanner
     * @return boolean
     */
    @Override
    public boolean addItem(Scanner scanner) {

        boolean result = super.addItem(scanner);

        if (result) {

            System.out.print("Enter the unit of measurement for the fruits/vegetables: ");

            scanner.nextLine();     // Fixes multi-word input errors
            measurement = scanner.nextLine();

            return true;

        }

        return false;

    }

    /**
     * Adds orchard name to file output
     * @param writer Formatter
     */
    @Override
    public void outputItem(Formatter writer) {

        super.outputItem(writer);       // Call parent class
        writer.format("%s\n", measurement);     // Append to writer

    }
}
