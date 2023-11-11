import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

/* 

------ README.md --------------------------------------------------------------------------



## Composition

- Student Class: 
    Student class that encapsulates
    the attributes of a student, including student ID, name, age, and grade. 
    This is a classic example of composition, where a class (Classroom) 
    is composed of one or more objects of another class (Student).

- Classroom Class: 
    The Classroom class represents a composition relationship with the Student class. 
    It contains a collection of Student objects (using a HashMap) to represent
    the students in the classroom. This class encapsulates and manages the 
    student objects, demonstrating the composition principle.



## Aggregation

- Main Class: 
    In the Main class, the principle  of aggregation is applied  by creating a Map 
    called classes to store class objects (Classroom). This map holds multiple Classroom
    objects, effectively aggregating them. It helps in organizing and managing 
    class-related data.

- Class Relationships: 
    In the Main class, Student objects are created iteratively and are added to specific 
    Classroom objects based on the class ID. This usage of maps (classes) to hold and 
    organize multiple class objects demonstrates the aggregation principle, where you 
    assemble objects into a more complex structure.

----------------------------------------------------------------------------------------
 */



class Student {
    // data members are all privated as is the convention
    private String studentID ;
    private String name ;
    private String age ;
    private String grade; 
 

    public Student(String studentID, String name,String  age, String grade){
        this.studentID = studentID;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    // Getters and Setters
    public String getStudentID() { return studentID; }
    public String getName()      { return name;      }
    public String getAge()       { return age;       }
    public String getGrade()     { return grade;     }

    public void setStudentID(String studentID) { this.studentID = studentID;}
    public void setName(String name)           { this.name = name;          }
    public void setAge(String age)             { this.age = age;            }
    public void setGrade(String grade)         { this.grade = grade;        }

}




class Classroom{
    private String classId;
    private int numOfStudents;

    // Hashmap of StudentId : Student Object
    private Map<String,Student> classMap;

    public Classroom(String classId){ 
        this.classMap = new HashMap<>();
        this.classId  = classId;
        this.numOfStudents = 0;
    }

    public String getClassId()       { return classId;       }
    public int getNumOfStudents()    { return numOfStudents; }


    // inserting to a Hashmap, key = isbn
    public void addStudent(Student std)          { this.classMap.put( std.getStudentID(), std); this.numOfStudents ++; }
    // removing from Hashmap
    public void removeStudent(String stdID)      { this.classMap.remove(stdID); this.numOfStudents -- ;                }
    public Student findStudentById(String stdID) { return classMap.get(stdID);                                         }


    public void displayAllStudents() {
        for (Student std : this.classMap.values()) 
                System.out.println(std.getStudentID() + " " + std.getName() + " " + std.getAge() + " " + std.getGrade());
    }
}


/* ------------------------ TEST CASES TO TRY ( Directly copy paste ) ------------------------
 2 5 
 1 1 Ram 16 11 
 1 2 Shyam 17 11 
 2 3 Mohan 18 12 
 2 4 Ravi 18 12 
 2 5 Sandeep 19 12  
 -----------------------------------------------------------------------*/

public class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

        int numClasses    = sc.nextInt();
        int numStudents   = sc.nextInt();
        String ___        = sc.nextLine();

        // Hashmap to keep track of how many Class objects are created
        Map<String, Classroom> classes = new HashMap<>();
            

        while (numStudents-- != 0) {
            
            String classID = sc.next();

            String stdID   = sc.next();
            String name    = sc.next();
            String age     = sc.next(); 
            String grade   = sc.next();
            
            Student std = new Student(stdID, name, age, grade);

            // if the hashmap deos not have that particular classID as a key
            // that means that classID is that of a new class object
            if (classes.get(classID) == null){
                Classroom classroom = new Classroom(classID);
                classroom.addStudent(std);

                // add the newly created class object to the classes HashMap
                classes.put(classID, classroom);
            }
            else{
                // retrieve the class object with that particualr classId
                Classroom classroom = classes.get(classID);
                classroom.addStudent(std);
            }
        }

        for (Classroom class01 : classes.values()) {
            System.out.println("Classroom " + class01.getClassId() + " " + class01.getNumOfStudents());
            class01.displayAllStudents();
        }

        // Scanner closed.
        sc.close();


    }
}
