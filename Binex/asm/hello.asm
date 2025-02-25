; Filename: hello.asm
; Author: MIDN 2/C Ian Coffey (m261194)
; Hello World in assembly

section .data:
    hello db 'Hello, World!', 0x0a

; Everything here will be marked as executable
section .text
    global _start

_start:
    mov rax, 1              ; 1 = sys_write
    mov rdi, 1              ; stdout = 1
    ;lea rsi, [rel hello]   ; mov rsi, hello
    mov rsi, hello
    mov rdx, 13            ; 13 chars to be printed out 
    
    syscall                 ; sys_write, stdout, hello, 13 

    mov rax, 60             ; 60 = sys_exit
    xor rdi, rdi            ; 0 = exit code
    
    syscall                 ; sys_exit, 0
