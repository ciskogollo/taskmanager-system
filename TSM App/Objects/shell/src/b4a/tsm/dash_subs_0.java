package b4a.tsm;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class dash_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,20);
if (RapidSub.canDelegate("activity_create")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 21;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(1048576);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 22;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
Debug.ShouldStop(2097152);
dash._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_initialize" /*RemoteObject*/ ,dash.processBA,(Object)(dash.getObject()),(Object)(RemoteObject.createImmutable("wshand")));
 BA.debugLineNum = 23;BA.debugLine="wshand.Connect(endpoint)";
Debug.ShouldStop(4194304);
dash._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_connect" /*RemoteObject*/ ,(Object)(dash._endpoint));
 BA.debugLineNum = 24;BA.debugLine="If Main.session.Get(\"state\") = \"signed\" Then";
Debug.ShouldStop(8388608);
if (RemoteObject.solveBoolean("=",dash.mostCurrent._main._session /*RemoteObject*/ .runMethod(false,"Get",(Object)((RemoteObject.createImmutable("state")))),RemoteObject.createImmutable(("signed")))) { 
 BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
Debug.ShouldStop(16777216);
dash.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),dash.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 27;BA.debugLine="Main.session.Put(\"state\",\"empty\")";
Debug.ShouldStop(67108864);
dash.mostCurrent._main._session /*RemoteObject*/ .runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("state"))),(Object)((RemoteObject.createImmutable("empty"))));
 BA.debugLineNum = 28;BA.debugLine="StartActivity(\"Main\")";
Debug.ShouldStop(134217728);
dash.mostCurrent.__c.runVoidMethod ("StartActivity",dash.processBA,(Object)((RemoteObject.createImmutable("Main"))));
 };
 };
 BA.debugLineNum = 32;BA.debugLine="Activity.AddMenuItem(\"Usuarios\",\"Usuarios\")";
Debug.ShouldStop(-2147483648);
dash.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Usuarios")),(Object)(RemoteObject.createImmutable("Usuarios")));
 BA.debugLineNum = 33;BA.debugLine="Activity.AddMenuItem(\"Unidades\",\"Unidades\")";
Debug.ShouldStop(1);
dash.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Unidades")),(Object)(RemoteObject.createImmutable("Unidades")));
 BA.debugLineNum = 34;BA.debugLine="Activity.AddMenuItem(\"Roles\",\"Roles\")";
Debug.ShouldStop(2);
dash.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Roles")),(Object)(RemoteObject.createImmutable("Roles")));
 BA.debugLineNum = 35;BA.debugLine="Activity.AddMenuItem(\"Flujos\",\"Flujos\")";
Debug.ShouldStop(4);
dash.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Flujos")),(Object)(RemoteObject.createImmutable("Flujos")));
 BA.debugLineNum = 36;BA.debugLine="UpdateStatus";
Debug.ShouldStop(8);
_updatestatus();
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,56);
if (RapidSub.canDelegate("activity_pause")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 56;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 58;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,52);
if (RapidSub.canDelegate("activity_resume")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","activity_resume");}
 BA.debugLineNum = 52;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Private ListViewUsuarios As ListView";
dash.mostCurrent._listviewusuarios = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private ListViewUnidades As ListView";
dash.mostCurrent._listviewunidades = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private ListViewRoles As ListView";
dash.mostCurrent._listviewroles = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private ListViewProcesos As ListView";
dash.mostCurrent._listviewprocesos = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public wshand As WebSocketHandler";
dash._wshand = RemoteObject.createNew ("b4a.tsm.websockethandler");
 //BA.debugLineNum = 8;BA.debugLine="Private endpoint As String = \"ws://192.168.0.159:";
dash._endpoint = BA.ObjectToString("ws://192.168.0.159:8080/TaskManagerWeb/userws");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _updatestatus() throws Exception{
try {
		Debug.PushSubsStack("UpdateStatus (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,39);
if (RapidSub.canDelegate("updatestatus")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","updatestatus");}
RemoteObject _data = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 39;BA.debugLine="Sub UpdateStatus";
Debug.ShouldStop(64);
 BA.debugLineNum = 40;BA.debugLine="If wshand.ws.Connected Then";
Debug.ShouldStop(128);
if (dash._wshand.getField(false,"_ws" /*RemoteObject*/ ).runMethod(true,"getConnected").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 41;BA.debugLine="Log(\"Conectado: \"&endpoint)";
Debug.ShouldStop(256);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","11441794",RemoteObject.concat(RemoteObject.createImmutable("Conectado: "),dash._endpoint),0);
 BA.debugLineNum = 42;BA.debugLine="Dim data As Map";
Debug.ShouldStop(512);
_data = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("data", _data);
 BA.debugLineNum = 43;BA.debugLine="data.Initialize";
Debug.ShouldStop(1024);
_data.runVoidMethod ("Initialize");
 BA.debugLineNum = 44;BA.debugLine="data.Put(\"event\", \"listar\")";
Debug.ShouldStop(2048);
_data.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("event"))),(Object)((RemoteObject.createImmutable("listar"))));
 BA.debugLineNum = 45;BA.debugLine="data.Put(\"obj\",\"dashboard\")";
Debug.ShouldStop(4096);
_data.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("obj"))),(Object)((RemoteObject.createImmutable("dashboard"))));
 BA.debugLineNum = 46;BA.debugLine="wshand.SendEventToEndPoint(\"listar\", data)";
Debug.ShouldStop(8192);
dash._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_sendeventtoendpoint" /*RemoteObject*/ ,(Object)(BA.ObjectToString("listar")),(Object)(_data));
 }else {
 BA.debugLineNum = 48;BA.debugLine="Log(\"Websocket Desconectado.\")";
Debug.ShouldStop(32768);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","11441801",RemoteObject.createImmutable("Websocket Desconectado."),0);
 };
 BA.debugLineNum = 50;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_processlist(RemoteObject _paramap) throws Exception{
try {
		Debug.PushSubsStack("wshand_processlist (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,95);
if (RapidSub.canDelegate("wshand_processlist")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","wshand_processlist", _paramap);}
RemoteObject _mapprocess = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
int _i = 0;
Debug.locals.put("paramap", _paramap);
 BA.debugLineNum = 95;BA.debugLine="Sub wshand_processlist(paramap As Map)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="Dim mapProcess As Map";
Debug.ShouldStop(-2147483648);
_mapprocess = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("mapProcess", _mapprocess);
 BA.debugLineNum = 97;BA.debugLine="mapProcess.Initialize";
Debug.ShouldStop(1);
_mapprocess.runVoidMethod ("Initialize");
 BA.debugLineNum = 98;BA.debugLine="For i = 1 To paramap.Size";
Debug.ShouldStop(2);
{
final int step3 = 1;
final int limit3 = _paramap.runMethod(true,"getSize").<Integer>get().intValue();
_i = 1 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 99;BA.debugLine="mapProcess = paramap.Get(\"process\"&i)";
Debug.ShouldStop(4);
_mapprocess.setObject(_paramap.runMethod(false,"Get",(Object)((RemoteObject.concat(RemoteObject.createImmutable("process"),RemoteObject.createImmutable(_i))))));
 BA.debugLineNum = 100;BA.debugLine="Log(\"Map:Process\"&i&\"= \"&mapProcess)";
Debug.ShouldStop(8);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","12555909",RemoteObject.concat(RemoteObject.createImmutable("Map:Process"),RemoteObject.createImmutable(_i),RemoteObject.createImmutable("= "),_mapprocess),0);
 BA.debugLineNum = 102;BA.debugLine="ListViewProcesos.AddTwoLines(mapProcess.Get(\"nom";
Debug.ShouldStop(32);
dash.mostCurrent._listviewprocesos.runVoidMethod ("AddTwoLines",(Object)(BA.ObjectToCharSequence(_mapprocess.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombre")))))),(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("ID: "),_mapprocess.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id")))),RemoteObject.createImmutable(" | "),RemoteObject.createImmutable("Proceso: "),_mapprocess.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombreusuario"))))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 104;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_rolelist(RemoteObject _paramap) throws Exception{
try {
		Debug.PushSubsStack("wshand_rolelist (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,84);
if (RapidSub.canDelegate("wshand_rolelist")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","wshand_rolelist", _paramap);}
RemoteObject _maproles = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
int _i = 0;
Debug.locals.put("paramap", _paramap);
 BA.debugLineNum = 84;BA.debugLine="Sub wshand_rolelist(paramap As Map)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 85;BA.debugLine="Dim mapRoles As Map";
Debug.ShouldStop(1048576);
_maproles = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("mapRoles", _maproles);
 BA.debugLineNum = 86;BA.debugLine="mapRoles.Initialize";
Debug.ShouldStop(2097152);
_maproles.runVoidMethod ("Initialize");
 BA.debugLineNum = 87;BA.debugLine="For i = 1 To paramap.Size";
Debug.ShouldStop(4194304);
{
final int step3 = 1;
final int limit3 = _paramap.runMethod(true,"getSize").<Integer>get().intValue();
_i = 1 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 88;BA.debugLine="mapRoles = paramap.Get(\"role\"&i)";
Debug.ShouldStop(8388608);
_maproles.setObject(_paramap.runMethod(false,"Get",(Object)((RemoteObject.concat(RemoteObject.createImmutable("role"),RemoteObject.createImmutable(_i))))));
 BA.debugLineNum = 89;BA.debugLine="Log(\"Map:Role\"&i&\"= \"&mapRoles)";
Debug.ShouldStop(16777216);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","12490373",RemoteObject.concat(RemoteObject.createImmutable("Map:Role"),RemoteObject.createImmutable(_i),RemoteObject.createImmutable("= "),_maproles),0);
 BA.debugLineNum = 91;BA.debugLine="ListViewRoles.AddTwoLines(mapRoles.Get(\"nombre\")";
Debug.ShouldStop(67108864);
dash.mostCurrent._listviewroles.runVoidMethod ("AddTwoLines",(Object)(BA.ObjectToCharSequence(_maproles.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombre")))))),(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("ID: "),_maproles.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id"))))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 93;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_unitlist(RemoteObject _paramap) throws Exception{
try {
		Debug.PushSubsStack("wshand_unitlist (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,73);
if (RapidSub.canDelegate("wshand_unitlist")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","wshand_unitlist", _paramap);}
RemoteObject _mapunits = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
int _i = 0;
Debug.locals.put("paramap", _paramap);
 BA.debugLineNum = 73;BA.debugLine="Sub wshand_unitlist(paramap As Map)";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="Dim mapUnits As Map";
Debug.ShouldStop(512);
_mapunits = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("mapUnits", _mapunits);
 BA.debugLineNum = 75;BA.debugLine="mapUnits.Initialize";
Debug.ShouldStop(1024);
_mapunits.runVoidMethod ("Initialize");
 BA.debugLineNum = 76;BA.debugLine="For i = 1 To paramap.Size";
Debug.ShouldStop(2048);
{
final int step3 = 1;
final int limit3 = _paramap.runMethod(true,"getSize").<Integer>get().intValue();
_i = 1 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 77;BA.debugLine="mapUnits = paramap.Get(\"unit\"&i)";
Debug.ShouldStop(4096);
_mapunits.setObject(_paramap.runMethod(false,"Get",(Object)((RemoteObject.concat(RemoteObject.createImmutable("unit"),RemoteObject.createImmutable(_i))))));
 BA.debugLineNum = 78;BA.debugLine="Log(\"Map:Unit\"&i&\"= \"&mapUnits)";
Debug.ShouldStop(8192);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","12424837",RemoteObject.concat(RemoteObject.createImmutable("Map:Unit"),RemoteObject.createImmutable(_i),RemoteObject.createImmutable("= "),_mapunits),0);
 BA.debugLineNum = 80;BA.debugLine="ListViewUnidades.AddTwoLines(mapUnits.Get(\"nombr";
Debug.ShouldStop(32768);
dash.mostCurrent._listviewunidades.runVoidMethod ("AddTwoLines",(Object)(BA.ObjectToCharSequence(_mapunits.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombre")))))),(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("ID: "),_mapunits.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("id")))),RemoteObject.createImmutable(" | "),RemoteObject.createImmutable("Proceso: "),_mapunits.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("proceso"))))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 82;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_userlist(RemoteObject _paramap) throws Exception{
try {
		Debug.PushSubsStack("wshand_userlist (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,60);
if (RapidSub.canDelegate("wshand_userlist")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","wshand_userlist", _paramap);}
RemoteObject _mapusers = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
int _i = 0;
Debug.locals.put("paramap", _paramap);
 BA.debugLineNum = 60;BA.debugLine="Sub wshand_userlist(paramap As Map)";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 61;BA.debugLine="Dim mapUsers As Map";
Debug.ShouldStop(268435456);
_mapusers = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("mapUsers", _mapusers);
 BA.debugLineNum = 62;BA.debugLine="mapUsers.Initialize";
Debug.ShouldStop(536870912);
_mapusers.runVoidMethod ("Initialize");
 BA.debugLineNum = 63;BA.debugLine="For i = 1 To paramap.Size";
Debug.ShouldStop(1073741824);
{
final int step3 = 1;
final int limit3 = _paramap.runMethod(true,"getSize").<Integer>get().intValue();
_i = 1 ;
for (;(step3 > 0 && _i <= limit3) || (step3 < 0 && _i >= limit3) ;_i = ((int)(0 + _i + step3))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 64;BA.debugLine="mapUsers = paramap.Get(\"user\"&i)";
Debug.ShouldStop(-2147483648);
_mapusers.setObject(_paramap.runMethod(false,"Get",(Object)((RemoteObject.concat(RemoteObject.createImmutable("user"),RemoteObject.createImmutable(_i))))));
 BA.debugLineNum = 66;BA.debugLine="Log(\"Map:User\"&i&\"= \"&mapUsers)";
Debug.ShouldStop(2);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","11638406",RemoteObject.concat(RemoteObject.createImmutable("Map:User"),RemoteObject.createImmutable(_i),RemoteObject.createImmutable("= "),_mapusers),0);
 BA.debugLineNum = 68;BA.debugLine="ListViewUsuarios.AddTwoLines(mapUsers.Get(\"nombr";
Debug.ShouldStop(8);
dash.mostCurrent._listviewusuarios.runVoidMethod ("AddTwoLines",(Object)(BA.ObjectToCharSequence(_mapusers.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("nombre")))))),(Object)(BA.ObjectToCharSequence(RemoteObject.concat(_mapusers.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("rut")))),RemoteObject.createImmutable(" | "),_mapusers.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("correo"))))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 71;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}