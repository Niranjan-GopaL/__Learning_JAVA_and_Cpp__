#include <bits/stdc++.h>
using namespace std;


class Student{

private:
    int std_ID;

public:

    Student(int std_ID){ this->std_ID = std_ID; }

    int get_std_ID() { return this->std_ID; }
    void set_std_ID(int new_std_ID) { this->std_ID = new_std_ID; }

};


class Classroom{

private:
    vector<Student*> students;
    unordered_map<int,Student*> students_;


public:

    Classroom(){}

    void add_student(Student* std)  { this->students.push_back(std);                    }
    void add_student_(Student* std) { this->students_.emplace(std->get_std_ID(), std ); }

    vector<Student*>   get_students()           { return this->students;  }
    unordered_map<int,Student*> get_students_() { return this->students_; }

};



int main(){

    Classroom classroom;

    Student std1(1);
    Student std2(2);
    Student std3(3);
    Student std4(4);

    //  adding stuff to vector
    classroom.add_student(&std1);
    classroom.add_student(&std2);
    classroom.add_student(&std3);
    classroom.add_student(&std4);

    //  adding stuff to map
    classroom.add_student_(&std1);
    classroom.add_student_(&std2);
    classroom.add_student_(&std3);
    classroom.add_student_(&std4);


    for (Student* std : classroom.get_students()){
        cout << std->get_std_ID() <<'\n';
    }
    
    cout << "\n\n";
    cout << '\n\n';

    for (auto &pair : classroom.get_students_()){
        cout << "Roll No: " << pair.first << " object :-" << pair.second <<'\n';
    }

    
    for (auto pair : classroom.get_students_()){
        cout << "Roll No: " << pair.first << " object :-" << pair.second <<'\n';
    }
    
/*
                DIFFERENCE BETWEEN `auto &pair` and `auto pair`
    


    for (auto pair : classroom.get_students_()){
        cout << "Roll No: " << pair.first << " object :-" << pair.second <<'\n';
    }

        auto pair creates a copy of each element fetched from the container returned by 
        classroom.get_students_(). The elements are copied during each iteration, and 
        modifications made to pair won't affect the original elements in the container. 
        This is useful when you want to work with copies of the elements and don't intend 
        to modify the original elements.




    for (auto &pair : classroom.get_students_()){
        cout << "Roll No: " << pair.first << " object :-" << pair.second <<'\n';
    }

        auto &pair creates a reference to each element fetched from the container returned 
        by classroom.get_students_(). It avoids copying the elements, allowing direct access
        and potential modification of the original elements in the container. This is more 
        efficient than creating copies, especially for larger objects, and any modifications
        made to pair will affect the original elements in the container.
*/





}