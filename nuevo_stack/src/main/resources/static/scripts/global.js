document.addEventListener("DOMContentLoaded", itemSidebarSeleccion);

const cerrarIncidenteBotones = document.getElementsByClassName('incidente-acciones')
Array.from(cerrarIncidenteBotones).forEach(boton => {
  boton.addEventListener("click", handleCerrarIncidente)
})

function handleCerrarIncidente(event) {
  const id = event.target.getAttribute("id");
  const entidad = event.target.getAttribute("entidad");
  const servicio = event.target.getAttribute("servicio");
  const observacion = event.target.getAttribute("observacion");
  const titulos = document.getElementById("cerrar-incidente-data").getElementsByTagName("p")

  const form = document.getElementById("cerrar-incidente-form")
  form.setAttribute("action", "/incidentes/" + + id + "/cerrar")

  titulos[0].innerHTML = entidad;
  titulos[1].innerHTML = servicio;
  titulos[2].innerHTML = observacion;
}

function itemSidebarSeleccion() {
  const sidebarDiv = document.querySelector("#sidebar");
  const links = sidebarDiv.querySelectorAll("a");

  function setActiveLink() {
    const currentPath = window.location.pathname;
    links.forEach((link) => {
      const linkPath = link.getAttribute("href");
      if (currentPath === linkPath) {
        link.getElementsByClassName("sidebar-item")[0].classList.add("selected");
      } else {
        link.getElementsByClassName("sidebar-item")[0].classList.remove("selected");
      }
    });
  }

  setActiveLink();
  window.addEventListener("popstate", setActiveLink);
}

function login() {
  window.location.href = '/'
}

function logout() {
  window.location.href = '/login'
}

MicroModal.init({
  openTrigger: 'modal-open',
  closeTrigger: 'modal-close',
  disableScroll: true,
  disableFocus: true,
  awaitCloseAnimation: false,
});

const dropzoneConfig = {
  paramName: "file",
  uploadMultiple: false,
  acceptedFiles: ".csv",
  maxFiles: 1,
  accept: function (file, done) {
    console.log(file);
    done();
  }
}

Dropzone.options.entidadesPrestadorasDropzone = dropzoneConfig;
Dropzone.options.organismosDeControlDropzone = dropzoneConfig;
