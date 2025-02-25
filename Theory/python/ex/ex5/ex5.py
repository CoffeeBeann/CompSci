def printOnLinesV1(L):
    for i in range(len(L)):
        print(L[i])

def printOnLinesV2(L):
    for x in L:
        print(x)

def doit(L):
    for i in range(len(L)):
        print(" "*i + L[i]) # str s * int i => i copies of s

def doitx(L):
    i = 0
    for x in L:
        print(" "*i + str(x)) # str s * int i => i copies of s
        i += 1
        
        

