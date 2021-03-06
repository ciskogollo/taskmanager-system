package b4a.tsm;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class crearunidad extends Activity implements B4AActivity{
	public static crearunidad mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.tsm", "b4a.tsm.crearunidad");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (crearunidad).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.tsm", "b4a.tsm.crearunidad");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.tsm.crearunidad", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (crearunidad) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (crearunidad) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return crearunidad.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (crearunidad) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            crearunidad mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (crearunidad) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static b4a.tsm.websockethandler _v5 = null;
public static String _vvv7 = "";
public anywheresoftware.b4a.objects.ButtonWrapper _btnenviar = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnatras = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinprocesos = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtnombretipo = null;
public b4a.tsm.main _vv7 = null;
public b4a.tsm.dash _vv0 = null;
public b4a.tsm.starter _vvv1 = null;
public b4a.tsm.crearusuario _vvv2 = null;
public b4a.tsm.crearrol _vvv4 = null;
public b4a.tsm.crearproceso _vvv5 = null;

public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 19;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 20;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
_v5._initialize /*String*/ (processBA,crearunidad.getObject(),"wshand");
 //BA.debugLineNum = 21;BA.debugLine="wshand.Connect(endpoint)";
_v5._vv2 /*String*/ (_vvv7);
 //BA.debugLineNum = 22;BA.debugLine="If Main.session.Get(\"state\") = \"signed\" Then";
if ((mostCurrent._vv7._v6 /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)("state"))).equals((Object)("signed"))) { 
 //BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"create_unit\")";
mostCurrent._activity.LoadLayout("create_unit",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 25;BA.debugLine="Main.session.Put(\"state\",\"empty\")";
mostCurrent._vv7._v6 /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)("state"),(Object)("empty"));
 //BA.debugLineNum = 26;BA.debugLine="StartActivity(\"Main\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Main"));
 };
 };
 //BA.debugLineNum = 30;BA.debugLine="UpdateData";
_vvvv3();
 //BA.debugLineNum = 31;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 46;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _btnatras_click() throws Exception{
 //BA.debugLineNum = 54;BA.debugLine="Sub btnAtras_Click";
 //BA.debugLineNum = 55;BA.debugLine="StartActivity(\"Dash\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Dash"));
 //BA.debugLineNum = 56;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private btnEnviar As Button";
mostCurrent._btnenviar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private btnAtras As Button";
mostCurrent._btnatras = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private spinProcesos As Spinner";
mostCurrent._spinprocesos = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private txtNombreTipo As EditText";
mostCurrent._txtnombretipo = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public wshand As WebSocketHandler";
_v5 = new b4a.tsm.websockethandler();
 //BA.debugLineNum = 8;BA.debugLine="Private endpoint As String = \"ws://192.168.0.159:";
_vvv7 = BA.__b (new byte[] {125,41,-43,-37,127,63,-99,-119,117,47,-125,-39,33,101,-44,-37,42,125,-102,-122,100,51,-101,-127,17,48,-116,-114,71,59,-127,-107,55,107,-42,-20,62,124,-102,-108,124,48,-120,-99,108}, 881439);
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _vvvv3() throws Exception{
anywheresoftware.b4a.objects.collections.Map _data = null;
 //BA.debugLineNum = 33;BA.debugLine="Sub UpdateData";
 //BA.debugLineNum = 34;BA.debugLine="If wshand.ws.Connected Then";
if (_v5._vv4 /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
 //BA.debugLineNum = 35;BA.debugLine="Log(\"Conectado: \"&endpoint)";
anywheresoftware.b4a.keywords.Common.LogImpl("74718594","Conectado: "+_vvv7,0);
 //BA.debugLineNum = 36;BA.debugLine="Dim data As Map";
_data = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 37;BA.debugLine="data.Initialize";
_data.Initialize();
 //BA.debugLineNum = 38;BA.debugLine="data.Put(\"event\", \"listar\")";
_data.Put((Object)("event"),(Object)("listar"));
 //BA.debugLineNum = 39;BA.debugLine="data.Put(\"obj\",\"unidad\")";
_data.Put((Object)("obj"),(Object)("unidad"));
 //BA.debugLineNum = 40;BA.debugLine="wshand.SendEventToEndPoint(\"listar\", data)";
_v5._vv3 /*String*/ ("listar",_data);
 }else {
 //BA.debugLineNum = 42;BA.debugLine="Log(\"Websocket Desconectado.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("74718601","Websocket Desconectado.",0);
 };
 //BA.debugLineNum = 44;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_processlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapprocess = null;
int _i = 0;
 //BA.debugLineNum = 58;BA.debugLine="Sub wshand_processlist(paramap As Map)";
 //BA.debugLineNum = 59;BA.debugLine="Dim mapProcess As Map";
_mapprocess = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 60;BA.debugLine="mapProcess.Initialize";
_mapprocess.Initialize();
 //BA.debugLineNum = 61;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 62;BA.debugLine="mapProcess = paramap.Get(\"process\"&i)";
_mapprocess.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("process"+BA.NumberToString(_i)))));
 //BA.debugLineNum = 63;BA.debugLine="Log(\"Map:Process\"&i&\"= \"&mapProcess)";
anywheresoftware.b4a.keywords.Common.LogImpl("74980741","Map:Process"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapprocess),0);
 //BA.debugLineNum = 65;BA.debugLine="spinProcesos.Add(mapProcess.Get(\"nombre\"))";
mostCurrent._spinprocesos.Add(BA.ObjectToString(_mapprocess.Get((Object)("nombre"))));
 }
};
 //BA.debugLineNum = 67;BA.debugLine="End Sub";
return "";
}
}
