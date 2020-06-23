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
	'These global variables will be redeclared each time the activity is created.
	'These variables can only be accessed from this module.

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
	Activity.AddMenuItem("Usuarios","")
	UpdateStatus
End Sub

Sub UpdateStatus
	If wshand.ws.Connected Then
		Log("Conectado: "&endpoint)
		Dim data As Map
		data.Initialize
		data.Put("obj","usuario")
		wshand.SendEventToEndPoint("listar", data)
	Else
		Log("Websocket Desconectado.")
	End If
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub
