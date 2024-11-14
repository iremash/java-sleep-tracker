import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


public class ReverseTransp {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> arrays = new ArrayList<>();
        while (input.hasNextLine()) {
            arrays.add(input.nextLine());
        }
        input.close();
        int[][] numbers = new int[arrays.size()][1];
        int maxIndex = 0;
        for (int i = 0; i < arrays.size(); i++) {
            Scanner strings = new Scanner(arrays.get(i));
            int index = 0;
            while (strings.hasNextInt()) {
                if (index >= numbers[i].length) {
                    numbers[i] = Arrays.copyOf(numbers[i], numbers[i].length * 2);
                }
                numbers[i][index] = strings.nextInt();
                index++;
            }
            maxIndex = Math.max(maxIndex, index);
            strings.close();
            if (index > 1) {
                numbers[i] = Arrays.copyOfRange(numbers[i], 0, index);
            }
        }
        int amoutOfLines = 0;
        for (int g = 0; g <= maxIndex; g++) {
            int flag = 0;
            for (int h = 0; h < numbers.length; h++) {
                while (h < numbers.length && g >= numbers[h].length) {
                    h++;
                }
                if (h >= numbers.length) {
                    break;
                }
                if (Math.abs(numbers[h][g]) % 2 == 1) {
                    System.out.print(numbers[h][g] + " ");
                    flag = 1;
                } else if (Math.abs(numbers[h][g]) % 2 == 0) {
                    flag = 1;
                }
            }
            if (flag == 1 && amoutOfLines < maxIndex) {
                System.out.println();
                amoutOfLines++;
            }


        }

    }
}
