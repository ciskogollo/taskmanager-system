﻿B4A=true
Group=com.TaskManagerSystem
ModulesStructureVersion=1
Type=Activity
Version=9.801
@EndOfDesignText@
#Region  Activity Attributes 
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Public wshand As WebSocketHandler
	Private endpoint As String = "ws://192.168.0.159:8080/TaskManagerWeb/userws"
End Sub

Sub Globals
	Private btnEnviar As Button
	Private btnAtras As Button
	Private spinProcesos As Spinner
	Private txtNombreTipo As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If FirstTime Then
		wshand.Initialize(Me, "wshand")
		wshand.Connect(endpoint)
		If Main.session.Get("state") = "signed" Then
			Activity.LoadLayout("create_unit")
		Else
			Main.session.Put("state","empty")
			StartActivity("Main")
		End If
	End If
	
	UpdateData
End Sub

Sub UpdateData
	If wshand.ws.Connected Then
		Log("Conectado: "&endpoint)
		Dim data As Map
		data.Initialize
		data.Put("event", "listar")
		data.Put("obj","unidad")
		wshand.SendEventToEndPoint("listar", data)
	Else
		Log("Websocket Desconectado.")
	End If
End Sub

Sub RecordData (datosExt As Map)
	If wshand.ws.Connected Then
		Log("Registrar en: "&endpoint)
		Dim data As Map
		data.Initialize
		data.Put("event", "registrar")
		data.Put("obj", "unidad")
		data.Put("datos", datosExt)
		wshand.SendEventToEndPoint("registrar", data)
	End If
	
	Activity.Finish
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub btnAtras_Click
	Activity.Finish
	StartActivity("Dash")
End Sub

Sub wshand_processlist(paramap As Map)
	Dim mapProcess As Map
	mapProcess.Initialize
	For i = 1 To paramap.Size
		mapProcess = paramap.Get("process"&i)
		Log("Map:Process"&i&"= "&mapProcess)
		
		spinProcesos.Add(mapProcess.Get("nombre"))
	Next
End Sub

Sub btnEnviar_Click
	Dim datosExt As Map
	datosExt.Initialize

	Dim selProcess As String = spinProcesos.SelectedItem
	Log(selProcess)
	datosExt.Put("proceso", spinProcesos.SelectedItem)
	datosExt.Put("nombre", txtNombreTipo.Text)
	
	RecordData(datosExt)
'	Antes iba aqui
'	Activity.Finish
	StartActivity("Dash")
End Sub