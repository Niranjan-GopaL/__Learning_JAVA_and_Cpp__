/**
 * Prime_via_loops
 */
import java.util.Scanner;



public class Prime_via_loops {

    public static boolean is_prime(int num){
        for(int i=2; i < Math.sqrt(num) ; i++) 
        {
            if(num % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Generated all prime till the entered number: ");

        int num;
        while (true){
            num = sc.nextInt();
            if (num >= 2)break;
            System.out.println("Enter a number greater than 1");
        }
        sc.close();
        System.out.println(num);


        for (int i = 2; i <= num; i++) {
            if (is_prime(i) ) System.out.println(i) ;
         }

        
    }
}