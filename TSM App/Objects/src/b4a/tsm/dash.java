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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.tsm", "b4a.tsm.dash");
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



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static b4a.tsm.websockethandler _wshand = null;
public static String _endpoint = "";
public anywheresoftware.b4a.objects.ListViewWrapper _listviewusuarios = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listviewunidades = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listviewroles = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listviewprocesos = null;
public b4a.tsm.main _main = null;
public b4a.tsm.starter _starter = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=1376256;
 //BA.debugLineNum = 1376256;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=1376257;
 //BA.debugLineNum = 1376257;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
RDebugUtils.currentLine=1376258;
 //BA.debugLineNum = 1376258;BA.debugLine="wshand.Initialize(Me, \"wshand\")";
_wshand._initialize /*String*/ (null,processBA,dash.getObject(),"wshand");
RDebugUtils.currentLine=1376259;
 //BA.debugLineNum = 1376259;BA.debugLine="wshand.Connect(endpoint)";
_wshand._connect /*String*/ (null,_endpoint);
RDebugUtils.currentLine=1376260;
 //BA.debugLineNum = 1376260;BA.debugLine="If Main.session.Get(\"state\") = \"signed\" Then";
if ((mostCurrent._main._session /*anywheresoftware.b4a.objects.collections.Map*/ .Get((Object)("state"))).equals((Object)("signed"))) { 
RDebugUtils.currentLine=1376261;
 //BA.debugLineNum = 1376261;BA.debugLine="Activity.LoadLayout(\"dashboard\")";
mostCurrent._activity.LoadLayout("dashboard",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=1376263;
 //BA.debugLineNum = 1376263;BA.debugLine="Main.session.Put(\"state\",\"empty\")";
mostCurrent._main._session /*anywheresoftware.b4a.objects.collections.Map*/ .Put((Object)("state"),(Object)("empty"));
RDebugUtils.currentLine=1376264;
 //BA.debugLineNum = 1376264;BA.debugLine="StartActivity(\"Main\")";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)("Main"));
 };
 };
RDebugUtils.currentLine=1376268;
 //BA.debugLineNum = 1376268;BA.debugLine="Activity.AddMenuItem(\"Usuarios\",\"Usuarios\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Usuarios"),"Usuarios");
RDebugUtils.currentLine=1376269;
 //BA.debugLineNum = 1376269;BA.debugLine="Activity.AddMenuItem(\"Unidades\",\"Unidades\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Unidades"),"Unidades");
RDebugUtils.currentLine=1376270;
 //BA.debugLineNum = 1376270;BA.debugLine="Activity.AddMenuItem(\"Roles\",\"Roles\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Roles"),"Roles");
RDebugUtils.currentLine=1376271;
 //BA.debugLineNum = 1376271;BA.debugLine="Activity.AddMenuItem(\"Flujos\",\"Flujos\")";
mostCurrent._activity.AddMenuItem(BA.ObjectToCharSequence("Flujos"),"Flujos");
RDebugUtils.currentLine=1376272;
 //BA.debugLineNum = 1376272;BA.debugLine="UpdateStatus";
_updatestatus();
RDebugUtils.currentLine=1376273;
 //BA.debugLineNum = 1376273;BA.debugLine="End Sub";
return "";
}
public static String  _updatestatus() throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "updatestatus", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "updatestatus", null));}
anywheresoftware.b4a.objects.collections.Map _data = null;
RDebugUtils.currentLine=1441792;
 //BA.debugLineNum = 1441792;BA.debugLine="Sub UpdateStatus";
RDebugUtils.currentLine=1441793;
 //BA.debugLineNum = 1441793;BA.debugLine="If wshand.ws.Connected Then";
if (_wshand._ws /*anywheresoftware.b4a.objects.WebSocketWrapper*/ .getConnected()) { 
RDebugUtils.currentLine=1441794;
 //BA.debugLineNum = 1441794;BA.debugLine="Log(\"Conectado: \"&endpoint)";
anywheresoftware.b4a.keywords.Common.LogImpl("11441794","Conectado: "+_endpoint,0);
RDebugUtils.currentLine=1441795;
 //BA.debugLineNum = 1441795;BA.debugLine="Dim data As Map";
_data = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1441796;
 //BA.debugLineNum = 1441796;BA.debugLine="data.Initialize";
_data.Initialize();
RDebugUtils.currentLine=1441797;
 //BA.debugLineNum = 1441797;BA.debugLine="data.Put(\"event\", \"listar\")";
_data.Put((Object)("event"),(Object)("listar"));
RDebugUtils.currentLine=1441798;
 //BA.debugLineNum = 1441798;BA.debugLine="data.Put(\"obj\",\"dashboard\")";
_data.Put((Object)("obj"),(Object)("dashboard"));
RDebugUtils.currentLine=1441799;
 //BA.debugLineNum = 1441799;BA.debugLine="wshand.SendEventToEndPoint(\"listar\", data)";
_wshand._sendeventtoendpoint /*String*/ (null,"listar",_data);
 }else {
RDebugUtils.currentLine=1441801;
 //BA.debugLineNum = 1441801;BA.debugLine="Log(\"Websocket Desconectado.\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11441801","Websocket Desconectado.",0);
 };
RDebugUtils.currentLine=1441803;
 //BA.debugLineNum = 1441803;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="dash";
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_processlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_processlist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_processlist", new Object[] {_paramap}));}
anywheresoftware.b4a.objects.collections.Map _mapprocess = null;
int _i = 0;
RDebugUtils.currentLine=2555904;
 //BA.debugLineNum = 2555904;BA.debugLine="Sub wshand_processlist(paramap As Map)";
RDebugUtils.currentLine=2555905;
 //BA.debugLineNum = 2555905;BA.debugLine="Dim mapProcess As Map";
_mapprocess = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=2555906;
 //BA.debugLineNum = 2555906;BA.debugLine="mapProcess.Initialize";
_mapprocess.Initialize();
RDebugUtils.currentLine=2555907;
 //BA.debugLineNum = 2555907;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=2555908;
 //BA.debugLineNum = 2555908;BA.debugLine="mapProcess = paramap.Get(\"process\"&i)";
_mapprocess.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("process"+BA.NumberToString(_i)))));
RDebugUtils.currentLine=2555909;
 //BA.debugLineNum = 2555909;BA.debugLine="Log(\"Map:Process\"&i&\"= \"&mapProcess)";
anywheresoftware.b4a.keywords.Common.LogImpl("12555909","Map:Process"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapprocess),0);
RDebugUtils.currentLine=2555911;
 //BA.debugLineNum = 2555911;BA.debugLine="ListViewProcesos.AddTwoLines(mapProcess.Get(\"nom";
mostCurrent._listviewprocesos.AddTwoLines(BA.ObjectToCharSequence(_mapprocess.Get((Object)("nombre"))),BA.ObjectToCharSequence("ID: "+BA.ObjectToString(_mapprocess.Get((Object)("id")))+" | "+"Proceso: "+BA.ObjectToString(_mapprocess.Get((Object)("nombreusuario")))));
 }
};
RDebugUtils.currentLine=2555913;
 //BA.debugLineNum = 2555913;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_rolelist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_rolelist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_rolelist", new Object[] {_paramap}));}
anywheresoftware.b4a.objects.collections.Map _maproles = null;
int _i = 0;
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Sub wshand_rolelist(paramap As Map)";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="Dim mapRoles As Map";
_maproles = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=2490370;
 //BA.debugLineNum = 2490370;BA.debugLine="mapRoles.Initialize";
_maproles.Initialize();
RDebugUtils.currentLine=2490371;
 //BA.debugLineNum = 2490371;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=2490372;
 //BA.debugLineNum = 2490372;BA.debugLine="mapRoles = paramap.Get(\"role\"&i)";
_maproles.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("role"+BA.NumberToString(_i)))));
RDebugUtils.currentLine=2490373;
 //BA.debugLineNum = 2490373;BA.debugLine="Log(\"Map:Role\"&i&\"= \"&mapRoles)";
anywheresoftware.b4a.keywords.Common.LogImpl("12490373","Map:Role"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_maproles),0);
RDebugUtils.currentLine=2490375;
 //BA.debugLineNum = 2490375;BA.debugLine="ListViewRoles.AddTwoLines(mapRoles.Get(\"nombre\")";
mostCurrent._listviewroles.AddTwoLines(BA.ObjectToCharSequence(_maproles.Get((Object)("nombre"))),BA.ObjectToCharSequence("ID: "+BA.ObjectToString(_maproles.Get((Object)("id")))));
 }
};
RDebugUtils.currentLine=2490377;
 //BA.debugLineNum = 2490377;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_unitlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_unitlist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_unitlist", new Object[] {_paramap}));}
anywheresoftware.b4a.objects.collections.Map _mapunits = null;
int _i = 0;
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Sub wshand_unitlist(paramap As Map)";
RDebugUtils.currentLine=2424833;
 //BA.debugLineNum = 2424833;BA.debugLine="Dim mapUnits As Map";
_mapunits = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=2424834;
 //BA.debugLineNum = 2424834;BA.debugLine="mapUnits.Initialize";
_mapunits.Initialize();
RDebugUtils.currentLine=2424835;
 //BA.debugLineNum = 2424835;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=2424836;
 //BA.debugLineNum = 2424836;BA.debugLine="mapUnits = paramap.Get(\"unit\"&i)";
_mapunits.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("unit"+BA.NumberToString(_i)))));
RDebugUtils.currentLine=2424837;
 //BA.debugLineNum = 2424837;BA.debugLine="Log(\"Map:Unit\"&i&\"= \"&mapUnits)";
anywheresoftware.b4a.keywords.Common.LogImpl("12424837","Map:Unit"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapunits),0);
RDebugUtils.currentLine=2424839;
 //BA.debugLineNum = 2424839;BA.debugLine="ListViewUnidades.AddTwoLines(mapUnits.Get(\"nombr";
mostCurrent._listviewunidades.AddTwoLines(BA.ObjectToCharSequence(_mapunits.Get((Object)("nombre"))),BA.ObjectToCharSequence("ID: "+BA.ObjectToString(_mapunits.Get((Object)("id")))+" | "+"Proceso: "+BA.ObjectToString(_mapunits.Get((Object)("proceso")))));
 }
};
RDebugUtils.currentLine=2424841;
 //BA.debugLineNum = 2424841;BA.debugLine="End Sub";
return "";
}
public static String  _wshand_userlist(anywheresoftware.b4a.objects.collections.Map _paramap) throws Exception{
RDebugUtils.currentModule="dash";
if (Debug.shouldDelegate(mostCurrent.activityBA, "wshand_userlist", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "wshand_userlist", new Object[] {_paramap}));}
anywheresoftware.b4a.objects.collections.Map _mapusers = null;
int _i = 0;
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub wshand_userlist(paramap As Map)";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="Dim mapUsers As Map";
_mapusers = new anywheresoftware.b4a.objects.collections.Map();
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="mapUsers.Initialize";
_mapusers.Initialize();
RDebugUtils.currentLine=1638403;
 //BA.debugLineNum = 1638403;BA.debugLine="For i = 1 To paramap.Size";
{
final int step3 = 1;
final int limit3 = _paramap.getSize();
_i = (int) (1) ;
for (;_i <= limit3 ;_i = _i + step3 ) {
RDebugUtils.currentLine=1638404;
 //BA.debugLineNum = 1638404;BA.debugLine="mapUsers = paramap.Get(\"user\"&i)";
_mapusers.setObject((anywheresoftware.b4a.objects.collections.Map.MyMap)(_paramap.Get((Object)("user"+BA.NumberToString(_i)))));
RDebugUtils.currentLine=1638406;
 //BA.debugLineNum = 1638406;BA.debugLine="Log(\"Map:User\"&i&\"= \"&mapUsers)";
anywheresoftware.b4a.keywords.Common.LogImpl("11638406","Map:User"+BA.NumberToString(_i)+"= "+BA.ObjectToString(_mapusers),0);
RDebugUtils.currentLine=1638408;
 //BA.debugLineNum = 1638408;BA.debugLine="ListViewUsuarios.AddTwoLines(mapUsers.Get(\"nombr";
mostCurrent._listviewusuarios.AddTwoLines(BA.ObjectToCharSequence(_mapusers.Get((Object)("nombre"))),BA.ObjectToCharSequence(BA.ObjectToString(_mapusers.Get((Object)("rut")))+" | "+BA.ObjectToString(_mapusers.Get((Object)("correo")))));
 }
};
RDebugUtils.currentLine=1638411;
 //BA.debugLineNum = 1638411;BA.debugLine="End Sub";
return "";
}
}