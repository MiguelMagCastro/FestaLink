document.addEventListener("DOMContentLoaded", function () {
  ajustarNavbar();
  carregarDados();
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

function toggleDropdown() {
  var dropdown = document.getElementById("profile-dropdown");
  dropdown.classList.toggle("show");
}

function logout() {
  localStorage.clear();

  window.location.href = "index.html";

  window.history.pushState(null, "", window.location.href);
  window.onpopstate = function () {
    window.history.pushState(null, "", window.location.href);
  };
}

function createNotification(type, title, message) {
  const notification = document.createElement("div");
  notification.className = `notification ${type}`;
  notification.innerHTML = `
      <div class="notification-header">${title}<span class="notification-close">&times;</span></div>
      <div>${message}</div>
    `;

  const container = document.getElementById("notification-container");
  container.appendChild(notification);

  // Clique para fechar
  notification.querySelector(".notification-close").onclick = function () {
    notification.style.opacity = "0";
    setTimeout(() => notification.remove(), 300);
  };

  // Desaparecer automaticamente após 3 segundos
  setTimeout(() => {
    notification.style.opacity = "0";
    setTimeout(() => notification.remove(), 300);
  }, 3000);
}

async function carregarDados() {
  try {
    const response = await fetch(
      "http://localhost:8080/proprietarios/metricas/" +
        localStorage.getItem("idUsuario")
    ); // Substituir {id} pelo ID necessário
    const data = await response.json();
    mostrarDadosNoDashboard(data);
    criarGraficos(data);
  } catch (error) {
    console.error("Erro ao buscar os dados: ", error);
  }
}

function mostrarDadosNoDashboard(data) {
  document.getElementById("salaoNome").innerHTML = `<strong>Nome:</strong> ${data.melhorSalaoDeFesta.nome}`;
  document.getElementById("salaoLocalizacao").innerHTML = `<strong>Localização:</strong> ${data.melhorSalaoDeFesta.localizacao}`;
  document.getElementById("salaoCapacidade").innerHTML = `<strong>Capacidade:</strong> ${data.melhorSalaoDeFesta.capacidade}`;
  document.getElementById("salaoDescricao").innerHTML = `<strong>Descrição:</strong> ${data.melhorSalaoDeFesta.descricao}`;
  document.getElementById("numeroReservas").innerHTML = `<strong>Reservas:</strong> ${data.numeroReservasMelhorSalao}`;
  document.getElementById("abrirSalao").innerHTML = `<button class="btnCard" onclick="showDetails('${data.melhorSalaoDeFesta.id}')">Ver mais Detalhes</button>`
 
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


function criarGraficos(data) {
  new Chart(document.getElementById("grafico-taxaCancelamento"), {
    type: "pie",
    data: {
      labels: ["Taxa de Cancelamento", "Total"],
      datasets: [
        {
          label: "Taxa de Cancelamento",
          data: [data.taxaCancelamento, 100 - data.taxaCancelamento],
          backgroundColor: [
            "rgba(255, 99, 132, 0.5)",
            "rgba(75, 192, 192, 0.5)",
          ],
          borderColor: ["rgba(255, 99, 132, 1)", "rgba(75, 192, 192, 1)"],
          borderWidth: 1,
        },
      ],
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: "top",
        },
        tooltip: {
          callbacks: {
            label: function (tooltipItem) {
              return (
                tooltipItem.label + ": " + tooltipItem.raw.toFixed(2) + "%"
              ); 
            },
          },
        },
      },
    },
  });

  document.getElementById('valorArrecadado').textContent = formatarMoeda(data.valorArrecadado);
  document.getElementById('valorAReceber').textContent = formatarMoeda(data.valorAReceber);

  document.getElementById('numeroAvl').textContent = "Quantidade: " + data.numeroAvaliacoes;
  document.getElementById('mediaAvl').textContent = "Média: " + data.mediaAvaliacoes.toFixed(2);

}

function formatarMoeda(valor) {
  return `R$ ${valor.toFixed(2).replace('.', ',')}`;
}
