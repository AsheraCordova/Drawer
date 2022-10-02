package com.ashera.drawerlayout;
// start - imports
import java.util.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.*;
import android.widget.*;
import android.view.View.*;

import com.ashera.widget.BaseHasWidgets;

import android.annotation.SuppressLint;

import com.ashera.core.IFragment;
import com.ashera.widget.bus.*;
import com.ashera.converter.*;
import com.ashera.widget.bus.Event.*;
import com.ashera.widget.*;
import com.ashera.widget.IWidgetLifeCycleListener.*;
import com.ashera.layout.*;

import android.graphics.Canvas;
import android.widget.*;
import androidx.core.view.*;
import android.view.*;

import static com.ashera.widget.IWidget.*;
//end - imports

import androidx.drawerlayout.widget.DrawerLayout;
import android.graphics.drawable.Drawable;
public class DrawerLayoutImpl extends BaseHasWidgets {
	//start - body
	public final static String LOCAL_NAME = "androidx.drawerlayout.widget.DrawerLayout"; 
	public final static String GROUP_NAME = "androidx.drawerlayout.widget.DrawerLayout";
	private androidx.drawerlayout.widget.DrawerLayout drawerLayout;
	

	
		@SuppressLint("NewApi")
		final static class DrawerLockMode extends AbstractEnumToIntConverter{
		private Map<String, Integer> mapping = new HashMap<>();
				{
				mapping.put("LOCK_MODE_UNLOCKED",  0x0);
				mapping.put("LOCK_MODE_LOCKED_CLOSED",  0x1);
				mapping.put("LOCK_MODE_LOCKED_OPEN",  0x2);
				mapping.put("LOCK_MODE_UNDEFINED",  0x3);
				}
		@Override
		public Map<String, Integer> getMapping() {
				return mapping;
				}

		@Override
		public Integer getDefault() {
				return 0;
				}
				}
	@Override
	public void loadAttributes(String localName) {
		ViewGroupImpl.register(localName);

		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("elevation").withType("dimensionfloat"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("scrimColor").withType("color"));
		ConverterFactory.register("androidx.drawerlayout.widget.DrawerLayout.drawerLockMode", new DrawerLockMode());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawerLockMode").withType("androidx.drawerlayout.widget.DrawerLayout.drawerLockMode"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("statusBarBackground").withType("drawable"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerSlide").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerOpened").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerClosed").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerStateChange").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawerGravity").withType("gravity").beforeChildAdd());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("openDrawer").withType("gravity").withUiFlag(UPDATE_UI_REQUEST_LAYOUT));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("closeDrawer").withType("gravity"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("isOpenStart").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("isOpenEnd").withType("boolean"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("edgeSize").withType("dimension"));
	
	}
	
	public DrawerLayoutImpl() {
		super(GROUP_NAME, LOCAL_NAME);
	}
	public  DrawerLayoutImpl(String localname) {
		super(GROUP_NAME, localname);
	}
	public  DrawerLayoutImpl(String groupName, String localname) {
		super(groupName, localname);
	}

	@Override
	public IWidget newInstance() {
		return new DrawerLayoutImpl();
	}
	
	@SuppressLint("NewApi")
	@Override
	public void create(IFragment fragment, Map<String, Object> params) {
		super.create(fragment, params);
Context context = (Context) fragment.getRootActivity();
	Object systemStyle = params.get("systemStyle");
	Object systemAndroidAttrStyle = params.get("systemAndroidAttrStyle");
	
	if (systemStyle == null && systemAndroidAttrStyle == null) {
		drawerLayout = new DrawerLayoutExt(context);
	} else {
		int defStyleAttr = 0;
		int defStyleRes = 0;
		
		if (systemStyle != null) {
			defStyleRes = context.getResources().getIdentifier((String) systemStyle, "style", context.getPackageName());	
		}
		
		if (systemAndroidAttrStyle != null) {
			defStyleAttr = context.getResources().getIdentifier((String) systemAndroidAttrStyle, "attr", "android");	
		}
		
		if (defStyleRes == 0) {
			drawerLayout = new DrawerLayoutExt(context, null, defStyleAttr);	
		} else {
		}
		
	}

		
		nativeCreate(params);
		
		
		ViewGroupImpl.registerCommandConveter(this);
	}

	@Override
	public Object asWidget() {
		return drawerLayout;
	}

	@Override
	public boolean remove(IWidget w) {
		boolean remove = super.remove(w);
		drawerLayout.removeView((View) w.asWidget());
		return remove;
	}
	
	@Override
    public boolean remove(int index) {
		IWidget widget = widgets.get(index);
        boolean remove = super.remove(index);

        if (index + 1 <= drawerLayout.getChildCount()) {
            drawerLayout.removeViewAt(index);
        }    
        return remove;
    }
	
	@Override
	public void add(IWidget w, int index) {
		if (index != -2) {
			View view = (View) w.asWidget();
			createLayoutParams(view);
			handleChildAddition(w, index, view);
		}
		
		ViewGroupImpl.nativeAddView(asNativeWidget(), w.asNativeWidget());
		super.add(w, index);
	}
	
	private void createLayoutParams(View view) {
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams layoutParams = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams) view.getLayoutParams();
		
		layoutParams = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams) view.getLayoutParams();
		if (layoutParams == null) {
			layoutParams = new androidx.drawerlayout.widget.DrawerLayout.LayoutParams(-2, -2);
			view.setLayoutParams(layoutParams);
		}  else {
			layoutParams.height = -2;
			layoutParams.width = -2;
		}
	}
	
	private androidx.drawerlayout.widget.DrawerLayout.LayoutParams getLayoutParams(View view) {
		return (androidx.drawerlayout.widget.DrawerLayout.LayoutParams) view.getLayoutParams();		
	}
	
	@SuppressLint("NewApi")
	@Override
	public void setChildAttribute(IWidget w, WidgetAttribute key, String strValue, Object objValue) {
		View view = (View) w.asWidget();
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams layoutParams = getLayoutParams(view);
		ViewGroupImpl.setChildAttribute(w, key, objValue, layoutParams);		
		
		switch (key.getAttributeName()) {
		case "layout_width":
			layoutParams.width = (int) objValue;
			break;	
		case "layout_height":
			layoutParams.height = (int) objValue;
			break;
		default:
			break;
		}
		
		
		view.setLayoutParams(layoutParams);		
	}
	
	@SuppressLint("NewApi")
	@Override
	public Object getChildAttribute(IWidget w, WidgetAttribute key) {
		Object attributeValue = ViewGroupImpl.getChildAttribute(w, key);		
		if (attributeValue != null) {
			return attributeValue;
		}
		View view = (View) w.asWidget();
		androidx.drawerlayout.widget.DrawerLayout.LayoutParams layoutParams = getLayoutParams(view);

		switch (key.getAttributeName()) {
		case "layout_width":
			return layoutParams.width;
		case "layout_height":
			return layoutParams.height;
		}
		
		return null;

	}
	
		
	public class DrawerLayoutExt extends androidx.drawerlayout.widget.DrawerLayout implements ILifeCycleDecorator, com.ashera.widget.IMaxDimension{
		private MeasureEvent measureFinished = new MeasureEvent();
		private OnLayoutEvent onLayoutEvent = new OnLayoutEvent();
		private int mMaxWidth = -1;
		private int mMaxHeight = -1;
		@Override
		public void setMaxWidth(int width) {
			mMaxWidth = width;
		}
		@Override
		public void setMaxHeight(int height) {
			mMaxHeight = height;
		}
		@Override
		public int getMaxWidth() {
			return mMaxWidth;
		}
		@Override
		public int getMaxHeight() {
			return mMaxHeight;
		}

		public DrawerLayoutExt(Context context, android.util.AttributeSet attrs, int defStyleAttr) {
	        super(context, attrs, defStyleAttr);
	    }

		public DrawerLayoutExt(Context context) {
			super(context);
			
			
			
			
			
			
		}
		
		@Override
		public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

			if(mMaxWidth > 0) {
	        	widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, MeasureSpec.AT_MOST);
	        }
	        if(mMaxHeight > 0) {
	            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxHeight, MeasureSpec.AT_MOST);

	        }

	        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			IWidgetLifeCycleListener listener = (IWidgetLifeCycleListener) getListener();
			if (listener != null) {
			    measureFinished.setWidth(getMeasuredWidth());
			    measureFinished.setHeight(getMeasuredHeight());
				listener.eventOccurred(EventId.measureFinished, measureFinished);
			}
		}
		
		@Override
		protected void onLayout(boolean changed, int l, int t, int r, int b) {
			super.onLayout(changed, l, t, r, b);
			ViewImpl.nativeMakeFrame(asNativeWidget(), l, t, r, b);
			replayBufferedEvents();
			
			IWidgetLifeCycleListener listener = (IWidgetLifeCycleListener) getListener();
			if (listener != null) {
				onLayoutEvent.setB(b);
				onLayoutEvent.setL(l);
				onLayoutEvent.setR(r);
				onLayoutEvent.setT(t);
				onLayoutEvent.setChanged(changed);
				listener.eventOccurred(EventId.onLayout, onLayoutEvent);
			}
			
			if (isInvalidateOnFrameChange() && isInitialised()) {
				DrawerLayoutImpl.this.invalidate();
			}
		}	
		
		@Override
		public void onDraw(Canvas canvas) {
			Runnable runnable = () -> super.onDraw(canvas);
			executeMethodListeners("onDraw", runnable, canvas);
		}

		@Override
		public void draw(Canvas canvas) {
			Runnable runnable = () -> super.draw(canvas);
			executeMethodListeners("draw", runnable, canvas);
		}

		@SuppressLint("WrongCall")
		@Override
		public void execute(String method, Object... args) {
			switch (method) {
				case "onDraw":
					setOnMethodCalled(true);
					super.onDraw((Canvas) args[0]);
					break;

				case "draw":
					setOnMethodCalled(true);
					super.draw((Canvas) args[0]);
					break;

				default:
					break;
			}
		}

		public void updateMeasuredDimension(int width, int height) {
			setMeasuredDimension(width, height);
		}


		@Override
		public ILifeCycleDecorator newInstance(IWidget widget) {
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAttribute(WidgetAttribute widgetAttribute,
				String strValue, Object objValue) {
			throw new UnsupportedOperationException();
		}		
		

		@Override
		public List<String> getMethods() {
			throw new UnsupportedOperationException();
		}
		
		@Override
		public void initialized() {
			throw new UnsupportedOperationException();
		}
		
        @Override
        public Object getAttribute(WidgetAttribute widgetAttribute) {
            throw new UnsupportedOperationException();
        }
        @Override
        public void drawableStateChanged() {
        	super.drawableStateChanged();
        	ViewImpl.drawableStateChanged(DrawerLayoutImpl.this);
        }
	}
	
	public void updateMeasuredDimension(int width, int height) {
		((DrawerLayoutExt) drawerLayout).updateMeasuredDimension(width, height);
	}
	

	@SuppressLint("NewApi")
	@Override
	public void setAttribute(WidgetAttribute key, String strValue, Object objValue, ILifeCycleDecorator decorator) {
		ViewGroupImpl.setAttribute(this, key, strValue, objValue, decorator);
		Object nativeWidget = asNativeWidget();
		switch (key.getAttributeName()) {
			case "elevation": {


	drawerLayout.setDrawerElevation((float)objValue);



			}
			break;
			case "scrimColor": {


	drawerLayout.setScrimColor((int)objValue);



			}
			break;
			case "drawerLockMode": {


	drawerLayout.setDrawerLockMode((int)objValue);



			}
			break;
			case "statusBarBackground": {


	drawerLayout.setStatusBarBackground((Drawable)objValue);



			}
			break;
			case "onDrawerSlide": {


		if (objValue instanceof String) {setDrawerListener(new DrawerListener(this, strValue, "onDrawerSlide"));} else {setDrawerListener((DrawerLayout.DrawerListener) objValue);}



			}
			break;
			case "onDrawerOpened": {


		if (objValue instanceof String) {setDrawerListener(new DrawerListener(this, strValue, "onDrawerOpened"));} else {setDrawerListener((DrawerLayout.DrawerListener) objValue);}



			}
			break;
			case "onDrawerClosed": {


		if (objValue instanceof String) {setDrawerListener(new DrawerListener(this, strValue, "onDrawerClosed"));} else {setDrawerListener((DrawerLayout.DrawerListener) objValue);}



			}
			break;
			case "onDrawerStateChange": {


		if (objValue instanceof String) {setDrawerListener(new DrawerListener(this, strValue, "onDrawerStateChange"));} else {setDrawerListener((DrawerLayout.DrawerListener) objValue);}



			}
			break;
			case "drawerGravity": {


		setDrawerGravity(objValue);



			}
			break;
			case "openDrawer": {


	drawerLayout.openDrawer((int)objValue);



			}
			break;
			case "closeDrawer": {


	drawerLayout.closeDrawer((int)objValue);



			}
			break;
			case "edgeSize": {


		setEdgeSize(objValue);



			}
			break;
		default:
			break;
		}
		postSetAttribute(key, strValue, objValue, decorator);
	}
	
	@Override
	@SuppressLint("NewApi")
	public Object getAttribute(WidgetAttribute key, ILifeCycleDecorator decorator) {
		Object attributeValue = ViewGroupImpl.getAttribute(this, key, decorator);
		if (attributeValue != null) {
			return attributeValue;
		}
		Object nativeWidget = asNativeWidget();
		switch (key.getAttributeName()) {
			case "elevation": {
return drawerLayout.getDrawerElevation();			}
			case "isOpenStart": {
return isOpenStart();			}
			case "isOpenEnd": {
return isOpenEnd();			}
		}
		return null;
	}


	@Override
    public Object asNativeWidget() {
        return drawerLayout;
    }
    private void nativeCreate(Map<String, Object> params) {
    }
    
    @Override
    public void requestLayout() {
    	if (isInitialised()) {
    		ViewImpl.requestLayout(this, asNativeWidget());
    	}
    }
    
    @Override
    public void invalidate() {
    	if (isInitialised()) {
    		ViewImpl.invalidate(this, asNativeWidget());
    	}
    }
    
	
	@SuppressLint("NewApi")
private static class DrawerListener implements DrawerLayout.DrawerListener, com.ashera.widget.IListener{
private IWidget w; private View view; private String strValue; private String action;
public String getAction() {return action;}
public DrawerListener(IWidget w, String strValue)  {
this.w = w; this.strValue = strValue;
}
public DrawerListener(IWidget w, String strValue, String action)  {
this.w = w; this.strValue = strValue;this.action=action;
}
public void onDrawerSlide(View drawerView, float slideOffset){
    
	if (action == null || action.equals("onDrawerSlide")) {
		// populate the data from ui to pojo
		w.syncModelFromUiToPojo("onDrawerSlide");
	    java.util.Map<String, Object> obj = getOnDrawerSlideEventObj(drawerView,slideOffset);
	    String commandName =  (String) obj.get(EventExpressionParser.KEY_COMMAND_NAME);
	    
	    // execute command based on command type
	    String commandType = (String)obj.get(EventExpressionParser.KEY_COMMAND_TYPE);
		switch (commandType) {
		case "+":
		case ":":
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, drawerView,slideOffset);
		    }
		    if (commandType.equals(":")) {
		    	return;
		    }
			
			break;
		default:
			break;
		}
		
		if (obj.containsKey("refreshUiFromModel")) {
			Object widgets = obj.remove("refreshUiFromModel");
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, widgets, true);
		}
		if (w.getModelUiToPojoEventIds() != null) {
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, w.getModelUiToPojoEventIds(), true);
		}
		if (strValue != null && !strValue.isEmpty()) {
		    com.ashera.core.IActivity activity = (com.ashera.core.IActivity)w.getFragment().getRootActivity();
		    activity.sendEventMessage(obj);
		}
	}
    return;
}//#####

public java.util.Map<String, Object> getOnDrawerSlideEventObj(View drawerView,float slideOffset) {
	java.util.Map<String, Object> obj = com.ashera.widget.PluginInvoker.getJSONCompatMap();
    obj.put("action", "action");
    obj.put("eventType", "drawerslide");
    obj.put("fragmentId", w.getFragment().getFragmentId());
    obj.put("actionUrl", w.getFragment().getActionUrl());
    
    PluginInvoker.putJSONSafeObjectIntoMap(obj, "id", w.getId());
     
        PluginInvoker.putJSONSafeObjectIntoMap(obj, "slideOffset", slideOffset);
    
    // parse event info into the map
    EventExpressionParser.parseEventExpression(strValue, obj);
    
    // update model data into map
    w.updateModelToEventMap(obj, "onDrawerSlide", (String)obj.get(EventExpressionParser.KEY_EVENT_ARGS));
    return obj;
}public void onDrawerOpened(View drawerView){
    
	if (action == null || action.equals("onDrawerOpened")) {
		// populate the data from ui to pojo
		w.syncModelFromUiToPojo("onDrawerOpened");
	    java.util.Map<String, Object> obj = getOnDrawerOpenedEventObj(drawerView);
	    String commandName =  (String) obj.get(EventExpressionParser.KEY_COMMAND_NAME);
	    
	    // execute command based on command type
	    String commandType = (String)obj.get(EventExpressionParser.KEY_COMMAND_TYPE);
		switch (commandType) {
		case "+":
		case ":":
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, drawerView);
		    }
		    if (commandType.equals(":")) {
		    	return;
		    }
			
			break;
		default:
			break;
		}
		
		if (obj.containsKey("refreshUiFromModel")) {
			Object widgets = obj.remove("refreshUiFromModel");
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, widgets, true);
		}
		if (w.getModelUiToPojoEventIds() != null) {
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, w.getModelUiToPojoEventIds(), true);
		}
		if (strValue != null && !strValue.isEmpty()) {
		    com.ashera.core.IActivity activity = (com.ashera.core.IActivity)w.getFragment().getRootActivity();
		    activity.sendEventMessage(obj);
		}
	}
    return;
}//#####

public java.util.Map<String, Object> getOnDrawerOpenedEventObj(View drawerView) {
	java.util.Map<String, Object> obj = com.ashera.widget.PluginInvoker.getJSONCompatMap();
    obj.put("action", "action");
    obj.put("eventType", "draweropened");
    obj.put("fragmentId", w.getFragment().getFragmentId());
    obj.put("actionUrl", w.getFragment().getActionUrl());
    
    PluginInvoker.putJSONSafeObjectIntoMap(obj, "id", w.getId());
     
    
    // parse event info into the map
    EventExpressionParser.parseEventExpression(strValue, obj);
    
    // update model data into map
    w.updateModelToEventMap(obj, "onDrawerOpened", (String)obj.get(EventExpressionParser.KEY_EVENT_ARGS));
    return obj;
}public void onDrawerClosed(View drawerView){
    
	if (action == null || action.equals("onDrawerClosed")) {
		// populate the data from ui to pojo
		w.syncModelFromUiToPojo("onDrawerClosed");
	    java.util.Map<String, Object> obj = getOnDrawerClosedEventObj(drawerView);
	    String commandName =  (String) obj.get(EventExpressionParser.KEY_COMMAND_NAME);
	    
	    // execute command based on command type
	    String commandType = (String)obj.get(EventExpressionParser.KEY_COMMAND_TYPE);
		switch (commandType) {
		case "+":
		case ":":
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, drawerView);
		    }
		    if (commandType.equals(":")) {
		    	return;
		    }
			
			break;
		default:
			break;
		}
		
		if (obj.containsKey("refreshUiFromModel")) {
			Object widgets = obj.remove("refreshUiFromModel");
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, widgets, true);
		}
		if (w.getModelUiToPojoEventIds() != null) {
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, w.getModelUiToPojoEventIds(), true);
		}
		if (strValue != null && !strValue.isEmpty()) {
		    com.ashera.core.IActivity activity = (com.ashera.core.IActivity)w.getFragment().getRootActivity();
		    activity.sendEventMessage(obj);
		}
	}
    return;
}//#####

public java.util.Map<String, Object> getOnDrawerClosedEventObj(View drawerView) {
	java.util.Map<String, Object> obj = com.ashera.widget.PluginInvoker.getJSONCompatMap();
    obj.put("action", "action");
    obj.put("eventType", "drawerclosed");
    obj.put("fragmentId", w.getFragment().getFragmentId());
    obj.put("actionUrl", w.getFragment().getActionUrl());
    
    PluginInvoker.putJSONSafeObjectIntoMap(obj, "id", w.getId());
     
    
    // parse event info into the map
    EventExpressionParser.parseEventExpression(strValue, obj);
    
    // update model data into map
    w.updateModelToEventMap(obj, "onDrawerClosed", (String)obj.get(EventExpressionParser.KEY_EVENT_ARGS));
    return obj;
}public void onDrawerStateChanged(int newState){
    
	if (action == null || action.equals("onDrawerStateChanged")) {
		// populate the data from ui to pojo
		w.syncModelFromUiToPojo("onDrawerStateChanged");
	    java.util.Map<String, Object> obj = getOnDrawerStateChangedEventObj(newState);
	    String commandName =  (String) obj.get(EventExpressionParser.KEY_COMMAND_NAME);
	    
	    // execute command based on command type
	    String commandType = (String)obj.get(EventExpressionParser.KEY_COMMAND_TYPE);
		switch (commandType) {
		case "+":
		case ":":
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, newState);
		    }
		    if (commandType.equals(":")) {
		    	return;
		    }
			
			break;
		default:
			break;
		}
		
		if (obj.containsKey("refreshUiFromModel")) {
			Object widgets = obj.remove("refreshUiFromModel");
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, widgets, true);
		}
		if (w.getModelUiToPojoEventIds() != null) {
			com.ashera.layout.ViewImpl.refreshUiFromModel(w, w.getModelUiToPojoEventIds(), true);
		}
		if (strValue != null && !strValue.isEmpty()) {
		    com.ashera.core.IActivity activity = (com.ashera.core.IActivity)w.getFragment().getRootActivity();
		    activity.sendEventMessage(obj);
		}
	}
    return;
}//#####

public java.util.Map<String, Object> getOnDrawerStateChangedEventObj(int newState) {
	java.util.Map<String, Object> obj = com.ashera.widget.PluginInvoker.getJSONCompatMap();
    obj.put("action", "action");
    obj.put("eventType", "drawerstatechanged");
    obj.put("fragmentId", w.getFragment().getFragmentId());
    obj.put("actionUrl", w.getFragment().getActionUrl());
    
    PluginInvoker.putJSONSafeObjectIntoMap(obj, "id", w.getId());
     
        PluginInvoker.putJSONSafeObjectIntoMap(obj, "newState", newState);
    
    // parse event info into the map
    EventExpressionParser.parseEventExpression(strValue, obj);
    
    // update model data into map
    w.updateModelToEventMap(obj, "onDrawerStateChanged", (String)obj.get(EventExpressionParser.KEY_EVENT_ARGS));
    return obj;
}
}


	@Override
	public void setId(String id){
		if (id != null && !id.equals("")){
			super.setId(id);
			drawerLayout.setId(IdGenerator.getId(id));
		}
	}
	
    

	
private DrawerLayoutCommandBuilder builder;
private DrawerLayoutBean bean;
public Object getPlugin(String plugin) {
	return WidgetFactory.getAttributable(plugin).newInstance(this);
}
public DrawerLayoutBean getBean() {
	if (bean == null) {
		bean = new DrawerLayoutBean();
	}
	return bean;
}
public DrawerLayoutCommandBuilder getBuilder() {
	if (builder == null) {
		builder = new DrawerLayoutCommandBuilder();
	}
	return builder;
}


public  class DrawerLayoutCommandBuilder extends com.ashera.layout.ViewGroupImpl.ViewGroupCommandBuilder <DrawerLayoutCommandBuilder> {
    public DrawerLayoutCommandBuilder() {
	}
	
	public DrawerLayoutCommandBuilder execute(boolean setter) {
		if (setter) {
			executeCommand(command, null, IWidget.COMMAND_EXEC_SETTER_METHOD);
			getFragment().remeasure();
		}
		executeCommand(command, null, IWidget.COMMAND_EXEC_GETTER_METHOD);
return this;	}

public DrawerLayoutCommandBuilder tryGetElevation() {
	Map<String, Object> attrs = initCommand("elevation");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object getElevation() {
	Map<String, Object> attrs = initCommand("elevation");
	return attrs.get("commandReturnValue");
}
public DrawerLayoutCommandBuilder setElevation(String value) {
	Map<String, Object> attrs = initCommand("elevation");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setScrimColor(String value) {
	Map<String, Object> attrs = initCommand("scrimColor");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setDrawerLockMode(String value) {
	Map<String, Object> attrs = initCommand("drawerLockMode");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setStatusBarBackground(String value) {
	Map<String, Object> attrs = initCommand("statusBarBackground");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setOnDrawerSlide(String value) {
	Map<String, Object> attrs = initCommand("onDrawerSlide");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setOnDrawerOpened(String value) {
	Map<String, Object> attrs = initCommand("onDrawerOpened");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setOnDrawerClosed(String value) {
	Map<String, Object> attrs = initCommand("onDrawerClosed");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setOnDrawerStateChange(String value) {
	Map<String, Object> attrs = initCommand("onDrawerStateChange");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder setDrawerGravity(String value) {
	Map<String, Object> attrs = initCommand("drawerGravity");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder openDrawer(String value) {
	Map<String, Object> attrs = initCommand("openDrawer");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder closeDrawer(String value) {
	Map<String, Object> attrs = initCommand("closeDrawer");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
public DrawerLayoutCommandBuilder tryGetIsOpenStart() {
	Map<String, Object> attrs = initCommand("isOpenStart_");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object isIsOpenStart() {
	Map<String, Object> attrs = initCommand("isOpenStart_");
	return attrs.get("commandReturnValue");
}

public DrawerLayoutCommandBuilder tryGetIsOpenEnd() {
	Map<String, Object> attrs = initCommand("isOpenEnd_");
	attrs.put("type", "attribute");
	attrs.put("getter", true);
	attrs.put("orderGet", ++orderGet);
return this;}

public Object isIsOpenEnd() {
	Map<String, Object> attrs = initCommand("isOpenEnd_");
	return attrs.get("commandReturnValue");
}

public DrawerLayoutCommandBuilder edgeSize(String value) {
	Map<String, Object> attrs = initCommand("edgeSize");
	attrs.put("type", "attribute");
	attrs.put("setter", true);
	attrs.put("orderSet", ++orderSet);

	attrs.put("value", value);
return this;}
}
public class DrawerLayoutBean extends com.ashera.layout.ViewGroupImpl.ViewGroupBean{
		public DrawerLayoutBean() {
			super(DrawerLayoutImpl.this);
		}
public Object getElevation() {
	return getBuilder().reset().tryGetElevation().execute(false).getElevation(); 
}
public void setElevation(String value) {
	getBuilder().reset().setElevation(value).execute(true);
}

public void setScrimColor(String value) {
	getBuilder().reset().setScrimColor(value).execute(true);
}

public void setDrawerLockMode(String value) {
	getBuilder().reset().setDrawerLockMode(value).execute(true);
}

public void setStatusBarBackground(String value) {
	getBuilder().reset().setStatusBarBackground(value).execute(true);
}

public void setOnDrawerSlide(String value) {
	getBuilder().reset().setOnDrawerSlide(value).execute(true);
}

public void setOnDrawerOpened(String value) {
	getBuilder().reset().setOnDrawerOpened(value).execute(true);
}

public void setOnDrawerClosed(String value) {
	getBuilder().reset().setOnDrawerClosed(value).execute(true);
}

public void setOnDrawerStateChange(String value) {
	getBuilder().reset().setOnDrawerStateChange(value).execute(true);
}

public void setDrawerGravity(String value) {
	getBuilder().reset().setDrawerGravity(value).execute(true);
}

public void openDrawer(String value) {
	getBuilder().reset().openDrawer(value).execute(true);
}

public void closeDrawer(String value) {
	getBuilder().reset().closeDrawer(value).execute(true);
}

public Object isIsOpenStart() {
	return getBuilder().reset().tryGetIsOpenStart().execute(false).isIsOpenStart(); 
}
public Object isIsOpenEnd() {
	return getBuilder().reset().tryGetIsOpenEnd().execute(false).isIsOpenEnd(); 
}
public void edgeSize(String value) {
	getBuilder().reset().edgeSize(value).execute(true);
}

}


private DrawerLayoutCommandParamsBuilder paramsBuilder;
private DrawerLayoutParamsBean paramsBean;

public DrawerLayoutParamsBean getParamsBean() {
	if (paramsBean == null) {
		paramsBean = new DrawerLayoutParamsBean();
	}
	return paramsBean;
}
public DrawerLayoutCommandParamsBuilder getParamsBuilder() {
	if (paramsBuilder == null) {
		paramsBuilder = new DrawerLayoutCommandParamsBuilder();
	}
	return paramsBuilder;
}



public class DrawerLayoutParamsBean extends com.ashera.layout.ViewGroupImpl.ViewGroupParamsBean{
}





public class DrawerLayoutCommandParamsBuilder extends com.ashera.layout.ViewGroupImpl.ViewGroupCommandParamsBuilder<DrawerLayoutCommandParamsBuilder>{
}

	//end - body
	//start - drawer
	private int drawerGravity = android.view.Gravity.START;
	private void setDrawerGravity(Object objValue) {
		drawerGravity = (int) objValue;
	}
	
	private void handleChildAddition(IWidget w, int index, View view) {
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		String id = w.getId();
		if ("@+id/drawerlayout".equals(id)) {
			androidx.drawerlayout.widget.DrawerLayout.LayoutParams drawerLayoutParams = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)layoutParams;
			drawerLayoutParams.gravity= drawerGravity;
			addView(view, index, layoutParams);
		} else if ("@+id/drawerlayoutstart".equals(id)) {
			androidx.drawerlayout.widget.DrawerLayout.LayoutParams drawerLayoutParams = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)layoutParams;
			drawerLayoutParams.gravity= android.view.Gravity.START;
			addView(view, index, layoutParams);
		} else if ("@+id/drawerlayoutend".equals(id)) {
			androidx.drawerlayout.widget.DrawerLayout.LayoutParams drawerLayoutParams = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)layoutParams;
			drawerLayoutParams.gravity= android.view.Gravity.END;
			addView(view, index, layoutParams);
		} else {
			addView(view, index, layoutParams);
		}
		
	}

	private void addView(View view, int index, ViewGroup.LayoutParams layoutParams) {
		if (index == -1) {
			drawerLayout.addView(view, index, layoutParams);
		} else {
			drawerLayout.addView(view, layoutParams);
		}
	}

	private Object isOpenStart() {
		return drawerLayout.isDrawerOpen(Gravity.START);
	}
	
	private Object isOpenEnd() {
		return drawerLayout.isDrawerOpen(Gravity.END);
	}
	
	private Map<String, DrawerLayout.DrawerListener> listeners = new HashMap<>();
	private void setDrawerListener(DrawerLayout.DrawerListener drawerListener) {
		String action = ((IListener)drawerListener).getAction();
		DrawerLayout.DrawerListener existingDrawerListener = listeners.get(action);
		if (existingDrawerListener != null) {
			drawerLayout.removeDrawerListener(existingDrawerListener);
		}
		listeners.put(action, drawerListener);
		drawerLayout.addDrawerListener(drawerListener);
	}

	//end - drawer
	
	
	private void postSetAttribute(WidgetAttribute key, String strValue, Object objValue,
			ILifeCycleDecorator decorator) {
		switch (key.getAttributeName()) {
			case "fitsSystemWindows": {
				if ((boolean) objValue) {
		            if (Build.VERSION.SDK_INT >= 21) {
		                drawerLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
		                    @Override
		                    public WindowInsets onApplyWindowInsets(View view, WindowInsets insets) {
		                        final DrawerLayout drawerLayout = (DrawerLayout) view;
		                        drawerLayout.setChildInsets(insets, insets.getSystemWindowInsetTop() > 0);
		                        return insets.consumeSystemWindowInsets();
		                    }
		                });
		                drawerLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
		            } else {
		            	drawerLayout.setStatusBarBackground(null);
		            }
		        }

			}
		break;
		}
	}
	
	
	private void setEdgeSize(Object objValue) {
		androidx.customview.widget.ViewDragHelper leftDragger = (androidx.customview.widget.ViewDragHelper) getFieldValueUsingReflection(drawerLayout, "mLeftDragger");
		if (leftDragger != null) {
			setFieldUsingReflection(leftDragger, "mEdgeSize", objValue);
		}
		
		androidx.customview.widget.ViewDragHelper rightDragger = (androidx.customview.widget.ViewDragHelper) getFieldValueUsingReflection(drawerLayout, "mRightDragger");
		if (rightDragger != null) {
			setFieldUsingReflection(rightDragger, "mEdgeSize", objValue);
		}
		
	}
}
