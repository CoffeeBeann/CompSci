; Filename: copycat.asm
; Author: MIDN 2/C Ian Coffey (m261194)
; Copy from stdin

; Compilation Instructions...
; nasm -felf64 copycat.asm -o copycat.o
; ld copycat.o -o copycat
; ./copycat < something.txt

bits 64 

section .text
    global _start

_start:
    xor rbx, rbx  ; zero out rbx to prevent null bytes
                  ; rbx will be used to cmp to rax
    inc rbx       ; rbx = 1
    xor rcx, rcx  ; zero out rcx to prevent null bytes 
                  ; rcx will be used to allocate memory
    inc rcx       ; rcx = 1
    xor rdx, rdx  ; zero out rdx to prevent null bytes
                  ; rdx is used as read size in syscall
    inc rdx       ; rdx = 1
    
; Loop & write loop until rax = 0 (EOF thrown)
_loop:
    ; Allocate 1 byte of memory on stack
    sub rsp, rcx

    ; Setup syscall
    xor rdi, rdi  ; stdin = 0
    mov rsi, rsp  ; location of buffer
    xor rax, rax  ; 0 = read(stdin, buffer, rdx=1)
    syscall       ; call read()
    
    ; Check if 0 (EOF) is in rax
    cmp rax, rbx
    jne exit
    nop rax       ; NOP to scew null bytes

    ; Setup syscall for write
    inc rdi       ; stdout = 1 
    syscall       ; 1 = write(stdout, buffer, rdx=1)
    
    ; Deallocate 1 byte from the stack
    add rsp, rcx
    jmp _loop

exit:
    add rsi, rsp  ; Reset stack and return
    xor rax, rax  ; rax = 0
    inc rax       ; rax = 1
    inc rax       ; rax = 2
    shl rax, 5    ; rax = 64
    sub rax, 4    ; rax = 60
    xor rdi, rdi  ; rdi = 0
    syscall       ; exit(0)
