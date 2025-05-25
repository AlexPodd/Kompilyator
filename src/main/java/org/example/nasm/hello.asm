section .data
fmtGlobal db "Результат: %f", 10, 0
extern printf
_200.0 dq 200.0
_20.0 dq 20.0
_10.0 dq 10.0
_6.0 dq 6.0
_7.0 dq 7.0
_0.0 dq 0.0
_1.0 dq 1.0
гол db "qwety", 10
результат dd 0
qwety db "qwety", 10
_150.0 dq 150.0
а dd 200
б dd 400
в dd 150
_qwety db "qwety", 10
г dd 200
д dd 20
_400.0 dq 400.0
section .bss
num_buf resb 32
global _start
section .text
print_int:
    push rbp
    mov rbp, rsp
    push rbx
    push rcx
    push rdx
    push rsi
    push rdi
    push rax
    sub rsp, 40
    mov rax, [rbp+16]
    mov rdi, num_buf
    call int_to_str
    mov rsi, rdi
    mov rdx, rax
    mov rax, 1
    mov rdi, 1
    syscall
    add rsp, 40
    pop rax
    pop rdi
    pop rsi
    pop rdx
    pop rcx
    pop rbx
    mov rsp, rbp
    pop rbp
    ret
int_to_str:
    push rbx
    push rcx
    push rdx
    push rsi
    mov rcx, 0
    mov rsi, rdi
    cmp rax, 0
    jge .convert
    mov byte [rsi], '-'
    inc rsi
    neg rax
.convert:
    mov rbx, 10
    .store_loop:
    xor rdx, rdx
    div rbx
    push rdx
    inc rcx
    test rax, rax
    jnz .store_loop
.write_digits:
    pop rdx
    add dl, '0'
    mov [rsi], dl
    inc rsi
    loop .write_digits
    mov byte [rsi], 10
    inc rsi
    mov rax, rsi
    sub rax, rdi
    pop rsi
    pop rdx
    pop rcx
    pop rbx
    ret
сложноеВычисление:
    push rbp
    mov rbp, rsp
    sub rsp,24
    mov dword[rbp-4], EDI
    mov dword[rbp-8], ESI
    mov dword[rbp-12], EDX
    mov dword[rbp-16], ECX
    mov dword[rbp-20], R8D
    mov dword[rbp-24], 0
    mov RAX, 0
    cmp RSI, RAX
    jne L2
    sub rsp, 8
    mov qword[rbp-32], 0
    mov [rbp-32], RAX
    mov dword[rbp-16], ECX
    mov dword[rbp-12], EDX
    jmp L3
L2:
    mov RAX, 1
    mov [rbp-32], RAX
    mov [rbp-32], RAX
L3:
    mov RAX, [rbp-32]
    cmp RAX, 0
    je L1
    sub rsp,24
    mov RCX, RDI
    add RCX, RSI
    mov EBX, dword [rbp-12]
    mov EDX, dword [rbp-16]
    mov RAX, RBX
    sub RAX, RDX
    push RDX
    push RCX
    push RAX
    pop RCX
    pop RAX
    imul RAX, RCX
    mov RBX, RAX
    pop RDX
    push RDX
    mov RAX, RBX
    cqo
    idiv R8
    pop RDX
    sub rsp, 8
    mov qword[rbp-64], 0
    leave
    ret
    sub rsp,24
    jmp L0
L1:
    sub rsp,24
    mov RAX, -1
    mov dword[rbp-24], EAX
    leave
    ret
L0:
    sub rsp,24
    mov EAX, dword [rbp-24]
    leave
    ret
цикл_сумма:
    push rbp
    mov rbp, rsp
    sub rsp,4
    mov dword[rbp-4], 0
    sub rsp,8
    mov dword[rbp-8], 1
    sub rsp, 8
    mov qword[rbp-20], 0
    mov RAX, 1
    mov dword[rbp-8], EAX
L4:
    mov EAX, dword [rbp-8]
    mov RCX, 10
    cmp RAX, RCX
    jle L7
    sub rsp, 8
    mov qword[rbp-28], 0
    mov RBX, 0
    mov [rbp-28], RBX
    mov [rbp-28], RBX
    jmp L8
L7:
    mov RAX, 1
    mov [rbp-28], RAX
    mov [rbp-28], RAX
L8:
    mov RAX, [rbp-28]
    cmp RAX, 0
    je L5
    mov ECX, dword [rbp-4]
    mov EBX, dword [rbp-8]
    mov RDX, RCX
    add RDX, RBX
    sub rsp, 8
    mov qword[rbp-36], 0
    mov dword[rbp-4], EDX
L6:
    mov EAX, dword [rbp-8]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-8], EBX
    jmp L4
L5:
    sub rsp,4
    mov EAX, dword [rbp-4]
    leave
    ret
арифметикаИБулевы:
    push rbp
    mov rbp, rsp
    sub rsp,12
    mov dword[rbp-4], EDI
    mov dword[rbp-8], ESI
    mov dword[rbp-12], 0
    cmp RDI, RSI
    jg L13
    sub rsp, 8
    mov qword[rbp-20], 0
    mov RAX, 0
    mov [rbp-20], RAX
    mov [rbp-20], RAX
    jmp L14
L13:
    mov RAX, 1
    mov [rbp-20], RAX
    mov [rbp-20], RAX
L14:
    mov RAX, [rbp-20]
    cmp RAX, 0
    je L11
    mov [rbp-20], RAX
    cmp RDI, RSI
    je L15
    sub rsp, 8
    mov qword[rbp-28], 0
    mov RAX, 0
    mov [rbp-28], RAX
    mov [rbp-28], RAX
    jmp L16
L15:
    mov RAX, 1
    mov [rbp-28], RAX
    mov [rbp-28], RAX
L16:
    mov RAX, [rbp-28]
    cmp RAX, 0
    jne L17
    sub rsp, 8
    mov qword[rbp-36], 0
    mov RCX, 1
    mov [rbp-36], RCX
    jmp L18
L17:
    mov RAX, 0
    mov [rbp-36], RAX
L18:
    mov RAX, [rbp-36]
    cmp RAX, 0
    je L11
    sub rsp, 8
    mov qword[rbp-44], 0
    mov RCX, 1
    mov [rbp-44], RCX
    jmp L12
L11:
    mov RAX, 0
    mov [rbp-44], RAX
L12:
    mov RAX, [rbp-44]
    cmp RAX, 0
    je L10
    sub rsp,12
    mov RCX, RDI
    add RCX, RSI
    mov RBX, RDI
    sub RBX, RSI
    push RAX
    mov RAX, RCX
    imul RAX, RBX
    mov RDX, RAX
    pop RAX
    mov RAX, RDX
    cqo
    idiv RSI
    push RDX
    cqo
    idiv RDI
    mov RCX, RDX
    pop RDX
    sub rsp, 8
    mov qword[rbp-64], 0
    mov dword[rbp-12], ECX
    sub rsp,12
    jmp L9
L10:
    sub rsp,12
    mov RAX, -1
    mov dword[rbp-12], EAX
L9:
    sub rsp,12
    mov EAX, dword [rbp-12]
    leave
    ret
логика:
    push rbp
    mov rbp, rsp
    sub rsp,12
    mov dword[rbp-4], 7
    mov dword[rbp-8], 6
    mov dword[rbp-12], 0
    mov EAX, dword [rbp-4]
    mov ECX, dword [rbp-8]
    cmp RAX, RCX
    jg L21
    sub rsp, 8
    mov qword[rbp-20], 0
    mov RBX, 0
    mov [rbp-20], RBX
    mov [rbp-20], RBX
    jmp L22
L21:
    mov RAX, 1
    mov [rbp-20], RAX
    mov [rbp-20], RAX
L22:
    mov RAX, [rbp-20]
    cmp RAX, 0
    je L20
    sub rsp,12
    sub rsp, 8
    mov qword[rbp-40], 0
    mov RCX, 1
    mov dword[rbp-12], ECX
    mov RAX, RCX
    leave
    ret
    sub rsp,12
    jmp L19
L20:
    mov EAX, dword [rbp-4]
    mov ECX, dword [rbp-8]
    cmp RAX, RCX
    je L24
    sub rsp, 8
    mov qword[rbp-60], 0
    mov RBX, 0
    mov [rbp-60], RBX
    mov [rbp-60], RBX
    jmp L25
L24:
    mov RAX, 1
    mov [rbp-60], RAX
    mov [rbp-60], RAX
L25:
    mov RAX, [rbp-60]
    cmp RAX, 0
    je L23
    sub rsp,12
    mov RCX, 2
    mov dword[rbp-12], ECX
    mov RAX, RCX
    leave
    ret
    sub rsp,12
    jmp L19
L23:
    sub rsp,12
    mov RAX, 3
    mov dword[rbp-12], EAX
    leave
    ret
L19:
    sub rsp,12
    mov EAX, dword [rbp-12]
    leave
    ret
факториал:
    push rbp
    mov rbp, rsp
    sub rsp,4
    mov dword[rbp-4], EDI
    mov RAX, 1
    cmp RDI, RAX
    je L27
    sub rsp, 8
    mov qword[rbp-12], 0
    mov RCX, 0
    mov [rbp-12], RCX
    mov [rbp-12], RCX
    jmp L28
L27:
    mov RAX, 1
    mov [rbp-12], RAX
    mov [rbp-12], RAX
L28:
    mov RAX, [rbp-12]
    cmp RAX, 0
    je L26
    sub rsp,4
    mov RCX, 1
    mov RAX, RCX
    leave
    ret
L26:
    sub rsp,4
    mov RAX, 1
    mov RCX, RDI
    sub RCX, RAX
    mov dword[rbp-4], EDI
    mov RDI, RCX
sub rsp, 4
    call факториал
    add rsp, 4
    mov ECX, dword [rbp-4]
    push RDX
    imul RAX, RCX
    mov RBX, RAX
    pop RDX
    mov RAX, RBX
    leave
    ret
сумма:
    push rbp
    mov rbp, rsp
    sub rsp,12
    mov dword[rbp-4], EDI
    mov dword[rbp-8], ESI
    mov dword[rbp-12], 0
    mov RAX, RDI
    add RAX, RSI
    sub rsp, 8
    mov qword[rbp-20], 0
    leave
    ret
разность:
    push rbp
    mov rbp, rsp
    sub rsp,12
    mov dword[rbp-4], EDI
    mov dword[rbp-8], ESI
    mov dword[rbp-12], 0
    mov RAX, RDI
    sub RAX, RSI
    sub rsp, 8
    mov qword[rbp-20], 0
    leave
    ret
умножение:
    push rbp
    mov rbp, rsp
    sub rsp,12
    mov dword[rbp-4], EDI
    mov dword[rbp-8], ESI
    mov dword[rbp-12], 0
    push RDX
    mov RAX, RDI
    imul RAX, RSI
    pop RDX
    sub rsp, 8
    mov qword[rbp-20], 0
    leave
    ret
деление:
    push rbp
    mov rbp, rsp
    sub rsp,12
    mov dword[rbp-4], EDI
    mov dword[rbp-8], ESI
    mov dword[rbp-12], 0
    push RDX
    mov RAX, RDI
    cqo
    idiv RSI
    pop RDX
    sub rsp, 8
    mov qword[rbp-20], 0
    leave
    ret
_start:
    push rbp
    mov rbp, rsp
    sub rsp,0
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 8
    call сумма
    add rsp, 8
    sub rsp, 8
    mov qword[rbp-8], 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 0
    call разность
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 0
    call умножение
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 0
    call деление
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov RDI, 10
sub rsp, 0
    call факториал
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
    mov EDX, [в]
    mov ECX, [г]
    mov R8D, [д]
sub rsp, 0
    call сложноеВычисление
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
sub rsp, 0
    call цикл_сумма
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 0
    call арифметикаИБулевы
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov RDI, 25
    mov RSI, 12
sub rsp, 0
    call арифметикаИБулевы
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
sub rsp, 0
    call логика
    add rsp, 0
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov rax, 60
    syscall
