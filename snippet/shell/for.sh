#!/bin/sh
ALL_PARAMS=$*

for p in $ALL_PARAMS
do
echo param: $p
done


echo ------------------



PARAM=$1
ZORE=0
for((i=1;i<=$PARAM;i++))
do
echo i: $i $ZORE+i:  $[$ZORE+$i]
ZORE=$[$ZORE+$i]
done


echo ------------------

INDEX=$PARAM
while [ $INDEX -gt 0 ]
do
echo index: $INDEX 
INDEX=$[$INDEX-1]
done

echo ------------------
