#include <bits/stdc++.h>
using namespace std;

/*
Apparently :-
    `map` to store students, and `map` requires a default constructor for the value type. 
     Initially in Student class, there was no default constructor provided, so  it was throwing error

     solution :- 
        -> either use unordered map
        -> or provide default Constructor ( which is what I tried here )
*/


class Student {
    public:
        string studentID;
        string name;
        int    age;
        string grade;

    public:

        // Default constructor
        Student() : studentID(""), name(""), age(0), grade("") {}

        // Normal constructor
        Student( string studentID, string name, int age, string grade):
            studentID(studentID),
            name(name),
            age(age),
            grade(grade)
        {}

        string get_studentID() { return studentID; }
        string get_name()      { return name;      }
        int    get_age()       { return age;       }
        string get_grade()     { return grade;     }

};





class Classroom 
{
    public:
        map<string, Student> classMap;
        string classId;

        Classroom() {}

        void addStudent(const Student& std) { classMap[std.studentID] = std; }
        void removeStudent(const string& stdID) { classMap.erase(stdID);}

        void displayAvailableStudents() {
            cout << "Students of class " << classId << " :-\n";
            for (const auto& pair : classMap) {
                const Student& std = pair.second;
                cout << std.name << "\n";
            }
        }


        Student findStudentById(const string& stdID) {
            auto it = classMap.find(stdID);
            if (it != classMap.end()) 
                return it->second;
            
            return Student("", "", 0, "");
        }
};




int main() {

    Student student1("imt001", "NJ", 18, "A");
    Student student2("imt002", "DJ", 19, "B");
    Student student3("imt003", "MJ", 17, "C");

    Classroom classroom;
    classroom.classId = "A106";

    classroom.addStudent(student1);
    classroom.addStudent(student2);
    classroom.addStudent(student3);

    classroom.displayAvailableStudents();
    cout << "\n";

    string studentIdToFind = "imt002";
    Student foundStudent = classroom.findStudentById(studentIdToFind);
    if (foundStudent.studentID != "") 
        cout << "Found Student: " << foundStudent.name << "\n";
    else 
        cout << "Student with ID " << studentIdToFind << " not found." << "\n\n";
    

    // Remove a student
    string studentIdToRemove = "imt001";
    classroom.removeStudent(studentIdToRemove);

    // Display available students after removal
    cout << "Available Students after Removal:\n";
    classroom.displayAvailableStudents();

    return 0;
}