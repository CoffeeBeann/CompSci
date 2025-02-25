#!/usr/bin/python3
#######################################################################
# PROGRAM: This program reads input and prints "MATCH!" for each line #
# containing "b0b" or "b1b", "NO MATCH!" for each line that doesn't.  #
#######################################################################

import sys             # "sys" module means "system"
import re

# Loop over each line of input
for line in sys.stdin: # loops over each line of input
    
    mres = re.search(r'SI(\d\d\d[A-Z]?)',line) 

    # use r'...' strings for regexs
    if mres:
        print(f"{mres[1]}")

