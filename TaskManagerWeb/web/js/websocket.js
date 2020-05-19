/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.hostname + ":8080/TaskManagerWeb/" + "LoginWs";
var ws = new WebSocket(wsUri);
var output = document.getElementById("output");

ws.onerror = function(evt) { onError(evt); };
ws.onopen = function(evt) { onOpen(evt); };
//ws.onmessage = function(evt) { onMessage(evt) };
ws.onmessage = onMessage;

function sendText(msg){
    console.log('Enviando msg...');
    try{
        ws.send(msg);
    }catch(e){
        console.log(e);
        console.error(e);
    }
}

function onMessage(evt){
    var recibidomsg = evt.data;
    var arraypv = evt.data.split("/");
    console.log('Mensaje recibido :)');
    document.getElementById('boxtext').value = recibidomsg;
    document.getElementById("price").innerHTML = arraypv[0];
    document.getElementById("volume").innerHTML = arraypv[1];
}

/*
function onMessage(evt){
    var arraypv = evt.data.split("/");
    console.log('Recibiendo msg: ' + arraypv);
    
    document.getElementById("price").innerHTML = arraypv[0];
    document.getElementById("volume").innerHTML = arraypv[1];
    writeToScreen(evt.data);
}
*/

function onOpen(){
    console.log('Conectado al WS');
    writeToScreen('Conectado a ' + wsUri);
    /*document.getElementById("btnsend").addEventListener('click', function(e){
        sendText(document.getElementById("txtinput").value);
    });*/
}

function writeToScreen(msg){
    output.innerHTML += msg + "</br>";
}

function onError(evt) {
    writeToScreen('<span style="color:red;">ERROR: </span> ' + evt.data);
}

document.getElementById("btnsignuser").addEventListener('click',logUser);
function logUser(){
    var inputEmail = document.getElementById("inputEmail").value;
    var inputPassword = document.getElementById("inputPassword").value;
    if(inputEmail === ''){
        console.log('User: vacío');
    }else{
        console.log('User: '+inputEmail);
    }
    if(inputPassword === ''){
        console.log('Password: vacío');
    }else{
        console.log('Password: *****');
    }
    
    var datosUser = {
        type: "datos",
        us:inputEmail,
        pshash:inputPassword,
        id: 1,
        date: Date.now()
    }
    console.log('Enviando datos al server...');
    try{
        ws.send(JSON.stringify(datosUser));
    }catch(e){
        console.log(e);
        console.error(e);
    }
}