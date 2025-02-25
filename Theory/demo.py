

def inchToFootInch(x):
    feet = int(x / 12)
    inch = x % 12
    return f"{feet}' {inch}\""

def vowelCount(x):
    vowelList = list(x)
    vowelCount = 0

    for y in x:
        if (y == "a" or y == "e" or y =="i" or y == "o" or y == "u"):
            vowelCount += 1

    print(vowelCount)




myDict = {"a":"u", "u":"i", "i":"a", "o":"e"}

def vowelSwap(x):
    
    rtn = ""
    x = list(x)

    for y in x:
        if y in myDict:
            rtn += myDict[y]
        else:
            rtn += y

    return rtn
