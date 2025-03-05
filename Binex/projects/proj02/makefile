compile: shellcode.asm
	nasm -f elf64 shellcode.asm -o shellcode.o
	ld shellcode.o -o shellcode
run: shellcode
	@./shellcode
test: shellcode.asm 
	nasm -felf64 shellcode.asm -l shellcode.lst
