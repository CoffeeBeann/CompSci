import ex1a # We want to use 'quadraticFormula'!
import re

def roots(p):
    M = {'':1.0, '-':-1.0, '+':1.0}
    L = [s.replace(' ','') for s in re.split(r'x\^2|x|$',p)]
    C = [float(z) if re.search(r'\d',z) else M[z] for z in L[0:3]]
    a,b,c = C
    return ex1a.quadraticFormula(a,b,c)
    
p = "x^2 - x - 1"
r1,r = roots(p)
print(f'golden ratio r = {r} is a root of {p}.')
print(f'check: r^2 - r - 1 = {r*r - r - 1}')
# note: math.sqrt(2) is an error!  Module math is not
#       "inherited" from import ex1a
