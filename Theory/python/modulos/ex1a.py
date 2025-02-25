from math import sqrt

def quadraticFormula(a,b,c):
    D = b*b - 4*a*c
    rD = sqrt(D);
    return [(-b-rD)/(2*a),(-b+rD)/(2*a)]
