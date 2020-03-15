#!/bin/sh
function sum()
{

SUM=0
SUM=$[$1+$2]
echo $SUM
}

read -p "input the first param:" P1

read -p "input the second param:" P2


sum $P1 $P2
