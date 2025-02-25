
M0 = ({'q1','q2','q3'},{'a','b','c'},{('q1','a','q2'),('q1','b','q1'),('q2','a','q1'),('q2','b','q2'),('q2','c','q3')}, 'q1', {'q3'})

M1 = ({1,2,3},{4,6,8},{(1,4,2),(1,6,1),(2,4,1),(2,6,2),(2,8,3)},1,{3})

class DFA:
    def __init__(self,M):
        self.Q,self.E,self.D,self.s,self.W = M
        self.Tmap = {(p,x):q for p,x,q in self.D}

    def run(self,w):
        try:
            q = self.s
            for x in w:
                q = self.Tmap[(q,x)]
            return q in self.W
        except KeyError:
            print("KeyError Raised")
            return False

