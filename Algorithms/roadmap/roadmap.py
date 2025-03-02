# Filename: roadmap.py
# Psuedocode to demonstrate the roadmap problem
# This wont actually work if you try to run it

# Function to start the recursive calls
def findPath(N, d):
  call = [[] for i in range(d)]
  result = findPathRecursive(call, N, d, 0)
  return result

def findPathRecursive(call, N, d, index):
  if (index == len(N)):
    return sumSquare(call)
  else:
    result = [0]*d
    for i in range(d):
      newCall = call
      newCall[i].append(N[index])
      result[i] = findPathRecursive(newCall, N, d, index + 1)
    return min(result)

def sumSquare(call):
  ans = 0
  for i in range(len(call)):
    sum = 0 
    for j in range(len(call[i])):
      sum += call[i][j]
    ans += sum*sum
  return ans
