import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class KnapsackProblem {

    Knapsack solution;
    ArrayList<Item> itemsToChoseFrom;
    KTable possibilities;

    //Knapsack problem constructor takes in a file string. From there, it reads the file and then converts
    //its information into a knapsack. the items in the knapsack are evaluated using the KTable to find the optimal solution
    //the optimal solution is of object type knapsack.
    public KnapsackProblem(String fileString) throws FileNotFoundException {
        Knapsack parsedItems = this.convertRawtoKnapsack(this.readRaw(fileString));
        possibilities = new KTable(parsedItems);
        possibilities.fillTable();
        solution = possibilities.optimalKnapsack();

    }

    //reads the file and returns a string array. this array will later be turned into valuable information that we can parse into a knapsack
    public String[] readRaw(String filename) throws FileNotFoundException,
            NumberFormatException {

        File file = new File(filename);
        Scanner scan = new Scanner(file);
        String fileContent = "";

        while (scan.hasNextLine()) {
            fileContent += scan.nextLine() + "\n";

        }
        scan.close();

        String[] content = fileContent.split("\n");
        return content;
    }

    //parses content from document to create a knapsack instance
    public Knapsack convertRawtoKnapsack(String[] content) {
        Knapsack sack = new Knapsack();
        try {

            // gets the capacity and number of items

            sack.setItemsNum(Integer.parseInt(content[0]));
            sack.setCapacity(Integer.parseInt(content[content.length - 1]));

            // items between number of items and capacity
            for (int i = 1; i < content.length - 1; i++) {

                Item item = new Item();
                String[] tempItem = content[i].split(" ");
                //condition for edge cases. if the weight is <0 and if the item is poorly formatted on each line
                if (tempItem.length < 3 || Integer.parseInt(tempItem[2]) <= 0) {
                    throw new NumberFormatException();
                } else {
                    //initializes knapsack attributes
                    item.setName(tempItem[0]);
                    item.setValue(Integer.parseInt(tempItem[1]));
                    item.setWeight(Integer.parseInt(tempItem[2]));
                    sack.add(item);

                }
            }
            //exception handling
        } catch (NumberFormatException e) {
            System.out.println("Invalid file format. Please ensure file is populated correctly");
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Invalid file format. Please ensure file is populated correctly");
        }

        return sack;

    }

    //writes solution into a new file. 
    public void writeSolution(String filename) {
        try {
            File write = new File(filename.replace("txt", "sol")); //names the file
            FileWriter writer = new FileWriter(write);
            writer.write(String.valueOf(solution.getTotalVal())+'\n'); //writes optimal value on the first line
            writer.write(solution.printKnapsack()); //writes items on the second line
            writer.close();

        //exception handling
        } catch (IOException e) {
            System.out.println("There's a problem!");
        }

    }

    public static void main(String args[]) throws FileNotFoundException {
        Boolean solvingMethod = args[1] == "D"; // flag
        if (solvingMethod) {
            System.out.println(
                    "Please insert D after text name to solve the Knapsack problem using Dynammic Programming");
        } else {
            KnapsackProblem problem = new KnapsackProblem(args[0]);
            problem.writeSolution(args[0]);
        }
    }

}
