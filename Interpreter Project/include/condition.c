#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include "condition.h"



bool evaluate_condition(int x, char *op, int y)
{
    // switch will only compare with first character
    // so if it was switch(*op) === switch('>') so > and >= will trigger same case

    switch(op[0]){
        case '>': 
            if(strcmp(op,">"))
                return x > y;
            else
                return x>= y;

        case '<':
            if(strcmp(op,"<"))
                return x < y;
            else
                return x <= y;


        case '=': 
            if(strcmp(op,"=="))
                return x == y;
            else
                return x != y;
            
    }
}

