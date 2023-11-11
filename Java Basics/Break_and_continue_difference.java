/**
 * Break_and_continue_difference
 */
public class Break_and_continue_difference {

    public static void Break(){
        short num = 50;
        System.out.println("Break Example");
        System.out.println(num);
        do
        {
            num -= 10;
            System.out.println(num);
            if(num == 30)break;
            
        }while(num!=0);
        System.out.println("Outside Loop");
    }
    
    
    public static void Continue(){
        int num = 50;
        System.out.println("Continue Example");
        System.out.println(num);
        do
        {
            if(num == 30)continue;
            num -= 10;
            System.out.println(num);
            
        }while(num!=0);
        System.out.println("Outside Loop");
    }


    public static void main(String[] args) {
        Break();
        Continue();
    }
}