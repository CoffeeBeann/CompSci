compile: copycat.asm
	nasm -f elf64 copycat.asm -o copycat.o
	ld copycat.o -o copycat
run: copycat.asm test.txt
	@./copycat < test.txt
test: copycat.asm 
	nasm -felf64 copycat.asm -l copycat.lst
