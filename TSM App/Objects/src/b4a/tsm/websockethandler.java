package b4a.tsm;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class websockethandler extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.tsm.websockethandler");
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

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.WebSocketWrapper _ws = null;
public Object _callback = null;
public String _eventname = "";
public b4a.tsm.main _main = null;
public b4a.tsm.dash _dash = null;
public b4a.tsm.starter _starter = null;
public String  _initialize(b4a.tsm.websockethandler __ref,anywheresoftware.b4a.BA _ba,Object _vcallbk,String _vevtname) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "initialize", false))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_vcallbk,_vevtname}));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Public Sub Initialize (vCallbk As Object, vEvtName";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="Callback = vCallbk";
__ref._callback /*Object*/  = _vcallbk;
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="EventName = vEvtName";
__ref._eventname /*String*/  = _vevtname;
RDebugUtils.currentLine=1114115;
 //BA.debugLineNum = 1114115;BA.debugLine="ws.Initialize(\"ws\")";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .Initialize(ba,"ws");
RDebugUtils.currentLine=1114116;
 //BA.debugLineNum = 1114116;BA.debugLine="End Sub";
return "";
}
public String  _connect(b4a.tsm.websockethandler __ref,String _url) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "connect", false))
	 {return ((String) Debug.delegate(ba, "connect", new Object[] {_url}));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Public Sub Connect(url As String)";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="ws.Connect(url)";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .Connect(_url);
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="End Sub";
return "";
}
public String  _sendeventtoendpoint(b4a.tsm.websockethandler __ref,String _event,anywheresoftware.b4a.objects.collections.Map _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "sendeventtoendpoint", false))
	 {return ((String) Debug.delegate(ba, "sendeventtoendpoint", new Object[] {_event,_data}));}
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsgen = null;
RDebugUtils.currentLine=1310720;
 //BA.debugLineNum = 1310720;BA.debugLine="Public Sub SendEventToEndPoint(Event As String, Da";
RDebugUtils.currentLine=1310721;
 //BA.debugLineNum = 1310721;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1310722;
 //BA.debugLineNum = 1310722;BA.debugLine="m.Initialize";
_m.Initialize();
RDebugUtils.currentLine=1310723;
 //BA.debugLineNum = 1310723;BA.debugLine="m.Put(\"type\",\"event\")";
_m.Put((Object)("type"),(Object)("event"));
RDebugUtils.currentLine=1310724;
 //BA.debugLineNum = 1310724;BA.debugLine="m.Put(\"event\", Event)";
_m.Put((Object)("event"),(Object)(_event));
RDebugUtils.currentLine=1310725;
 //BA.debugLineNum = 1310725;BA.debugLine="m.Put(\"param\", Data)";
_m.Put((Object)("param"),(Object)(_data.getObject()));
RDebugUtils.currentLine=1310726;
 //BA.debugLineNum = 1310726;BA.debugLine="Dim jsgen As JSONGenerator";
_jsgen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=1310727;
 //BA.debugLineNum = 1310727;BA.debugLine="jsgen.Initialize(m)";
_jsgen.Initialize(_m);
RDebugUtils.currentLine=1310728;
 //BA.debugLineNum = 1310728;BA.debugLine="Log(jsgen.ToString)";
__c.LogImpl("71310728",_jsgen.ToString(),0);
RDebugUtils.currentLine=1310729;
 //BA.debugLineNum = 1310729;BA.debugLine="ws.SendText(jsgen.ToString)";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .SendText(_jsgen.ToString());
RDebugUtils.currentLine=1310730;
 //BA.debugLineNum = 1310730;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.tsm.websockethandler __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Public ws As WebSocket";
_ws = new anywheresoftware.b4a.objects.WebSocketWrapper();
RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="Private Callback As Object";
_callback = new Object();
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="Private EventName As String";
_eventname = "";
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="End Sub";
return "";
}
public String  _close(b4a.tsm.websockethandler __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "close", false))
	 {return ((String) Debug.delegate(ba, "close", null));}
RDebugUtils.currentLine=1245184;
 //BA.debugLineNum = 1245184;BA.debugLine="Public Sub Close";
RDebugUtils.currentLine=1245185;
 //BA.debugLineNum = 1245185;BA.debugLine="If ws.Connected Then";
if (__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
RDebugUtils.currentLine=1245186;
 //BA.debugLineNum = 1245186;BA.debugLine="ws.Close";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .Close();
 };
RDebugUtils.currentLine=1245188;
 //BA.debugLineNum = 1245188;BA.debugLine="End Sub";
return "";
}
public String  _ws_closed(b4a.tsm.websockethandler __ref,String _reason) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "ws_closed", false))
	 {return ((String) Debug.delegate(ba, "ws_closed", new Object[] {_reason}));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Private Sub ws_Closed (Reason As String)";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="CallSub2(Callback, EventName & \"_Closed\", Reason)";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_Closed",(Object)(_reason));
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="End Sub";
return "";
}
public String  _ws_connected(b4a.tsm.websockethandler __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "ws_connected", false))
	 {return ((String) Debug.delegate(ba, "ws_connected", null));}
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Private Sub ws_Connected";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="CallSub(Callback,  EventName & \"_Connected\")";
__c.CallSubNew(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_Connected");
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="End Sub";
return "";
}
public String  _ws_textmessage(b4a.tsm.websockethandler __ref,String _msg) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "ws_textmessage", false))
	 {return ((String) Debug.delegate(ba, "ws_textmessage", new Object[] {_msg}));}
anywheresoftware.b4a.objects.collections.JSONParser _jspars = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
String _etype = "";
String _event = "";
String _param = "";
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Private Sub ws_TextMessage (msg As String)";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="Try";
try {RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="Dim jspars As JSONParser";
_jspars = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=2097155;
 //BA.debugLineNum = 2097155;BA.debugLine="jspars.Initialize(msg)";
_jspars.Initialize(_msg);
RDebugUtils.currentLine=2097156;
 //BA.debugLineNum = 2097156;BA.debugLine="Dim m As Map = jspars.NextObject";
_m = new anywheresoftware.b4a.objects.collections.Map();
_m = _jspars.NextObject();
RDebugUtils.currentLine=2097157;
 //BA.debugLineNum = 2097157;BA.debugLine="Log(\"Mapa: \"&m)";
__c.LogImpl("72097157","Mapa: "+BA.ObjectToString(_m),0);
RDebugUtils.currentLine=2097158;
 //BA.debugLineNum = 2097158;BA.debugLine="Dim etype As String = m.get(\"type\")";
_etype = BA.ObjectToString(_m.Get((Object)("type")));
RDebugUtils.currentLine=2097159;
 //BA.debugLineNum = 2097159;BA.debugLine="Dim event As String = m.Get(\"event\")";
_event = BA.ObjectToString(_m.Get((Object)("event")));
RDebugUtils.currentLine=2097160;
 //BA.debugLineNum = 2097160;BA.debugLine="Dim param As String = m.Get(\"param\")";
_param = BA.ObjectToString(_m.Get((Object)("param")));
RDebugUtils.currentLine=2097163;
 //BA.debugLineNum = 2097163;BA.debugLine="If event = \"runFunction\" Then";
if ((_event).equals("runFunction")) { 
RDebugUtils.currentLine=2097164;
 //BA.debugLineNum = 2097164;BA.debugLine="CallSub2(Callback, EventName & \"_\" & event, par";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+_event,(Object)(_param));
RDebugUtils.currentLine=2097165;
 //BA.debugLineNum = 2097165;BA.debugLine="Log(\"Evento: \"&CallSub2(Callback, EventName & \"";
__c.LogImpl("72097165","Evento: "+BA.ObjectToString(__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+_event,(Object)(_param))),0);
 }else 
{RDebugUtils.currentLine=2097166;
 //BA.debugLineNum = 2097166;BA.debugLine="Else If event = \"notice\" Then";
if ((_event).equals("notice")) { 
RDebugUtils.currentLine=2097167;
 //BA.debugLineNum = 2097167;BA.debugLine="Log(\"Noticia: \"&param)";
__c.LogImpl("72097167","Noticia: "+_param,0);
 }}
;
 } 
       catch (Exception e16) {
			ba.setLastException(e16);RDebugUtils.currentLine=2097170;
 //BA.debugLineNum = 2097170;BA.debugLine="Log(\"TextMsg Error: \" & LastException)";
__c.LogImpl("72097170","TextMsg Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
RDebugUtils.currentLine=2097172;
 //BA.debugLineNum = 2097172;BA.debugLine="End Sub";
return "";
}
}