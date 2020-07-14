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
	Private spinCliente As Spinner
	Private spinUsuario As Spinner
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If FirstTime Then
		wshand.Initialize(Me, "wshand")
		wshand.Connect(endpoint)
		If Main.session.Get("state") = "signed" Then
			Activity.LoadLayout("create_process")
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
		data.Put("obj","proceso")
		wshand.SendEventToEndPoint("listar", data)
	Else
		Log("Websocket Desconectado.")
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub btnAtras_Click
	StartActivity("Dash")
End Sub

Sub wshand_userlist(paramap As Map)
	Dim mapUsers As Map
	mapUsers.Initialize
	For i = 1 To paramap.Size
		mapUsers = paramap.Get("user"&i)
		''Log("user"&i)
		Log("Map:User"&i&"= "&mapUsers)
		
		spinUsuario.Add(mapUsers.Get("nombre"))
	Next
End Sub

Sub wshand_clientlist(paramap As Map)
	Dim mapClients As Map
	mapClients.Initialize
	For i = 1 To paramap.Size
		mapClients = paramap.Get("client"&i)
		''Log("user"&i)
		Log("Map:Client"&i&"= "&mapClients)
		
		spinCliente.Add(mapClients.Get("nombre"))
	Next
End Sub