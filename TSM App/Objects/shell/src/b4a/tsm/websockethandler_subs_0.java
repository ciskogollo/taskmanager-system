package b4a.tsm;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class websockethandler_subs_0 {


public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Public ws As WebSocket";
websockethandler._ws = RemoteObject.createNew ("anywheresoftware.b4a.objects.WebSocketWrapper");__ref.setField("_ws",websockethandler._ws);
 //BA.debugLineNum = 5;BA.debugLine="Private Callback As Object";
websockethandler._callback = RemoteObject.createNew ("Object");__ref.setField("_callback",websockethandler._callback);
 //BA.debugLineNum = 6;BA.debugLine="Private EventName As String";
websockethandler._eventname = RemoteObject.createImmutable("");__ref.setField("_eventname",websockethandler._eventname);
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _close(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("Close (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,19);
if (RapidSub.canDelegate("close")) { return __ref.runUserSub(false, "websockethandler","close", __ref);}
 BA.debugLineNum = 19;BA.debugLine="Public Sub Close";
Debug.ShouldStop(262144);
 BA.debugLineNum = 20;BA.debugLine="If ws.Connected Then";
Debug.ShouldStop(524288);
if (__ref.getField(false,"_ws" /*RemoteObject*/ ).runMethod(true,"getConnected").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 21;BA.debugLine="ws.Close";
Debug.ShouldStop(1048576);
__ref.getField(false,"_ws" /*RemoteObject*/ ).runVoidMethod ("Close");
 };
 BA.debugLineNum = 23;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _connect(RemoteObject __ref,RemoteObject _url) throws Exception{
try {
		Debug.PushSubsStack("Connect (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,15);
if (RapidSub.canDelegate("connect")) { return __ref.runUserSub(false, "websockethandler","connect", __ref, _url);}
Debug.locals.put("url", _url);
 BA.debugLineNum = 15;BA.debugLine="Public Sub Connect(url As String)";
Debug.ShouldStop(16384);
 BA.debugLineNum = 16;BA.debugLine="ws.Connect(url)";
Debug.ShouldStop(32768);
__ref.getField(false,"_ws" /*RemoteObject*/ ).runVoidMethod ("Connect",(Object)(_url));
 BA.debugLineNum = 17;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba,RemoteObject _vcallbk,RemoteObject _vevtname) throws Exception{
try {
		Debug.PushSubsStack("Initialize (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,9);
if (RapidSub.canDelegate("initialize")) { return __ref.runUserSub(false, "websockethandler","initialize", __ref, _ba, _vcallbk, _vevtname);}
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
Debug.locals.put("vCallbk", _vcallbk);
Debug.locals.put("vEvtName", _vevtname);
 BA.debugLineNum = 9;BA.debugLine="Public Sub Initialize (vCallbk As Object, vEvtName";
Debug.ShouldStop(256);
 BA.debugLineNum = 10;BA.debugLine="Callback = vCallbk";
Debug.ShouldStop(512);
__ref.setField ("_callback" /*RemoteObject*/ ,_vcallbk);
 BA.debugLineNum = 11;BA.debugLine="EventName = vEvtName";
Debug.ShouldStop(1024);
__ref.setField ("_eventname" /*RemoteObject*/ ,_vevtname);
 BA.debugLineNum = 12;BA.debugLine="ws.Initialize(\"ws\")";
Debug.ShouldStop(2048);
__ref.getField(false,"_ws" /*RemoteObject*/ ).runVoidMethod ("Initialize",__ref.getField(false, "ba"),(Object)(RemoteObject.createImmutable("ws")));
 BA.debugLineNum = 13;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _sendeventtoendpoint(RemoteObject __ref,RemoteObject _event,RemoteObject _data) throws Exception{
try {
		Debug.PushSubsStack("SendEventToEndPoint (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,26);
if (RapidSub.canDelegate("sendeventtoendpoint")) { return __ref.runUserSub(false, "websockethandler","sendeventtoendpoint", __ref, _event, _data);}
RemoteObject _m = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _jsgen = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
Debug.locals.put("Event", _event);
Debug.locals.put("Data", _data);
 BA.debugLineNum = 26;BA.debugLine="Public Sub SendEventToEndPoint(Event As String, Da";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 27;BA.debugLine="Dim m As Map";
Debug.ShouldStop(67108864);
_m = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("m", _m);
 BA.debugLineNum = 28;BA.debugLine="m.Initialize";
Debug.ShouldStop(134217728);
_m.runVoidMethod ("Initialize");
 BA.debugLineNum = 29;BA.debugLine="m.Put(\"type\",\"event\")";
Debug.ShouldStop(268435456);
_m.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("type"))),(Object)((RemoteObject.createImmutable("event"))));
 BA.debugLineNum = 30;BA.debugLine="m.Put(\"event\", Event)";
Debug.ShouldStop(536870912);
_m.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("event"))),(Object)((_event)));
 BA.debugLineNum = 31;BA.debugLine="m.Put(\"param\", Data)";
Debug.ShouldStop(1073741824);
_m.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("param"))),(Object)((_data.getObject())));
 BA.debugLineNum = 32;BA.debugLine="Dim jsgen As JSONGenerator";
Debug.ShouldStop(-2147483648);
_jsgen = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("jsgen", _jsgen);
 BA.debugLineNum = 33;BA.debugLine="jsgen.Initialize(m)";
Debug.ShouldStop(1);
_jsgen.runVoidMethod ("Initialize",(Object)(_m));
 BA.debugLineNum = 34;BA.debugLine="Log(jsgen.ToString)";
Debug.ShouldStop(2);
websockethandler.__c.runVoidMethod ("LogImpl","1983048",_jsgen.runMethod(true,"ToString"),0);
 BA.debugLineNum = 35;BA.debugLine="ws.SendText(jsgen.ToString)";
Debug.ShouldStop(4);
__ref.getField(false,"_ws" /*RemoteObject*/ ).runVoidMethod ("SendText",(Object)(_jsgen.runMethod(true,"ToString")));
 BA.debugLineNum = 36;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ws_closed(RemoteObject __ref,RemoteObject _reason) throws Exception{
try {
		Debug.PushSubsStack("ws_Closed (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,118);
if (RapidSub.canDelegate("ws_closed")) { return __ref.runUserSub(false, "websockethandler","ws_closed", __ref, _reason);}
Debug.locals.put("Reason", _reason);
 BA.debugLineNum = 118;BA.debugLine="Private Sub ws_Closed (Reason As String)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 119;BA.debugLine="CallSub2(Callback, EventName & \"_Closed\", Reason)";
Debug.ShouldStop(4194304);
websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_Closed"))),(Object)((_reason)));
 BA.debugLineNum = 120;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ws_connected(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("ws_Connected (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,114);
if (RapidSub.canDelegate("ws_connected")) { return __ref.runUserSub(false, "websockethandler","ws_connected", __ref);}
 BA.debugLineNum = 114;BA.debugLine="Private Sub ws_Connected";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="CallSub(Callback,  EventName & \"_Connected\")";
Debug.ShouldStop(262144);
websockethandler.__c.runMethodAndSync(false,"CallSubNew",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_Connected"))));
 BA.debugLineNum = 116;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _ws_textmessage(RemoteObject __ref,RemoteObject _msg) throws Exception{
try {
		Debug.PushSubsStack("ws_TextMessage (websockethandler) ","websockethandler",1,__ref.getField(false, "ba"),__ref,38);
if (RapidSub.canDelegate("ws_textmessage")) { return __ref.runUserSub(false, "websockethandler","ws_textmessage", __ref, _msg);}
RemoteObject _jspars = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser");
RemoteObject _jsonparam = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");
RemoteObject _m = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _etype = RemoteObject.createImmutable("");
RemoteObject _event = RemoteObject.createImmutable("");
RemoteObject _param = RemoteObject.createImmutable("");
RemoteObject _parammap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
int _i = 0;
RemoteObject _usuariosmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _unidadesmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _rolesmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
RemoteObject _procesosmap = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
Debug.locals.put("msg", _msg);
 BA.debugLineNum = 38;BA.debugLine="Private Sub ws_TextMessage (msg As String)";
Debug.ShouldStop(32);
 BA.debugLineNum = 39;BA.debugLine="Try";
Debug.ShouldStop(64);
try { BA.debugLineNum = 40;BA.debugLine="Dim jspars As JSONParser";
Debug.ShouldStop(128);
_jspars = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser");Debug.locals.put("jspars", _jspars);
 BA.debugLineNum = 41;BA.debugLine="Dim jsonParam As JSONGenerator";
Debug.ShouldStop(256);
_jsonparam = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator");Debug.locals.put("jsonParam", _jsonparam);
 BA.debugLineNum = 42;BA.debugLine="Dim m As Map";
Debug.ShouldStop(512);
_m = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("m", _m);
 BA.debugLineNum = 43;BA.debugLine="jspars.Initialize(msg)";
Debug.ShouldStop(1024);
_jspars.runVoidMethod ("Initialize",(Object)(_msg));
 BA.debugLineNum = 44;BA.debugLine="Try";
Debug.ShouldStop(2048);
try { BA.debugLineNum = 45;BA.debugLine="m = jspars.NextObject";
Debug.ShouldStop(4096);
_m = _jspars.runMethod(false,"NextObject");Debug.locals.put("m", _m);
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e9) {
			BA.rdebugUtils.runVoidMethod("setLastException",__ref.getField(false, "ba"), e9.toString()); BA.debugLineNum = 47;BA.debugLine="Log(\"No se ha podido parsear de JSON a MAP .- \"";
Debug.ShouldStop(16384);
websockethandler.__c.runVoidMethod ("LogImpl","11048585",RemoteObject.concat(RemoteObject.createImmutable("No se ha podido parsear de JSON a MAP .- "),websockethandler.__c.runMethod(false,"LastException",__ref.runMethod(false,"getActivityBA"))),0);
 };
 BA.debugLineNum = 51;BA.debugLine="Dim etype As String = m.get(\"type\")";
Debug.ShouldStop(262144);
_etype = BA.ObjectToString(_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("type")))));Debug.locals.put("etype", _etype);Debug.locals.put("etype", _etype);
 BA.debugLineNum = 52;BA.debugLine="Dim event As String = m.Get(\"event\")";
Debug.ShouldStop(524288);
_event = BA.ObjectToString(_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("event")))));Debug.locals.put("event", _event);Debug.locals.put("event", _event);
 BA.debugLineNum = 53;BA.debugLine="Dim param As String = m.Get(\"param\")";
Debug.ShouldStop(1048576);
_param = BA.ObjectToString(_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("param")))));Debug.locals.put("param", _param);Debug.locals.put("param", _param);
 BA.debugLineNum = 54;BA.debugLine="Log(param)";
Debug.ShouldStop(2097152);
websockethandler.__c.runVoidMethod ("LogImpl","11048592",_param,0);
 BA.debugLineNum = 57;BA.debugLine="Log(\"---------------\")";
Debug.ShouldStop(16777216);
websockethandler.__c.runVoidMethod ("LogImpl","11048595",RemoteObject.createImmutable("---------------"),0);
 BA.debugLineNum = 58;BA.debugLine="Log(\"eType: \"&etype)";
Debug.ShouldStop(33554432);
websockethandler.__c.runVoidMethod ("LogImpl","11048596",RemoteObject.concat(RemoteObject.createImmutable("eType: "),_etype),0);
 BA.debugLineNum = 59;BA.debugLine="If etype = \"event\" Then";
Debug.ShouldStop(67108864);
if (RemoteObject.solveBoolean("=",_etype,BA.ObjectToString("event"))) { 
 BA.debugLineNum = 60;BA.debugLine="Select event";
Debug.ShouldStop(134217728);
switch (BA.switchObjectToInt(_event,BA.ObjectToString("runFunction"))) {
case 0: {
 BA.debugLineNum = 62;BA.debugLine="CallSub2(Callback, EventName & \"_\" & event, p";
Debug.ShouldStop(536870912);
websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),_event)),(Object)((_param)));
 BA.debugLineNum = 63;BA.debugLine="Log(\"Evento: \"&CallSub2(Callback, EventName &";
Debug.ShouldStop(1073741824);
websockethandler.__c.runVoidMethod ("LogImpl","11048601",RemoteObject.concat(RemoteObject.createImmutable("Evento: "),websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),_event)),(Object)((_param)))),0);
 break; }
}
;
 }else 
{ BA.debugLineNum = 66;BA.debugLine="Else If etype = \"response\" Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",_etype,BA.ObjectToString("response"))) { 
 BA.debugLineNum = 67;BA.debugLine="Dim paramMap As Map = m.Get(\"param\")";
Debug.ShouldStop(4);
_parammap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_parammap.setObject(_m.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("param")))));Debug.locals.put("paramMap", _parammap);
 BA.debugLineNum = 70;BA.debugLine="Log(\"MSGRecibido: \"& param)";
Debug.ShouldStop(32);
websockethandler.__c.runVoidMethod ("LogImpl","11048608",RemoteObject.concat(RemoteObject.createImmutable("MSGRecibido: "),_param),0);
 BA.debugLineNum = 71;BA.debugLine="For i=0 To paramMap.Size -1";
Debug.ShouldStop(64);
{
final int step26 = 1;
final int limit26 = RemoteObject.solve(new RemoteObject[] {_parammap.runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step26 > 0 && _i <= limit26) || (step26 < 0 && _i >= limit26) ;_i = ((int)(0 + _i + step26))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 72;BA.debugLine="Log(\"ParamMap-Keys: \"&paramMap.getKeyAt(i))";
Debug.ShouldStop(128);
websockethandler.__c.runVoidMethod ("LogImpl","11048610",RemoteObject.concat(RemoteObject.createImmutable("ParamMap-Keys: "),_parammap.runMethod(false,"GetKeyAt",(Object)(BA.numberCast(int.class, _i)))),0);
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 75;BA.debugLine="Try";
Debug.ShouldStop(1024);
try { BA.debugLineNum = 76;BA.debugLine="Dim usuariosMap As Map = paramMap.Get(\"userlis";
Debug.ShouldStop(2048);
_usuariosmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_usuariosmap.setObject(_parammap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("userlist")))));Debug.locals.put("usuariosMap", _usuariosmap);
 BA.debugLineNum = 77;BA.debugLine="Dim unidadesMap As Map = paramMap.Get(\"unitlis";
Debug.ShouldStop(4096);
_unidadesmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_unidadesmap.setObject(_parammap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("unitlist")))));Debug.locals.put("unidadesMap", _unidadesmap);
 BA.debugLineNum = 78;BA.debugLine="Dim rolesMap As Map = paramMap.Get(\"rolelist\")";
Debug.ShouldStop(8192);
_rolesmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_rolesmap.setObject(_parammap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("rolelist")))));Debug.locals.put("rolesMap", _rolesmap);
 BA.debugLineNum = 79;BA.debugLine="Dim procesosMap As Map = paramMap.Get(\"process";
Debug.ShouldStop(16384);
_procesosmap = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
_procesosmap.setObject(_parammap.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("processlist")))));Debug.locals.put("procesosMap", _procesosmap);
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e35) {
			BA.rdebugUtils.runVoidMethod("setLastException",__ref.getField(false, "ba"), e35.toString()); BA.debugLineNum = 81;BA.debugLine="Log(\"Maps Error: \" & LastException)";
Debug.ShouldStop(65536);
websockethandler.__c.runVoidMethod ("LogImpl","11048619",RemoteObject.concat(RemoteObject.createImmutable("Maps Error: "),websockethandler.__c.runMethod(false,"LastException",__ref.runMethod(false,"getActivityBA"))),0);
 };
 BA.debugLineNum = 84;BA.debugLine="Select event";
Debug.ShouldStop(524288);
switch (BA.switchObjectToInt(_event,BA.ObjectToString("dashboard"))) {
case 0: {
 BA.debugLineNum = 92;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"userlis";
Debug.ShouldStop(134217728);
websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),RemoteObject.createImmutable("userlist"))),(Object)((_usuariosmap)));
 BA.debugLineNum = 93;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"unitlis";
Debug.ShouldStop(268435456);
websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),RemoteObject.createImmutable("unitlist"))),(Object)((_unidadesmap)));
 BA.debugLineNum = 94;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"rolelis";
Debug.ShouldStop(536870912);
websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),RemoteObject.createImmutable("rolelist"))),(Object)((_rolesmap)));
 BA.debugLineNum = 95;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"process";
Debug.ShouldStop(1073741824);
websockethandler.__c.runMethodAndSync(false,"CallSubNew2",__ref.getField(false, "ba"),(Object)(__ref.getField(false,"_callback" /*RemoteObject*/ )),(Object)(RemoteObject.concat(__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),RemoteObject.createImmutable("processlist"))),(Object)((_procesosmap)));
 BA.debugLineNum = 98;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
Debug.ShouldStop(2);
websockethandler.__c.runVoidMethod ("LogImpl","11048636",RemoteObject.concat(RemoteObject.createImmutable("Function: CallSub2("),__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),_parammap.runMethod(false,"GetKeyAt",(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable(", param("),_usuariosmap,RemoteObject.createImmutable(")")),0);
 BA.debugLineNum = 99;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
Debug.ShouldStop(4);
websockethandler.__c.runVoidMethod ("LogImpl","11048637",RemoteObject.concat(RemoteObject.createImmutable("Function: CallSub2("),__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),_parammap.runMethod(false,"GetKeyAt",(Object)(BA.numberCast(int.class, 1))),RemoteObject.createImmutable(", param("),_procesosmap,RemoteObject.createImmutable(")")),0);
 BA.debugLineNum = 100;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
Debug.ShouldStop(8);
websockethandler.__c.runVoidMethod ("LogImpl","11048638",RemoteObject.concat(RemoteObject.createImmutable("Function: CallSub2("),__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),_parammap.runMethod(false,"GetKeyAt",(Object)(BA.numberCast(int.class, 2))),RemoteObject.createImmutable(", param("),_rolesmap,RemoteObject.createImmutable(")")),0);
 BA.debugLineNum = 101;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
Debug.ShouldStop(16);
websockethandler.__c.runVoidMethod ("LogImpl","11048639",RemoteObject.concat(RemoteObject.createImmutable("Function: CallSub2("),__ref.getField(true,"_eventname" /*RemoteObject*/ ),RemoteObject.createImmutable("_"),_parammap.runMethod(false,"GetKeyAt",(Object)(BA.numberCast(int.class, 3))),RemoteObject.createImmutable(", param("),_unidadesmap,RemoteObject.createImmutable(")")),0);
 break; }
}
;
 }}
;
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e50) {
			BA.rdebugUtils.runVoidMethod("setLastException",__ref.getField(false, "ba"), e50.toString()); BA.debugLineNum = 110;BA.debugLine="Log(\"TextMessage Error: \" & LastException)";
Debug.ShouldStop(8192);
websockethandler.__c.runVoidMethod ("LogImpl","11048648",RemoteObject.concat(RemoteObject.createImmutable("TextMessage Error: "),websockethandler.__c.runMethod(false,"LastException",__ref.runMethod(false,"getActivityBA"))),0);
 };
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}