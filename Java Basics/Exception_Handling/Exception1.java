public class Exception1{
    public static void main(String args[]){
        System.out.println("Program start");
        int a = 10;
        int b = 2;
        int arr[] = new int[10];
        String str = null;
        try{
            int c = a/b;
            System.out.print(arr[8]);
            System.out.println(str.length());
        }
        catch(ArithmeticException e){
            System.out.println("Divide by Zero");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Array Index out of Bounds");
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Program end");;
    }
}