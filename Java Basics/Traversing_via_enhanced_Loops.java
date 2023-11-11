/**
 * Traversing_via_enhanced_Loops
 */
import java.util.Scanner;


public class Traversing_via_enhanced_Loops {

    public static void main(String[] args) {
        int size = 10;
        int[] arr = new int[size];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < arr.length; i++) {
           arr[i] = sc.nextInt(); 
        }
        sc.close();

        for(int iterator : arr){
            System.out.println(iterator);
        } 
    }
}