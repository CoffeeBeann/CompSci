#!/usr/bin/python3

import sys
import math

class noNum(Exception):
    pass

class negDiv(Exception):
    pass


def compute(B):
    sum = 0
    for x in B:
        sum += 1/x

    if sum < 0:
        raise negDiv()

    ssum = math.sqrt(sum/len(B))
    return int(100*ssum)

def getSF(A):
    try:
        B = [ int(s) for s in A ]
    except ValueError:
        raise noNum()
    return compute(B)

try:
    print(getSF(sys.argv[1].split(",")))

except ZeroDivisionError:
    print("Error! no zeros allowed")

except IndexError:
    print("Usage: ./exceptions1.py <numlist>")

except negDiv:
    print ("Error! sqrting negative")
except noNum:
    print("Error ... something wasn't a number")

