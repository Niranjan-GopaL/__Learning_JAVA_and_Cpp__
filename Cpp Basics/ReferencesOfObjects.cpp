#include <bits/stdc++.h>
using namespace std;


class Student{

private:
    int std_ID;
    bool sex;

public:

    Student(int std_ID, bool sex){
        this->std_ID = std_ID;
        this->sex = sex;
    }

    int get_std_ID(){
        this->std_ID;
    }

};


class Classroom{

private:
    vector<Student*> students;

public:

    Classroom(){}

    void add_student(Student* std){
        this->students.push_back(std);
    }

    vector<Student*> get_students(){
        this->students;
    }

};



int main(){

    Classroom classroom;

    Student std1(1,0);
    Student std2(2,0);
    Student std3(3,0);
    Student std4(4,0);

    classroom.add_student(&std1);
    classroom.add_student(&std2);
    classroom.add_student(&std3);
    classroom.add_student(&std4);

    for (Student* std : classroom.get_students())
    {
        cout << std->get_std_ID() ;
    }
    

}