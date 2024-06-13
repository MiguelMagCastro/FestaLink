document.addEventListener("DOMContentLoaded", function () {
  ajustarNavbar();
});

let paginaAtual = 1;
const saloesPorPagina = 6; 
let totalSaloes = 0;

function realizarBusca(pagina = 1) {
    const nomeSalao = document.getElementById('nomeSalao').value;
    const localizacao = document.getElementById('localizacaoSalao').value;
    const capacidadeMinima = Math.round(sliderCapacidade.noUiSlider.get()[0]);
    const capacidadeMaxima = Math.round(sliderCapacidade.noUiSlider.get()[1]);
    const precoBaseMinimo = sliderPreco.noUiSlider.get()[0];
    const precoBaseMaximo = sliderPreco.noUiSlider.get()[1];
    
    const queryParameters = new URLSearchParams({
        pagina,
        limite: saloesPorPagina  
    });

    if (nomeSalao) queryParameters.append('nomeSalao', nomeSalao);
    if (localizacao) queryParameters.append('localizacao', localizacao);
    if (capacidadeMinima) queryParameters.append('capacidadeMinima', capacidadeMinima);
    if (capacidadeMaxima) queryParameters.append('capacidadeMaxima', capacidadeMaxima);
    if (precoBaseMinimo) queryParameters.append('precoBaseMinimo', precoBaseMinimo);
    if (precoBaseMaximo) queryParameters.append('precoBaseMaximo', precoBaseMaximo);

    fetch(`http://localhost:8080/salao-de-festa/filtrar-saloes?${queryParameters.toString()}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({})
    })
    .then(response => response.json())
    .then(data => {
        if(data.length === 0){
            const results = document.getElementById('saloesResults');
            results.innerHTML = '';
            results.innerHTML = `<span style="display: block">Nenhum Salão encontrado!</span>`;
            document.getElementById('totalSaloes').textContent = `Total de Salões: ${data.length}`;
        }else{
            const results = document.getElementById('saloesResults');
            results.innerHTML = '';
            totalSaloes = data.length;
            document.getElementById('totalSaloes').textContent = `Total de Salões: ${totalSaloes}`;
            const inicio = (pagina - 1) * saloesPorPagina;
            const saloesPaginados = data.slice(inicio, inicio + saloesPorPagina);
    
            saloesPaginados.forEach(salao => {
                let salaoDiv = document.createElement('div');
                salaoDiv.classList.add('salao-item');
                salaoDiv.innerHTML = `
                    <div>${salao.nome}</div>
                    <div>${salao.localizacao}</div>
                    <div>${salao.capacidade} pessoas</div>
                    <div>R$${salao.precoBase.toFixed(2)}</div>
                    <div><button onclick="showDetails('${salao.id}')">Detalhes</button></div>
                `;
                results.appendChild(salaoDiv);
            });
        }
        
        
    
    document.getElementById('paginaAtual').textContent = pagina;
    paginaAtual = pagina;
})
.catch(error => console.error('Erro ao buscar salões:', error));
        

        
}

function mudarPagina(delta) {
    const novaPagina = paginaAtual + delta;
    const maximoPaginas = Math.ceil(totalSaloes / saloesPorPagina); 

   
    if (novaPagina > 0 && novaPagina <= maximoPaginas) {
        realizarBusca(novaPagina);
    }
}


document
  .getElementById("searchForm")
  .addEventListener("input", function (event) {
    event.preventDefault();
    realizarBusca();
  });

document
  .getElementById("searchForm")
  .addEventListener("click", function (event) {
    event.preventDefault();
    realizarBusca();
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

window.onclick = function (event) {
  if (
    !event.target.matches(".profile-btn") &&
    !event.target.matches(".profile-btn img")
  ) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    for (var i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains("show")) {
        openDropdown.classList.remove("show");
      }
    }
  }
};

var sliderCapacidade = document.getElementById("slider-capacidade");
noUiSlider.create(sliderCapacidade, {
  start: [20, 500],
  connect: true,
  step: 1,
  range: {
    min: 20,
    max: 1000,
  },
});
sliderCapacidade.noUiSlider.on("update", function (values, handle) {
  var valueMin = Math.round(values[0]);
  var valueMax = Math.round(values[1]);
  document.getElementById("capacidadeValor").innerHTML =
    valueMin + " - " + valueMax;
});

var sliderPreco = document.getElementById("slider-preco");
noUiSlider.create(sliderPreco, {
  start: [50, 300],
  connect: true,
  step: 10,
  range: {
    min: 10,
    max: 1000,
  },
});
sliderPreco.noUiSlider.on("update", function (values, handle) {
  document.getElementById("precoValor").innerHTML =
    "R$ " + parseInt(values[0]) + ",00 - R$ " + parseInt(values[1]) + ",00";
});


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

function logout() {
        
  localStorage.clear();


  window.location.href = 'index.html';

  window.history.pushState(null, "", window.location.href);
  window.onpopstate = function () {
      window.history.pushState(null, "", window.location.href);
  };  
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