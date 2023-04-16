
var ButtonNewTweet = document.getElementById('BotonNuevoTweet');
var tableBody = document.getElementById('TableBody');

var listaDatos = [
    { user: 'Usuario1', tweet: 'Mensaje1', fecha: '2023-04-16' },
    { user: 'Usuario2', tweet: 'Mensaje2', fecha: '2023-04-17' },
    { user: 'Usuario3', tweet: 'Mensaje3', fecha: '2023-04-18' }
];

class TweetsPage {

    constructor() {
    }

     mostrarTweets() {

        tableBody.innerHTML = '';

        for (var i = 0; i < listaDatos.length; i++) {
            var fila = document.createElement('tr');
            var celdaUser = document.createElement('td');
            var celdaTweet = document.createElement('td');
            var celdaFecha = document.createElement('td');

            celdaUser.textContent = listaDatos[i].user;
            celdaTweet.textContent = listaDatos[i].tweet;
            celdaFecha.textContent = listaDatos[i].fecha;

            fila.appendChild(celdaUser);
            fila.appendChild(celdaTweet);
            fila.appendChild(celdaFecha);

            tableBody.appendChild(fila);
        }
    }

    agregarnuevoTweet(){
        window.location.href='/Pages/AddTweet/NewTweet.html';
    }
}