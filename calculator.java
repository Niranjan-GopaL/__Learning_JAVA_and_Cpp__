import java.util.Scanner;


public class calculator{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        String key = sc.next();
        sc.close();
        

        int c = 0;

        
        switch (key) {
            case "+":
                c = a + b;
                break;
                
            case "-":
                c = a + b;
                break;
                
            case "*":
                c = a + b;
                break;

            case "/":
                c = a + b;
                break;
        } 


        System.out.println("Result of " + a + key  + b + " = " + c);
    }
} 