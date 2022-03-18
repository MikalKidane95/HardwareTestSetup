import java.util.List;
import java.util.Scanner;

public class utility {
    public Scanner in;

    utility() {
        in = new Scanner(System.in);
    }

    public void printChoices(List choicesList) {
        int n = 0;

        String choiceListString = "";
        for (Object choice : choicesList) {
            choiceListString += n++ + " " + choice.toString() + "\n";
        }
        System.out.println(choiceListString);
    }

    public int getInput(int maxNum) {
        while (true) {
            String userInput = in.nextLine();
            int selectedNumber;
            try {
                selectedNumber = Integer.valueOf(userInput);
                if (selectedNumber >= 0 && selectedNumber < maxNum) {
                    return selectedNumber;
                } else {
                    throw new Exception("Min allowed number is 0 and Max allowed Number is " + (maxNum - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid Number");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
