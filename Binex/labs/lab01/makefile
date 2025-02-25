compile: functions.asm part1.c
	nasm -f elf64 functions.asm -o functions.o
	gcc -o part1 part1.c functions.o -z noexecstack

run:
	@./part1

