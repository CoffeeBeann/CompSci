# Filename: structs_typeconvert.py
# Author: MIDN 2/C Ian Coffey (m261194)
# To demonstrate struct types and type conversions

# Import Libraries
import struct

# Data packing (big endian)
# 3i - 3 ints
data = struct.pack('>3i', 1, 2, 3)
print(data)

# Unpacked data
unpacked = struct.unpack('>3i',data)
print(unpacked)

# Pack IP example (store numbers as char)
pack_ip = struct.pack('>BBBB',10, 3, 2, 4)
print(pack_ip)
unpack_ip = struct.unpack('>BBBB', pack_ip)
print(unpack_ip)

# For loop example (little endian)
d1 = struct.pack('<H', 1000)
for d in d1:
    print(f"{d:02x}", end = " ")
print(d1)



