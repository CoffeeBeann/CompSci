# Filename: basics.py
# Author: MIDN Ian Coffey (m261194)
# To go over the basic syntax of python

#!/bin/bash

import sys

# Loop
myList1 = [1,2,3,4,5]
for x in myList1:
    print(x, end = ",")

# Newline
print()

myList2 = [i**2 for i in range(5)]
for y in myList2:
    print(y, end = ",")

# Newline
print()

# Dictionary {Values, Key}
myDict1 = {'a':1, 'b':2}
print(myDict1.keys())
print(myDict1.values())

# Bytes & Bytearrays (Not Mutable)
a = b"Hello World"
for byte in a: # Prints out bytes of each character
    print(byte, end = " = " + chr(byte) + "\n")

# Data Sanitization
n = int(input("Enter a number: "))
print(n*2)

# I/O 
try:
    f = open(sys.argv[1], "r")
    lines = f.readlines()
    print(lines[0].split())
except:
    print("no file found")

# Reading as bytes (includes every character, including /n)
try:
    f = open(sys.argv[1], "rb")
    b = f.read()
    print(type(b))
    print(len(b))
except:
    print("no file found")









