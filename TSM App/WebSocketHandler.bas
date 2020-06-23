B4A=true
Group=com.TaskManagerSystem
ModulesStructureVersion=1
Type=Class
Version=9.801
@EndOfDesignText@
#Event: Connected
#Event: Closed (Reason As String)
Sub Class_Globals
	Public ws As WebSocket
	Private Callback As Object
	Private EventName As String
End Sub

Public Sub Initialize (vCallbk As Object, vEvtName As String)
	Callback = vCallbk
	EventName = vEvtName
	ws.Initialize("ws")
End Sub

Public Sub Connect(url As String)
	ws.Connect(url)
End Sub

Public Sub Close
	If ws.Connected Then
		ws.Close
	End If
End Sub

'Provocamos un evento en el ws endpoint.
Public Sub SendEventToEndPoint(Event As String, Data As Map)
	Dim m As Map
	m.Initialize
	m.Put("type","event")
	m.Put("event", Event)
	m.Put("param", Data)
	Dim jsgen As JSONGenerator
	jsgen.Initialize(m)
	Log(jsgen.ToString)
	ws.SendText(jsgen.ToString)
End Sub

Private Sub ws_TextMessage (msg As String)
	Try
		Dim jspars As JSONParser
		jspars.Initialize(msg)
		Dim m As Map = jspars.NextObject
		Log("Mapa: "&m)
		Dim etype As String = m.get("type")
		Dim event As String = m.Get("event")
		Dim param As String = m.Get("param")
		'Log("MSGRecibido: "& param)
		
		If event = "runFunction" Then
			CallSub2(Callback, EventName & "_" & event, param)
			Log("Evento: "&CallSub2(Callback, EventName & "_" & event, param))
		Else If event = "list" Then
			'AQUI SE DEBE RECIBIR DATOS JSON DEL SERVER
		Else If event = "notice" Then
			Log("Noticia: "&param)
		End If
	Catch
		Log("TextMsg Error: " & LastException)
	End Try
End Sub

Private Sub ws_Connected
	CallSub(Callback,  EventName & "_Connected")
End Sub

Private Sub ws_Closed (Reason As String)
	CallSub2(Callback, EventName & "_Closed", Reason)
End Sub