/**
 * Which_inner_loop_breaks
 */
public class Which_inner_loop_breaks {

    public static void main(String[] args) {


        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int j2 = 0; j2 < 10; j2++) {
                    for (int k = 0; k < 10; k++) {
                       System.out.println("In LOOP NO 1");
                       break;
                    }
                    System.out.println("In LOOP NO 2");
                }
                System.out.println("In LOOP NO 3");
            } 
            System.out.println("In LOOP NO 4");
        }
        System.out.println("OUTSIDE LOOP");



        // SO ONLY THE INNER WOULD BE BROKEN
    }
}