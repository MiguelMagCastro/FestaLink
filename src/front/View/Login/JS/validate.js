document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('register-form');
    const registerButton = document.getElementById('register-btn');

    // Variáveis de controle para validação
    let isFullnameValid = false;
    let isEmailValid = false;
    let isPasswordValid = false;
    let isConfirmPasswordValid = false;
    let isPhoneValid = false;

    // Função para verificar se o formulário está válido
    function checkFormValidity() {
        const allFieldsValid = isFullnameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid && isPhoneValid;
        registerButton.disabled = !allFieldsValid;
        registerButton.style.opacity = allFieldsValid ? '1' : '0.5';
    }

    // Função para validar um campo específico
    function validateField(field, regex, errorMessage, flagSetter) {
        const label = field.previousElementSibling;
        const errorSpan = field.nextElementSibling;

        if (!regex.test(field.value.trim())) {
            field.classList.add('error-border');
            errorSpan.textContent = errorMessage;
            errorSpan.style.display = 'block';
            if (!label.innerHTML.includes('<span')) {
                label.innerHTML += ' <span class="required">*</span>';
            }
            flagSetter(false);
        } else {
            field.classList.remove('error-border');
            errorSpan.style.display = 'none';
            label.innerHTML = label.innerHTML.replace(' <span class="required">*</span>', '');
            flagSetter(true);
        }
        checkFormValidity();
    }

    // Eventos de validação para cada campo
    const fullname = document.getElementById('fullname');
    fullname.addEventListener('blur', function () {
        validateField(fullname, /.+/, 'O campo nome é obrigatório.', (isValid) => isFullnameValid = isValid);
    });

    const email = document.getElementById('email');
    email.addEventListener('blur', function () {
        validateField(email, /^[^\s@]+@[^\s@]+\.[^\s@]+$/, 'Insira um e-mail válido.', (isValid) => isEmailValid = isValid);
    });

    const password = document.getElementById('password');
    password.addEventListener('blur', function () {
        validateField(password, /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/, 'A senha deve conter letras e números e ter no mínimo 8 caracteres.', (isValid) => isPasswordValid = isValid);
    });

    const confirmPassword = document.getElementById('confirm-password');
    confirmPassword.addEventListener('blur', function () {
        const errorSpan = confirmPassword.nextElementSibling;
        const label = confirmPassword.previousElementSibling;
        const isMatching = confirmPassword.value === password.value && confirmPassword.value.trim() !== '';
        if (!isMatching) {
            confirmPassword.classList.add('error-border');
            errorSpan.textContent = 'As senhas não coincidem.';
            errorSpan.style.display = 'block';
            if (!label.innerHTML.includes('<span')) {
                label.innerHTML += ' <span class="required">*</span>';
            }
            isConfirmPasswordValid = false;
        } else {
            confirmPassword.classList.remove('error-border');
            errorSpan.style.display = 'none';
            label.innerHTML = label.innerHTML.replace(' <span class="required">*</span>', '');
            isConfirmPasswordValid = true;
        }
        checkFormValidity();
    });

    const phone = document.getElementById('phone');
    phone.addEventListener('blur', function () {
        validateField(phone, /^\(\d{2}\) 9\d{4}-\d{4}$/, 'Insira um telefone válido no formato (DD) 9XXXX-XXXX.', (isValid) => isPhoneValid = isValid);
    });

    // Adiciona máscara de telefone (opcional)
    phone.addEventListener('input', function () {
        let formattedValue = phone.value.replace(/\D/g, '');
        if (formattedValue.length > 11) {
            formattedValue = formattedValue.slice(0, 11);
        }
        formattedValue = formattedValue.replace(/^(\d{2})(\d{1})(\d{4})(\d{4})$/, '($1) $2$3-$4');
        phone.value = formattedValue;
    });

    // Submissão do formulário
    form.addEventListener('submit', function (event) {
        event.preventDefault();
    
        // Verifica a validade do formulário novamente antes de enviar
        checkFormValidity();
        if (registerButton.disabled) {
            alert('Por favor, preencha todos os campos corretamente antes de enviar.');
            return;
        }
    
        // Coleta os valores do formulário
        const nome = fullname.value.trim();
        const emailValue = email.value.trim();
        const senha = password.value.trim();
        const tipo = document.getElementById('tipo').value;
        const telefone = phone.value.trim();
    
        // Define o URL baseado no tipo do usuário
        const baseUrl = 'http://localhost:8080';
        const endpoint = tipo === 'Cliente' ? '/clientes' : '/proprietarios';
        const url = baseUrl + endpoint;
    
        // Cria o objeto JSON para enviar
        const usuarioData = {
            nome,
            email: emailValue,
            senha,
            telefone // Supõe-se que tanto Cliente quanto Proprietário têm um telefone
        };
    
        // Envia os dados para o backend
        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuarioData)
        })
        .then(response => response.json())
        .then(data => {
            form.reset();
            window.location.href = 'index.html';
        })
        .catch(error => {
            console.error('Erro:', error);
            alert('Erro ao registrar usuário!');
        });
    });
    
});


