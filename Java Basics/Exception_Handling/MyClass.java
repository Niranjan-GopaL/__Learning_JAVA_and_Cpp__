public class MyClass {
    public static void main(String[] args){
        AnotherClass.main("Temp");
    }    
}
class AnotherClass{
    public static void main(String arg[]){
        System.out.println("Inside Another Class");
    }
}
