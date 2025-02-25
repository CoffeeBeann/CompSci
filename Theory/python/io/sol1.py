#!/usr/bin/env python3

# Import Libraries
import os 
import sys

# Grab Command Line Arguments
args = sys.argv

# Open & Read first File
file1 = args[1]
max = 0
A = []
with open(file1, "r") as fh:
    for line in fh:
        tmp =  line.strip();
        if len(tmp) > max:
            max = len(tmp)
        A.append(tmp)
    
# Open & Read Second File
file2 = args[2]
with open(file2, "r") as fh:
    B = [ line.strip() for line in fh ]

# Print out Adjacent Messages
for lineA, lineB in zip(A,B):
    print(lineA + (max - len(lineA) + 1)*" " + lineB)


