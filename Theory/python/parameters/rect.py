# Ian Coffey
# Rect.py
# Make a rectangle

import math

def ASCIIrectangle(w, h, offset = 0, bchar="*", filled = False, msg = ""):
    
    count = 0;
    h2 = math.ceil(h/2) - 1
    w2 = 0;
    count = 0;
    if(msg):
        w2 = (w - len(msg)) / 2
        w2 += offset
        

    for i in range(0, h):
        
        for j in range(0, w + offset):

            # Print offest
            if (j < offset):
                print(" ",end='')

            elif(h2 == i and (j >= w2) and count < len(msg)):
                print(msg[count],end='')
                count += 1

            elif (i == 0 or i == h - 1):
                print(bchar,end='')
            
            elif (j == 0 + offset): # edge of rectangle
                print(bchar,end='')
            
            elif (j == w + offset - 1): # outer rectangle edge
                print(bchar,end='')

            elif (filled): # fill
                print(bchar,end='')

            elif (not filled):
                print(" ",end='')
    
        print() # new line


