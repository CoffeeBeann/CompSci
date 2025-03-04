# Filename: roadmap.py
# Psuedocode to demonstrate the roadmap problem

import copy

# Function to start the recursive calls
def findPath(N, d):
  if d == 0:
      return [len(N)]
  call = [[] for i in range(d)]
  result = findPathRecursive(call, N, d, 0)
  return result[1]

# Recursive path finder O(nd^n)
def findPathRecursive(call, N, d, index):
  if (index == len(N)):
    return sumSquare(call)
  else:
    result = [0]*d
    for i in range(d):
      newCall = copy.deepcopy(call)
      newCall[i].append(N[index])
      result[i] = findPathRecursive(newCall, N, d, index + 1)
    return findMin(result, d)

# O(n) runtime
def sumSquare(call):
  square = 0
  path = []
  offset = 0
  for i in range(len(call)):
    sum = 0
    if len(call[i]) != 0:
      path.append(len(call[i]) + offset)
      offset += len(call[i])
      for j in range(len(call[i])):
        sum += call[i][j]
      square += sum*sum
  ans = []
  ans.append(square)
  ans.append(path)
  return ans

# O(d) runtime
def findMin(result, d):
  sumArray = []
  for i in range(d):
    sumArray.append(result[i][0])
  return result[sumArray.index(min(sumArray))]

# Main for testing
if __name__ == '__main__':

  N = [2,5,3,3]
  d = 2
  print(findPath(N, d))
