section .data
fmtGlobal db "Результат: %f", 10, 0
extern printf
_200.0 dq 200.0
_20.0 dq 20.0
_10.0 dq 10.0
_8.4 dq 8.4
_8.0 dq 8.0
_0.0 dq 0.0
_3.0 dq 3.0
_2.0 dq 2.0
_1.0 dq 1.0
гол db "qwety", 10
результат dq 0.0
qwety db "qwety", 10
_150.0 dq 150.0
_minus1.0 dq -1.0
а dq 200.0
б dq 400.0
в dq 150.0
_qwety db "qwety", 10
г dq 200.0
_22.0 dq 22.0
д dq 20.0
_26.0 dq 26.0
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
арифметикаИБулевы:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    movsd xmm2, [_0.0]
    movsd [rbp-24], xmm2
    ucomisd xmm0, xmm1
    ja L4
    sub rsp, 8
    mov qword[rbp-32], 0
    mov RAX, 0
    mov [rbp-32], RAX
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    mov [rbp-32], RAX
    jmp L5
L4:
    mov RAX, 1
    mov [rbp-32], RAX
    mov [rbp-32], RAX
L5:
    mov RAX, [rbp-32]
    cmp RAX, 0
    je L2
    mov [rbp-32], RAX
    movsd xmm0, [rbp-8]
    movsd xmm1, [rbp-16]
    ucomisd xmm0, xmm1
    je L6
    sub rsp, 8
    mov qword[rbp-40], 0
    mov RAX, 0
    mov [rbp-40], RAX
    mov [rbp-40], RAX
    jmp L7
L6:
    mov RAX, 1
    mov [rbp-40], RAX
    mov [rbp-40], RAX
L7:
    mov RAX, [rbp-40]
    cmp RAX, 0
    jne L8
    sub rsp, 8
    mov qword[rbp-48], 0
    mov RCX, 1
    mov [rbp-48], RCX
    jmp L9
L8:
    mov RAX, 0
    mov [rbp-48], RAX
L9:
    mov RAX, [rbp-48]
    cmp RAX, 0
    je L2
    sub rsp, 8
    mov qword[rbp-56], 0
    mov RCX, 1
    mov [rbp-56], RCX
    jmp L3
L2:
    mov RAX, 0
    mov [rbp-56], RAX
L3:
    mov RAX, [rbp-56]
    cmp RAX, 0
    je L1
    sub rsp, 32 ; резерв под локальные
    movsd xmm0, [rbp-8]
    movsd xmm1, [rbp-16]
    movsd xmm2, xmm0
    addsd xmm2, xmm1
    movsd xmm3, xmm0
    subsd xmm3, xmm1
    movsd xmm4, xmm2
    mulsd xmm4, xmm3
    movsd xmm5, xmm4
    divsd xmm5, xmm1
    movsd [rbp-24], xmm5
    jmp L0
L1:
    movsd xmm0, [_minus1.0]
    movsd [rbp-24], xmm0
L0:
    movsd xmm0, [rbp-24]
    leave
    ret
цикл_сумма:
    push rbp
    mov rbp, rsp
    sub rsp,8
    movsd xmm0, [_0.0]
    movsd [rbp-8], xmm0
    mov dword[rbp-12], 1
    mov RAX, 1
    mov dword[rbp-12], EAX
L10:
    mov EAX, dword [rbp-12]
    mov RCX, 10
    cmp RAX, RCX
    jle L13
    sub rsp, 8
    mov qword[rbp-20], 0
    mov RBX, 0
    mov [rbp-20], RBX
    mov [rbp-20], RBX
    jmp L14
L13:
    mov RAX, 1
    mov [rbp-20], RAX
    mov [rbp-20], RAX
L14:
    mov RAX, [rbp-20]
    cmp RAX, 0
    je L11
    sub rsp, 4 ; резерв под локальные
    mov dword[rbp-16], 1
    mov RCX, 1
    mov dword[rbp-16], ECX
L15:
    mov EAX, dword [rbp-16]
    mov RCX, 10
    cmp RAX, RCX
    jle L18
    sub rsp, 8
    mov qword[rbp-24], 0
    mov RBX, 0
    mov [rbp-24], RBX
    mov [rbp-24], RBX
    jmp L19
L18:
    mov RAX, 1
    mov [rbp-24], RAX
    mov [rbp-24], RAX
L19:
    mov RAX, [rbp-24]
    cmp RAX, 0
    je L16
    movsd xmm0, [rbp-8]
    mov ECX, dword [rbp-16]
    cvtsi2sd xmm2, RCX
    movsd xmm1, xmm0
    addsd xmm1, xmm2
    movsd [rbp-8], xmm1
L17:
    mov EAX, dword [rbp-16]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-16], EBX
    jmp L15
L16:
L12:
    sub rsp, 12 ; резерв под локальные
    mov EAX, dword [rbp-12]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-12], EBX
    jmp L10
L11:
    sub rsp, 4 ; резерв под локальные
    movsd xmm0, [rbp-8]
    leave
    ret
логика:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd xmm0, [_8.4]
    movsd [rbp-8], xmm0
    movsd xmm0, [_8.0]
    movsd [rbp-16], xmm0
    movsd xmm0, [_0.0]
    movsd [rbp-24], xmm0
    movsd xmm0, [rbp-8]
    movsd xmm1, [rbp-16]
    ucomisd xmm0, xmm1
    ja L22
    sub rsp, 8
    mov qword[rbp-32], 0
    mov RAX, 0
    mov [rbp-32], RAX
    mov [rbp-32], RAX
    jmp L23
L22:
    mov RAX, 1
    mov [rbp-32], RAX
    mov [rbp-32], RAX
L23:
    mov RAX, [rbp-32]
    cmp RAX, 0
    je L21
    sub rsp, 8 ; резерв под локальные
    movsd xmm0, [_1.0]
    movsd [rbp-24], xmm0
    leave
    ret
    jmp L20
L21:
    movsd xmm0, [rbp-8]
    movsd xmm1, [rbp-16]
    ucomisd xmm0, xmm1
    je L25
    sub rsp, 8
    mov qword[rbp-32], 0
    mov RAX, 0
    mov [rbp-32], RAX
    mov [rbp-32], RAX
    jmp L26
L25:
    mov RAX, 1
    mov [rbp-32], RAX
    mov [rbp-32], RAX
L26:
    mov RAX, [rbp-32]
    cmp RAX, 0
    je L24
    sub rsp, 8 ; резерв под локальные
    movsd xmm0, [_2.0]
    movsd [rbp-24], xmm0
    leave
    ret
    jmp L20
L24:
    movsd xmm0, [_3.0]
    movsd [rbp-24], xmm0
    leave
    ret
L20:
    movsd xmm0, [rbp-24]
    leave
    ret
сложноеВычисление:
    push rbp
    mov rbp, rsp
    sub rsp,48
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    movsd [rbp-24], xmm2
    movsd [rbp-32], xmm3
    movsd [rbp-40], xmm4
    movsd xmm5, [_0.0]
    movsd [rbp-48], xmm5
    movsd xmm5, [_0.0]
    ucomisd xmm1, xmm5
    jne L29
    sub rsp, 8
    mov qword[rbp-56], 0
    mov RAX, 0
    mov [rbp-56], RAX
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    movsd [rbp-24], xmm2
    movsd [rbp-32], xmm3
    movsd [rbp-40], xmm4
    mov [rbp-56], RAX
    jmp L30
L29:
    mov RAX, 1
    mov [rbp-56], RAX
    mov [rbp-56], RAX
L30:
    mov RAX, [rbp-56]
    cmp RAX, 0
    je L28
    sub rsp, 8 ; резерв под локальные
    movsd xmm0, [rbp-8]
    movsd xmm1, [rbp-16]
    movsd xmm2, xmm0
    addsd xmm2, xmm1
    movsd xmm3, [rbp-24]
    movsd xmm4, [rbp-32]
    movsd xmm5, xmm3
    subsd xmm5, xmm4
    movsd xmm6, xmm2
    mulsd xmm6, xmm5
    movsd xmm7, [rbp-40]
    movsd xmm0, xmm6
    divsd xmm0, xmm7
    leave
    ret
    jmp L27
L28:
    movsd xmm0, [_minus1.0]
    movsd [rbp-48], xmm0
    leave
    ret
L27:
    movsd xmm0, [rbp-48]
    leave
    ret
сумма:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    pxor xmm2, xmm2
    movsd [rbp-24], xmm2
    movsd xmm2, xmm0
    addsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
разность:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    pxor xmm2, xmm2
    movsd [rbp-24], xmm2
    movsd xmm2, xmm0
    subsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
умножение:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    pxor xmm2, xmm2
    movsd [rbp-24], xmm2
    movsd xmm2, xmm0
    mulsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
деление:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd [rbp-8], xmm0
    movsd [rbp-16], xmm1
    pxor xmm2, xmm2
    movsd [rbp-24], xmm2
    movsd xmm2, xmm0
    divsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
_start:
    push rbp
    mov rbp, rsp
    sub rsp,0
    movsd xmm0, [а]
    movsd xmm1, [б]
sub rsp, 8
    call сумма
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movsd xmm0, [а]
    movsd xmm1, [б]
sub rsp, 8
    call разность
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movsd xmm0, [а]
    movsd xmm1, [б]
sub rsp, 8
    call умножение
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movsd xmm0, [а]
    movsd xmm1, [б]
sub rsp, 8
    call деление
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movsd xmm0, [а]
    movsd xmm1, [б]
    movsd xmm2, [в]
    movsd xmm3, [г]
    movsd xmm4, [д]
sub rsp, 8
    call сложноеВычисление
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
sub rsp, 8
    call цикл_сумма
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movsd xmm0, [а]
    movsd xmm1, [б]
sub rsp, 8
    call арифметикаИБулевы
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movsd xmm0, [_26.0]
    movsd xmm1, [_22.0]
sub rsp, 8
    call арифметикаИБулевы
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
sub rsp, 8
    call логика
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    mov rax, 60
    syscall
