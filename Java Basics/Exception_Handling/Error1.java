import java.util.ArrayList;
import java.util.List;

public class Error1 {
    public static void main(String args[]){
        List<int[]> list  = new  ArrayList<>();
 //       try{
            while(true){
                int[] array = new int[Integer.MAX_VALUE];
                list.add(array);
            }
   /*     }
        catch(OutOfMemoryError e){
            System.out.println("Outofmemory error");
        }*/
    }  
}
