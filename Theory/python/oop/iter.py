#!/usr/bin/python3

class CountdownIterator:
    def __init__(self,N,K):
        self.count=N
        self.stop=K

    def __next__(self):
        if self.count==self.stop - 1:
            raise StopIteration
        res = self.count
        self.count -= 1
        return res

class Countdown:
    def __init__(self,N,K):
        self.N=N
        self.K=K

    def __iter__(self):
        return CountdownIterator(self.N,self.K)

class Fiberator:
    def __init__(self,z):
        self.stop = z
        self.prevval = 0
        self.currval = 1
        self.count = 0

    def __next__(self):
        if self.count  == z:
            raise StopIteration

        tmpPre = self.currval
        self.currval = self.prevval + 0

class Fibseq:
    def __init__(self,z):
        self.stop = z
    
    def __iter__(self)
        return Fiberator(self.z)
    







        
