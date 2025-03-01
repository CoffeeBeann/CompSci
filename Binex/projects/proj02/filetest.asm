; Filename: copycat.asm
; Author: MIDN 2/C Ian Coffey (m261194)
; Copy from stdin

; Compilation Instructions...
; nasm -felf64 copycat.asm -o copycat.o
; ld copycat.o -o copycat
; ./copycat < something.txt

bits 64 

section .data:
    win db 'win.txt'

section .text
    global _start

_start:
    mov rax, 5
    mov rbx, win
    mov rcx, 0
    
