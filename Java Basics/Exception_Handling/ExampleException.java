class CustomException extends Exception{
    public CustomException(String string){
        super(string);
    }
}
public class ExampleException {
    private static void performCustomOperation(int value)throws CustomException{
        if(value < 10){
            throw new CustomException("Value is less than 10");
        }
        else{
            System.out.println("Successfull");
        }
    }
    public static void main(String[] args) {
        try{
            performCustomOperation(5);
            //throw new CustomException("test");
        }
        catch(CustomException e){
            System.out.println(e);
        }
    }
    
}
