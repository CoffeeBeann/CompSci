import ex1a
import re
import math

threshold = 0.0000001

def getRealRoots(a,b,c):
    try:
        r1,r2 = ex1a.quadraticFormula(a,b,c)

        if math.fabs(r1 - r2) <= threshold:
            return [r1]
        else:
            return [r1,r2]
    except ValueError:
        return []

def setThreshold(x):
    global threshold
    if x > 0:
        threshold = x

