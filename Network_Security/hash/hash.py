import hashlib

h1 = hashlib.md5()
h1.update(b"zello")

h1.digest()
print("digest: ", h1.digest())

h1.hexdigest()
print("hexdigest: ", h1.hexdigest())

