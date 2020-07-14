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
'	Private Ips As List
'	Public timerconnect As Timer
'	Dim countadd As Int = 0
'	Private ipformuled As String
End Sub

Public Sub Initialize (vCallbk As Object, vEvtName As String)
	Callback = vCallbk
	EventName = vEvtName
	ws.Initialize("ws")
	
'	Ips.Initialize
'	Ips.Add("192.168.0.59")
'	Ips.Add("192.168.0.159")
'	Ips.Add("192.168.0.7")
'	For i=2 To 200
'		Dim strhost As String
'		'strhost = "ws://" & "192.168.0." & i & ":8080/TaskManagerWeb/"
'		strhost = "192.168.0." & i
'		Ips.Add(strhost)
'	Next
'	timerconnect.Initialize("timerconnect", 300)
End Sub

Public Sub Connect(url As String)
	ws.Connect(url)
	
'	If ws.Connected <> True Then
'		Log("WebSocket Desconectado. Intentando conexión...")
'		timerconnect.Enabled = True
'	Else
'		Log("WebSocket: Conexión establecida.")
'		timerconnect.Enabled = False
'	End If
End Sub

'Private Sub timerconnect_Tick
'	If ws.Connected <> True Then
'		ipformuled = "ws://" & Ips.Get(countadd) & ":8080/TaskManagerWeb/loginws"
'		Log("Conectando a: " & ipformuled)
'		ws.Connect(ipformuled)
'		countadd = countadd +1
'	End If
'End Sub

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
		Dim jsonParam As JSONGenerator
		Dim m As Map
		jspars.Initialize(msg)
		Try
			m = jspars.NextObject
		Catch
			Log("No se ha podido parsear de JSON a MAP .- "&LastException)
		End Try
		
		'Log("Mapa: "&m)
		Dim etype As String = m.get("type")
		Dim event As String = m.Get("event")
		Dim param As String = m.Get("param")
		Log(param)
		
		'AQUI SE DEBE RECIBIR DATOS JSON DEL SERVER
		Log("---------------")
		Log("eType: "&etype)
		If etype = "event" Then
			Select event
				Case "runFunction"
					CallSub2(Callback, EventName & "_" & event, param)
					Log("Evento: "&CallSub2(Callback, EventName & "_" & event, param))
			End Select
			
		Else If etype = "response" Then
			Dim paramMap As Map = m.Get("param")
		
			' IMPRIMIENDO MSG RECIBIDO
			Log("MSGRecibido: "& param)
			For i=0 To paramMap.Size -1
				Log("ParamMap-Keys: "&paramMap.getKeyAt(i))
			Next
			
			Try
				Dim usuariosMap As Map = paramMap.Get("userlist")
				Dim unidadesMap As Map = paramMap.Get("unitlist")
				Dim rolesMap As Map = paramMap.Get("rolelist")
				Dim procesosMap As Map = paramMap.Get("processlist")
				Dim clientesMap As Map = paramMap.Get("clientlist")
			Catch
				Log("Maps Error: " & LastException)
			End Try
			
			Select event
				Case "dashboard"
					CallSub2(Callback, EventName & "_" & "userlist", usuariosMap)
					CallSub2(Callback, EventName & "_" & "unitlist",unidadesMap)
					CallSub2(Callback, EventName & "_" & "rolelist", rolesMap)
					CallSub2(Callback, EventName & "_" & "processlist", procesosMap)
					
'					Log("Function: CallSub2(" & EventName & "_" & paramMap.getKeyAt(0) & ", param(" & usuariosMap&")")
'					Log("Function: CallSub2(" & EventName & "_" & paramMap.getKeyAt(1) & ", param(" & procesosMap&")")
'					Log("Function: CallSub2(" & EventName & "_" & paramMap.getKeyAt(2) & ", param(" & rolesMap&")")
'					Log("Function: CallSub2(" & EventName & "_" & paramMap.getKeyAt(3) & ", param(" & unidadesMap&")")
				Case "usuario"
					CallSub2(Callback, EventName & "_" & "rolelist", rolesMap)
				Case "unidad"
					CallSub2(Callback, EventName & "_" & "processlist", procesosMap)
				Case "proceso"
					CallSub2(Callback, EventName & "_" & "clientlist", clientesMap)
					CallSub2(Callback, EventName & "_" & "userlist", usuariosMap)
			End Select
		End If
		
	Catch
		Log("TextMessage Error: " & LastException)
	End Try
End Sub

Private Sub ws_Connected
	CallSub(Callback,  EventName & "_Connected")
End Sub

Private Sub ws_Closed (Reason As String)
	CallSub2(Callback, EventName & "_Closed", Reason)
End Sub