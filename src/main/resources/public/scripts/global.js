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

function handleUnirmeComunidad() {
  var selectedValue = document.getElementById('comunidad-select').value;
  fetch("/comunidades/" + selectedValue + "/alta-miembro", {
    method: 'POST',
    body: {}
  }).then(() => {
    window.location.href = '/comunidades'
  })
}

function handleBajaComunidad() {
  var selectedValue = document.getElementById('comunidad-select').value;
  fetch("/comunidades/" + selectedValue + "/alta-miembro", {
    method: 'POST',
    body: {}
  }).then(() => {
    window.location.href = '/comunidades'
  })
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
}

Dropzone.options.entidadesPrestadorasDropzone = {
  ...dropzoneConfig, accept: function (file, done) {
    const formData = new FormData();
    formData.append("file", file);

    fetch('/instituciones/importar-entidades-prestadoras', {
      method: 'POST',
      body: formData,
    })
      .then(response => response.json())
      .then(data => {
        console.log('File uploaded successfully:', data);
        done();
      })
      .catch(error => {
        console.error('Error uploading file:', error);
        done(error);
      });
  },
};

Dropzone.options.organismosDeControlDropzone = {
  ...dropzoneConfig, accept: function (file, done) {
    const formData = new FormData();
    formData.append("file", file);

    fetch('/instituciones/importar-organismos-de-control', {
      method: 'POST',
      body: formData,
    })
      .then(response => response.json())
      .then(data => {
        console.log('File uploaded successfully:', data);
        done();
      })
      .catch(error => {
        console.error('Error uploading file:', error);
        done(error);
      });
  },
};
