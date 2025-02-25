from pwn import *

p = process("./bitbomb")

p.recvuntil("Today we'll be exploring string comparisons! What's the answer?")
p.sendline(b'Go Navy!')

p.recvuntil("(Here's a hint - I need your answer as a single character!)")
p.sendline(b'*')

p.recvuntil("What essential piece of kit did the leader of the Knights of the Round Table wield?")
p.sendline(b'excalibur')

p.recvuntil("Enter level 5 input:")
p.interactive()

