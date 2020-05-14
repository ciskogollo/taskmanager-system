/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "LoginWs";
var ws = new WebSocket(wsUri);
var output = document.getElementById("output");

ws.onerror = function(evt) { onError(evt); };
ws.onopen = function(evt) { onOpen(evt); };
//ws.onmessage = function(evt) { onMessage(evt) };
ws.onmessage = onMessage;

function sendText(msg){
    console.log('Enviando msg...');
    ws.send(msg);
}

function onMessage(evt){
    var arraypv = evt.data.split("/");
    console.log('Recibiendo msg: ' + arraypv);
    
    document.getElementById("price").innerHTML = arraypv[0];
    document.getElementById("volume").innerHTML = arraypv[1];
    writeToScreen(evt.data);
}

function onOpen(){
    console.log('Conectado al WS');
    writeToScreen('Conectado a ' + wsUri);
    document.getElementById("btnsend").addEventListener('click', function(e){
        sendText(document.getElementById("txtinput").value);
    });
}

function writeToScreen(msg){
    output.innerHTML += msg + "</br>";
}

function onError(evt) {
    writeToScreen('<span style="color:red;">ERROR: </span> ' + evt.data);
}