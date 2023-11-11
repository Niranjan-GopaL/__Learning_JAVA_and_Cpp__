#include <stdio.h>
#include <string.h>
#define ARRAY_LENGTH(ARRAY) (sizeof(ARRAY) / sizeof(ARRAY[0]))


void tokenizer(char* code_line){
    char* word = "";
    for (int i = 0; i < ARRAY_LENGTH(code_line); i++)
    {
        if(code_line[i] != ' ')
            strncat(word, code_line[i],1);
        else{
            printf(word);
            // setting all characters in word to be \0
            memset(word, '\0', sizeof(word));
        }       
    }
    
}


void execute_c_minus_minus(char* code){

    tokenizer(code); 
    for (int i = 0; i < ARRAY_LENGTH(code); i++)
    {
        // Maybe do the tokenizing on your own later
        // char* token = strtok(code, " ");

        // while (token != NULL) {
        //         printf("Word: %s \n", token);
        //         // Get the next word
        //         token = strtok(NULL, " "); 
        // }

    }
    
}


int main() {
    char code[] =
        "int x = 10;"
        "int y = 5;"
        "int z = x + y;"
        "print z;"
        "if (z > 10) {"
        "    int w = 20;"
        "    print w;"
        "}";
    

    // printf("%s",code);
    execute_c_minus_minus(code);

    return 0;
}