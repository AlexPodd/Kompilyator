section .data
fmtGlobal db "Результат: %f", 10, 0
extern printf
_200.0 dq 200.0
_20.0 dq 20.0
_10.0 dq 10.0
_5.0 dq 5.0
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
д dq 20.0
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
цикл_сумма:
    push rbp
    mov rbp, rsp
    sub rsp,8
    movsd xmm0, qword [_0.0]
    movsd qword [rbp-8], xmm0
    sub rsp,12
    mov dword[rbp-12], 1
    mov RAX, 1
    mov dword[rbp-12], EAX
L0:
    mov EAX, dword [rbp-12]
    mov RCX, 10
    cmp RAX, RCX
    jg L3
    sub rsp, 8
    mov qword[rbp-28], 0
    movsd xmm0, [rbp-28]
    sub rsp, 8
    mov qword[rbp-36], 0
    movsd [rbp-36], xmm0
    movsd [rbp-28], xmm0
    jmp L4
L3:
    push RAX
    mov rax, 1
    cvtsi2sd xmm0, rax
    pop RAX
    movsd [rbp-36], xmm0
L4:
    movsd xmm0, [rbp-36]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L1
    sub rsp,16
    mov dword[rbp-16], 1
    mov RAX, 1
    mov dword[rbp-16], EAX
L5:
    mov EAX, dword [rbp-16]
    mov RCX, 10
    cmp RAX, RCX
    jg L8
    movsd xmm0, [rbp-28]
    sub rsp, 8
    mov qword[rbp-60], 0
    movsd [rbp-60], xmm0
    movsd [rbp-28], xmm0
    jmp L9
L8:
    push RAX
    mov rax, 1
    cvtsi2sd xmm0, rax
    pop RAX
    movsd [rbp-60], xmm0
L9:
    movsd xmm0, [rbp-60]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L6
    movsd xmm1, qword [rbp-8]
    mov EAX, dword [rbp-16]
    cvtsi2sd xmm3, RAX
    movsd xmm2, xmm1
    addsd xmm2, xmm3
    movsd qword[rbp-8], xmm2
L7:
    mov EAX, dword [rbp-16]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-16], EBX
    jmp L5
L2:
    sub rsp,12
    mov EAX, dword [rbp-12]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-12], EBX
    jmp L0
L1:
    sub rsp,8
    movsd xmm0, qword [rbp-8]
    leave
    ret
арифметикаИБулевы:
    push rbp
    mov rbp, rsp
    sub rsp,24
    pxor xmm2, xmm2
    movsd qword [rbp-8], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-16], xmm2
    movsd xmm2, qword [_minus1.0]
    movsd qword [rbp-24], xmm2
    ucomisd xmm0, xmm1
    jbe L14
    sub rsp, 8
    mov qword[rbp-32], 0
    movsd xmm2, [rbp-32]
    movsd qword[rbp-8], xmm0
    movsd qword[rbp-16], xmm1
    sub rsp, 8
    mov qword[rbp-40], 0
    movsd [rbp-40], xmm2
    movsd [rbp-32], xmm2
    jmp L15
L14:
    sub rsp, 8
    mov qword[rbp-48], 0
    movsd xmm0, [rbp-48]
    movsd [rbp-40], xmm0
    movsd [rbp-48], xmm0
L15:
    movsd xmm0, [rbp-40]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L12
L16:
    movsd xmm0, qword [rbp-8]
    movsd xmm1, qword [rbp-16]
    ucomisd xmm0, xmm1
    jne L19
    movsd xmm2, [rbp-32]
    sub rsp, 8
    mov qword[rbp-56], 0
    movsd [rbp-56], xmm2
    movsd [rbp-32], xmm2
    jmp L20
L19:
    movsd xmm0, [rbp-48]
    movsd [rbp-56], xmm0
    movsd [rbp-48], xmm0
L20:
    movsd xmm0, [rbp-56]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L17
    jmp L18
L17:
L18:
    sub rsp, 8
    mov qword[rbp-64], 0
    movsd xmm0, [rbp-64]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L12
L21:
    jmp L13
L12:
L13:
    sub rsp, 8
    mov qword[rbp-72], 0
    movsd xmm0, [rbp-72]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L11
    sub rsp,24
    movsd xmm1, qword [rbp-8]
    movsd xmm2, qword [rbp-16]
    movsd xmm3, xmm1
    addsd xmm3, xmm2
    movsd xmm4, xmm1
    subsd xmm4, xmm2
    movsd xmm5, xmm3
    mulsd xmm5, xmm4
    movsd xmm6, xmm5
    divsd xmm6, xmm2
    movsd qword[rbp-24], xmm6
    sub rsp,24
    jmp L10
L11:
L10:
    movsd xmm0, qword [rbp-24]
    leave
    ret
логика:
    push rbp
    mov rbp, rsp
    sub rsp,24
    movsd xmm0, qword [_5.0]
    movsd qword [rbp-8], xmm0
    movsd xmm0, qword [_5.0]
    movsd qword [rbp-16], xmm0
    movsd xmm0, qword [_3.0]
    movsd qword [rbp-24], xmm0
    movsd xmm0, qword [rbp-8]
    movsd xmm1, qword [rbp-16]
    ucomisd xmm0, xmm1
    jbe L24
    sub rsp, 8
    mov qword[rbp-32], 0
    movsd xmm2, [rbp-32]
    sub rsp, 8
    mov qword[rbp-40], 0
    movsd [rbp-40], xmm2
    movsd [rbp-32], xmm2
    jmp L25
L24:
    sub rsp, 8
    mov qword[rbp-48], 0
    movsd xmm0, [rbp-48]
    movsd [rbp-40], xmm0
    movsd [rbp-48], xmm0
L25:
    movsd xmm0, [rbp-40]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L23
    sub rsp,24
    movsd xmm1, qword [rbp-24]
    movsd xmm0, xmm1
    leave
    ret
    sub rsp,24
    jmp L22
L23:
    movsd xmm0, qword [rbp-8]
    movsd xmm1, qword [rbp-16]
    ucomisd xmm0, xmm1
    jne L27
    movsd xmm2, [rbp-32]
    sub rsp, 8
    mov qword[rbp-104], 0
    movsd [rbp-104], xmm2
    movsd [rbp-32], xmm2
    jmp L28
L27:
    movsd xmm0, [rbp-48]
    movsd [rbp-104], xmm0
    movsd [rbp-48], xmm0
L28:
    movsd xmm0, [rbp-104]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L26
    sub rsp,24
    movsd xmm1, qword [rbp-24]
    movsd xmm0, xmm1
    leave
    ret
    sub rsp,24
    jmp L22
L26:
    sub rsp,24
    movsd xmm0, qword [rbp-24]
    leave
    ret
L22:
    sub rsp,24
    movsd xmm0, qword [rbp-24]
    leave
    ret
сложноеВычисление:
    push rbp
    mov rbp, rsp
    sub rsp,48
    pxor xmm5, xmm5
    movsd qword [rbp-8], xmm5
    pxor xmm5, xmm5
    movsd qword [rbp-16], xmm5
    pxor xmm5, xmm5
    movsd qword [rbp-24], xmm5
    pxor xmm5, xmm5
    movsd qword [rbp-32], xmm5
    pxor xmm5, xmm5
    movsd qword [rbp-40], xmm5
    movsd xmm5, qword [_minus1.0]
    movsd qword [rbp-48], xmm5
    movsd xmm5, [_0.0]
    ucomisd xmm1, xmm5
    je L31
    sub rsp, 8
    mov qword[rbp-56], 0
    movsd xmm6, [rbp-56]
    movsd qword[rbp-8], xmm0
    movsd qword[rbp-16], xmm1
    movsd qword[rbp-24], xmm2
    movsd qword[rbp-32], xmm3
    movsd qword[rbp-40], xmm4
    sub rsp, 8
    mov qword[rbp-64], 0
    movsd [rbp-64], xmm6
    movsd [rbp-56], xmm6
    jmp L32
L31:
    sub rsp, 8
    mov qword[rbp-72], 0
    movsd xmm0, [rbp-72]
    movsd [rbp-64], xmm0
    movsd [rbp-72], xmm0
L32:
    movsd xmm0, [rbp-64]
    xorpd xmm15, xmm15
    ucomisd xmm0, xmm15
    jne L30
    sub rsp,48
    movsd xmm1, qword [rbp-8]
    movsd xmm2, qword [rbp-16]
    movsd xmm3, xmm1
    addsd xmm3, xmm2
    movsd xmm4, qword [rbp-24]
    movsd xmm5, qword [rbp-32]
    movsd xmm6, xmm4
    subsd xmm6, xmm5
    movsd xmm7, xmm3
    mulsd xmm7, xmm6
    movsd xmm0, qword [rbp-40]
    movsd xmm1, xmm7
    divsd xmm1, xmm0
    movsd xmm0, xmm1
    leave
    ret
    sub rsp,48
    jmp L29
L30:
    sub rsp,48
    movsd xmm0, qword [rbp-48]
    leave
    ret
L29:
    sub rsp,48
    movsd xmm0, qword [rbp-48]
    leave
    ret
сумма:
    push rbp
    mov rbp, rsp
    sub rsp,24
    pxor xmm2, xmm2
    movsd qword [rbp-8], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-16], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-24], xmm2
    movsd xmm2, xmm0
    addsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
разность:
    push rbp
    mov rbp, rsp
    sub rsp,24
    pxor xmm2, xmm2
    movsd qword [rbp-8], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-16], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-24], xmm2
    movsd xmm2, xmm0
    subsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
умножение:
    push rbp
    mov rbp, rsp
    sub rsp,24
    pxor xmm2, xmm2
    movsd qword [rbp-8], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-16], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-24], xmm2
    movsd xmm2, xmm0
    mulsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
деление:
    push rbp
    mov rbp, rsp
    sub rsp,24
    pxor xmm2, xmm2
    movsd qword [rbp-8], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-16], xmm2
    pxor xmm2, xmm2
    movsd qword [rbp-24], xmm2
    movsd xmm2, xmm0
    divsd xmm2, xmm1
    movsd xmm0, xmm2
    leave
    ret
_start:
    push rbp
    mov rbp, rsp
    sub rsp,0
    movq xmm0, [а]
    movq xmm1, [б]
sub rsp, 8
    call сумма
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movq xmm0, [а]
    movq xmm1, [б]
sub rsp, 8
    call разность
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movq xmm0, [а]
    movq xmm1, [б]
sub rsp, 8
    call умножение
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movq xmm0, [а]
    movq xmm1, [б]
sub rsp, 8
    call деление
    add rsp, 8
    mov rdi, fmtGlobal
    sub rsp, 8
    mov eax, 1
    call printf
    add rsp, 8
    movsd [результат], xmm0
    movq xmm0, [а]
    movq xmm1, [б]
    movq xmm2, [в]
    movq xmm3, [г]
    movq xmm4, [д]
sub rsp, 8
    call сложноеВычисление
    add rsp, 8
    sub rsp, 8
    mov qword[rbp-8], 0
    movsd xmm0, [rbp-8]
    mov rdi, fmtGlobal
    sub rsp, 0
    mov eax, 1
    call printf
    add rsp, 0
    movsd [результат], xmm0
sub rsp, 0
    call цикл_сумма
    add rsp, 0
    mov rdi, fmtGlobal
    sub rsp, 0
    mov eax, 1
    call printf
    add rsp, 0
    movsd [результат], xmm0
    movq xmm0, [а]
    movq xmm1, [б]
sub rsp, 0
    call арифметикаИБулевы
    add rsp, 0
    mov rdi, fmtGlobal
    sub rsp, 0
    mov eax, 1
    call printf
    add rsp, 0
    movsd [результат], xmm0
sub rsp, 0
    call арифметикаИБулевы
    add rsp, 0
    mov rdi, fmtGlobal
    sub rsp, 0
    mov eax, 1
    call printf
    add rsp, 0
    movsd [результат], xmm0
sub rsp, 0
    call логика
    add rsp, 0
    mov rdi, fmtGlobal
    sub rsp, 0
    mov eax, 1
    call printf
    add rsp, 0
    movsd [результат], xmm0
    mov rax, 60
    syscall
