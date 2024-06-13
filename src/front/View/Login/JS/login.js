const loginForm = document.querySelector('.login-form');
const loginError = document.getElementById('login-error');

window.onload = function() {
    // Sobrescreve a sessão do histórico para garantir que não se possa voltar à sessão anterior
    history.replaceState(null, null, location.href);
    history.pushState(null, null, location.href);
    window.onpopstate = function(event) {
        history.go(1);
    };
};


document.getElementById('togglePassword').addEventListener('click', function () {
    const password = document.getElementById('password');
    const passwordIcon = this.children[0];
    // verifica se o tipo é password para então mudar para text, e vice-versa
    if (password.type === 'password') {
        password.type = 'text';
        passwordIcon.className = 'bi bi-eye-fill'; // mudar ícone para olho aberto
    } else {
        password.type = 'password';
        passwordIcon.className = 'bi bi-eye-slash-fill'; // mudar ícone para olho fechado
    }
});


loginForm.addEventListener('submit', function(event) {
    event.preventDefault();
    const email = document.getElementById('username').value;
    const senha = document.getElementById('password').value;
    const tipoUsuario = document.querySelector('input[name="tipo"]:checked').value;
    const url = tipoUsuario === 'Cliente' ? 'http://localhost:8080/clientes/login' : 'http://localhost:8080/proprietarios/login';

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email: email, senha: senha })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Usuário ou senha informados são inválidos');
        }
        return response.text();
    })
    .then(data => {
        if (data.startsWith('Login bem-sucedido como')) {

            const tipoUsuario = data.split(' ')[3].slice(0, -1);
            localStorage.setItem('tipoUsuario', tipoUsuario);
            
            const idUsuario = data.split(' ')[5] ;
            localStorage.setItem('idUsuario', idUsuario);

            const nomeUsuario = data.split(' ')[7] +" " + data.split(' ')[8];
            localStorage.setItem('nomeUsuario', nomeUsuario);

            const emailUsuario = data.split(' ')[10];
            localStorage.setItem('emailUsuario', emailUsuario);

            const senhaUsuario = data.split(' ')[12];
            localStorage.setItem('senhaUsuario', senhaUsuario);

            const telefoneUsuario = data.split(' ')[14];
            localStorage.setItem('telefoneUsuario', telefoneUsuario);



            const redirectUrl = 'home.html';
            window.location.href = redirectUrl;
        } else {
            loginError.textContent = 'Usuário ou senha informados são inválidos';
            loginError.style.color = 'red';
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        loginError.textContent = error.message;
        loginError.style.color = 'red';
    });
});
