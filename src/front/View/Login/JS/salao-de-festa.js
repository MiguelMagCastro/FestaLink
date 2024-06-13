document.addEventListener("DOMContentLoaded", function () {
  ajustarNavbar();
  initCarousel();
  ajustarTelaParaUsuario();
  const salao = JSON.parse(localStorage.getItem("detalhesSalao"));
  if (salao) {
    preencherDetalhesSalao(salao);
    preencherComentariosSalao(salao.id);
  }
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

function ajustarTelaParaUsuario() {
  const tipoUsuario = localStorage.getItem("tipoUsuario");
  if (tipoUsuario === "Cliente") {
    document.querySelector(".btnVisualizarReservas").style.display = "block";
    setupModalReserva();
    setupAvalaciao();
    document.querySelector("#feedback-area").style.display = "block";
    initializeRatingSystem();
    document
      .getElementById("submit-rating")
      .addEventListener("click", enviarAvaliacao);
  } else if (tipoUsuario === "Proprietario") {
    setupAvalaciao();
    exibirBotaoBloqueio()
    }
}

function exibirBotaoBloqueio(){
  const salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
  const prorpietarioSalaoId = salaoDetalhes.usuario.id;prorpietarioSalaoId
  const userAtivoId = localStorage.getItem("idUsuario");
  if(userAtivoId == prorpietarioSalaoId ){
    document.querySelector(".btnAdicionarBloqueio").style.display = "block";
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

function showDetails(salaoId) {
  fetch(`/salao-de-festa/${salaoId}`)
    .then((response) => response.json())
    .then((data) => updateDetailsPage(data))
    .catch((error) =>
      console.error("Erro ao buscar detalhes do salão:", error)
    );
}

function updateDetailsPage(salao) {
  document.getElementById("salao-name").textContent = salao.nome;
  document.getElementById(
    "salao-endereco"
  ).textContent = `Endereço: ${salao.localizacao}`;
  document.getElementById(
    "salao-capacity"
  ).textContent = `${salao.capacidade} pessoas`;

  if (salao.descricao) {
    document.getElementById("salao-description").textContent = salao.descricao;
  } else {
    document.getElementById("salao-description").textContent =
      "O salão não possui descrição...";
  }

  document.getElementById(
    "price-per-hour"
  ).textContent = `${salao.precoBase.toFixed(2)} reais p/h`;
  document.getElementById("proprietario-nome").textContent =
    salao.proprietario.nome;
  document.getElementById("proprietario-telefone").textContent =
    salao.proprietario.telefone;
}

function initCarousel() {
  const prevButton = document.querySelector(".prev");
  const nextButton = document.querySelector(".next");
  const carousel = document.querySelector(".carousel");
  let currentImageIndex = 0;

  const images = carousel.querySelectorAll("img");
  const totalImages = images.length;

  updateCarousel();

  prevButton.addEventListener("click", function () {
    if (currentImageIndex > 0) {
      currentImageIndex--;
    } else {
      currentImageIndex = totalImages - 1;
    }
    updateCarousel();
  });

  nextButton.addEventListener("click", function () {
    if (currentImageIndex < totalImages - 1) {
      currentImageIndex++;
    } else {
      currentImageIndex = 0;
    }
    updateCarousel();
  });

  function updateCarousel() {
    images.forEach((img) => (img.style.display = "none"));
    images[currentImageIndex].style.display = "block";
  }
}

function preencherDetalhesSalao(salao) {
  document.getElementById("salao-name").textContent = salao.nome;
  document.getElementById(
    "salao-endereco"
  ).textContent = `Endereço: ${salao.localizacao}`;
  document.getElementById(
    "salao-capacity"
  ).textContent = `${salao.capacidade} pessoas`;

  if (salao.descricao) {
    document.getElementById("salao-description").textContent = salao.descricao;
  } else {
    document.getElementById("salao-description").textContent =
      "O salão não possui descrição...";
  }

  document.getElementById(
    "price-per-hour"
  ).textContent = `${salao.precoBase.toFixed(2)} reais p/h`;
  document.getElementById("proprietario-nome").textContent = salao.usuario.nome;
  document.getElementById("proprietario-telefone").textContent =
    salao.usuario.telefone;
}

function preencherComentariosSalao(salaoId) {
  const url = `http://localhost:8080/avaliacao/${salaoId}`;
  fetch(url)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Falha ao buscar dados do salão");
      }
      return response.json();
    })
    .then((avaliacoes) => {
      const comentariosContainer = document.getElementById("feedback-feitos");
      comentariosContainer.innerHTML = "";

      avaliacoes.forEach((avaliacao) => {
        const comentarioHtml = `
                    <div class="feedback-item">
                        <h3>${avaliacao.usuarioNome}</h3>
                        <div class="feedback-stars">${createStars(
                          avaliacao.notaAtribuida
                        )}</div>
                        <div class="feedback-comment">${
                          avaliacao.comentario
                        }</div>
                        <div class="feedback-date">${
                          avaliacao.horaAvaliacao
                        }</div>
                    </div>
                `;
        comentariosContainer.innerHTML += comentarioHtml;
      });
    })
    .catch((error) => {
      console.error("Erro na requisição:", error);
    });
}

function createStars(rating) {
  let stars = "";
  for (let i = 0; i < 5; i++) {
    if (rating >= i + 1) {
      stars += "★";
    } else if (rating >= i + 0.5) {
      stars += "★";
    } else {
      stars += "☆";
    }
  }
  return stars;
}

function logout() {
  localStorage.clear();
  window.location.replace("index.html");
}

// <--------------- Modal RESERVA ------------>

function setupCalendar() {
  const salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
  if (salaoDetalhes && salaoDetalhes.id) {
    fetch(
      `http://localhost:8080/salao-de-festa/datas-bloqueadas/${salaoDetalhes.id}`
    )
      .then((response) => response.json())
      .then((dataBloqueada) => {
        const disabledDates = dataBloqueada.map((db) => db.data);
        flatpickr("#datepicker", {
          dateFormat: "Y-m-d",
          disable: disabledDates,
          altFormat: "d/m/Y",
          onChange: function (selectedDates, dateStr, instance) {
            document.getElementById("datepicker").value = dateStr;
          },
        });
      })
      .catch((error) => console.error("Error fetching blocked dates:", error));
  }
}

function setupModalReserva() {
  document.getElementById("modalArea").innerHTML = `
    <div class="modal-content">
        <div class="modal-header">
            <span class="modal-title">Criar nova reserva</span>
            <span class="close-btn" onclick="closeModalReserva()">&times;</span>
        </div>
        <div class="modal-body">
            <input type="number" id="guestsInput" placeholder="Quantidade de convidados" min="1" />
            <p id="guestsError" style="color: red; display: none"></p>
            <input type="number" id="hourInput" placeholder="Duração do Evento em horas " min="1" />
            <p id="hourError" style="color: red; display: none"></p>
            <input type="text" id="datepicker" placeholder="Escolha uma data" />
            <p id="dataError" style="color: red; display: none"></p>
        </div>
        <div class="modal-footer">
            <button onclick="cancelModalReserva()">Cancelar</button>
            <button onclick="saveModalReserva()">Salvar</button>
        </div>
    </div>`;
}

function openModalReserva() {
    document.getElementById("modalArea").style.display = "block";
    setupCalendar();
}

function closeModalReserva() {
  const modal = document.getElementById("modalArea");

  modal.style.display = "none";

  const datepicker = document.getElementById("datepicker");
  if (datepicker) {
    datepicker.value = "";
  }

  const guestsInput = document.getElementById("guestsInput");
  if (guestsInput) {
    guestsInput.value = "";
  }

  const guestsError = document.getElementById("guestsError");
  if (guestsError) {
    guestsError.textContent = "";
    guestsError.style.display = "none";
  }

  const hourInput = document.getElementById("hourInput");
  if (hourInput) {
    hourInput.value = "";
  }

  const hourError = document.getElementById("hourError");
  if (hourError) {
    hourError.textContent = "";
    hourError.style.display = "none";
  }
}

function cancelModalReserva() {
    closeModalReserva();
}

function validateGuests() {
  const capacity = JSON.parse(localStorage.getItem("detalhesSalao")).capacidade;
  const guestsInput = document.getElementById("guestsInput");
  const guestsError = document.getElementById("guestsError");
  const numberOfGuests = parseInt(guestsInput.value);

  if (!numberOfGuests || numberOfGuests <= 0) {
    guestsError.textContent = "Campo obrigatório";
    guestsError.style.display = "block";
    guestsInput.style.border = "1px solid red";
    return false;
  } else if (numberOfGuests > capacity) {
    guestsError.textContent = "Capacidade máxima do salão excedida";
    guestsError.style.display = "block";
    guestsInput.style.border = "1px solid red";
    return false;
  } else {
    guestsError.style.display = "none";
    return true;
  }
}

function validadeEventDuration() {
  const hourInput = document.getElementById("hourInput");
  const hourError = document.getElementById("hourError");
  const eventDuration = parseInt(hourInput.value);

  if (!eventDuration || eventDuration <= 0) {
    hourError.textContent = "Campo obrigatório";
    hourError.style.display = "block";
    hourInput.style.border = "1px solid red";
    return false;
  } else if (eventDuration > 24) {
    hourError.textContent = "Duração não pode ser maior que 24h";
    hourError.style.display = "block";
    hourInput.style.border = "1px solid red";
    return false;
  } else {
    hourError.style.display = "none";
    return true;
  }
}

function validadeEventDate() {
  const dateInput = document.getElementById("datepicker");
  const dateError = document.getElementById("dataError");
  const eventDate = dateInput.value;

  if (eventDate == null || eventDate === "") {
    dateError.textContent = "Campo obrigatório";
    dateError.style.display = "block";
    dateInput.style.border = "1px solid red";
    return false;
  } else {
    dateError.style.display = "none";
    return true;
  }
}

function saveModalReserva() {
  if (validateGuests() && validadeEventDate() && validadeEventDuration()) {
    const salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
    const clienteId = localStorage.getItem("idUsuario");

    const dataEvento = document.getElementById("datepicker").value;
    const numeroConvidados = document.getElementById("guestsInput").value;
    const eventDuration = document.getElementById("hourInput").value;

    const reservaDTO = {
      dataEvento: dataEvento,
      salaoId: salaoDetalhes.id,
      clienteId: clienteId,
      numeroConvidados: numeroConvidados,
      duracao: eventDuration,
    };

    fetch("http://localhost:8080/reserva", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(reservaDTO),
    })
      .then((response) => response.text())
      .then((data) => {
        createNotification("success", "Reserva Registrada!", data ); 
        closeModalReserva(); // Fecha o modal após a operação
      })
      .catch((error) => {
        createNotification("error", "Reserva não Registrada!", data ); 
      });
  }
}

// <--------------- Avaliação -------------->

function initializeRatingSystem() {
  var stars = document.querySelectorAll(".rating .fa");
  stars.forEach(function (star) {
    star.addEventListener("click", function () {
      handleStarClick(stars, this);
    });
  });
}

function handleStarClick(stars, selectedStar) {
  resetStars(stars);
  selectedStar.classList.add("active");
  var value = parseInt(selectedStar.getAttribute("data-value"), 10);
  stars.forEach(function (star) {
    if (parseInt(star.getAttribute("data-value"), 10) <= value) {
      star.classList.add("active");
    }
  }); // Output the rating value to console or send it to server
}

function resetStars(stars) {
  stars.forEach(function (star) {
    star.classList.remove("active");
  });
}

function enviarAvaliacao() {
  var ratingValue = document
    .querySelector(".rating .fa.active")
    .getAttribute("data-value");
  var comentario = document.getElementById("feedback").value;
  var salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
  var clienteId = localStorage.getItem("idUsuario");
  var avaliacaoData = {
    usuario: clienteId,
    salaoDeFesta: salaoDetalhes.id,
    notaAtribuida: parseFloat(ratingValue),
    comentario: comentario,
  };

  fetch("http://localhost:8080/avaliacao", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(avaliacaoData),
  })
    .then((response) => response.json())
    .then((data) => {
      createNotification("success", "Avaliação Registrada!", "Avaliação registrada com sucesso." ); 
      preencherComentariosSalao(salaoDetalhes.id);
    })
    .catch((error) => {
      createNotification("error", "Avaliação não Registrada!", data ); 
    });
}

function setupAvalaciao() {
    document.getElementById("feedback-area").innerHTML = `
    <label for="feedback"><b>Avaliação:</b></label>
        <div class="rating">
            <div class="estrelas">
            <i class="fa fa-star" data-value="5"></i>
            <i class="fa fa-star" data-value="4"></i>
            <i class="fa fa-star" data-value="3"></i>
            <i class="fa fa-star" data-value="2"></i>
            <i class="fa fa-star" data-value="1"></i>
        </div>
        <textarea id="feedback" rows="3"></textarea>
        </div>
        <div id="feedback-submit">
            <button id="submit-rating">Enviar Avaliação</button>
        </div>
    `;
}

// <------------ Modal data bloqueada ------------>

function setupModalDatasBloqueio(){
    document.getElementById("modalArea").innerHTML = `
    <div class="modal-content">
        <div class="modal-header">
            <span class="modal-title">Bloquear Data</span>
            <span class="close-btn" onclick="closeModalDatasBloqueio()">&times;</span>
        </div>
        <div class="modal-body">
            <input type="text" id="datepicker" placeholder="Escolha uma data" />
            <p id="dataError" style="color: red; display: none"></p>
        </div>
        <div class="modal-footer">
            <button onclick="cancelModalDatasBloqueio()">Cancelar</button>
            <button onclick="saveModalDatasBloqueio()">Salvar</button>
        </div>
    </div>`;
}

function openModalDatasBloqueio() {
  setupModalDatasBloqueio();
    document.getElementById("modalArea").style.display = "block";
    setupCalendar();
}

function closeModalDatasBloqueio() {
  const modal = document.getElementById("modalArea");

  modal.style.display = "none";

  const datepicker = document.getElementById("datepicker");
  if (datepicker) {
    datepicker.value = "";
  }

}

function cancelModalDatasBloqueio() {
  closeModalDatasBloqueio();
}

function saveModalDatasBloqueio() {
  if(validadeEventDate()){
    const salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
    const dataBloqueio = document.getElementById("datepicker").value;
    
    const dataDTO = {
      data: dataBloqueio,
    };

    fetch(`http://localhost:8080/salao-de-festa/datas-bloqueadas/${salaoDetalhes.id}`,{ 
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dataDTO),
    })
      .then((response) => response.text())
      .then((data) => {
        createNotification("success","Sucesso", data ); 
        closeModalReserva(); 
      })
      .catch((error) => {
        console.error("Erro ao restringir data:", error);
        createNotification(error,"Falha ao restringir data", "");
      });

  }
}

//<------------------- MODAL ----------------->

function setupCalendarDesbloqueio() {
  const salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
  if (salaoDetalhes && salaoDetalhes.id) {
    fetch(`http://localhost:8080/salao-de-festa/datas-restringidas/${salaoDetalhes.id}`)
      .then((response) => response.json())
      .then((dataBloqueada) => {
        const enabledDates = dataBloqueada.map((db) => db.data); // As datas que devem ser habilitadas
        flatpickr("#datepicker", {
          dateFormat: "Y-m-d",
          enable: enabledDates, // Habilita apenas as datas especificadas
          altFormat: "d/m/Y",
          onChange: function (selectedDates, dateStr, instance) {
            document.getElementById("datepicker").value = dateStr;
          },
        });
      })
      .catch((error) => console.error("Error fetching blocked dates:", error));
  }
}


function setupModalDatasBloqueioLiberacao(){
  document.getElementById("modalArea").innerHTML = `
  <div class="modal-content">
      <div class="modal-header">
          <span class="modal-title">Desbloquear Data</span>
          <span class="close-btn" onclick="closeModalDatasBloqueioLiberacao()">&times;</span>
      </div>
      <div class="modal-body">
          <input type="text" id="datepicker" placeholder="Escolha uma data" />
          <p id="dataError" style="color: red; display: none"></p>
      </div>
      <div class="modal-footer">
          <button onclick="cancelModalDatasBloqueioLiberacao()">Cancelar</button>
          <button onclick="saveModalDatasBloqueioLiberacao()">Salvar</button>
      </div>
  </div>`;
}

function openModalDatasBloqueioLiberacao() {
  setupModalDatasBloqueioLiberacao();
  document.getElementById("modalArea").style.display = "block";
  setupCalendarDesbloqueio();
}

function closeModalDatasBloqueioLiberacao() {
  const modal = document.getElementById("modalArea");

  modal.style.display = "none";

  const datepicker = document.getElementById("datepicker");
  if (datepicker) {
    datepicker.value = "";
  }

}

function cancelModalDatasBloqueioLiberacao() {
  closeModalDatasBloqueioLiberacao();
}

function saveModalDatasBloqueioLiberacao() {
  if(validadeEventDate()){
    const salaoDetalhes = JSON.parse(localStorage.getItem("detalhesSalao"));
    const dataDesbloqueio = document.getElementById("datepicker").value;
    
    const dataDTO = {
      data: dataDesbloqueio,
    };

    fetch(`http://localhost:8080/salao-de-festa/desbloquearData/${salaoDetalhes.id}`,{ 
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(dataDTO),
    })
      .then((response) => response.text())
      .then((data) => {
        createNotification("success","Sucesso", data ); 
        closeModalReserva(); 
      })
      .catch((error) => {
        console.error("Erro ao restringir data:", error);
        createNotification(error,"Falha ao restringir data", "");
      });

  }
}


// type: success, error, warning
function createNotification(type, title, message) {
  const notification = document.createElement('div');
  notification.className = `notification ${type}`;
  notification.innerHTML = `
    <div class="notification-header">${title}<span class="notification-close">&times;</span></div>
    <div>${message}</div>
  `;

  const container = document.getElementById('notification-container');
  container.appendChild(notification);

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
