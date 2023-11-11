#include <bits/stdc++.h>
using namespace std;


#define ARR_SIZE 2
#define NUM_OF_SUBJECTS 5

// THIS IS THE MOST USEFUL MACRO I'VE EVER USED
// gives a random number in range [mn,mx]
#define randNum(mn,mx)  rand() % (mx - mn + 1) + mn;      




typedef struct {
    string Name;
    int id;
    int score[NUM_OF_SUBJECTS];
    double average; 
}Student;



double calculateAverage(int* score){
    int sum = 0;
    for(int i = 0; i < NUM_OF_SUBJECTS; i++)
         sum += score[i];

    return (double)(sum / NUM_OF_SUBJECTS);
}


Student findHighestScorer(Student* students){
    Student max_ = students[0];
    for (int i = 0; i < ARR_SIZE; i++)
        if(students[i].average > max_.average)max_ = students[i];
    return max_;
}


void swapStudents(Student &std1, Student &std2){
    // swap(std1.score,std2.score);

    for (int i = 0; i < NUM_OF_SUBJECTS; i++)
        swap(std1.score[i],std2.score[i]);    
}



void displayStudentInfo(Student std){
    cout << "Student name   :" << std.Name << '\n';
    cout << "Student id    :" << std.id << '\n';
    cout << "Student average:" << std.average ;
    cout << "\n\n======= SCORE CARD =====" << '\n';
    cout << "Score in phy :" << std.score[0] << '\n';
    cout << "Score in chem:" << std.score[1] << '\n';
    cout << "Score in math:" << std.score[2] << '\n';
    cout << "Score in eng :" << std.score[3] << '\n';
    cout << "Score in bio :" << std.score[4] << '\n';
}




// Gives realistic data that is randomly generated
// macros that are used throughout function
#define NUM_OF_STRINGS 20
#define UPPER_BOUND_SCORE 90
#define LOWER_BOUND_SCORE 70
void initializer(Student *students__){

    // array of dumy names
    string names[NUM_OF_STRINGS] = {
        "Dikshant_1","Niranjan_1","Nandu_1","Yash_1",
        "Dikshant_2","Niranjan_2","Nandu_2","Yash_2",
        "Dikshant_3","Niranjan_3","Nandu_3","Yash_3",
        "Dikshant_4","Niranjan_4","Nandu_4","Yash_4",
        "Dikshant_5","Niranjan_5","Nandu_5","Yash_5",
    };


    for (int i = 0; i < ARR_SIZE; i++){
        int idx = randNum(0,NUM_OF_STRINGS-1);   
        students__[i].Name = names[idx];

        // an array of dummy roll numbers is generated
        // we append 2022 to a randomly generated number between 530 and 560
        int rollno = randNum(530,560);
        students__[i].id = stoi("2022"+ to_string(rollno) );   

       // assigning dummy scores
       for (int j = 0; j < NUM_OF_SUBJECTS; j++){
            int score = randNum(LOWER_BOUND_SCORE,UPPER_BOUND_SCORE);
            students__[i].score[j] = score;
       }
       students__[i].average = calculateAverage(students__[i].score);
    }
}






// The following 3 function accepts input while 
// perforiming the required error handling
void AcceptName(Student &std, int i){
    cout << "Enter Std " << i+1 << " Name : " ;
    cin >> std.Name;
}

void AcceptId(Student &std, int i){
        cout << "Enter Std " << i+1 << " Id   : " ;
        cin >> std.id;
}

void AcceptScore(Student &std){
    int inp;
    for (int j = 0; j < NUM_OF_SUBJECTS; j++){
            cout << "Enter score of subject " << j+1 << " : ";
            cin >> inp;
            while(inp<0 || inp >100){
                cout << "Invalid input, scores needs to be between 0 and 100 !! \nEnter again :";
                cin >> inp;
            }
            std.score[j] = inp;
    }
    std.average = calculateAverage(std.score);
}








int main(){
    
    cout << "Would you wanna manually input data or generate random data to test on ?(1/0) : ";
    int input;cin >> input;

    Student students[ARR_SIZE];

    if(input == 0){
        cout << "Initialising random data...";
        initializer(students);
        cout << "Values initialised ...";
        cout << "========== DISPLAY ALL STUDENTS INFO =========\n\n";
        for (int i = 0; i < ARR_SIZE; i++){
            displayStudentInfo(students[i]);
            cout << '\n';
        }
    }else{
        for (int i = 0; i < ARR_SIZE; i++){
            AcceptName(students[i],i);
            AcceptId(students[i],i);
            cout << "\n";
            AcceptScore(students[i]);
            cout << "==============================\n\n\n" ;
        }
    }



    int inp; cout << " << ----------- MENU ----------------------------- >>\n";
    cout << "Enter choice : "; cin >> inp;


    // each cases' code is self explanatory
    while (inp!=5){
        switch (inp){
        case 1:
                cout << "\n\n========== AVERAGE SCORE OF EACH STUDENT =========\n\n";
                for (int i = 0; i < ARR_SIZE; i++){
                    cout << "Student : " << students[i].Name << "\n";
                    cout << "Average score :" << students[i].average << "\n";
                }
            break;
        

        case 2:
        // the initialisation of Studnet highest creates a very strange error := 
        // switch "transfer of control bypasses initialization of:"
        // found the solution here => https://stackoverflow.com/questions/5136295/switch-transfer-of-control-bypasses-initialization-of-when-calling-a-function
        // This is why I have braces around the case definition
        { 
            cout << "========== HIGHEST SCORER OF ALL STUDENT =========\n\n";
            Student highest = findHighestScorer(students);
            displayStudentInfo(highest);
            break;
        }


        case 3:{
            cout << "========== SWAP SCORES OF 2 STUDENTS =========\n\n";
            int id1, id2;
            cout << "Enter id of std 1 : "; cin >> id1;
            cout << "Enter id of std 2 : "; cin >> id2;
            cout <<'\n';
            Student std1,std2;
            for (int i = 0; i < ARR_SIZE; i++){
                if(students[i].id == id1)std1 = students[i];
                if(students[i].id == id2)std2 = students[i];
            }

            cout << "Before swapping ------------------------||>> \n";
            cout << "This is " << std1.Name << '\n';
            displayStudentInfo(std1);
            cout << "\nThis is " << std2.Name << '\n';
            displayStudentInfo(std2);

            cout << "\nSwapping student data...\n";
            swapStudents(std1,std2);
            cout << "Swapping complete...\n\n";

            cout << "After swapping -------------------------||>> \n\n";
            cout << "This is " << std1.Name << '\n';
            displayStudentInfo(std1);
            cout << "This is " << std2.Name << '\n';
            displayStudentInfo(std2);
            break;
        }

        case 4:
            cout << "========== DISPLAY ALL STUDENTS INFO =========\n\n";
            for (int i = 0; i < ARR_SIZE; i++){
                displayStudentInfo(students[i]);
                cout << '\n';
            }
            break;
        
    default:
            cout << "Invalid choice" << " \n";
            continue;
        }
         cout << "\n\nEnter choice : "; cin >> inp; cout << "\n\n" ;
    }
    return 0;
}