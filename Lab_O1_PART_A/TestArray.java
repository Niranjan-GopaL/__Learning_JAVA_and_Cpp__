
public class TestArray {

	public static void main(String[] args) {
			

		// Step 7
        int[] ints6 = {12, 23, 7, 92, 72, 82, 15, 19, 50};
		int temp;

		 // Sort an array using nested loops (such as bubble sort)  
            for(int i=0; i<ints6.length; i++){
                for (int j = i; j < ints6.length; j++) {
                    if(ints6[i] > ints6[j]){
                        temp = ints6[i];
                        ints6[i] = ints6[j];
                        ints6[j] = temp;
                    }
                }

            }

        // sort the array in ascending order (without using another array)
            for(int i=0; i<ints6.length; i++){
                for (int j = i; j < ints6.length; j++) {
                    if(ints6[i] > ints6[j]){
                        temp = ints6[i];
                        ints6[i] = ints6[j];
                        ints6[j] = temp;
                    }
                }

            }

            
        // print the elements of the array
            for (int i=0;i<ints6.length;i++) {
                System.out.println(ints6[i]);
            }
		 
	}

}
