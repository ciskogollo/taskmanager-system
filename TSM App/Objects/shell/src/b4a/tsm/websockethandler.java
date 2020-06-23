
package b4a.tsm;

import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RemoteObject;

public class websockethandler {
    public static RemoteObject myClass;
	public websockethandler() {
	}
    public static PCBA staticBA = new PCBA(null, websockethandler.class);

public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _ws = RemoteObject.declareNull("anywheresoftware.b4a.objects.WebSocketWrapper");
public static RemoteObject _callback = RemoteObject.declareNull("Object");
public static RemoteObject _eventname = RemoteObject.createImmutable("");
public static b4a.tsm.main _main = null;
public static b4a.tsm.dash _dash = null;
public static b4a.tsm.starter _starter = null;
public static Object[] GetGlobals(RemoteObject _ref) throws Exception {
		return new Object[] {"Callback",_ref.getField(false, "_callback"),"EventName",_ref.getField(false, "_eventname"),"ws",_ref.getField(false, "_ws")};
}
}