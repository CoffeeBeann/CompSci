SECTION .data
    stuff1:    db 0x45,0x4E,0x44,0x43,0x4F,0x56,0x49,0x44,0x13,0x0A
    stuff2:    db 0x4E,0x4F,0x4D,0x41,0x53,0x4B,0x00

SECTION .text    
    global _start   

_start:
    mov rax,85    
    mov rdi,stuff2  
    mov esi,0644Q
    syscall        

    mov rdx,11    
    mov rsi,stuff1  
    mov rdi,rax    
    mov rax,1       
    syscall          

    mov rdi,0        
    mov rax,60      
    syscall