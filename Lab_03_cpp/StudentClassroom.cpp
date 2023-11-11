#include <iostream>
#include <string>
#include <map>
using namespace std;


/*
Apparently :-
    `map` to store students, and `map` requires a default constructor for the value type. 
     Initially in Student class, there was  no default constructor provided, so  it was throwing error

     solution :- 
        -> either use unordered map  ( which prints the output in the wrong order )
        -> or provide default Constructor ( which is what I tried here )
*/



class Student {
private:
    string studentID;
    string name;
    int    age;
    string grade;

public:

    // Default constructor required to use map
    Student() {
    studentID = ""; 
    name      = ""; 
    age       = 0; 
    grade     = "";
    }


    Student( string studentID, string name, int age, string grade){
        this->studentID = studentID;
        this->name      = name;
        this->age       = age;
        this->grade     = grade;
    }

    // getters and setters
    string get_studentID() { return studentID; }
    string get_name()      { return name;      }
    int    get_age()       { return age;       }
    string get_grade()     { return grade;     }

    void set_studentID(string newStudentID) { studentID = newStudentID; }
    void set_name(string newName)           { name = newName;           }
    void set_age(int newAge)                { age = newAge;             }
    void set_grade(string newGrade)         { grade = newGrade;         }

};





class Classroom 
{
private:
    // hashMap to map studentID to student
    map<string, Student> classMap;
    string classId;
    int numOfStudents;

public:

    // default constructor
    Classroom(){
        classId = "";
        this->numOfStudents = 0; 
    }

    Classroom(string classId){
        this->classId = classId;
        this->numOfStudents = 0;
    }  


    // getters and setters 
    string getClassID()                  { return classId;      }
    int    getNumOfStudents()            { return numOfStudents;}
    void   setClassID(string newClassId) { classId = newClassId;}

    // adding and removing student to HashMap
    void addStudent(Student std)     { classMap[std.get_studentID()] = std; numOfStudents++ ;}
    void removeStudent(string stdID) { classMap.erase(stdID); numOfStudents--;               }


    Student findStudentById(string stdID) {
        auto it = classMap.find(stdID);
        if (it != classMap.end()) 
            return it->second;
        return Student("", "", 0, "");
    }
    

    void displayAvailableStudents() {
        for (auto pair : classMap) {
            Student std = pair.second;
            cout << std.get_studentID() << ' ' << std.get_name() << ' ' << std.get_age() << ' ' << std.get_grade() << '\n' ;
        }
    }


};

/* ------------------------ TEST CASES TO TRY ( Directly copy paste ) ------------------------
 2 5 
 1 1 Ram 16 11 
 1 2 Shyam 17 11 
 2 3 Mohan 18 12 
 2 4 Ravi 18 12 
 2 5 Sandeep 19 12
-------------------------------------------------------------------------------------------------*/


int main() {

    int numClasses, numStudents;
    string classID, stdID, name, ageStr, grade;
    map<string, Classroom> classes;



    cin >> numClasses >> numStudents;

    while (numStudents--) {
        cin >> classID >> stdID >> name >> ageStr >> grade;
        int age = stoi(ageStr); 

        Student std(stdID, name, age, grade);

        // If the classID is not in the map, create a new Classroom object
        if (classes.find(classID) == classes.end()) {
            Classroom classroom(classID);
            classroom.addStudent(std);
            classes[classID] = classroom;
        } else {
            Classroom& classroom = classes[classID];
            classroom.addStudent(std);
        }
    }

    for (auto pair : classes) {
        Classroom class01 = pair.second;
        cout << "Classroom " << class01.getClassID() << " " << class01.getNumOfStudents() << endl;
        class01.displayAvailableStudents();
    }

    return 0;
}
