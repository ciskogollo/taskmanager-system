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
	Private ListViewUsuarios As ListView
	Private ListViewUnidades As ListView
	Private ListViewRoles As ListView
	Private ListViewProcesos As ListView
	Private btnAbrirMenu As Button
	Private btnCerrarMenu As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If FirstTime Then
		wshand.Initialize(Me, "wshand")
		wshand.Connect(endpoint)
		If Main.session.Get("state") = "signed" Then
			Activity.LoadLayout("dashboard")
		Else
			Main.session.Put("state","empty")
			StartActivity("Main")
		End If
	End If
	
	Activity.AddMenuItem("Usuarios", "CrearUsuarios")
	Activity.AddMenuItem("Unidades","CrearUnidades")
	Activity.AddMenuItem("Roles","CrearRoles")
	Activity.AddMenuItem("Flujos","CrearFlujos")
	
	UpdateStatus
End Sub

Sub UpdateStatus
	If wshand.ws.Connected Then
		Log("Conectado: "&endpoint)
		Dim data As Map
		data.Initialize
		data.Put("event", "listar")
		data.Put("obj","dashboard")
		wshand.SendEventToEndPoint("listar", data)
	Else
		Log("Websocket Desconectado.")
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub wshand_userlist(paramap As Map)
	Dim mapUsers As Map
	mapUsers.Initialize
	For i = 1 To paramap.Size
		mapUsers = paramap.Get("user"&i)
		''Log("user"&i)
		Log("Map:User"&i&"= "&mapUsers)
		
		ListViewUsuarios.AddTwoLines(mapUsers.Get("nombre"), mapUsers.Get("rut")&" | "&mapUsers.Get("correo"))

	Next
End Sub

Sub wshand_unitlist(paramap As Map)
	Dim mapUnits As Map
	mapUnits.Initialize
	For i = 1 To paramap.Size
		mapUnits = paramap.Get("unit"&i)
		Log("Map:Unit"&i&"= "&mapUnits)
		
		ListViewUnidades.AddTwoLines(mapUnits.Get("nombre"), "ID: "& mapUnits.Get("id")&" | "&"Proceso: "&mapUnits.Get("proceso"))
	Next
End Sub

Sub wshand_rolelist(paramap As Map)
	Dim mapRoles As Map
	mapRoles.Initialize
	For i = 1 To paramap.Size
		mapRoles = paramap.Get("role"&i)
		Log("Map:Role"&i&"= "&mapRoles)
		
		ListViewRoles.AddTwoLines(mapRoles.Get("nombre"), "ID: "& mapRoles.Get("id"))
	Next
End Sub

Sub wshand_processlist(paramap As Map)
	Dim mapProcess As Map
	mapProcess.Initialize
	For i = 1 To paramap.Size
		mapProcess = paramap.Get("process"&i)
		Log("Map:Process"&i&"= "&mapProcess)
		
		ListViewProcesos.AddTwoLines(mapProcess.Get("nombre"), "ID: "& mapProcess.Get("id")&" | "&"Encargado: "&mapProcess.Get("nombreusuario"))
	Next
End Sub

Sub btnAbrirMenu_Click
	Activity.OpenMenu
End Sub

Sub CrearUsuarios_Click
	Msgbox("Estás a punto de crear un Usuario","¡Atención!")
	StartActivity("CrearUsuario")
End Sub

Sub CrearUnidades_Click
	Msgbox("Estás a punto de crear una Unidad","¡Atención!")
	StartActivity("CrearUnidad")
End Sub

Sub CrearRoles_Click
	Msgbox("Estás a punto de crear un Rol","¡Atención!")
	StartActivity("CrearRol")
End Sub

Sub CrearFlujos_Click
	Msgbox("Estás a punto de crear un Proceso","¡Atención!")
	StartActivity("CrearProceso")
End Sub