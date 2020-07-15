B4A=true
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
	Private txtNombre As EditText
	Private txtCorreo As EditText
	Private txtHash As EditText
	Private txtRut As EditText
	Private txtDireccion As EditText
	Private spinRol As Spinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If FirstTime Then
		wshand.Initialize(Me, "wshand")
		wshand.Connect(endpoint)
		If Main.session.Get("state") = "signed" Then
			Activity.LoadLayout("create_user")
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
		data.Put("obj","usuario")
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
		data.Put("obj", "usuario")
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
	StartActivity("Dash")
End Sub

Sub wshand_rolelist(paramap As Map)
	Dim mapRoles As Map
	mapRoles.Initialize
	For i = 1 To paramap.Size
		mapRoles = paramap.Get("role"&i)
		Log("Map:Role"&i&"= "&mapRoles)
		
		spinRol.Add(mapRoles.Get("nombre"))
	Next
End Sub

Sub btnEnviar_Click
	Dim datosExt As Map
	datosExt.Initialize

	Dim selRol As String = spinRol.SelectedItem
	Log(selRol)
	datosExt.Put("nombre", txtNombre.Text.ToLowerCase)
	datosExt.Put("correo", txtCorreo.Text.ToLowerCase)
	datosExt.Put("hash", txtHash.Text)
	datosExt.Put("rut", txtRut.Text)
	datosExt.Put("direccion", txtDireccion.Text)
	datosExt.Put("selRol", selRol)
	
	RecordData(datosExt)
'	Antes iba aqui
'	Activity.Finish
	StartActivity("Dash")
End Sub