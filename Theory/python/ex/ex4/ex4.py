def foo(x,y):
    res = x + y
    return res

def bar(s):
    A = s.split(":")
    return foo(A[0],A[1])

print(f"Testing foo ... {foo(6,7)}")
print(f"Testing bar ... {bar('6:7')}")
