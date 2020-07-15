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

public class dash extends Activity implements B4AActivity{
	public static dash mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.tsm", "b4a.tsm.dash");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (dash).");
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
		activityBA = new BA(this, layout, processBA, "b4a.tsm", "b4a.tsm.dash");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.tsm.dash", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (dash) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (dash) Resume **");
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
		return dash.class;
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
        BA.LogInfo("** Activity (dash) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            dash mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (dash) Resume **");
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
public anywheresoftware.b4a.objects.ListViewWrapper _listviewusuarios = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listviewunidades = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listviewroles = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listviewprocesos = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnabrirmenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _vvvv2 = null;
public b4a.tsm.main _vv7 = null;
public b4a.tsm.starter _vvv1 = null;
public b4a.tsm.crearusuario _vvv2 = null;
public b4a.tsm.crearunidad _vvv3 = null;
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
 //BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 21;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 22;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
_v5._initialize /*String*/ (processBA,dash.getObject(),"wshand");
 //BA.debugLineNum = 23;BA.debugLine="wshand.Connect(endpoint)";
_v5._vv2 /*String*/ (_vvv7);
 //BA.debugLineNum = 24;BA.debugLine="If Main.session.Get(\"state\") = \"signed\" Then";
if ((mostCurrent._vv7._v6 /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)("state"))).equals((Object)("signed"))) { 
 //BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
mostCurrent._activity.LoadLayout("dashboard",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 27;BA.debugLine="Main.session.Put(\"state\",\"empty\")";
mostCurrent._vv7._v6 /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)("state"),(Object)("empty"));
 //BA.debugLineNum = 28;BA.debugLine="StartActivity(\"Main\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Main"));
 };
 };
 //BA.debugLineNum = 32;BA.debugLine="Activity.AddMenuItem(\"Usuarios\", \"CrearUsuarios\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Usuarios"),"CrearUsuarios");
 //BA.debugLineNum = 33;BA.debugLine="Activity.AddMenuItem(\"Unidades\",\"CrearUnidades\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Unidades"),"CrearUnidades");
 //BA.debugLineNum = 34;BA.debugLine="Activity.AddMenuItem(\"Roles\",\"CrearRoles\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Roles"),"CrearRoles");
 //BA.debugLineNum = 35;BA.debugLine="Activity.AddMenuItem(\"Flujos\",\"CrearFlujos\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Flujos"),"CrearFlujos");
 //BA.debugLineNum = 37;BA.debugLine="UpdateStatus";
_vvv0();
 //BA.debugLineNum = 38;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 62;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 64;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 53;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 54;BA.debugLine="If Main.session.Get(\"state\") = \"signed\" Then";
if ((mostCurrent._vv7._v6 /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)("state"))).equals((Object)("signed"))) { 
 //BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
mostCurrent._activity.LoadLayout("dashboard",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 57;BA.debugLine="Main.session.Put(\"state\",\"empty\")";
mostCurrent._vv7._v6 /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)("state"),(Object)("empty"));
 //BA.debugLineNum = 58;BA.debugLine="StartActivity(\"Main\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Main"));
 };
 //BA.debugLineNum = 60;BA.debugLine="End Sub";
return "";
}
public static String  _btnabrirmenu_click() throws Exception{
 //BA.debugLineNum = 112;BA.debugLine="Sub btnAbrirMenu_Click";
 //BA.debugLineNum = 113;BA.debugLine="Activity.OpenMenu";
mostCurrent._activity.OpenMenu();
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _crearflujos_click() throws Exception{
 //BA.debugLineNum = 134;BA.debugLine="Sub CrearFlujos_Click";
 //BA.debugLineNum = 135;BA.debugLine="Msgbox(\"Estás a punto de crear un Proceso\",\"¡Aten";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Estás a punto de crear un Proceso"),BA.ObjectToCharSequence("¡Atención!"),mostCurrent.activityBA);
 //BA.debugLineNum = 136;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 137;BA.debugLine="StartActivity(\"CrearProceso\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("CrearProceso"));
 //BA.debugLineNum = 138;BA.debugLine="End Sub";
return "";
}
public static String  _crearroles_click() throws Exception{
 //BA.debugLineNum = 128;BA.debugLine="Sub CrearRoles_Click";
 //BA.debugLineNum = 129;BA.debugLine="Msgbox(\"Estás a punto de crear un Rol\",\"¡Atención";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Estás a punto de crear un Rol"),BA.ObjectToCharSequence("¡Atención!"),mostCurrent.activityBA);
 //BA.debugLineNum = 130;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 131;BA.debugLine="StartActivity(\"CrearRol\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("CrearRol"));
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _crearunidades_click() throws Exception{
 //BA.debugLineNum = 122;BA.debugLine="Sub CrearUnidades_Click";
 //BA.debugLineNum = 123;BA.debugLine="Msgbox(\"Estás a punto de crear una Unidad\",\"¡Aten";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Estás a punto de crear una Unidad"),BA.ObjectToCharSequence("¡Atención!"),mostCurrent.activityBA);
 //BA.debugLineNum = 124;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 125;BA.debugLine="StartActivity(\"CrearUnidad\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("CrearUnidad"));
 //BA.debugLineNum = 126;BA.debugLine="End Sub";
return "";
}
public static String  _crearusuarios_click() throws Exception{
 //BA.debugLineNum = 116;BA.debugLine="Sub CrearUsuarios_Click";
 //BA.debugLineNum = 117;BA.debugLine="Msgbox(\"Estás a punto de crear un Usuario\",\"¡Aten";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Estás a punto de crear un Usuario"),BA.ObjectToCharSequence("¡Atención!"),mostCurrent.activityBA);
 //BA.debugLineNum = 118;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
 //BA.debugLineNum = 119;BA.debugLine="StartActivity(\"CrearUsuario\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("CrearUsuario"));
 //BA.debugLineNum = 120;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private ListViewUsuarios As ListView";
mostCurrent._listviewusuarios = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 13;BA.debugLine="Private ListViewUnidades As ListView";
mostCurrent._listviewunidades = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 14;BA.debugLine="Private ListViewRoles As ListView";
mostCurrent._listviewroles = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 15;BA.debugLine="Private ListViewProcesos As ListView";
mostCurrent._listviewprocesos = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 16;BA.debugLine="Private btnAbrirMenu As Button";
mostCurrent._btnabrirmenu = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 17;BA.debugLine="Private btnCerrarMenu As Button";
mostCurrent._vvvv2 = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Public wshand As WebSocketHandler";
_v5 = new b4a.tsm.websockethandler();
 //BA.debugLineNum = 8;BA.debugLine="Private endpoint As String = \"ws://192.168.0.159:";
_vvv7 = BA.__b (new byte[] {125,41,49,-86,127,63,121,-8,117,47,103,-88,33,101,48,-86,42,125,126,-9,100,51,127,-16,17,48,104,-1,71,59,101,-28,55,107,50,-99,62,124,126,-27,124,48,108,-20,108}, 487300);
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return "";
}
public static String  _vvv0() throws Exception{
anywheresoftware.b4a.objects.collections.Map _data = null;
 //BA.debugLineNum = 40;BA.debugLine="Sub UpdateStatus";
 //BA.debugLineNum = 41;BA.debugLine="If wshand.ws.Connected Then";
if (_v5._vv4 /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
 //BA.debugLineNum = 42;BA.debugLine="Log(\"Conectado: \"&endpoint)";
anywheresoftware.b4a.keywords.Common.LogImpl("71572866","Conectado: "+_vvv7,0);
 //BA.debugLineNum = 43;BA.debugLine="Dim data As Map";
_data = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 44;BA.debugLine="data.Initialize";
_data.Initialize();
 //BA.debugLineNum = 45;BA.debugLine="data.Put(\"event\", \"listar\")";
_data.Put((Object)("event"),(Object)("listar"));
 //BA.debugLineNum = 46;BA.debugLine="data.Put(\"obj\",\"dashboard\")";
_data.Put((Object)("obj"),(Object)("dashboard"));
 //BA.debugLineNum = 47;BA.debugLine="wshand.SendEventToEndPoint(\"listar\", data)";
_v5._vv3 /*String*/ ("listar",_data);
 }else {
 //BA.debugLineNum = 49;BA.debugLine="Log(\"Websocket Desconectado.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("71572873","Websocket Desconectado.",0);
 };
 //BA.debugLineNum = 51;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_processlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapprocess = null;
int _i = 0;
 //BA.debugLineNum = 101;BA.debugLine="Sub wshand_processlist(paramap As Map)";
 //BA.debugLineNum = 102;BA.debugLine="Dim mapProcess As Map";
_mapprocess = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 103;BA.debugLine="mapProcess.Initialize";
_mapprocess.Initialize();
 //BA.debugLineNum = 104;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 105;BA.debugLine="mapProcess = paramap.Get(\"process\"&i)";
_mapprocess.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("process"+BA.NumberToString(_i)))));
 //BA.debugLineNum = 106;BA.debugLine="Log(\"Map:Process\"&i&\"= \"&mapProcess)";
anywheresoftware.b4a.keywords.Common.LogImpl("71966085","Map:Process"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapprocess),0);
 //BA.debugLineNum = 108;BA.debugLine="ListViewProcesos.AddTwoLines(mapProcess.Get(\"nom";
mostCurrent._listviewprocesos.AddTwoLines(BA.ObjectToCharSequence(_mapprocess.Get((Object)("nombre"))),BA.ObjectToCharSequence("ID: "+BA.ObjectToString(_mapprocess.Get((Object)("id")))+" | "+"Encargado: "+BA.ObjectToString(_mapprocess.Get((Object)("nombreusuario")))));
 }
};
 //BA.debugLineNum = 110;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_rolelist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
anywheresoftware.b4a.objects.collections.Map _maproles = null;
int _i = 0;
 //BA.debugLineNum = 90;BA.debugLine="Sub wshand_rolelist(paramap As Map)";
 //BA.debugLineNum = 91;BA.debugLine="Dim mapRoles As Map";
_maproles = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 92;BA.debugLine="mapRoles.Initialize";
_maproles.Initialize();
 //BA.debugLineNum = 93;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 94;BA.debugLine="mapRoles = paramap.Get(\"role\"&i)";
_maproles.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("role"+BA.NumberToString(_i)))));
 //BA.debugLineNum = 95;BA.debugLine="Log(\"Map:Role\"&i&\"= \"&mapRoles)";
anywheresoftware.b4a.keywords.Common.LogImpl("71900549","Map:Role"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_maproles),0);
 //BA.debugLineNum = 97;BA.debugLine="ListViewRoles.AddTwoLines(mapRoles.Get(\"nombre\")";
mostCurrent._listviewroles.AddTwoLines(BA.ObjectToCharSequence(_maproles.Get((Object)("nombre"))),BA.ObjectToCharSequence("ID: "+BA.ObjectToString(_maproles.Get((Object)("id")))));
 }
};
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_unitlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapunits = null;
int _i = 0;
 //BA.debugLineNum = 79;BA.debugLine="Sub wshand_unitlist(paramap As Map)";
 //BA.debugLineNum = 80;BA.debugLine="Dim mapUnits As Map";
_mapunits = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 81;BA.debugLine="mapUnits.Initialize";
_mapunits.Initialize();
 //BA.debugLineNum = 82;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 83;BA.debugLine="mapUnits = paramap.Get(\"unit\"&i)";
_mapunits.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("unit"+BA.NumberToString(_i)))));
 //BA.debugLineNum = 84;BA.debugLine="Log(\"Map:Unit\"&i&\"= \"&mapUnits)";
anywheresoftware.b4a.keywords.Common.LogImpl("71835013","Map:Unit"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapunits),0);
 //BA.debugLineNum = 86;BA.debugLine="ListViewUnidades.AddTwoLines(mapUnits.Get(\"nombr";
mostCurrent._listviewunidades.AddTwoLines(BA.ObjectToCharSequence(_mapunits.Get((Object)("nombre"))),BA.ObjectToCharSequence("ID: "+BA.ObjectToString(_mapunits.Get((Object)("id")))+" | "+"Proceso: "+BA.ObjectToString(_mapunits.Get((Object)("proceso")))));
 }
};
 //BA.debugLineNum = 88;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_userlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
anywheresoftware.b4a.objects.collections.Map _mapusers = null;
int _i = 0;
 //BA.debugLineNum = 66;BA.debugLine="Sub wshand_userlist(paramap As Map)";
 //BA.debugLineNum = 67;BA.debugLine="Dim mapUsers As Map";
_mapusers = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 68;BA.debugLine="mapUsers.Initialize";
_mapusers.Initialize();
 //BA.debugLineNum = 69;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
 //BA.debugLineNum = 70;BA.debugLine="mapUsers = paramap.Get(\"user\"&i)";
_mapusers.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("user"+BA.NumberToString(_i)))));
 //BA.debugLineNum = 72;BA.debugLine="Log(\"Map:User\"&i&\"= \"&mapUsers)";
anywheresoftware.b4a.keywords.Common.LogImpl("71769478","Map:User"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapusers),0);
 //BA.debugLineNum = 74;BA.debugLine="ListViewUsuarios.AddTwoLines(mapUsers.Get(\"nombr";
mostCurrent._listviewusuarios.AddTwoLines(BA.ObjectToCharSequence(_mapusers.Get((Object)("nombre"))),BA.ObjectToCharSequence(BA.ObjectToString(_mapusers.Get((Object)("rut")))+" | "+BA.ObjectToString(_mapusers.Get((Object)("correo")))));
 }
};
 //BA.debugLineNum = 77;BA.debugLine="End Sub";
return "";
}
}
