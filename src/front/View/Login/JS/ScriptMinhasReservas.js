document.addEventListener('DOMContentLoaded', function () {
    ajustarNavbar();
    fetchReservas();
     
});

function logout() {
    localStorage.clear();
    window.location.replace("index.html");
  }
  


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

function fetchReservas() {
    const idUsuario = localStorage.getItem('idUsuario');
    if (!idUsuario) return;

    fetch(`http://localhost:8080/reserva/cliente/${idUsuario}`)
        .then(response => response.json())
        .then(reservas => renderReservas(reservas))
        .catch(error => console.error('Erro ao buscar reservas:', error));
}

function renderReservas(reservas) {
    const container = document.getElementById('reserva-container');
    container.innerHTML = '';  // Limpa conteúdo existente

    const table = document.createElement('table');
    table.innerHTML = `
        <tr>
            <th>Salão</th>
            <th>Data do Evento</th>
            <th>Convidados</th>
            <th>Duração (Horas)</th>
            <th>Preço (R$)</th>
            <th>Status</th>
            <th>Ações</th>
        </tr>
    `;

    reservas.forEach(reserva => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${reserva.salaoDeFestaNome}</td>
            <td>${reserva.dataEvento}</td>
            <td>${reserva.numeroConvidados}</td>
            <td>${reserva.duracao}</td>
            <td>${reserva.preco.toFixed(2)}</td>
            <td>${reserva.statusReserva}</td>
            <td>
                <button class="cancel-btn" data-id="${reserva.id}" style="background-color: red; color: white;">Cancelar</button>
                ${reserva.statusReserva === 'Pendente' ? `<button class="confirm-btn" data-id="${reserva.id}" style="background-color: #007bff; color: white;">Confirmar</button>` : ''}
            </td>
        `;
        table.appendChild(row);
    });

    container.appendChild(table);
    attachConfirmEventListeners();
    attachCancelEventListeners();
}

function attachConfirmEventListeners() {
    const confirmButtons = document.querySelectorAll('.confirm-btn');
   
    confirmButtons.forEach(button => {
        button.addEventListener('click', function() {
            confirmarReserva(this.getAttribute('data-id'));
        });
    });

}

function confirmarReserva(id) {
    fetch(`http://localhost:8080/reserva/confirmar/${id}`, {
        method: 'PUT'
    })
    .then(response => response.text())
    .then(message => {
        createNotification("success", "Reserva Edita:",message);
        fetchReservas(); // Recarrega as reservas para atualizar a lista
    })
    .catch(error => console.error('Erro ao confirmar reserva:', error));
}

function  attachCancelEventListeners(){
    const cancelButtons = document.querySelectorAll('.cancel-btn');
    cancelButtons.forEach(cancelbutton => {
        cancelbutton.addEventListener('click', function() {
            cancelarReserva(this.getAttribute('data-id'));
    });
});
}

function cancelarReserva(id) {
    fetch(`http://localhost:8080/reserva/cancelar/${id}`, {
        method: 'DELETE'
    })
    .then(response => response.text())
    .then(message => {
        createNotification("success", "Reserva Edita:", message);
        fetchReservas();
    })
    .catch(error => console.error('Erro ao cancelar reserva:', error));
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