package b4a.tsm;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class dash_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,17);
if (RapidSub.canDelegate("activity_create")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 17;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 18;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(131072);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 19;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
Debug.ShouldStop(262144);
dash._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_initialize" /*RemoteObject*/ ,dash.processBA,(Object)(dash.getObject()),(Object)(RemoteObject.createImmutable("wshand")));
 BA.debugLineNum = 20;BA.debugLine="wshand.Connect(endpoint)";
Debug.ShouldStop(524288);
dash._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_connect" /*RemoteObject*/ ,(Object)(dash._endpoint));
 BA.debugLineNum = 21;BA.debugLine="If Main.session.Get(\"state\") = \"signed\" Then";
Debug.ShouldStop(1048576);
if (RemoteObject.solveBoolean("=",dash.mostCurrent._main._session /*RemoteObject*/ .runMethod(false,"Get",(Object)((RemoteObject.createImmutable("state")))),RemoteObject.createImmutable(("signed")))) { 
 BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
Debug.ShouldStop(2097152);
dash.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),dash.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 24;BA.debugLine="Main.session.Put(\"state\",\"empty\")";
Debug.ShouldStop(8388608);
dash.mostCurrent._main._session /*RemoteObject*/ .runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("state"))),(Object)((RemoteObject.createImmutable("empty"))));
 BA.debugLineNum = 25;BA.debugLine="StartActivity(\"Main\")";
Debug.ShouldStop(16777216);
dash.mostCurrent.__c.runVoidMethod ("StartActivity",dash.processBA,(Object)((RemoteObject.createImmutable("Main"))));
 };
 };
 BA.debugLineNum = 28;BA.debugLine="Activity.AddMenuItem(\"Usuarios\",\"\")";
Debug.ShouldStop(134217728);
dash.mostCurrent._activity.runVoidMethod ("AddMenuItem",(Object)(BA.ObjectToCharSequence("Usuarios")),(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 29;BA.debugLine="UpdateStatus";
Debug.ShouldStop(268435456);
_updatestatus();
 BA.debugLineNum = 30;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
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
		Debug.PushSubsStack("Activity_Pause (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,48);
if (RapidSub.canDelegate("activity_pause")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 48;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(32768);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,44);
if (RapidSub.canDelegate("activity_resume")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","activity_resume");}
 BA.debugLineNum = 44;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(2048);
 BA.debugLineNum = 46;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
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
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
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
		Debug.PushSubsStack("UpdateStatus (dash) ","dash",2,dash.mostCurrent.activityBA,dash.mostCurrent,32);
if (RapidSub.canDelegate("updatestatus")) { return b4a.tsm.dash.remoteMe.runUserSub(false, "dash","updatestatus");}
RemoteObject _data = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 32;BA.debugLine="Sub UpdateStatus";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 33;BA.debugLine="If wshand.ws.Connected Then";
Debug.ShouldStop(1);
if (dash._wshand.getField(false,"_ws" /*RemoteObject*/ ).runMethod(true,"getConnected").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 34;BA.debugLine="Log(\"Conectado: \"&endpoint)";
Debug.ShouldStop(2);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","75701634",RemoteObject.concat(RemoteObject.createImmutable("Conectado: "),dash._endpoint),0);
 BA.debugLineNum = 35;BA.debugLine="Dim data As Map";
Debug.ShouldStop(4);
_data = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("data", _data);
 BA.debugLineNum = 36;BA.debugLine="data.Initialize";
Debug.ShouldStop(8);
_data.runVoidMethod ("Initialize");
 BA.debugLineNum = 37;BA.debugLine="data.Put(\"obj\",\"usuario\")";
Debug.ShouldStop(16);
_data.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("obj"))),(Object)((RemoteObject.createImmutable("usuario"))));
 BA.debugLineNum = 38;BA.debugLine="wshand.SendEventToEndPoint(\"listar\", data)";
Debug.ShouldStop(32);
dash._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_sendeventtoendpoint" /*RemoteObject*/ ,(Object)(BA.ObjectToString("listar")),(Object)(_data));
 }else {
 BA.debugLineNum = 40;BA.debugLine="Log(\"Websocket Desconectado.\")";
Debug.ShouldStop(128);
dash.mostCurrent.__c.runVoidMethod ("LogImpl","75701640",RemoteObject.createImmutable("Websocket Desconectado."),0);
 };
 BA.debugLineNum = 42;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}