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
public anywheresoftware.b4a.objects.WebSocketWrapper _vv4 = null;
public Object _vv5 = null;
public String _vv6 = "";
public b4a.tsm.main _vv7 = null;
public b4a.tsm.dash _vv0 = null;
public b4a.tsm.starter _vvv1 = null;
public b4a.tsm.crearusuario _vvv2 = null;
public b4a.tsm.crearunidad _vvv3 = null;
public b4a.tsm.crearrol _vvv4 = null;
public b4a.tsm.crearproceso _vvv5 = null;
public String  _class_globals() throws Exception{
 //BA.debugLineNum = 3;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 4;BA.debugLine="Public ws As WebSocket";
_vv4 = new anywheresoftware.b4a.objects.WebSocketWrapper();
 //BA.debugLineNum = 5;BA.debugLine="Private Callback As Object";
_vv5 = new Object();
 //BA.debugLineNum = 6;BA.debugLine="Private EventName As String";
_vv6 = "";
 //BA.debugLineNum = 11;BA.debugLine="End Sub";
return "";
}
public String  _vv1() throws Exception{
 //BA.debugLineNum = 52;BA.debugLine="Public Sub Close";
 //BA.debugLineNum = 53;BA.debugLine="If ws.Connected Then";
if (_vv4.getConnected()) { 
 //BA.debugLineNum = 54;BA.debugLine="ws.Close";
_vv4.Close();
 };
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public String  _vv2(String _url) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Public Sub Connect(url As String)";
 //BA.debugLineNum = 32;BA.debugLine="ws.Connect(url)";
_vv4.Connect(_url);
 //BA.debugLineNum = 41;BA.debugLine="End Sub";
return "";
}
public String  _initialize(anywheresoftware.b4a.BA _ba,Object _vcallbk,String _vevtname) throws Exception{
innerInitialize(_ba);
 //BA.debugLineNum = 13;BA.debugLine="Public Sub Initialize (vCallbk As Object, vEvtName";
 //BA.debugLineNum = 14;BA.debugLine="Callback = vCallbk";
_vv5 = _vcallbk;
 //BA.debugLineNum = 15;BA.debugLine="EventName = vEvtName";
_vv6 = _vevtname;
 //BA.debugLineNum = 16;BA.debugLine="ws.Initialize(\"ws\")";
_vv4.Initialize(ba,"ws");
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public String  _vv3(String _event,anywheresoftware.b4a.objects.collections.Map _data) throws Exception{
anywheresoftware.b4a.objects.collections.Map _m = null;
anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator _jsgen = null;
 //BA.debugLineNum = 59;BA.debugLine="Public Sub SendEventToEndPoint(Event As String, Da";
 //BA.debugLineNum = 60;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 61;BA.debugLine="m.Initialize";
_m.Initialize();
 //BA.debugLineNum = 62;BA.debugLine="m.Put(\"type\",\"event\")";
_m.Put((Object)("type"),(Object)("event"));
 //BA.debugLineNum = 63;BA.debugLine="m.Put(\"event\", Event)";
_m.Put((Object)("event"),(Object)(_event));
 //BA.debugLineNum = 64;BA.debugLine="m.Put(\"param\", Data)";
_m.Put((Object)("param"),(Object)(_data.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="Dim jsgen As JSONGenerator";
_jsgen = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 66;BA.debugLine="jsgen.Initialize(m)";
_jsgen.Initialize(_m);
 //BA.debugLineNum = 67;BA.debugLine="Log(jsgen.ToString)";
__c.LogImpl("5983048",_jsgen.ToString(),0);
 //BA.debugLineNum = 68;BA.debugLine="ws.SendText(jsgen.ToString)";
_vv4.SendText(_jsgen.ToString());
 //BA.debugLineNum = 69;BA.debugLine="End Sub";
return "";
}
public String  _ws_closed(String _reason) throws Exception{
 //BA.debugLineNum = 148;BA.debugLine="Private Sub ws_Closed (Reason As String)";
 //BA.debugLineNum = 149;BA.debugLine="CallSub2(Callback, EventName & \"_Closed\", Reason)";
__c.CallSubNew2(ba,_vv5,_vv6+"_Closed",(Object)(_reason));
 //BA.debugLineNum = 150;BA.debugLine="End Sub";
return "";
}
public String  _ws_connected() throws Exception{
 //BA.debugLineNum = 144;BA.debugLine="Private Sub ws_Connected";
 //BA.debugLineNum = 145;BA.debugLine="CallSub(Callback,  EventName & \"_Connected\")";
__c.CallSubNew(ba,_vv5,_vv6+"_Connected");
 //BA.debugLineNum = 146;BA.debugLine="End Sub";
return "";
}
public String  _ws_textmessage(String _msg) throws Exception{
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
anywheresoftware.b4a.objects.collections.Map _clientesmap = null;
 //BA.debugLineNum = 71;BA.debugLine="Private Sub ws_TextMessage (msg As String)";
 //BA.debugLineNum = 72;BA.debugLine="Try";
try { //BA.debugLineNum = 73;BA.debugLine="Dim jspars As JSONParser";
_jspars = new anywheresoftware.b4a.objects.collections.JSONParser();
 //BA.debugLineNum = 74;BA.debugLine="Dim jsonParam As JSONGenerator";
_jsonparam = new anywheresoftware.b4a.objects.collections.JSONParser.JSONGenerator();
 //BA.debugLineNum = 75;BA.debugLine="Dim m As Map";
_m = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 76;BA.debugLine="jspars.Initialize(msg)";
_jspars.Initialize(_msg);
 //BA.debugLineNum = 77;BA.debugLine="Try";
try { //BA.debugLineNum = 78;BA.debugLine="m = jspars.NextObject";
_m = _jspars.NextObject();
 } 
       catch (Exception e9) {
			ba.setLastException(e9); //BA.debugLineNum = 80;BA.debugLine="Log(\"No se ha podido parsear de JSON a MAP .- \"";
__c.LogImpl("51048585","No se ha podido parsear de JSON a MAP .- "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 //BA.debugLineNum = 84;BA.debugLine="Dim etype As String = m.get(\"type\")";
_etype = BA.ObjectToString(_m.Get((Object)("type")));
 //BA.debugLineNum = 85;BA.debugLine="Dim event As String = m.Get(\"event\")";
_event = BA.ObjectToString(_m.Get((Object)("event")));
 //BA.debugLineNum = 86;BA.debugLine="Dim param As String = m.Get(\"param\")";
_param = BA.ObjectToString(_m.Get((Object)("param")));
 //BA.debugLineNum = 87;BA.debugLine="Log(param)";
__c.LogImpl("51048592",_param,0);
 //BA.debugLineNum = 90;BA.debugLine="Log(\"---------------\")";
__c.LogImpl("51048595","---------------",0);
 //BA.debugLineNum = 91;BA.debugLine="Log(\"eType: \"&etype)";
__c.LogImpl("51048596","eType: "+_etype,0);
 //BA.debugLineNum = 92;BA.debugLine="If etype = \"event\" Then";
if ((_etype).equals("event")) { 
 //BA.debugLineNum = 93;BA.debugLine="Select event";
switch (BA.switchObjectToInt(_event,"runFunction")) {
case 0: {
 //BA.debugLineNum = 95;BA.debugLine="CallSub2(Callback, EventName & \"_\" & event, p";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+_event,(Object)(_param));
 //BA.debugLineNum = 96;BA.debugLine="Log(\"Evento: \"&CallSub2(Callback, EventName &";
__c.LogImpl("51048601","Evento: "+BA.ObjectToString(__c.CallSubNew2(ba,_vv5,_vv6+"_"+_event,(Object)(_param))),0);
 break; }
}
;
 }else if((_etype).equals("response")) { 
 //BA.debugLineNum = 100;BA.debugLine="Dim paramMap As Map = m.Get(\"param\")";
_parammap = new anywheresoftware.b4a.objects.collections.Map();
_parammap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_m.Get((Object)("param"))));
 //BA.debugLineNum = 103;BA.debugLine="Log(\"MSGRecibido: \"& param)";
__c.LogImpl("51048608","MSGRecibido: "+_param,0);
 //BA.debugLineNum = 104;BA.debugLine="For i=0 To paramMap.Size -1";
{
final int step26 = 1;
final int limit26 = (int) (_parammap.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit26 ;_i = _i + step26 ) {
 //BA.debugLineNum = 105;BA.debugLine="Log(\"ParamMap-Keys: \"&paramMap.getKeyAt(i))";
__c.LogImpl("51048610","ParamMap-Keys: "+BA.ObjectToString(_parammap.GetKeyAt(_i)),0);
 }
};
 //BA.debugLineNum = 108;BA.debugLine="Try";
try { //BA.debugLineNum = 109;BA.debugLine="Dim usuariosMap As Map = paramMap.Get(\"userlis";
_usuariosmap = new anywheresoftware.b4a.objects.collections.Map();
_usuariosmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("userlist"))));
 //BA.debugLineNum = 110;BA.debugLine="Dim unidadesMap As Map = paramMap.Get(\"unitlis";
_unidadesmap = new anywheresoftware.b4a.objects.collections.Map();
_unidadesmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("unitlist"))));
 //BA.debugLineNum = 111;BA.debugLine="Dim rolesMap As Map = paramMap.Get(\"rolelist\")";
_rolesmap = new anywheresoftware.b4a.objects.collections.Map();
_rolesmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("rolelist"))));
 //BA.debugLineNum = 112;BA.debugLine="Dim procesosMap As Map = paramMap.Get(\"process";
_procesosmap = new anywheresoftware.b4a.objects.collections.Map();
_procesosmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("processlist"))));
 //BA.debugLineNum = 113;BA.debugLine="Dim clientesMap As Map = paramMap.Get(\"clientl";
_clientesmap = new anywheresoftware.b4a.objects.collections.Map();
_clientesmap.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_parammap.Get((Object)("clientlist"))));
 } 
       catch (Exception e36) {
			ba.setLastException(e36); //BA.debugLineNum = 115;BA.debugLine="Log(\"Maps Error: \" & LastException)";
__c.LogImpl("51048620","Maps Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 //BA.debugLineNum = 118;BA.debugLine="Select event";
switch (BA.switchObjectToInt(_event,"dashboard","usuario","unidad","proceso")) {
case 0: {
 //BA.debugLineNum = 120;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"userlis";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"userlist",(Object)(_usuariosmap));
 //BA.debugLineNum = 121;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"unitlis";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"unitlist",(Object)(_unidadesmap));
 //BA.debugLineNum = 122;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"rolelis";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"rolelist",(Object)(_rolesmap));
 //BA.debugLineNum = 123;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"process";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"processlist",(Object)(_procesosmap));
 break; }
case 1: {
 //BA.debugLineNum = 130;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"rolelis";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"rolelist",(Object)(_rolesmap));
 break; }
case 2: {
 //BA.debugLineNum = 132;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"process";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"processlist",(Object)(_procesosmap));
 break; }
case 3: {
 //BA.debugLineNum = 134;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"clientl";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"clientlist",(Object)(_clientesmap));
 //BA.debugLineNum = 135;BA.debugLine="CallSub2(Callback, EventName & \"_\" & \"userlis";
__c.CallSubNew2(ba,_vv5,_vv6+"_"+"userlist",(Object)(_usuariosmap));
 break; }
}
;
 };
 } 
       catch (Exception e54) {
			ba.setLastException(e54); //BA.debugLineNum = 140;BA.debugLine="Log(\"TextMessage Error: \" & LastException)";
__c.LogImpl("51048645","TextMessage Error: "+BA.ObjectToString(__c.LastException(getActivityBA())),0);
 };
 //BA.debugLineNum = 142;BA.debugLine="End Sub";
return "";
}
public Object callSub(String sub, Object sender, Object[] args) throws Exception {
BA.senderHolder.set(sender);
return BA.SubDelegator.SubNotFound;
}
}
