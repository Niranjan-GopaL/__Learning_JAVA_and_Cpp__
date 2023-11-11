#include <stdio.h>
#include "arithmetic.h"


int perform_arithmetic(int left_operand, char *operator, int right_operator)
{
    switch(*operator){
        case '+': return left_operand + right_operator;  
        case '-': return left_operand - right_operator;
        case '*': return left_operand * right_operator;
        case '/': return left_operand / right_operator;
    }
}










