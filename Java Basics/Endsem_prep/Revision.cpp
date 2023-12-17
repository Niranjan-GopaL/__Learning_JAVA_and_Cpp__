
#include <bits/stdc++.h>
using namespace std;


class Class{

public:
int data;

Class(int data){
    this->data = data;
}

};


int main(){

    string input;
    getline(cin,input);
    stringstream ss(input);

    string word; vector<string> words;

    while(ss >> word)words.push_back(word);

    for(string w: words)
        cout << "WORD " << w << "\n" ;


    vector<Class*> objects;

    Class* class_obj = new Class(1);
    objects.push_back(class_obj);

    class_obj = new Class(2);
    objects.push_back(class_obj);

    class_obj = new Class(3);
    objects.push_back(class_obj);

    class_obj = new Class(4);
    objects.push_back(class_obj);


    for(Class* object : objects){
        cout << "DATA IS " << object->data << "\n";
    }

    objects.at(0)->data = 10;
    objects.at(1)->data = 20;
    objects.at(2)->data = 30;
    objects.at(3)->data = 40;

    for(Class* object : objects){
        cout << "DATA AFTER OPERATION IS " << object->data << "\n";
    }

    return 0;
}