#!/bin/bash
#prinargs_forloops.sh

# Check If arguements exist
if [ "$#" -ne 2 ]; then
    echo "ERROR: Require path and size" 1>&2
    exit 0
fi

# Check If File & Size Exist
if [ ! -e "$2" ]; then
    echo "ERROR: File $2 does not exist" 1>&2 
    exit 0
fi

# Check if arg 1 is a number
if [[ $1 =~ ^[-][0-9]+$ || $1 =~ ^[0-9]+$ ]]
then
    : # SKIP
else
    echo "ERROR: Require a number for size" 1>&2 
    exit 0
fi

# check if arg 1 is positive
if [ $1 -lt 0 ]; then
    echo "ERROR: Require a positive number for size" 1>&2 
    exit 0
fi

# Check if Size Specified is Greater than File
if [ $(stat -c %s "$2") -ge "$1" ]; then
    echo "yes"
else   
    echo "no"
fi