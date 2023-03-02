import java.util.Formatter;
import java.util.Scanner;

/**
 * Child class of FoodItem
 */
public class Protein extends FoodItem {

    /**
     * Preserve jar size
     */
    private String measurement;

    /**
     * No parameter constructor
     */
    public Protein() {

    }

    /**
     * Stores preserve specific data members into a string
     * @return String
     */
    @Override
    public String toString() {

        return super.toString() + measurement;

    }

    /**
     * Adds Preserve specific data member to Shopping List
     * @param scanner Scanner
     * @return boolean
     */
    public boolean addItem(Scanner scanner) {

        boolean result = super.addItem(scanner);

        if (result) {

            System.out.print("Enter the unit of measurement for the protein: ");

            scanner.nextLine();     // Fixes multi-word input errors
            measurement = scanner.nextLine();

            return true;

        }

        return false;

    }

    /**
     * Adds jar size to file output
     * @param writer Formatter
     */
    @Override
    public void outputItem(Formatter writer) {

        super.outputItem(writer);
        writer.format("%s\n", measurement); // Append to writer

    }
}
