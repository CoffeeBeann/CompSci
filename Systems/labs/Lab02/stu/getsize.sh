#!/bin/bash

if [ -e $1 ]
then
    ls -l | grep "$1" | tr -s ' ' | cut -d ' ' -f 5
else
    echo "ERROR: $1 does not exist" 1>&2 /dev/null
fi