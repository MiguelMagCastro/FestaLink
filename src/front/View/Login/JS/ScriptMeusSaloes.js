document.addEventListener('DOMContentLoaded', function () {
    ajustarNavbar();

    const idUsuario = localStorage.getItem('idUsuario');
    fetch('http://localhost:8080/proprietarios/salao-de-festa/' + idUsuario)
        .then(response => response.json())
        .then(data => {
            const container = document.getElementById('saloes-container');
            data.forEach(salao => {
                const card = document.createElement('div');
                card.className = 'salao-card';
                card.innerHTML = `
                    <h2>${salao.nome}</h2>
                    <p><b>Capacidade:</b> ${salao.capacidade} Pessoas</p>
                    <p><b>Endereço:</b> ${salao.localizacao}</p>
                    <p><b>Preço p/Hora:</b> R$ ${salao.precoBase.toFixed(2)}</p>
                    <button class="btnCard" onclick="showDetails('${salao.id}')">Ver mais Detalhes</button>
                `;
                container.appendChild(card);
            });
        })
        .catch(error => console.error('Erro ao buscar dados: ', error));
});

function openModal() {
    document.getElementById('salaoModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('salaoModal').style.display = 'none';
}

window.onclick = function(event) {
    if (event.target == document.getElementById('salaoModal')) {
        closeModal();
    }
}

document.getElementById('salaoForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const salaoData = {
        nome: document.getElementById('nome').value,
        localizacao: document.getElementById('localizacao').value,
        capacidade: document.getElementById('capacidade').value,
        descricao: document.getElementById('descricao').value,
        precoBase: document.getElementById('precoBase').value,
        proprietario: { id:localStorage.getItem('idUsuario'),
                        nome:localStorage.getItem('nomeUsuario'),
                        email:localStorage.getItem('emailUsuario'),
                        senha:localStorage.getItem('senhaUsuario'),
                        telefone:localStorage.getItem('telefoneUsuario'),
    }

    };

    fetch('http://localhost:8080/salao-de-festa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(salaoData)
    })
    .then(response => {
        if (response.ok) {
            
            closeModal();
            window.location.reload();
            createNotification("success", "Salão Registrado!", "Salão de Festas cadastrado com sucesso." ); 
        } else {
            throw new Error('Erro ao cadastrar salão');
        }
    })
    .catch(error => {
        console.error('Erro:', error);
        createNotification("error", "Erro ao cadastrar salão: ", error.message);
    });
});


function ajustarNavbar() {
    const tipoUsuario = localStorage.getItem("tipoUsuario");
    const navbar = document.querySelector(".nav-menu");
  
    if (tipoUsuario === "Cliente") {
      navbar.innerHTML += `<li><a href="minhas-reservas.html">Minhas Reservas</a></li>`;
    } else if (tipoUsuario === "Proprietario") {
      navbar.innerHTML += `<li><a href="meus-saloes.html">Meus Salões</a></li>`;
      navbar.innerHTML += `<li><a href="dashboard.html">DashBoard</a></li>`;
      
    }
  }

function showDetails(salaoId) {
    const url = `http://localhost:8080/salao-de-festa/${salaoId}`;
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao buscar dados do salão');
            }
            return response.json();
        })
        .then(salao => {
            localStorage.setItem('detalhesSalao', JSON.stringify(salao));
            window.location.href = 'salao-de-festa.html';
        })
        .catch(error => {
            console.error('Erro na requisição:', error);
        });
  }

  function toggleDropdown() {
    var dropdown = document.getElementById("profile-dropdown");
    dropdown.classList.toggle("show");
}

window.onclick = function(event) {
    if (!event.target.matches('.profile-btn') && !event.target.matches('.profile-btn img')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
        var openDropdown = dropdowns[i];
        if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
        }
    }
}
}

function toggleDropdown() {
    var dropdown = document.getElementById("profile-dropdown");
    dropdown.classList.toggle("show");
}

window.onclick = function(event) {
    if (!event.target.matches('.profile-btn') && !event.target.matches('.profile-btn img')) {
        var dropdowns = document.getElementsByClassName("dropdown-content");
        for (var i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
}
function logout() {
    
    localStorage.clear();


    window.location.replace('index.html');  
}

function createNotification(type, title, message) {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.innerHTML = `
      <div class="notification-header">${title}<span class="notification-close">&times;</span></div>
      <div>${message}</div>
    `;
  
    const container = document.getElementById('notification-container');
    container.appendChild(notification);
  
    // Clique para fechar
    notification.querySelector('.notification-close').onclick = function() {
      notification.style.opacity = '0';
      setTimeout(() => notification.remove(), 300);
    };
  
    // Desaparecer automaticamente após 3 segundos
    setTimeout(() => {
      notification.style.opacity = '0';
      setTimeout(() => notification.remove(), 300);
    }, 3000);
  }