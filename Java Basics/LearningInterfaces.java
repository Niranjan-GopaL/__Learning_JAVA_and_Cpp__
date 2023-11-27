

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


public class LearningInterfaces{

    public static void main(String[] args) {
        
        // lap and desktop are computer references;
        // but they are Laptop and Desktop objects specifically;
        Computer lap = new Laptop();
        Computer desktop = new Desktop();

        lap.code();
        desktop.code();

    }

}