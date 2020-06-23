package b4a.tsm;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,29);
if (RapidSub.canDelegate("activity_create")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 29;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 30;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(536870912);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 31;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
Debug.ShouldStop(1073741824);
main._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_initialize" /*RemoteObject*/ ,main.processBA,(Object)(main.getObject()),(Object)(RemoteObject.createImmutable("wshand")));
 BA.debugLineNum = 32;BA.debugLine="session.Initialize";
Debug.ShouldStop(-2147483648);
main._session.runVoidMethod ("Initialize");
 BA.debugLineNum = 33;BA.debugLine="session.Put(\"state\",\"init\")";
Debug.ShouldStop(1);
main._session.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("state"))),(Object)((RemoteObject.createImmutable("init"))));
 BA.debugLineNum = 34;BA.debugLine="Activity.LoadLayout(\"login\")";
Debug.ShouldStop(2);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("login")),main.mostCurrent.activityBA);
 BA.debugLineNum = 35;BA.debugLine="wshand.Connect(endpoint)";
Debug.ShouldStop(4);
main._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_connect" /*RemoteObject*/ ,(Object)(main._endpoint));
 BA.debugLineNum = 36;BA.debugLine="lblStatus.Text = \"Status: Conectando...\"";
Debug.ShouldStop(8);
main.mostCurrent._lblstatus.runMethod(true,"setText",BA.ObjectToCharSequence("Status: Conectando..."));
 }else {
 BA.debugLineNum = 38;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
Debug.ShouldStop(32);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboard")),main.mostCurrent.activityBA);
 };
 BA.debugLineNum = 40;BA.debugLine="End Sub";
Debug.ShouldStop(128);
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
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,91);
if (RapidSub.canDelegate("activity_pause")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 91;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 92;BA.debugLine="If UserClosed = True Then";
Debug.ShouldStop(134217728);
if (RemoteObject.solveBoolean("=",_userclosed,main.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 93;BA.debugLine="session = Null";
Debug.ShouldStop(268435456);
main._session.setObject(main.mostCurrent.__c.getField(false,"Null"));
 }else {
 BA.debugLineNum = 95;BA.debugLine="Log(\"Sesion: \"&session.Values)";
Debug.ShouldStop(1073741824);
main.mostCurrent.__c.runVoidMethod ("LogImpl","7262148",RemoteObject.concat(RemoteObject.createImmutable("Sesion: "),main._session.runMethod(false,"Values")),0);
 };
 BA.debugLineNum = 97;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,84);
if (RapidSub.canDelegate("activity_resume")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 84;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 88;BA.debugLine="UpdateStatus";
Debug.ShouldStop(8388608);
_updatestatus();
 BA.debugLineNum = 89;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnconectar_click() throws Exception{
try {
		Debug.PushSubsStack("btnConectar_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,75);
if (RapidSub.canDelegate("btnconectar_click")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","btnconectar_click");}
RemoteObject _data = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
 BA.debugLineNum = 75;BA.debugLine="Sub btnConectar_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 76;BA.debugLine="lblStatus.Text = \"Status: Consultando...\"";
Debug.ShouldStop(2048);
main.mostCurrent._lblstatus.runMethod(true,"setText",BA.ObjectToCharSequence("Status: Consultando..."));
 BA.debugLineNum = 77;BA.debugLine="Dim data As Map";
Debug.ShouldStop(4096);
_data = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");Debug.locals.put("data", _data);
 BA.debugLineNum = 78;BA.debugLine="data.Initialize";
Debug.ShouldStop(8192);
_data.runVoidMethod ("Initialize");
 BA.debugLineNum = 79;BA.debugLine="data.Put(\"user\", txtNombre.Text)";
Debug.ShouldStop(16384);
_data.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("user"))),(Object)((main.mostCurrent._txtnombre.runMethod(true,"getText"))));
 BA.debugLineNum = 80;BA.debugLine="data.Put(\"hash\", txtPass.Text)";
Debug.ShouldStop(32768);
_data.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("hash"))),(Object)((main.mostCurrent._txtpass.runMethod(true,"getText"))));
 BA.debugLineNum = 81;BA.debugLine="wshand.SendEventToEndPoint(\"Ingresar_movil\", data";
Debug.ShouldStop(65536);
main._wshand.runClassMethod (b4a.tsm.websockethandler.class, "_sendeventtoendpoint" /*RemoteObject*/ ,(Object)(BA.ObjectToString("Ingresar_movil")),(Object)(_data));
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 22;BA.debugLine="Private lblServerTime As Label";
main.mostCurrent._lblservertime = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private lblStatus As Label";
main.mostCurrent._lblstatus = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private btnConectar As Button";
main.mostCurrent._btnconectar = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private txtNombre As EditText";
main.mostCurrent._txtnombre = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private txtPass As EditText";
main.mostCurrent._txtpass = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 27;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
dash_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.tsm.main");
websockethandler.myClass = BA.getDeviceClass ("b4a.tsm.websockethandler");
dash.myClass = BA.getDeviceClass ("b4a.tsm.dash");
starter.myClass = BA.getDeviceClass ("b4a.tsm.starter");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private wshand As WebSocketHandler";
main._wshand = RemoteObject.createNew ("b4a.tsm.websockethandler");
 //BA.debugLineNum = 17;BA.debugLine="Private endpoint As String = \"ws://192.168.0.159:";
main._endpoint = BA.ObjectToString("ws://192.168.0.159:8080/TaskManagerWeb/loginws");
 //BA.debugLineNum = 18;BA.debugLine="Public session As Map";
main._session = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _updatestatus() throws Exception{
try {
		Debug.PushSubsStack("UpdateStatus (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,64);
if (RapidSub.canDelegate("updatestatus")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","updatestatus");}
 BA.debugLineNum = 64;BA.debugLine="Sub UpdateStatus";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 65;BA.debugLine="If wshand.ws.Connected Then";
Debug.ShouldStop(1);
if (main._wshand.getField(false,"_ws" /*RemoteObject*/ ).runMethod(true,"getConnected").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 66;BA.debugLine="Log(\"Conectado: \"&endpoint)";
Debug.ShouldStop(2);
main.mostCurrent.__c.runVoidMethod ("LogImpl","7917506",RemoteObject.concat(RemoteObject.createImmutable("Conectado: "),main._endpoint),0);
 BA.debugLineNum = 67;BA.debugLine="lblStatus.Text = \"Status: Conectado\"";
Debug.ShouldStop(4);
main.mostCurrent._lblstatus.runMethod(true,"setText",BA.ObjectToCharSequence("Status: Conectado"));
 }else {
 BA.debugLineNum = 69;BA.debugLine="lblStatus.Text = \"Status: Desconectado\"";
Debug.ShouldStop(16);
main.mostCurrent._lblstatus.runMethod(true,"setText",BA.ObjectToCharSequence("Status: Desconectado"));
 };
 BA.debugLineNum = 71;BA.debugLine="btnConectar.Enabled = Not(wshand.ws.Connected)";
Debug.ShouldStop(64);
main.mostCurrent._btnconectar.runMethod(true,"setEnabled",main.mostCurrent.__c.runMethod(true,"Not",(Object)(main._wshand.getField(false,"_ws" /*RemoteObject*/ ).runMethod(true,"getConnected"))));
 BA.debugLineNum = 72;BA.debugLine="btnConectar.Enabled = wshand.ws.Connected";
Debug.ShouldStop(128);
main.mostCurrent._btnconectar.runMethod(true,"setEnabled",main._wshand.getField(false,"_ws" /*RemoteObject*/ ).runMethod(true,"getConnected"));
 BA.debugLineNum = 73;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_closed(RemoteObject _reason) throws Exception{
try {
		Debug.PushSubsStack("wshand_Closed (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,50);
if (RapidSub.canDelegate("wshand_closed")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","wshand_closed", _reason);}
Debug.locals.put("Reason", _reason);
 BA.debugLineNum = 50;BA.debugLine="Sub wshand_Closed(Reason As String)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 51;BA.debugLine="UpdateStatus";
Debug.ShouldStop(262144);
_updatestatus();
 BA.debugLineNum = 52;BA.debugLine="ToastMessageShow(Reason, True)";
Debug.ShouldStop(524288);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(_reason)),(Object)(main.mostCurrent.__c.getField(true,"True")));
 BA.debugLineNum = 53;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_connected() throws Exception{
try {
		Debug.PushSubsStack("wshand_Connected (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,46);
if (RapidSub.canDelegate("wshand_connected")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","wshand_connected");}
 BA.debugLineNum = 46;BA.debugLine="Sub wshand_Connected";
Debug.ShouldStop(8192);
 BA.debugLineNum = 47;BA.debugLine="UpdateStatus";
Debug.ShouldStop(16384);
_updatestatus();
 BA.debugLineNum = 48;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_runfunction(RemoteObject _param) throws Exception{
try {
		Debug.PushSubsStack("wshand_runFunction (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,55);
if (RapidSub.canDelegate("wshand_runfunction")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","wshand_runfunction", _param);}
Debug.locals.put("Param", _param);
 BA.debugLineNum = 55;BA.debugLine="Sub wshand_runFunction(Param As String)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 56;BA.debugLine="If(Param = \"logued\") Then";
Debug.ShouldStop(8388608);
if ((RemoteObject.solveBoolean("=",_param,RemoteObject.createImmutable("logued")))) { 
 BA.debugLineNum = 57;BA.debugLine="session.Put(\"state\",\"signed\")";
Debug.ShouldStop(16777216);
main._session.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("state"))),(Object)((RemoteObject.createImmutable("signed"))));
 BA.debugLineNum = 58;BA.debugLine="session.Put(\"username\",txtNombre.Text)";
Debug.ShouldStop(33554432);
main._session.runVoidMethod ("Put",(Object)(RemoteObject.createImmutable(("username"))),(Object)((main.mostCurrent._txtnombre.runMethod(true,"getText"))));
 BA.debugLineNum = 59;BA.debugLine="StartActivity(\"Dash\")";
Debug.ShouldStop(67108864);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((RemoteObject.createImmutable("Dash"))));
 BA.debugLineNum = 60;BA.debugLine="Log(session.Values)";
Debug.ShouldStop(134217728);
main.mostCurrent.__c.runVoidMethod ("LogImpl","74849669",BA.ObjectToString(main._session.runMethod(false,"Values")),0);
 };
 BA.debugLineNum = 62;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _wshand_servertime(RemoteObject _params) throws Exception{
try {
		Debug.PushSubsStack("wshand_ServerTime (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,42);
if (RapidSub.canDelegate("wshand_servertime")) { return b4a.tsm.main.remoteMe.runUserSub(false, "main","wshand_servertime", _params);}
Debug.locals.put("Params", _params);
 BA.debugLineNum = 42;BA.debugLine="Sub wshand_ServerTime(Params As List)";
Debug.ShouldStop(512);
 BA.debugLineNum = 43;BA.debugLine="lblServerTime.Text = \"ServerTime: \" & Params.Get(";
Debug.ShouldStop(1024);
main.mostCurrent._lblservertime.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("ServerTime: "),_params.runMethod(false,"Get",(Object)(BA.numberCast(int.class, 0))))));
 BA.debugLineNum = 44;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}