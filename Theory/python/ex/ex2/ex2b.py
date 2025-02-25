def f(x):
    return x + y

def g(z):
    y = 2
    return f(z)

def h(z):
    y = 2
    def f(x):
        return x + y
    return f(z)
