public class ThrowException {
    public static void main(String args[]){
        int a = 10;
        int b = 0;

        try{
            if(b == 0)
                throw new ArithmeticException("Divide by Zero");
            int c = a/b;
        }
        catch(ArithmeticException e){
            System.out.println(e);
        }
    }
}
    