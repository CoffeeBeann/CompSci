bits 64

section .text
    global add
    global isPowerOfTwo
    global isOdd
    global bitToggle
    global oppositeSigns
    global countingBits

add:
    ; Adds two integers
    ; Parameters: rdi = x, rsi = y
    ; Return: rax = x + y
    mov rax, rdi    ;move 1st arg into rax
    add rax, rsi    ;add 2nd arg to rax
    ret             ;ret

isPowerOfTwo:
    ; Checks if a number is a power of two
    ; Parameters: rdi = x
    ; Return: (if n ^ 2 == rdi, then 1, else 0)
    mov rsi, rdi
    sub rsi, 1
    and rdi, rsi
    jz isPower
    mov rax, 0
    ret
isPower:
    mov rax, 1
    ret

isOdd:
    ; Checks if a number is divisible by two
    ; Parameters: rdi = x
    ; Return: (if n % 2 == 0, then 1, else 0)
    and rdi, 1
    mov rax, rdi
    ret 

bitToggle:
    ; Toggles the bit of a specified index
    ; Parameters: rdi = a, rsi = b
    ; Return: a with bit b toggled
    ; (a xor 2^(b-1))
    mov rcx, 1
    mov rdx, 1
shiftLoop:
    cmp rsi, rdx
    jz break
    shl rcx, 1
    inc rdx
    jmp shiftLoop
break:
    xor rdi, rcx
    mov rax, rdi
    ret

oppositeSigns:
    ; Determines if two numbers have opposite signs
    ; Parameters: rdi = a, rsi = b
    ; Return: 0 if a & b have the same signs, else 1
    xor edi, esi    
    js diffSign
    mov rax, 0
    ret
diffSign:
    mov rax, 1
    ret

countingBits:
    ; Determines in a number n how many bits are set to 1
    ; Parameters: rdi = a
    ; Return: n number of bits set to 1, stored in rax
    mov rsi, 0 ; count = 0
whileLoop:
    mov rbx, rdi
    sub rbx, 1
    cmp rdi, 0
    jz exit
    and rdi, rbx ; n &= (n-1)
    inc rsi
    sub rbx, 1 ; n - 1 again for next loop
    jmp whileLoop
exit:
    mov rax, rsi
    ret

