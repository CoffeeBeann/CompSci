#!/usr/bin/python3

class FooFault(Exception):
    pass

def foo(n):
    if n % 2 == 1:
        raise FooFault()
    return n//2

def tester(ins):
    return foo(foo(int(ins)))

try:
    s = input("Enter number: ")
    res = tester(s)
    print(f"result is {res}")
except FooFault:
    print("number rejected")

except:
    print("did you follow directions")


