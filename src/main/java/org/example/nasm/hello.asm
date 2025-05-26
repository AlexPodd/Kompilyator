section .data
fmtGlobal db "Результат: %f", 10, 0
extern printf, strlen
_200.0 dq 200.0
_20.0 dq 20.0
_10.0 dq 10.0
_12.0 dq 12.0
_7.0 dq 7.0
_0.0 dq 0.0
_3.0 dq 3.0
_2.0 dq 2.0
_1.0 dq 1.0
гол db "qwety asdf", 10
__HELLO_WORDL_ db "HELLO WORDL", 10
__SUMM_RESULT__ db "SUMM RESULT ", 10
результат dd 0
_150.0 dq 150.0
_minus1.0 dq -1.0
а dd 200
__Check_inside_func_ db "Check inside func", 10
б dd 400
в dd 150
г dd 200
д dd 20
__qwety_asdf_ db "qwety asdf", 10
_25.0 dq 25.0
_400.0 dq 400.0
section .bss
num_buf resb 32
input_buf resb 100
пример2 resd 1
пример1 resd 1
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
atoi_nasm:
    xor rax, rax
    xor rcx, rcx
    xor rbx, rbx
atoi_loop:
    mov bl, byte [rsi + rcx]
    cmp bl, 10
    je atoi_done
    cmp bl, 0
    je atoi_done
    sub bl, '0'
    imul rax, 10
    add rax, rbx
    inc rcx
    jmp atoi_loop
atoi_done:
    ret
atof_nasm:
    xor rax, rax
    xor rcx, rcx
    xor rbx, rbx
    pxor xmm0, xmm0
    pxor xmm1, xmm1
    pxor xmm2, xmm2
    pxor xmm3, xmm3
atof_int:
    mov bl, [rsi + rcx]
    cmp bl, '.'
    je atof_frac_start
    cmp bl, 10
    je atof_done
    cmp bl, 0
    je atof_done
    sub bl, '0'
    imul rax, 10
    add rax, rbx
    inc rcx
    jmp atof_int
atof_frac_start:
    cvtsi2sd xmm0, rax
    xor rax, rax
    inc rcx
    mov r8, 1
atof_frac:
    mov bl, [rsi + rcx]
    cmp bl, 10
    je atof_frac_done
    cmp bl, 0
    je atof_frac_done
    sub bl, '0'
    imul rax, 10
    inc rcx
    inc r8
    jmp atof_frac
atof_frac_done:
    mov rcx, r8
    dec rcx
    mov rdx, 1
atof_pow10:
    imul rdx, 10
    loop atof_pow10
    cvtsi2sd xmm1, rax
    cvtsi2sd xmm2, rdx
    divsd xmm1, xmm2
    addsd xmm0, xmm1
atof_done:
    ret
strcpy_nasm:
    xor rcx, rcx
strcpy_loop:
    mov al, [rsi + rcx]
    cmp al, 10
    je strcpy_done
    cmp al, 0
    je strcpy_done
    mov [rdi + rcx], al
    inc rcx
    jmp strcpy_loop
strcpy_done:
    mov byte [rdi + rcx], 0
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
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rax, 1
mov rdi, 1
mov rsi, __Check_inside_func_
mov rdx, 20
syscall
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
    mov dword[rbp-16], ECX
    mov dword[rbp-12], EDX
    mov RAX, 0
    cmp RSI, RAX
    jne L2
    sub rsp, 8
    mov qword[rbp-32], 0
    mov [rbp-32], RAX
    jmp L3
L2:
    mov RAX, 1
    mov [rbp-32], RAX
    mov [rbp-32], RAX
L3:
    mov RAX, [rbp-32]
    cmp RAX, 0
    je L1
    sub rsp, 8 ; резерв под локальные
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
    leave
    ret
    jmp L0
L1:
    mov RAX, -1
    mov dword[rbp-24], EAX
    leave
    ret
L0:
    mov EAX, dword [rbp-24]
    leave
    ret
цикл_сумма:
    push rbp
    mov rbp, rsp
    sub rsp,4
    mov dword[rbp-4], 0
    mov dword[rbp-8], 1
    mov RAX, 1
    mov dword[rbp-8], EAX
L4:
    mov EAX, dword [rbp-8]
    mov RCX, 10
    cmp RAX, RCX
    jle L7
    sub rsp, 8
    mov qword[rbp-16], 0
    mov RBX, 0
    mov [rbp-16], RBX
    mov [rbp-16], RBX
    jmp L8
L7:
    mov RAX, 1
    mov [rbp-16], RAX
    mov [rbp-16], RAX
L8:
    mov RAX, [rbp-16]
    cmp RAX, 0
    je L5
    sub rsp, 4 ; резерв под локальные
    mov dword[rbp-12], 1
    mov RCX, 1
    mov dword[rbp-12], ECX
L9:
    mov EAX, dword [rbp-12]
    mov RCX, 10
    cmp RAX, RCX
    jle L12
    sub rsp, 8
    mov qword[rbp-20], 0
    mov RBX, 0
    mov [rbp-20], RBX
    mov [rbp-20], RBX
    jmp L13
L12:
    mov RAX, 1
    mov [rbp-20], RAX
    mov [rbp-20], RAX
L13:
    mov RAX, [rbp-20]
    cmp RAX, 0
    je L10
    mov ECX, dword [rbp-4]
    mov EBX, dword [rbp-12]
    mov RDX, RCX
    add RDX, RBX
    mov dword[rbp-4], EDX
L11:
    mov EAX, dword [rbp-12]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-12], EBX
    jmp L9
L10:
L6:
    sub rsp, 12 ; резерв под локальные
    mov EAX, dword [rbp-8]
    mov RCX, 1
    mov RBX, RAX
    add RBX, RCX
    mov dword[rbp-8], EBX
    jmp L4
L5:
    sub rsp, 4 ; резерв под локальные
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
    jg L18
    sub rsp, 8
    mov qword[rbp-20], 0
    mov RAX, 0
    mov [rbp-20], RAX
    mov [rbp-20], RAX
    jmp L19
L18:
    mov RAX, 1
    mov [rbp-20], RAX
    mov [rbp-20], RAX
L19:
    mov RAX, [rbp-20]
    cmp RAX, 0
    je L16
    mov [rbp-20], RAX
    cmp RDI, RSI
    je L20
    sub rsp, 8
    mov qword[rbp-28], 0
    mov RAX, 0
    mov [rbp-28], RAX
    mov [rbp-28], RAX
    jmp L21
L20:
    mov RAX, 1
    mov [rbp-28], RAX
    mov [rbp-28], RAX
L21:
    mov RAX, [rbp-28]
    cmp RAX, 0
    jne L22
    sub rsp, 8
    mov qword[rbp-36], 0
    mov RCX, 1
    mov [rbp-36], RCX
    jmp L23
L22:
    mov RAX, 0
    mov [rbp-36], RAX
L23:
    mov RAX, [rbp-36]
    cmp RAX, 0
    je L16
    sub rsp, 8
    mov qword[rbp-44], 0
    mov RCX, 1
    mov [rbp-44], RCX
    jmp L17
L16:
    mov RAX, 0
    mov [rbp-44], RAX
L17:
    mov RAX, [rbp-44]
    cmp RAX, 0
    je L15
    sub rsp, 32 ; резерв под локальные
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
    mov dword[rbp-12], ECX
    jmp L14
L15:
    mov RAX, -1
    mov dword[rbp-12], EAX
L14:
    mov EAX, dword [rbp-12]
    leave
    ret
логика:
    push rbp
    mov rbp, rsp
    sub rsp,20
    mov dword[rbp-12], 7
    mov qword[rbp-8], RDI
    mov dword[rbp-16], 7
    mov dword[rbp-20], 0
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rdi, [rbp-8]
call strlen
mov rdx, rax
mov rax, 1
mov rdi, 1
mov rsi, [rbp-8]
syscall
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
    mov EAX, dword [rbp-12]
    mov ECX, dword [rbp-16]
    cmp RAX, RCX
    jg L26
    sub rsp, 8
    mov qword[rbp-28], 0
    mov RBX, 0
    mov [rbp-28], RBX
    mov [rbp-28], RBX
    jmp L27
L26:
    mov RAX, 1
    mov [rbp-28], RAX
    mov [rbp-28], RAX
L27:
    mov RAX, [rbp-28]
    cmp RAX, 0
    je L25
    sub rsp, 8 ; резерв под локальные
    mov RCX, 1
    mov dword[rbp-20], ECX
    mov RAX, RCX
    leave
    ret
    jmp L24
L25:
    mov EAX, dword [rbp-12]
    mov ECX, dword [rbp-16]
    cmp RAX, RCX
    je L29
    sub rsp, 8
    mov qword[rbp-28], 0
    mov RBX, 0
    mov [rbp-28], RBX
    mov [rbp-28], RBX
    jmp L30
L29:
    mov RAX, 1
    mov [rbp-28], RAX
    mov [rbp-28], RAX
L30:
    mov RAX, [rbp-28]
    cmp RAX, 0
    je L28
    sub rsp, 8 ; резерв под локальные
    mov RCX, 2
    mov dword[rbp-20], ECX
    mov RAX, RCX
    leave
    ret
    jmp L24
L28:
    mov RAX, 3
    mov dword[rbp-20], EAX
    leave
    ret
L24:
    mov EAX, dword [rbp-20]
    leave
    ret
факториал:
    push rbp
    mov rbp, rsp
    sub rsp,4
    mov dword[rbp-4], EDI
    mov RAX, 1
    cmp RDI, RAX
    je L32
    sub rsp, 8
    mov qword[rbp-12], 0
    mov RCX, 0
    mov [rbp-12], RCX
    mov [rbp-12], RCX
    jmp L33
L32:
    mov RAX, 1
    mov [rbp-12], RAX
    mov [rbp-12], RAX
L33:
    mov RAX, [rbp-12]
    cmp RAX, 0
    je L31
    sub rsp, 8 ; резерв под локальные
    mov RCX, 1
    mov RAX, RCX
    leave
    ret
L31:
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
    leave
    ret
_start:
    push rbp
    mov rbp, rsp
    sub rsp,0
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rax, 0
mov rdi, 0
mov rsi, input_buf
mov rdx, 100
syscall
lea rsi, [input_buf]
call atoi_nasm
    mov [пример1], EAX
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rax, 0
mov rdi, 0
mov rsi, input_buf
mov rdx, 100
syscall
lea rsi, [input_buf]
call atoi_nasm
    mov [пример2], EAX
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
    mov EDI, [пример1]
    mov ESI, [пример2]
sub rsp, 8
    call сумма
    add rsp, 8
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rax, 1
mov rdi, 1
mov rsi, __SUMM_RESULT__
mov rdx, 15
syscall
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rax, 1
mov rdi, 1
mov rsi, __HELLO_WORDL_
mov rdx, 14
syscall
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
push rax
push rcx
push rdx
push rsi
push rdi
push r8
push r9
push r10
push r11
mov rax, 1
mov rdi, 1
mov rsi, гол
mov rdx, 11
syscall
pop r11
pop r10
pop r9
pop r8
pop rdi
pop rsi
pop rdx
pop rcx
pop rax
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 8
    call сумма
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 8
    call разность
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 8
    call умножение
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 8
    call деление
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov RDI, 10
sub rsp, 8
    call факториал
    add rsp, 8
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
sub rsp, 8
    call сложноеВычисление
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
sub rsp, 8
    call цикл_сумма
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov EDI, [а]
    mov ESI, [б]
sub rsp, 8
    call арифметикаИБулевы
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov RDI, 25
    mov RSI, 12
sub rsp, 8
    call арифметикаИБулевы
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov RDI, гол
sub rsp, 8
    call логика
    add rsp, 8
    movsx RAX, EAX
    push RAX
    call print_int
    add rsp, 8
    mov [результат], EAX
    mov rax, 60
    syscall
