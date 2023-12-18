// Abstract classes can be used for generalising


/*
 
abstract class Computer{
    public abstract void code();
}

     
     
class Laptop extends Computer{
    public void code() {
        System.out.println("Code methode of Laptop");
    }
    
}

class Desktop extends Computer{
    public void code() {
        System.out.println("Code methode of Desktop");
    }
}
*/ 




/*
                The above code is implemented using interfaces normally.

                
                -> a methode in [Dev class] can accept a [laptop] OR [desktop]

                -> so how to achieve this ?

                -> both laptop and desktop are COMPUTER
                -> we create a computer INTERFACE and have lap and desktop IMPLEMENT this interface
                
                -> Both Laptop and Desktop are Computer
                
                -> so we create a Computer interface

 

 */


interface Computer {
    void code();    
}


class Laptop implements Computer{

    public void code() {
        System.out.println("Code methode of Laptop");
    }

}


class Desktop implements Computer{
    public void code() {
        System.out.println("Code methode of Desktop");
    }
}


class Developer{
    public void code(Computer computer){
        computer.code();
    }
}





public class LearningInterfaces{

    public static void main(String[] args) {
        
        // lap and desktop are computer references;
        // but they are Laptop and Desktop objects specifically;
        Computer lap = new Laptop();
        Computer desktop = new Desktop();

        Developer dev = new Developer();

        dev.code(lap);
        dev.code(desktop);

    }

}