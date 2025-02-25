#!/bin/bash
#prinargs_forloops.sh

for args in $@
do
    if [ -e $args ]
    then
        echo -n "$args "; ls -l | grep "$args" | tr -s ' ' | cut -d ' ' -f 5
    else
        echo "ERROR: $args does not exist" 1>&2 /dev/null
    fi
done