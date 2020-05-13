/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var wsUri = "ws://" + document.location.host + document.location.pathname + "LoginWs";
var ws = new WebSocket(wsUri);
var output = document.getElementById("output");

ws.onerror = function(evt) { onError(evt) };
ws.onopen = function(evt) { onOpen(evt) };

function writeToScreen(msg){
    output.innerHTML += msg + "</br>";
}

function onOpen(){
    writeToScreen('Conectado a ' + wsUri);
}

function onError(evt) {
    writeToScreen('<span style="color:red;">ERROR: </span> ' + evt.data);
}