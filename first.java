import java.util.Scanner;





public class first {


    // public static boolean is_prime(int num) {
    //     if(num < 2){
    //         return false;
    //     }
    //     else if(num == 2){
    //         return true;
    //     }
    // }

    public static void main(String[] args) {
        double a ;
        a = 10;
        System.out.print("This will not appeand new line at the end so next line :  ");    
        System.out.println("a: " + a);    

 
        Scanner sc = new Scanner(System.in);
        int integer_input = sc.nextInt();
        sc.close();

        byte aa = -1;
        short aaa = -300;
                
        System.out.println(integer_input+" is the Entered input");

    }    
}