
package b4a.tsm;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class dash implements IRemote{
	public static dash mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public dash() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("dash"), "b4a.tsm.dash");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, dash.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _wshand = RemoteObject.declareNull("b4a.tsm.websockethandler");
public static RemoteObject _endpoint = RemoteObject.createImmutable("");
public static RemoteObject _listviewusuarios = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _listviewunidades = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _listviewroles = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _listviewprocesos = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static b4a.tsm.main _main = null;
public static b4a.tsm.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",dash.mostCurrent._activity,"endpoint",dash._endpoint,"ListViewProcesos",dash.mostCurrent._listviewprocesos,"ListViewRoles",dash.mostCurrent._listviewroles,"ListViewUnidades",dash.mostCurrent._listviewunidades,"ListViewUsuarios",dash.mostCurrent._listviewusuarios,"Main",Debug.moduleToString(b4a.tsm.main.class),"Starter",Debug.moduleToString(b4a.tsm.starter.class),"wshand",dash._wshand};
}
}