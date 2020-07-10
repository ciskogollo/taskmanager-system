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
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Public Sub Initialize (vCallbk As Object, vEvtName";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="Callback = vCallbk";
__ref._callback /*Object*/  = _vcallbk;
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="EventName = vEvtName";
__ref._eventname /*String*/  = _vevtname;
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="ws.Initialize(\"ws\")";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .Initialize(ba,"ws");
RDebugUtils.currentLine=786436;
 //BA.debugLineNum = 786436;BA.debugLine="End Sub";
return "";
}
public String  _connect(b4a.tsm.websockethandler __ref,String _url) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "connect", false))
	 {return ((String) Debug.delegate(ba, "connect", new Object[] {_url}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Public Sub Connect(url As String)";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="ws.Connect(url)";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .Connect(_url);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="End Sub";
return "";
}
public String  _sendeventtoendpoint(b4a.tsm.websockethandler __ref,String _event,anywheresoftware.b4a.objects.collections.Map _data) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "sendeventtoendpoint", false))
	 {return ((String) Debug.delegate(ba, "sendeventtoendpoint", new Object[] {_event,_data}));}
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsgen = null;
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Public Sub SendEventToEndPoint(Event As String, Da";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="m.Initialize";
_m.Initialize();
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="m.Put(\"type\",\"event\")";
_m.Put((Object)("type"),(Object)("event"));
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="m.Put(\"event\", Event)";
_m.Put((Object)("event"),(Object)(_event));
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="m.Put(\"param\", Data)";
_m.Put((Object)("param"),(Object)(_data.getObject()));
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="Dim jsgen As JSONGenerator";
_jsgen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=983047;
 //BA.debugLineNum = 983047;BA.debugLine="jsgen.Initialize(m)";
_jsgen.Initialize(_m);
RDebugUtils.currentLine=983048;
 //BA.debugLineNum = 983048;BA.debugLine="Log(jsgen.ToString)";
__c.LogImpl("1983048",_jsgen.ToString(),0);
RDebugUtils.currentLine=983049;
 //BA.debugLineNum = 983049;BA.debugLine="ws.SendText(jsgen.ToString)";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .SendText(_jsgen.ToString());
RDebugUtils.currentLine=983050;
 //BA.debugLineNum = 983050;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.tsm.websockethandler __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Public ws As WebSocket";
_ws = new anywheresoftware.b4a.objects.WebSocketWrapper();
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="Private Callback As Object";
_callback = new Object();
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="Private EventName As String";
_eventname = "";
RDebugUtils.currentLine=720900;
 //BA.debugLineNum = 720900;BA.debugLine="End Sub";
return "";
}
public String  _close(b4a.tsm.websockethandler __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "close", false))
	 {return ((String) Debug.delegate(ba, "close", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Public Sub Close";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="If ws.Connected Then";
if (__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="ws.Close";
__ref._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .Close();
 };
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="End Sub";
return "";
}
public String  _ws_closed(b4a.tsm.websockethandler __ref,String _reason) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "ws_closed", false))
	 {return ((String) Debug.delegate(ba, "ws_closed", new Object[] {_reason}));}
RDebugUtils.currentLine=1179648;
 //BA.debugLineNum = 1179648;BA.debugLine="Private Sub ws_Closed (Reason As String)";
RDebugUtils.currentLine=1179649;
 //BA.debugLineNum = 1179649;BA.debugLine="CallSub2(Callback, EventName & \"_Closed\", Reason)";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_Closed",(Object)(_reason));
RDebugUtils.currentLine=1179650;
 //BA.debugLineNum = 1179650;BA.debugLine="End Sub";
return "";
}
public String  _ws_connected(b4a.tsm.websockethandler __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "ws_connected", false))
	 {return ((String) Debug.delegate(ba, "ws_connected", null));}
RDebugUtils.currentLine=1114112;
 //BA.debugLineNum = 1114112;BA.debugLine="Private Sub ws_Connected";
RDebugUtils.currentLine=1114113;
 //BA.debugLineNum = 1114113;BA.debugLine="CallSub(Callback,  EventName & \"_Connected\")";
__c.CallSubNew(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_Connected");
RDebugUtils.currentLine=1114114;
 //BA.debugLineNum = 1114114;BA.debugLine="End Sub";
return "";
}
public String  _ws_textmessage(b4a.tsm.websockethandler __ref,String _msg) throws Exception{
__ref = this;
RDebugUtils.currentModule="websockethandler";
if (Debug.shouldDelegate(ba, "ws_textmessage", false))
	 {return ((String) Debug.delegate(ba, "ws_textmessage", new Object[] {_msg}));}
anywheresoftware.b4a.objects.collections.JSONParser _jspars = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsonparam = null;
anywheresoftware.b4a.objects.collections.Map _m = null;
String _etype = "";
String _event = "";
String _param = "";
anywheresoftware.b4a.objects.collections.Map _parammap = null;
int _i = 0;
anywheresoftware.b4a.objects.collections.Map _usuariosmap = null;
anywheresoftware.b4a.objects.collections.Map _unidadesmap = null;
anywheresoftware.b4a.objects.collections.Map _rolesmap = null;
anywheresoftware.b4a.objects.collections.Map _procesosmap = null;
RDebugUtils.currentLine=1048576;
 //BA.debugLineNum = 1048576;BA.debugLine="Private Sub ws_TextMessage (msg As String)";
RDebugUtils.currentLine=1048577;
 //BA.debugLineNum = 1048577;BA.debugLine="Try";
try {RDebugUtils.currentLine=1048578;
 //BA.debugLineNum = 1048578;BA.debugLine="Dim jspars As JSONParser";
_jspars = new anywheresoftware.b4a.objects.collections.JSONParser();
RDebugUtils.currentLine=1048579;
 //BA.debugLineNum = 1048579;BA.debugLine="Dim jsonParam As JSONGenerator";
_jsonparam = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
RDebugUtils.currentLine=1048580;
 //BA.debugLineNum = 1048580;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1048581;
 //BA.debugLineNum = 1048581;BA.debugLine="jspars.Initialize(msg)";
_jspars.Initialize(_msg);
RDebugUtils.currentLine=1048582;
 //BA.debugLineNum = 1048582;BA.debugLine="Try";
try {RDebugUtils.currentLine=1048583;
 //BA.debugLineNum = 1048583;BA.debugLine="m = jspars.NextObject";
_m = _jspars.NextObject();
 } 
       catch (Exception e9) {
			ba.setLastException(e9);RDebugUtils.currentLine=1048585;
 //BA.debugLineNum = 1048585;BA.debugLine="Log(\"No se ha podido parsear de JSON a MAP .- \"";
__c.LogImpl("11048585","No se ha podido parsear de JSON a MAP .- "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
RDebugUtils.currentLine=1048589;
 //BA.debugLineNum = 1048589;BA.debugLine="Dim etype As String = m.get(\"type\")";
_etype = BA.ObjectToString(_m.Get((Object)("type")));
RDebugUtils.currentLine=1048590;
 //BA.debugLineNum = 1048590;BA.debugLine="Dim event As String = m.Get(\"event\")";
_event = BA.ObjectToString(_m.Get((Object)("event")));
RDebugUtils.currentLine=1048591;
 //BA.debugLineNum = 1048591;BA.debugLine="Dim param As String = m.Get(\"param\")";
_param = BA.ObjectToString(_m.Get((Object)("param")));
RDebugUtils.currentLine=1048592;
 //BA.debugLineNum = 1048592;BA.debugLine="Log(param)";
__c.LogImpl("11048592",_param,0);
RDebugUtils.currentLine=1048595;
 //BA.debugLineNum = 1048595;BA.debugLine="Log(\"---------------\")";
__c.LogImpl("11048595","---------------",0);
RDebugUtils.currentLine=1048596;
 //BA.debugLineNum = 1048596;BA.debugLine="Log(\"eType: \"&etype)";
__c.LogImpl("11048596","eType: "+_etype,0);
RDebugUtils.currentLine=1048597;
 //BA.debugLineNum = 1048597;BA.debugLine="If etype = \"event\" Then";
if ((_etype).equals("event")) { 
RDebugUtils.currentLine=1048598;
 //BA.debugLineNum = 1048598;BA.debugLine="Select event";
switch (BA.switchObjectToInt(_event,"runFunction")) {
case 0: {
RDebugUtils.currentLine=1048600;
 //BA.debugLineNum = 1048600;BA.debugLine="CallSub2(Callback, EventName & \"_\" & event, p";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+_event,(Object)(_param));
RDebugUtils.currentLine=1048601;
 //BA.debugLineNum = 1048601;BA.debugLine="Log(\"Evento: \"&CallSub2(Callback, EventName &";
__c.LogImpl("11048601","Evento: "+BA.ObjectToString(__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+_event,(Object)(_param))),0);
 break; }
}
;
 }else 
{RDebugUtils.currentLine=1048604;
 //BA.debugLineNum = 1048604;BA.debugLine="Else If etype = \"response\" Then";
if ((_etype).equals("response")) { 
RDebugUtils.currentLine=1048605;
 //BA.debugLineNum = 1048605;BA.debugLine="Dim paramMap As Map = m.Get(\"param\")";
_parammap = new anywheresoftware.b4a.objects.collections.Map();
_parammap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_m.Get((Object)("param"))));
RDebugUtils.currentLine=1048608;
 //BA.debugLineNum = 1048608;BA.debugLine="Log(\"MSGRecibido: \"& param)";
__c.LogImpl("11048608","MSGRecibido: "+_param,0);
RDebugUtils.currentLine=1048609;
 //BA.debugLineNum = 1048609;BA.debugLine="For i=0 To paramMap.Size -1";
{
final int step26 = 1;
final int limit26 = (int) (_parammap.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit26 ;_i = _i + step26 ) {
RDebugUtils.currentLine=1048610;
 //BA.debugLineNum = 1048610;BA.debugLine="Log(\"ParamMap-Keys: \"&paramMap.getKeyAt(i))";
__c.LogImpl("11048610","ParamMap-Keys: "+BA.ObjectToString(_parammap.GetKeyAt(_i)),0);
 }
};
RDebugUtils.currentLine=1048613;
 //BA.debugLineNum = 1048613;BA.debugLine="Try";
try {RDebugUtils.currentLine=1048614;
 //BA.debugLineNum = 1048614;BA.debugLine="Dim usuariosMap As Map = paramMap.Get(\"userlis";
_usuariosmap = new anywheresoftware.b4a.objects.collections.Map();
_usuariosmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("userlist"))));
RDebugUtils.currentLine=1048615;
 //BA.debugLineNum = 1048615;BA.debugLine="Dim unidadesMap As Map = paramMap.Get(\"unitlis";
_unidadesmap = new anywheresoftware.b4a.objects.collections.Map();
_unidadesmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("unitlist"))));
RDebugUtils.currentLine=1048616;
 //BA.debugLineNum = 1048616;BA.debugLine="Dim rolesMap As Map = paramMap.Get(\"rolelist\")";
_rolesmap = new anywheresoftware.b4a.objects.collections.Map();
_rolesmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("rolelist"))));
RDebugUtils.currentLine=1048617;
 //BA.debugLineNum = 1048617;BA.debugLine="Dim procesosMap As Map = paramMap.Get(\"process";
_procesosmap = new anywheresoftware.b4a.objects.collections.Map();
_procesosmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("processlist"))));
 } 
       catch (Exception e35) {
			ba.setLastException(e35);RDebugUtils.currentLine=1048619;
 //BA.debugLineNum = 1048619;BA.debugLine="Log(\"Maps Error: \" & LastException)";
__c.LogImpl("11048619","Maps Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
RDebugUtils.currentLine=1048622;
 //BA.debugLineNum = 1048622;BA.debugLine="Select event";
switch (BA.switchObjectToInt(_event,"dashboard")) {
case 0: {
RDebugUtils.currentLine=1048630;
 //BA.debugLineNum = 1048630;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"userlis";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+"userlist",(Object)(_usuariosmap));
RDebugUtils.currentLine=1048631;
 //BA.debugLineNum = 1048631;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"unitlis";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+"unitlist",(Object)(_unidadesmap));
RDebugUtils.currentLine=1048632;
 //BA.debugLineNum = 1048632;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"rolelis";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+"rolelist",(Object)(_rolesmap));
RDebugUtils.currentLine=1048633;
 //BA.debugLineNum = 1048633;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"process";
__c.CallSubNew2(ba,__ref._callback /*Object*/ ,__ref._eventname /*String*/ +"_"+"processlist",(Object)(_procesosmap));
RDebugUtils.currentLine=1048636;
 //BA.debugLineNum = 1048636;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
__c.LogImpl("11048636","Function: CallSub2("+__ref._eventname /*String*/ +"_"+BA.ObjectToString(_parammap.GetKeyAt((int) (0)))+", param("+BA.ObjectToString(_usuariosmap)+")",0);
RDebugUtils.currentLine=1048637;
 //BA.debugLineNum = 1048637;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
__c.LogImpl("11048637","Function: CallSub2("+__ref._eventname /*String*/ +"_"+BA.ObjectToString(_parammap.GetKeyAt((int) (1)))+", param("+BA.ObjectToString(_procesosmap)+")",0);
RDebugUtils.currentLine=1048638;
 //BA.debugLineNum = 1048638;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
__c.LogImpl("11048638","Function: CallSub2("+__ref._eventname /*String*/ +"_"+BA.ObjectToString(_parammap.GetKeyAt((int) (2)))+", param("+BA.ObjectToString(_rolesmap)+")",0);
RDebugUtils.currentLine=1048639;
 //BA.debugLineNum = 1048639;BA.debugLine="Log(\"Function: CallSub2(\" & EventName & \"_\" &";
__c.LogImpl("11048639","Function: CallSub2("+__ref._eventname /*String*/ +"_"+BA.ObjectToString(_parammap.GetKeyAt((int) (3)))+", param("+BA.ObjectToString(_unidadesmap)+")",0);
 break; }
}
;
 }}
;
 } 
       catch (Exception e50) {
			ba.setLastException(e50);RDebugUtils.currentLine=1048648;
 //BA.debugLineNum = 1048648;BA.debugLine="Log(\"TextMessage Error: \" & LastException)";
__c.LogImpl("11048648","TextMessage Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
RDebugUtils.currentLine=1048650;
 //BA.debugLineNum = 1048650;BA.debugLine="End Sub";
return "";
}
}