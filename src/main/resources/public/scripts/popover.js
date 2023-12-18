

const showEvents = ['mouseenter'];
const hideEvents = ['mouseleave'];

const button = document.querySelector('#notificaciones');
const tooltip = document.querySelector('#sugerencia-revision');

const tooltipNotificaciones = Popper.createPopper(button, tooltip, {
  modifiers: [
    {
      name: 'offset',
      options: {
        offset: [0, 8],
      },
    },
  ],
});

function mostrarNotificaciones() {
  tooltip.setAttribute('data-show', '');

  tooltipNotificaciones.setOptions((options) => ({
    ...options,
    modifiers: [
      ...options.modifiers,
      { name: 'eventListeners', enabled: true },
    ],
  }));

  tooltipNotificaciones.update();
}

function ocultarNotificaciones() {
  tooltip.removeAttribute('data-show');

  tooltipNotificaciones.setOptions((options) => ({
    ...options,
    modifiers: [
      ...options.modifiers,
      { name: 'eventListeners', enabled: false },
    ],
  }));
}

showEvents.forEach((event) => {
  button.addEventListener(event, mostrarNotificaciones);
});

hideEvents.forEach((event) => {
  tooltip.addEventListener(event, ocultarNotificaciones);
});

// const menuButtons = document.getElementsByClassName('incidente-acciones');
// const menuTooltip = document.querySelector('#incidente-acciones-menu');

// Array.from(menuButtons).forEach(menuButton => {
//   const menuIncidentes = Popper.createPopper(menuButton, menuTooltip, {
//     placement: 'bottom-start',
//   });

//   function mostrarMenuIncidentes() {
//     menuTooltip.setAttribute('data-show', '');

//     menuIncidentes.setOptions((options) => ({
//       ...options,
//       modifiers: [
//         ...options.modifiers,
//         { name: 'eventListeners', enabled: true },
//       ],
//     }));

//     menuIncidentes.update();
//   }

//   function ocultarMenuIncidentes() {
//     menuTooltip.removeAttribute('data-show');

//     menuIncidentes.setOptions((options) => ({
//       ...options,
//       modifiers: [
//         ...options.modifiers,
//         { name: 'eventListeners', enabled: false },
//       ],
//     }));
//   }

//   [...showEvents, 'focus'].forEach((event) => {
//     menuButton.addEventListener(event, mostrarMenuIncidentes);
//   });

//   [...hideEvents, 'blur'].forEach((event) => {
//     menuTooltip.addEventListener(event, ocultarMenuIncidentes);
//   });
// })

