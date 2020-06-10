package b4a.tsm;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class websockethandler extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new BA(_ba, this, htSubs, "b4a.tsm.websockethandler");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.tsm.websockethandler.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.WebSocketWrapper _vv1 = null;
public Object _vv2 = null;
public String _vv3 = "";
public b4a.tsm.main _vv4 = null;
public b4a.tsm.starter _vv5 = null;
public b4a.tsm.dash _vv6 = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Public ws As WebSocket";
_vv1 = new anywheresoftware.b4a.objects.WebSocketWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Private Callback As Object";
_vv2 = new Object();
 //BA.debugLineNum = 6;BA.debugLine="Private EventName As String";
_vv3 = "";
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return "";
}
public String  _v6() throws Exception{
 //BA.debugLineNum = 19;BA.debugLine="Public Sub Close";
 //BA.debugLineNum = 20;BA.debugLine="If ws.Connected Then";
if (_vv1.getConnected()) { 
 //BA.debugLineNum = 21;BA.debugLine="ws.Close";
_vv1.Close();
 };
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public String  _v7(String _url) throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Public Sub Connect(url As String)";
 //BA.debugLineNum = 16;BA.debugLine="ws.Connect(url)";
_vv1.Connect(_url);
 //BA.debugLineNum = 17;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _vcallbk,String _vevtname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 9;BA.debugLine="Public Sub Initialize (vCallbk As Object, vEvtName";
 //BA.debugLineNum = 10;BA.debugLine="Callback = vCallbk";
_vv2 = _vcallbk;
 //BA.debugLineNum = 11;BA.debugLine="EventName = vEvtName";
_vv3 = _vevtname;
 //BA.debugLineNum = 12;BA.debugLine="ws.Initialize(\"ws\")";
_vv1.Initialize(ba,"ws");
 //BA.debugLineNum = 13;BA.debugLine="End Sub";
return "";
}
public String  _v0(String _event,anywheresoftware.b4a.objects.collections.Map _data) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsgen = null;
 //BA.debugLineNum = 26;BA.debugLine="Public Sub SendEventToEndPoint(Event As String, Da";
 //BA.debugLineNum = 27;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 28;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 29;BA.debugLine="m.Put(\"type\",\"event\")";
_m.Put((Object)("type"),(Object)("event"));
 //BA.debugLineNum = 30;BA.debugLine="m.Put(\"event\", Event)";
_m.Put((Object)("event"),(Object)(_event));
 //BA.debugLineNum = 31;BA.debugLine="m.Put(\"param\", Data)";
_m.Put((Object)("param"),(Object)(_data.getObject()));
 //BA.debugLineNum = 32;BA.debugLine="Dim jsgen As JSONGenerator";
_jsgen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 33;BA.debugLine="jsgen.Initialize(m)";
_jsgen.Initialize(_m);
 //BA.debugLineNum = 34;BA.debugLine="Log(jsgen.ToString)";
__c.LogImpl("01310728",_jsgen.ToString(),0);
 //BA.debugLineNum = 35;BA.debugLine="ws.SendText(jsgen.ToString)";
_vv1.SendText(_jsgen.ToString());
 //BA.debugLineNum = 36;BA.debugLine="End Sub";
return "";
}
public String  _ws_closed(String _reason) throws Exception{
 //BA.debugLineNum = 58;BA.debugLine="Private Sub ws_Closed (Reason As String)";
 //BA.debugLineNum = 59;BA.debugLine="CallSub2(Callback, EventName & \"_Closed\", Reason)";
__c.CallSubNew2(ba,_vv2,_vv3+"_Closed",(Object)(_reason));
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public String  _ws_connected() throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Private Sub ws_Connected";
 //BA.debugLineNum = 55;BA.debugLine="CallSub(Callback,  EventName & \"_Connected\")";
__c.CallSubNew(ba,_vv2,_vv3+"_Connected");
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public String  _ws_receivemsg(String _msg) throws Exception{
anywheresoftware.b4a.objects.collections.JSONParser _jspars = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
String _etype = "";
anywheresoftware.b4a.objects.collections.List _params = null;
String _event = "";
 //BA.debugLineNum = 38;BA.debugLine="Private Sub ws_ReceiveMsg (msg As String)";
 //BA.debugLineNum = 39;BA.debugLine="Try";
try { //BA.debugLineNum = 40;BA.debugLine="Dim jspars As JSONParser";
_jspars = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 41;BA.debugLine="jspars.Initialize(msg)";
_jspars.Initialize(_msg);
 //BA.debugLineNum = 42;BA.debugLine="Dim m As Map = jspars.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _jspars.NextObject();
 //BA.debugLineNum = 43;BA.debugLine="Dim etype As String = m.get(\"etype\")";
_etype = BA.ObjectToString(_m.Get((Object)("etype")));
 //BA.debugLineNum = 44;BA.debugLine="Dim params As List = m.Get(\"value\")";
_params = new anywheresoftware.b4a.objects.collections.List();
_params.setObject((java.util.List)(_m.Get((Object)("value"))));
 //BA.debugLineNum = 45;BA.debugLine="Dim event As String = m.Get(\"prop\")";
_event = BA.ObjectToString(_m.Get((Object)("prop")));
 //BA.debugLineNum = 46;BA.debugLine="If etype = \"runFunction\" Then";
if ((_etype).equals("runFunction")) { 
 //BA.debugLineNum = 47;BA.debugLine="CallSub2(Callback, EventName & \"_\" & event, par";
__c.CallSubNew2(ba,_vv2,_vv3+"_"+_event,(Object)(_params));
 };
 } 
       catch (Exception e12) {
			ba.setLastException(e12); //BA.debugLineNum = 50;BA.debugLine="Log(\"Error: \" & LastException)";
__c.LogImpl("01376268","Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
