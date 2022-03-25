console.log('Activado');
// Global Settings
let edit = false;

var taskResult = document.querySelector('#task-result');
var divMensaje = document.getElementById('mensaje');
var txtBusqueda = document.getElementById('search');
// search key type event
txtBusqueda.addEventListener('keyup', e =>{
  console.log('Buscando 1');
  if(txtBusqueda.value) {
    let search = txtBusqueda.value;
    
    const data = new FormData();
    data.append('search', search);

    fetch('searchTask', { 
      method: 'POST',
      body: data
    })
    .then((response) => {    
      var contentType = response.headers.get("content-type");
      if(contentType && contentType.indexOf("application/json") !== -1) {
        return response.json();
      } else {
        mensaje = 'La respuesta no es un JSON \n' + response.text(); 
        divMensaje.innerHTML = mensaje;
        console.log(mensaje);
      }    
    })
    .catch((error) => {
      mensaje = 'Hubo un problema con la petición Fetch en RESPONSE:' + error.message; 
      divMensaje.innerHTML = mensaje;
      console.log(mensaje);   
    })
    .then((data) => {        
      mensaje = "<h3>Datos</h3><br>" + JSON.stringify(data);
      divMensaje.innerHTML = mensaje;    
      document.querySelector('#task-form').reset();
            
      let tasks = data.records;
      let template = '';
      tasks.forEach(task => {
        template += `<li><a href="#" class="task-item">${task.name}</a></li>
        ` 
      });
      taskResult.style.display = '';
      var cB = document.querySelector('#containerBusqueda');      
      cB.innerHTML = template;

    })
    .catch((error) => {
      mensaje = 'Hubo un problema con la petición Fetch en DATOS:' + error.message; 
      divMensaje.innerHTML = mensaje;
      console.log(mensaje);   
    });

  }else{    
    var cB = document.querySelector('#containerBusqueda');      
    cB.innerHTML = '';
    taskResult.style.display = 'none';
  }
});

var formularioTareas = document.querySelector('#task-form');
formularioTareas.addEventListener('submit', e => {
    console.log('Submit '); 
    e.preventDefault(); //Impedir que el enlace abra una URL refrescando la página
    
  const data = new FormData();
  data.append('name', document.getElementById('name').value);
  data.append('description', document.getElementById('description').value);
  
  fetch('addTask', { 
      method: 'POST',
      mode: 'cors',
      body: data
  })
  .then((response) => {    
    var contentType = response.headers.get("content-type");
    if(contentType && contentType.indexOf("application/json") !== -1) {
      return response.json();
    } else {
      mensaje = 'La respuesta no es un JSON \n' + response.text(); 
      divMensaje.innerHTML = mensaje;
      console.log(mensaje);
    }    
  })
  .catch((error) => {
    mensaje = 'Hubo un problema con la petición Fetch en RESPONSE:' + error.message; 
    divMensaje.innerHTML = mensaje;
    console.log(mensaje);   
  })
  .then((data) => {        
    mensaje = "<h3>Datos</h3><br>" + JSON.stringify(data);
    divMensaje.innerHTML = mensaje;    
    document.querySelector('#task-form').reset();
    fetchTasks();  
  })
  .catch((error) => {
    mensaje = 'Hubo un problema con la petición Fetch en DATOS:' + error.message; 
    divMensaje.innerHTML = mensaje;
    console.log(mensaje);   
  });

});


// Fetching Tasks
function fetchTasks() {  
  var mensaje;

  var cabecera = new Headers();
  cabecera.append("Content-Type", "application/json");

  var miInit = { method: 'GET',
               headers: cabecera,
               mode: 'cors',
               cache: 'default' };

  var miRequest = new Request('readTasks', miInit);

  fetch(miRequest)
  .then((response) => {    
    var contentType = response.headers.get("content-type");
    if(contentType && contentType.indexOf("application/json") !== -1) {
      return response.json();
    } else {
      mensaje = 'La respouesta no es un JSON \n' + response.text(); 
      divMensaje.innerHTML = mensaje;
      console.log(mensaje);
    }    
  })
  .catch((error) => {
    mensaje = 'Hubo un problema con la petición Fetch en RESPONSE:' + error.message; 
    divMensaje.innerHTML = mensaje;
    console.log(mensaje);   
  })
  .then((data) => {    
    visualizarData(data);
    mensaje = "<h3>Datos</h3><br>" + JSON.stringify(data);
    divMensaje.innerHTML = mensaje;    
  })
  .catch((error) => {
    mensaje = 'Hubo un problema con la petición Fetch en DATOS:' + error.message; 
    divMensaje.innerHTML = mensaje;
    console.log(mensaje);   
  });
}

function visualizarData(data){   
  const tasks = data.records;
  let template = '';

  tasks.forEach(task => {    
    template += `
            <tr taskId="${task.id}">
            <td>${task.id}</td>
            <td>
            <a href="#" class="task-item">
              ${task.name} 
            </a>
            </td>
            <td>${task.description}</td>
            <td>
              <button class="task-delete btn btn-danger" onclick="eliminarTarea()">
               Eliminar 
              </button>
            </td>
            </tr>
          `
        });
    document.querySelector('#tasks').innerHTML = template;
 }

 function eliminarTarea(){   
   if(confirm('¿Está seguro que quiere eliminar esta tarea?')) {
    const element = document.activeElement.parentElement.parentElement;        
    const id = element.attributes.taskId.value;
    console.log(id);

    const data = new FormData();
    data.append('id', id);  
  
    fetch('delTask', { 
        method: 'POST',
        body: data
    })
    .then((response) => {    
      var contentType = response.headers.get("content-type");
      if(contentType && contentType.indexOf("application/json") !== -1) {
        return response.json();
      } else {
        mensaje = 'La respuesta no es un JSON \n' + response.text(); 
        divMensaje.innerHTML = mensaje;
        console.log(mensaje);
      }    
    })
    .catch((error) => {
      mensaje = 'Hubo un problema con la petición Fetch en RESPONSE:' + error.message; 
      divMensaje.innerHTML = mensaje;
      console.log(mensaje);   
    })
    .then((data) => {        
      mensaje = "<h3>Datos</h3><br>" + JSON.stringify(data);
      divMensaje.innerHTML = mensaje;    
      document.querySelector('#task-form').reset();
      fetchTasks();  
    })
    .catch((error) => {
      mensaje = 'Hubo un problema con la petición Fetch en DATOS:' + error.message; 
      divMensaje.innerHTML = mensaje;
      console.log(mensaje);   
    });

  }
 }

fetchTasks();
taskResult.style.display = 'none';