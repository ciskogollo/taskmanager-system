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

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
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
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.tsm", "b4a.tsm.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
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
		activityBA = new BA(this, layout, processBA, "b4a.tsm", "b4a.tsm.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.tsm.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
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
		return main.class;
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
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
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
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
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
public static anywheresoftware.b4a.objects.collections.List _vvv6 = null;
public static String _vvv7 = "";
public static anywheresoftware.b4a.objects.collections.Map _v6 = null;
public static anywheresoftware.b4a.objects.Timer _v7 = null;
public static int _v0 = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lblservertime = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstatus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnconectar = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtnombre = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpass = null;
public static String _vvvv1 = "";
public b4a.tsm.dash _vv0 = null;
public b4a.tsm.starter _vvv1 = null;
public b4a.tsm.crearusuario _vvv2 = null;
public b4a.tsm.crearunidad _vvv3 = null;
public b4a.tsm.crearrol _vvv4 = null;
public b4a.tsm.crearproceso _vvv5 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (dash.mostCurrent != null);
vis = vis | (crearusuario.mostCurrent != null);
vis = vis | (crearunidad.mostCurrent != null);
vis = vis | (crearrol.mostCurrent != null);
vis = vis | (crearproceso.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 34;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 35;BA.debugLine="Ips.Initialize";
_vvv6.Initialize();
 //BA.debugLineNum = 38;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 39;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
_v5._initialize /*String*/ (processBA,main.getObject(),"wshand");
 //BA.debugLineNum = 40;BA.debugLine="session.Initialize";
_v6.Initialize();
 //BA.debugLineNum = 41;BA.debugLine="session.Put(\"state\",\"init\")";
_v6.Put((Object)("state"),(Object)("init"));
 //BA.debugLineNum = 42;BA.debugLine="Activity.LoadLayout(\"login\")";
mostCurrent._activity.LoadLayout("login",mostCurrent.activityBA);
 //BA.debugLineNum = 45;BA.debugLine="Try";
try { //BA.debugLineNum = 46;BA.debugLine="lblStatus.Text = \"Status: Conectando...\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Conectando..."));
 //BA.debugLineNum = 47;BA.debugLine="wshand.Connect(endpoint)";
_v5._vv2 /*String*/ (_vvv7);
 } 
       catch (Exception e11) {
			processBA.setLastException(e11); //BA.debugLineNum = 49;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.LogImpl("7131087",BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)),0);
 };
 }else {
 //BA.debugLineNum = 53;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
mostCurrent._activity.LoadLayout("dashboard",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 55;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 108;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 109;BA.debugLine="If UserClosed = True Then";
if (_userclosed==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 110;BA.debugLine="session = Null";
_v6.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 }else {
 //BA.debugLineNum = 112;BA.debugLine="Log(\"Sesion: \"&session.Values)";
anywheresoftware.b4a.keywords.Common.LogImpl("7655364","Sesion: "+BA.ObjectToString(_v6.Values()),0);
 };
 //BA.debugLineNum = 114;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 101;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 105;BA.debugLine="UpdateStatus";
_vvv0();
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static String  _btnconectar_click() throws Exception{
anywheresoftware.b4a.objects.collections.Map _data = null;
 //BA.debugLineNum = 92;BA.debugLine="Sub btnConectar_Click";
 //BA.debugLineNum = 93;BA.debugLine="lblStatus.Text = \"Status: Consultando...\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Consultando..."));
 //BA.debugLineNum = 94;BA.debugLine="Dim data As Map";
_data = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 95;BA.debugLine="data.Initialize";
_data.Initialize();
 //BA.debugLineNum = 96;BA.debugLine="data.Put(\"user\", txtNombre.Text)";
_data.Put((Object)("user"),(Object)(mostCurrent._txtnombre.getText()));
 //BA.debugLineNum = 97;BA.debugLine="data.Put(\"hash\", txtPass.Text)";
_data.Put((Object)("hash"),(Object)(mostCurrent._txtpass.getText()));
 //BA.debugLineNum = 98;BA.debugLine="wshand.SendEventToEndPoint(\"Ingresar_movil\", data";
_v5._vv3 /*String*/ ("Ingresar_movil",_data);
 //BA.debugLineNum = 99;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 25;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 26;BA.debugLine="Private lblServerTime As Label";
mostCurrent._lblservertime = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private lblStatus As Label";
mostCurrent._lblstatus = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private btnConectar As Button";
mostCurrent._btnconectar = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private txtNombre As EditText";
mostCurrent._txtnombre = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private txtPass As EditText";
mostCurrent._txtpass = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private ipformuled As String";
mostCurrent._vvvv1 = "";
 //BA.debugLineNum = 32;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
dash._process_globals();
starter._process_globals();
crearusuario._process_globals();
crearunidad._process_globals();
crearrol._process_globals();
crearproceso._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 17;BA.debugLine="Private wshand As WebSocketHandler";
_v5 = new b4a.tsm.websockethandler();
 //BA.debugLineNum = 18;BA.debugLine="Private Ips As List";
_vvv6 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 19;BA.debugLine="Private endpoint As String = \"ws://192.168.0.159:";
_vvv7 = BA.__b (new byte[] {125,41,39,15,127,63,111,93,117,47,113,13,33,101,38,15,42,125,104,82,100,51,105,85,17,48,126,90,71,59,115,65,55,107,36,56,62,124,104,89,96,50,97,80,104,55}, 454408);
 //BA.debugLineNum = 20;BA.debugLine="Public session As Map";
_v6 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 21;BA.debugLine="Public timerconnect As Timer";
_v7 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 22;BA.debugLine="Dim countadd As Int = 0";
_v0 = (int) (0);
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return "";
}
public static String  _vvv0() throws Exception{
 //BA.debugLineNum = 81;BA.debugLine="Sub UpdateStatus";
 //BA.debugLineNum = 82;BA.debugLine="If wshand.ws.Connected Then";
if (_v5._vv4 /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
 //BA.debugLineNum = 83;BA.debugLine="Log(\"Conectado: \"&endpoint)";
anywheresoftware.b4a.keywords.Common.LogImpl("7458754","Conectado: "+_vvv7,0);
 //BA.debugLineNum = 84;BA.debugLine="lblStatus.Text = \"Status: Conectado\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Conectado"));
 }else {
 //BA.debugLineNum = 86;BA.debugLine="lblStatus.Text = \"Status: Desconectado\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Desconectado"));
 };
 //BA.debugLineNum = 88;BA.debugLine="btnConectar.Enabled = Not(wshand.ws.Connected)";
mostCurrent._btnconectar.setEnabled(anywheresoftware.b4a.keywords.Common.Not(_v5._vv4 /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()));
 //BA.debugLineNum = 89;BA.debugLine="btnConectar.Enabled = wshand.ws.Connected";
mostCurrent._btnconectar.setEnabled(_v5._vv4 /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected());
 //BA.debugLineNum = 90;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_closed(String _reason) throws Exception{
 //BA.debugLineNum = 65;BA.debugLine="Sub wshand_Closed(Reason As String)";
 //BA.debugLineNum = 66;BA.debugLine="UpdateStatus";
_vvv0();
 //BA.debugLineNum = 67;BA.debugLine="ToastMessageShow(Reason, True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_reason),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_connected() throws Exception{
 //BA.debugLineNum = 61;BA.debugLine="Sub wshand_Connected";
 //BA.debugLineNum = 62;BA.debugLine="UpdateStatus";
_vvv0();
 //BA.debugLineNum = 63;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_runfunction(String _param) throws Exception{
 //BA.debugLineNum = 70;BA.debugLine="Sub wshand_runFunction(Param As String)";
 //BA.debugLineNum = 71;BA.debugLine="If(Param = \"logued\") Then";
if (((_param).equals("logued"))) { 
 //BA.debugLineNum = 72;BA.debugLine="session.Put(\"state\",\"signed\")";
_v6.Put((Object)("state"),(Object)("signed"));
 //BA.debugLineNum = 73;BA.debugLine="session.Put(\"username\",txtNombre.Text)";
_v6.Put((Object)("username"),(Object)(mostCurrent._txtnombre.getText()));
 //BA.debugLineNum = 74;BA.debugLine="StartActivity(\"Dash\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Dash"));
 //BA.debugLineNum = 75;BA.debugLine="Log(session.Values)";
anywheresoftware.b4a.keywords.Common.LogImpl("7393221",BA.ObjectToString(_v6.Values()),0);
 }else if(((_param).equals("user_incorrect"))) { 
 //BA.debugLineNum = 77;BA.debugLine="Log(\"Usuario y/o contraseña incorrectos\")";
anywheresoftware.b4a.keywords.Common.LogImpl("7393223","Usuario y/o contraseña incorrectos",0);
 };
 //BA.debugLineNum = 79;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_servertime(anywheresoftware.b4a.objects.collections.List _params) throws Exception{
 //BA.debugLineNum = 57;BA.debugLine="Sub wshand_ServerTime(Params As List)";
 //BA.debugLineNum = 58;BA.debugLine="lblServerTime.Text = \"ServerTime: \" & Params.Get(";
mostCurrent._lblservertime.setText(BA.ObjectToCharSequence("ServerTime: "+BA.ObjectToString(_params.Get((int) (0)))));
 //BA.debugLineNum = 59;BA.debugLine="End Sub";
return "";
}
}
