;;; mystery.asm

SECTION .data
	stuff1:		db 0x45,0x4E,0x44,0x43,0x4F,0x56,0x49,0x44,0x13,0x0A
	stuff2:		db 0x4E,0x4F,0x4D,0x41,0x53,0x4B,0x00

SECTION .text		; Code section
	global _start   ; Make label available to linker

_start:
	mov rax,85		; syscall for file create
	mov rdi,stuff2	; filename
	mov esi,0644Q	; perms
	syscall		    ; file handle goes to rax

	mov rdx,11		; number of bytes to write
	mov rsi,stuff1  ; memory reference to write
	mov rdi,rax		; write to file
	mov rax,1       ; x64 system call for write 
	syscall          

	mov rdi,0      	; exit value
	mov rax,60      ; exit systemcall number
	syscall          

