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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.tsm", "b4a.tsm.main");
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



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
vis = vis | (dash.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

 {
            Activity __a = null;
            if (dash.previousOne != null) {
				__a = dash.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(dash.mostCurrent == null ? null : dash.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static b4a.tsm.websockethandler _wshand = null;
public static String _endpoint = "";
public static anywheresoftware.b4a.objects.collections.Map _session = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblservertime = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblstatus = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnconectar = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtnombre = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtpass = null;
public b4a.tsm.dash _dash = null;
public b4a.tsm.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
_wshand._initialize /*String*/ (null,processBA,main.getObject(),"wshand");
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="session.Initialize";
_session.Initialize();
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="session.Put(\"state\",\"init\")";
_session.Put((Object)("state"),(Object)("init"));
RDebugUtils.currentLine=131077;
 //BA.debugLineNum = 131077;BA.debugLine="Activity.LoadLayout(\"login\")";
mostCurrent._activity.LoadLayout("login",mostCurrent.activityBA);
RDebugUtils.currentLine=131078;
 //BA.debugLineNum = 131078;BA.debugLine="wshand.Connect(endpoint)";
_wshand._connect /*String*/ (null,_endpoint);
RDebugUtils.currentLine=131079;
 //BA.debugLineNum = 131079;BA.debugLine="lblStatus.Text = \"Status: Conectando...\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Conectando..."));
 }else {
RDebugUtils.currentLine=131081;
 //BA.debugLineNum = 131081;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
mostCurrent._activity.LoadLayout("dashboard",mostCurrent.activityBA);
 };
RDebugUtils.currentLine=131083;
 //BA.debugLineNum = 131083;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="If UserClosed = True Then";
if (_userclosed==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="session = Null";
_session.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(anywheresoftware.b4a.keywords.Common.Null));
 }else {
RDebugUtils.currentLine=262148;
 //BA.debugLineNum = 262148;BA.debugLine="Log(\"Sesion: \"&session.Values)";
anywheresoftware.b4a.keywords.Common.LogImpl("7262148","Sesion: "+BA.ObjectToString(_session.Values()),0);
 };
RDebugUtils.currentLine=262150;
 //BA.debugLineNum = 262150;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196612;
 //BA.debugLineNum = 196612;BA.debugLine="UpdateStatus";
_updatestatus();
RDebugUtils.currentLine=196613;
 //BA.debugLineNum = 196613;BA.debugLine="End Sub";
return "";
}
public static String  _updatestatus() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatestatus", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatestatus", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub UpdateStatus";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="If wshand.ws.Connected Then";
if (_wshand._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="Log(\"Conectado: \"&endpoint)";
anywheresoftware.b4a.keywords.Common.LogImpl("7917506","Conectado: "+_endpoint,0);
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="lblStatus.Text = \"Status: Conectado\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Conectado"));
 }else {
RDebugUtils.currentLine=917509;
 //BA.debugLineNum = 917509;BA.debugLine="lblStatus.Text = \"Status: Desconectado\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Desconectado"));
 };
RDebugUtils.currentLine=917511;
 //BA.debugLineNum = 917511;BA.debugLine="btnConectar.Enabled = Not(wshand.ws.Connected)";
mostCurrent._btnconectar.setEnabled(anywheresoftware.b4a.keywords.Common.Not(_wshand._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()));
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="btnConectar.Enabled = wshand.ws.Connected";
mostCurrent._btnconectar.setEnabled(_wshand._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected());
RDebugUtils.currentLine=917513;
 //BA.debugLineNum = 917513;BA.debugLine="End Sub";
return "";
}
public static String  _btnconectar_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnconectar_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnconectar_click", null));}
anywheresoftware.b4a.objects.collections.Map _data = null;
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub btnConectar_Click";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="lblStatus.Text = \"Status: Consultando...\"";
mostCurrent._lblstatus.setText(BA.ObjectToCharSequence("Status: Consultando..."));
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="Dim data As Map";
_data = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=983043;
 //BA.debugLineNum = 983043;BA.debugLine="data.Initialize";
_data.Initialize();
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="data.Put(\"user\", txtNombre.Text)";
_data.Put((Object)("user"),(Object)(mostCurrent._txtnombre.getText()));
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="data.Put(\"hash\", txtPass.Text)";
_data.Put((Object)("hash"),(Object)(mostCurrent._txtpass.getText()));
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="wshand.SendEventToEndPoint(\"Ingresar_movil\", data";
_wshand._sendeventtoendpoint /*String*/ (null,"Ingresar_movil",_data);
RDebugUtils.currentLine=983047;
 //BA.debugLineNum = 983047;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_closed(String _reason) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_closed", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_closed", new Object[] {_reason}));}
RDebugUtils.currentLine=851968;
 //BA.debugLineNum = 851968;BA.debugLine="Sub wshand_Closed(Reason As String)";
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="UpdateStatus";
_updatestatus();
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="ToastMessageShow(Reason, True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence(_reason),anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_connected() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_connected", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_connected", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Sub wshand_Connected";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="UpdateStatus";
_updatestatus();
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_runfunction(String _param) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_runfunction", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_runfunction", new Object[] {_param}));}
RDebugUtils.currentLine=4849664;
 //BA.debugLineNum = 4849664;BA.debugLine="Sub wshand_runFunction(Param As String)";
RDebugUtils.currentLine=4849665;
 //BA.debugLineNum = 4849665;BA.debugLine="If(Param = \"logued\") Then";
if (((_param).equals("logued"))) { 
RDebugUtils.currentLine=4849666;
 //BA.debugLineNum = 4849666;BA.debugLine="session.Put(\"state\",\"signed\")";
_session.Put((Object)("state"),(Object)("signed"));
RDebugUtils.currentLine=4849667;
 //BA.debugLineNum = 4849667;BA.debugLine="session.Put(\"username\",txtNombre.Text)";
_session.Put((Object)("username"),(Object)(mostCurrent._txtnombre.getText()));
RDebugUtils.currentLine=4849668;
 //BA.debugLineNum = 4849668;BA.debugLine="StartActivity(\"Dash\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Dash"));
RDebugUtils.currentLine=4849669;
 //BA.debugLineNum = 4849669;BA.debugLine="Log(session.Values)";
anywheresoftware.b4a.keywords.Common.LogImpl("74849669",BA.ObjectToString(_session.Values()),0);
 };
RDebugUtils.currentLine=4849671;
 //BA.debugLineNum = 4849671;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_servertime(anywheresoftware.b4a.objects.collections.List _params) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_servertime", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_servertime", new Object[] {_params}));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub wshand_ServerTime(Params As List)";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="lblServerTime.Text = \"ServerTime: \" & Params.Get(";
mostCurrent._lblservertime.setText(BA.ObjectToCharSequence("ServerTime: "+BA.ObjectToString(_params.Get((int) (0)))));
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="End Sub";
return "";
}
}