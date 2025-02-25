#!/bin/bash
#POSIX
# Demonstrate basic shell code

name=COFFEY
echo "name: $name"
let n=1
let n=$n+1
echo "$n"

# outputs command typed
echo "arg 0: $0"

# echo commmands
echo "arg 1: $1"
echo "arg 2: $2"

# Echo # of arguments 
echo "$#"

# If Statement
if [ $1 == "pass" ]
then 
    echo "Correct Password 1"
else
    echo "Wrong Password, banished to home"

    #change directory
    cd $HOME

    #Output working directory
    echo $PWD
fi

if [ $2 != "word" ]
then
    echo "wrong"
else
    echo "yay :)"
fi

# Check for empty vars in $1
if [ -z $1 ] 
then 
    echo "not arg 1 given"
fi

# check for full vars in $2
if [ -n $2 ] 
then 
    echo "arg 2 exists"
fi

# Numerical Flags
let a=1
let b=1
if [ $a -eq $b ] # -eq = equal -ne = not equal -gt = greater than -lt = less than
then
    echo "a is equal to b"
else
    echo "a is not equal to b"
fi

# test if shell script exist (-e does file exist)
if [ -e "shell.bash" ]
then   
    echo "shell script exist"
fi

let i=1
while [ $i -lt 10 ]
do
    echo "hi"
    let i++
done
