window.addEventListener('load', function () {
	//Al cargar la pagina buscamos y obtenemos el formulario donde estarán
	//los datos que el usuario cargará de la nueva pelicula
	const formulario = document.querySelector('#add_new_turno');
  console.log(formulario);
	//Ante un submit del formulario se ejecutará la siguiente funcion
	formulario.addEventListener('submit', function (event) {
		event.preventDefault();
		//creamos un JSON que tendrá los datos de la nueva película
		const formData = {
			paciente: {id: document.querySelector('#pacienteId').value},
			odontologo: {id: document.querySelector('#odontologoId').value},
			fecha: document.querySelector('#fechaIngreso').value,
		};


    console.log(formData);
		//invocamos utilizando la función fetch la API peliculas con el método POST que guardará
		//la película que enviaremos en formato JSON
		const url = '/turno';
		const settings = {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(formData),
		};

		fetch(url, settings)
			.then((response) => response.json())
			.then((data) => {
				//Si no hay ningun error se muestra un mensaje diciendo que la pelicula
				//se agrego bien
				let successAlert =
					'<div class="alert alert-success alert-dismissible">' +
					'<button type="button" class="close" data-dismiss="alert">&times;</button>' +
					'<strong></strong> Turno agregada </div>';

				document.querySelector('#response').innerHTML = successAlert;
				document.querySelector('#response').style.display = 'block';
				resetUploadForm();
			})
			.catch((error) => {
				//Si hay algun error se muestra un mensaje diciendo que la pelicula
				//no se pudo guardar y se intente nuevamente
				let errorAlert =
					'<div class="alert alert-danger alert-dismissible">' +
					'<button type="button" class="close" data-dismiss="alert">&times;</button>' +
					'<strong> Error intente nuevamente</strong> </div>';

				document.querySelector('#response').innerHTML = errorAlert;
				document.querySelector('#response').style.display = 'block';
				//se dejan todos los campos vacíos por si se quiere ingresar otra pelicula
				resetUploadForm();
			});
	});

	function resetUploadForm() {
		document.querySelector('#pacienteId').value = '';
		document.querySelector('#odontologoId').value = '';
		document.querySelector('#fechaIngreso').value = '';
	}

	(function () {
		let pathname = window.location.pathname;
		if (pathname === '/') {
			document.querySelector('.nav .nav-item a:first').addClass('active');
		} else if (pathname == '/peliculaList.html') {
			document.querySelector('.nav .nav-item a:last').addClass('active');
		}
	})();
});
