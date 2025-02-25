y = 2

def foo(x):
    res = x + y
    return res

def bar(x):
    global y
    y = 50
    return x

print(foo(42))
print(bar(10))
print(foo(42))

