#!/usr/bin/python3

class Pos:
    
    key = {"N":(-1,0), "S":(1,0), "E":(0,1), "W":(0,-1)}

    def __init__(self, row, col):
        self.row = row
        self.col = col

    def len(self):
        return self.row + self.col

    def __str__(self):
        return Pos.disp(self.row,self.col)

    sep = ":"

    def disp(v1, v2):
        return f"({v1}{Pos.sep}{v2})"

    def __repr__(self):
        return self.__str__()
    
    def step(self, dir):
        self.row += self.key[dir][0]
        self.col += self.key[dir][1]

    def __eq__(self, other):
        return self.row==other.row and self.col==other.col

class LabPos(Pos):
    
    def __init__(self, row, col, lab):
        Pos.__init__(self, row, col)
        self.lab = lab

    def __str__(self):
        return Pos.__str__(self) + ":" + self.lab
    
    def getLabel(self):
        return self.lab

    def labMerge(T):
        rtn = []
        for x in T:
            rtn.append(x.getLabel())

        return "".join(rtn)


