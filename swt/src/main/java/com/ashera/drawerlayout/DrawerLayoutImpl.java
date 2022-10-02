package com.ashera.drawerlayout;
// start - imports
import java.util.*;

import r.android.annotation.SuppressLint;
import r.android.content.Context;
import r.android.os.Build;
import r.android.view.*;
import r.android.widget.*;
import r.android.view.View.*;

import com.ashera.widget.BaseHasWidgets;

import r.android.annotation.SuppressLint;

import com.ashera.core.IFragment;
import com.ashera.widget.bus.*;
import com.ashera.converter.*;
import com.ashera.widget.bus.Event.*;
import com.ashera.widget.*;
import com.ashera.widget.IWidgetLifeCycleListener.*;
import com.ashera.layout.*;

import androidx.core.view.*;

import static com.ashera.widget.IWidget.*;
//end - imports

import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerLayoutImpl extends BaseHasWidgets {
	//start - body
	private Object pane;
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

		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("openDrawer").withType("gravity"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("closeDrawer").withType("gravity"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("animationDurationInMs").withType("int"));
		ConverterFactory.register("androidx.drawerlayout.widget.DrawerLayout.drawerLockMode", new DrawerLockMode());
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawerLockMode").withType("androidx.drawerlayout.widget.DrawerLayout.drawerLockMode"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerSlide").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerOpened").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerClosed").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("onDrawerStateChange").withType("string"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("drawerGravity").withType("gravity").beforeChildAdd());
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
		drawerLayout = new DrawerLayoutExt();
		
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
         ViewGroupImpl.nativeRemoveView(w);            
		return remove;
	}
	
	@Override
    public boolean remove(int index) {
		IWidget widget = widgets.get(index);
        boolean remove = super.remove(index);

        if (index + 1 <= drawerLayout.getChildCount()) {
            drawerLayout.removeViewAt(index);
            ViewGroupImpl.nativeRemoveView(widget);            
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

		public DrawerLayoutExt() {
			
			
			
			
			super();
			
			
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
			ViewImpl.setDrawableBounds(DrawerLayoutImpl.this, l, t, r, b);
			ViewImpl.nativeMakeFrame(asNativeWidget(), l, t, r, b);
			replayBufferedEvents();
	        ViewImpl.redrawDrawables(DrawerLayoutImpl.this);
			
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
		public void execute(String method, Object... canvas) {
			
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
		@Override
		public void offsetTopAndBottom(int offset) {
			super.offsetTopAndBottom(offset);
			ViewImpl.nativeMakeFrame(asNativeWidget(), getLeft(), getTop(), getRight(), getBottom());
		}
		@Override
		public void offsetLeftAndRight(int offset) {
			super.offsetLeftAndRight(offset);
			ViewImpl.nativeMakeFrame(asNativeWidget(), getLeft(), getTop(), getRight(), getBottom());
		}
        @Override
        public void setVisibility(int visibility) {
            super.setVisibility(visibility);
            ((org.eclipse.swt.widgets.Control)asNativeWidget()).setVisible(View.VISIBLE == visibility);
            
        }
		 @Override
	        public void smoothSlideViewTo(r.android.view.View drawerView, int x, int y) {
			 DrawerLayoutImpl.this.smoothSlideViewTo(drawerView, x, y);
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
			case "openDrawer": {


		openDrawer(objValue);



			}
			break;
			case "closeDrawer": {


		closeDrawer(objValue);



			}
			break;
			case "animationDurationInMs": {


		setAnimationDuration(objValue);



			}
			break;
			case "drawerLockMode": {


	drawerLayout.setDrawerLockMode((int)objValue);



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
			case "edgeSize": {


		setEdgeSize(objValue);



			}
			break;
		default:
			break;
		}
		
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
			case "isOpenStart": {
return isOpenStart();			}
			case "isOpenEnd": {
return isOpenEnd();			}
		}
		return null;
	}


	@Override
    public Object asNativeWidget() {
        return pane;
    }
    private void nativeCreate(Map<String, Object> params) {
        pane = new org.eclipse.swt.widgets.Composite((org.eclipse.swt.widgets.Composite)ViewImpl.getParent(this), getStyle(params, fragment));
        ((org.eclipse.swt.widgets.Composite)pane).setLayout(new org.eclipse.nebula.widgets.layout.AbsoluteLayout());
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
    
	

	private int drawerGravity = r.android.view.Gravity.START;
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
			drawerLayoutParams.gravity= r.android.view.Gravity.START;
			addView(view, index, layoutParams);
		} else if ("@+id/drawerlayoutend".equals(id)) {
			androidx.drawerlayout.widget.DrawerLayout.LayoutParams drawerLayoutParams = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams)layoutParams;
			drawerLayoutParams.gravity= r.android.view.Gravity.END;
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
	
    
    @Override
    public void setVisible(boolean b) {
        ((View)asWidget()).setVisibility(b ? View.VISIBLE : View.GONE);
    }
    public int getStyle(String key, int initStyle, Map<String, Object> params, IFragment fragment) {
    	if (params == null) {
    		return initStyle;
    	}
    	Object style = params.get(key);
		if (style == null) {
			return initStyle;
		}
		int convertFrom = (int) ConverterFactory.get("swtbitflag").convertFrom(style.toString(), null, fragment);
		return convertFrom;
	}
	
	public int getStyle(Map<String, Object> params, IFragment fragment) {
		return getStyle("swtStyle", org.eclipse.swt.SWT.NONE, params, fragment);
	}
	
	public int getStyle(int initStyle, Map<String, Object> params, IFragment fragment) {
		return getStyle("swtStyle", initStyle, params, fragment);
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
public DrawerLayoutCommandBuilder setAnimationDurationInMs(int value) {
	Map<String, Object> attrs = initCommand("animationDurationInMs");
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
public void openDrawer(String value) {
	getBuilder().reset().openDrawer(value).execute(true);
}

public void closeDrawer(String value) {
	getBuilder().reset().closeDrawer(value).execute(true);
}

public void setAnimationDurationInMs(int value) {
	getBuilder().reset().setAnimationDurationInMs(value).execute(true);
}

public void setDrawerLockMode(String value) {
	getBuilder().reset().setDrawerLockMode(value).execute(true);
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
	private int startX = -1;  
	private boolean mouseMoved = false;

	private void openDrawer(Object objValue) {
		createBlurredPanel(objValue);
		drawerLayout.openDrawer((int) objValue);
	}
	
	private void closeDrawer(Object objValue) {
		hideBlurredPanel();
		drawerLayout.closeDrawer((int) objValue);
	}

	private void smoothSlideViewTo(r.android.view.View drawerView, int x, int y) {
		for (Iterator<IWidget> iterator = widgets.iterator(); iterator.hasNext();) {
			IWidget child = iterator.next();
			Object childView = child.asWidget();
			if (childView == drawerView) {
				animateView(child, x, y);
				break;
			}
		}
		
		resetPanVars();
	}

	private boolean isLeftDrawer(View view) {
		int gravity = androidx.core.view.GravityCompat.getAbsoluteGravity(getGravity(view), view.getLayoutDirection());
		return gravity == Gravity.LEFT;
	}
	
	private boolean isRightDrawer(View view) {
		int gravity = androidx.core.view.GravityCompat.getAbsoluteGravity(getGravity(view), view.getLayoutDirection());
		return gravity == Gravity.RIGHT;
	}

	private boolean isContentView(IWidget widget) {
		View view = (View) widget.asWidget();
		return drawerLayout.isViewContentView(view) && !"@+id/blurredView".equals(widget.getId());
	}
	
	private void handlePanStart(int eventX) {
		mouseMoved = false;
		startX = eventX;
	}

	private void handlePanEndOfDrawer(IWidget widget) {
		if (mouseMoved) {
			View view = (View) widget.asWidget();
			if (isLeftDrawer(view)) {
				handleLeftDrawerPanEnd(widget, view);
			}
			
			if (isRightDrawer(view)) {
				handleRightDrawerPanEnd(widget, view);
			}
		}
		
		resetPanVars();
	}

	private void handleRightDrawerPanEnd(IWidget widget, View view) {
		Object nativeWidget = widget.asNativeWidget();
		int gravity = getGravity(view);
		int contentViewWidth = getContentViewWidth();
		if (Math.abs(contentViewWidth - getCurrentX(nativeWidget)) >= view.getMeasuredWidth()/2) {
			openDrawer(gravity);
		} else {
			closeDrawer(gravity);
		}
	}

	private void handleLeftDrawerPanEnd(IWidget widget, View view) {
		Object nativeWidget = widget.asNativeWidget();
		int gravity = getGravity(view);

		if (Math.abs(getCurrentX(nativeWidget)) <= view.getMeasuredWidth() / 2) {
			openDrawer(gravity);
		} else {
			closeDrawer(gravity);
		}
	}

	private void handlePanDragOfDrawer(int eventX, IWidget widget) {
		if (startX != -1) {
			View view = (View) widget.asWidget();
			// left drawer
			if (isLeftDrawer(view)) {
				if (drawerLayout.getDrawerLockMode(view) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
					int correction = 0;
					if (requiresCurrentXCorrection()) {
						correction = getCurrentX(widget.asNativeWidget());
					}
					int x = correction + (eventX - startX);
					if (x <= 0) {
						mouseMoved = true;
						updateX(widget.asNativeWidget(), x);
					}
				}
			}
			
			if (isRightDrawer(view)) {
				if (drawerLayout.getDrawerLockMode(view) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {

					int initX = ((View) view.getParent()).getMeasuredWidth() - view.getMeasuredWidth();
					int correction = requiresCurrentXCorrection() ? getCurrentX(widget.asNativeWidget()) : initX;
					int x = correction + (eventX - startX);
					
					if (x >= initX ) {
						mouseMoved = true;
						updateX(widget.asNativeWidget(), x);
					}
				}
			}
		}
	}

	private void handlePanDrag(int eventX) {
		if (startX != -1) {
			// left drawer
			if (isLeftDrawerDragged(startX)) {
				for (IWidget widget : widgets) {
					View view = (View) widget.asWidget();
	
					if (isLeftDrawer(view)) {
						if(drawerLayout.getDrawerLockMode(view) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) { 
							int gravity = getGravity(view);
							createBlurredPanel(gravity);
							view.setVisibility(View.VISIBLE);
							
							int x = eventX - view.getMeasuredWidth() - startX;
							if (x <= 0) {
								mouseMoved = true;
								updateX(widget.asNativeWidget(), x);
							}
						}
						break;
					}
				}
			}
			
			//right drawer
			if (isRightDrawerDragged(startX)) {
				int contentViewWidth = getContentViewWidth();
				for (IWidget widget : widgets) {
					View view = (View) widget.asWidget();
	
					if (isRightDrawer(view)) {
						if(drawerLayout.getDrawerLockMode(view) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
							int gravity = getGravity(view);
							createBlurredPanel(gravity);
							view.setVisibility(View.VISIBLE);
							
							int x = contentViewWidth - (startX - eventX);
							if (x >= (contentViewWidth - view.getMeasuredWidth())) {
								mouseMoved = true;
								updateX(widget.asNativeWidget(), x);
							}
						}
						break;
					}
				}
			}
		}
	}

	private void handlePanEnd() {
		if (isLeftDrawerDragged(startX)) {
			for (IWidget widget : widgets) {
				View view = (View)widget.asWidget();
				if (mouseMoved) {
					if (isLeftDrawer(view)) {
						handleLeftDrawerPanEnd(widget, view);
					}
				}
			}
		}
		if (isRightDrawerDragged(startX)) {
			for (IWidget widget : widgets) {
				View view = (View)widget.asWidget();
				if (mouseMoved) {
					if (isRightDrawer(view)) {
						handleRightDrawerPanEnd(widget, view);
					}
				}
			}
		
		}
		resetPanVars();
	}
	

	private boolean isLeftDrawerDragged(int x) {
		return x <= edgeSize;
	}

	private boolean isRightDrawerDragged(int x) {
		boolean dragged = false;
		for (IWidget widget : widgets) {
			View view = (View)widget.asWidget();
			if (isContentView(widget)) {
				dragged = (x >= (view.getMeasuredWidth() - edgeSize));
				break;
			}
		}
		return dragged;
	}


	private int getContentViewWidth() {
		int contentViewWidth = 0;
		for (IWidget widget : widgets) {
			View view = (View) widget.asWidget();
			if (isContentView(widget)) {
				contentViewWidth = view.getMeasuredWidth();
				break;
			}
		}
		return contentViewWidth;
	}
	private void resetPanVars() {
		mouseMoved = false;
		startX = -1;
	}

	private int getGravity(View view) {
		return ((DrawerLayout.LayoutParams) view.getLayoutParams()).gravity;
	}
	

	private void hideDrawerView(View drawerView) {
		drawerView.setVisibility(View.INVISIBLE);
		hideBlurredPanel();
	}

	private void updateDrawerViewState(IWidget widget) {
		int currentX = getCurrentX(widget.asNativeWidget());
		updateDrawerViewState(widget, currentX);
	}

	private void updateDrawerViewState(IWidget widget, int currentX) {
		View drawerView = (View) widget.asWidget();
		int state = DrawerLayout.STATE_IDLE;

		if (isLeftDrawer(drawerView)) {
			if (drawerLayout.isOpening(drawerView)) {
				if (currentX != 0) {
					drawerView.setVisibility(View.VISIBLE);
					state = DrawerLayout.STATE_SETTLING;
				}
			} else {
				if (currentX != -drawerView.getMeasuredWidth()) {
					state = DrawerLayout.STATE_SETTLING;
				} else {
					hideDrawerView(drawerView);
				}
			}
		} else {
			int contentViewWidth = getContentViewWidth();
			int leftMargin = contentViewWidth - drawerView.getMeasuredWidth();
			
			if (drawerLayout.isOpening(drawerView)) {
				if (currentX !=leftMargin) {
					drawerView.setVisibility(View.VISIBLE);
					state = DrawerLayout.STATE_SETTLING;
				}
			} else {
				if (currentX != contentViewWidth) {
					state = DrawerLayout.STATE_SETTLING;
				} else {
					hideDrawerView(drawerView);
				}
			}
		
		}
		drawerLayout.onViewPositionChanged(drawerView, currentX, 0, 0, 0);
		drawerLayout.updateDrawerViewState(state, drawerView);
	}

	private int edgeSize;
	private void setEdgeSize(Object objValue) {
		edgeSize = (int) objValue;
	}
	//end - drawer
	//swt-specific code
	private class MouseListener implements org.eclipse.swt.widgets.Listener {
		@Override
		public void handleEvent(org.eclipse.swt.widgets.Event event) {
			int eventX = event.x;
			
			if (event.type == org.eclipse.swt.SWT.MouseDown) {
				handlePanStart(eventX);
			}
			
			if (event.type == org.eclipse.swt.SWT.MouseMove) {
				handlePanDrag(eventX);
			}
			if (event.type == org.eclipse.swt.SWT.MouseUp) {
				handlePanEnd();
			}
		}
	}
	
	private class DrawerDragListener implements org.eclipse.swt.widgets.Listener {
		private IWidget widget;
		public DrawerDragListener(IWidget widget) {
			this.widget = widget;
		}

		@Override
		public void handleEvent(org.eclipse.swt.widgets.Event event) {
			int eventX = event.x;
			if (event.type == org.eclipse.swt.SWT.MouseDown) {
				handlePanStart(eventX);
			}

			if (event.type == org.eclipse.swt.SWT.MouseMove) {
				handlePanDragOfDrawer(eventX, widget);
			}
			if (event.type == org.eclipse.swt.SWT.MouseUp) {
				handlePanEndOfDrawer(widget);
			}
		}
	}

	@Override
	public void initialized() {
		super.initialized();
		
		for (IWidget widget : widgets) {
			View view = (View) widget.asWidget();
			if (isLeftDrawer(view) || isRightDrawer(view)) {
				org.eclipse.swt.widgets.Composite composite = (org.eclipse.swt.widgets.Composite)widget.asNativeWidget();
				
				DrawerDragListener listener = new DrawerDragListener(widget);
				composite.addListener(org.eclipse.swt.SWT.MouseDown, listener);
				composite.addListener(org.eclipse.swt.SWT.MouseMove, listener);
				composite.addListener(org.eclipse.swt.SWT.MouseUp, listener);
			}
			if (isContentView(widget)) {
				org.eclipse.swt.widgets.Composite composite = (org.eclipse.swt.widgets.Composite)widget.asNativeWidget();
				
				MouseListener listener = new MouseListener();
				composite.addListener(org.eclipse.swt.SWT.MouseDown, listener);
				composite.addListener(org.eclipse.swt.SWT.MouseMove, listener);
				composite.addListener(org.eclipse.swt.SWT.MouseUp, listener);

			}
		}
	}


	private void updateX(Object nativeWidget, int x) {
		org.eclipse.swt.widgets.Control control = (org.eclipse.swt.widgets.Control) nativeWidget;
		control.setBounds(x, control.getBounds().y, control.getBounds().width, control.getBounds().height);
	}


	private int getCurrentX(Object nativeWidget){
		return ((org.eclipse.swt.widgets.Control)nativeWidget).getBounds().x;
	}
	

	private void animateView(IWidget widget, int x, int y) {
		org.eclipse.swt.widgets.Control childControl = (org.eclipse.swt.widgets.Control)widget.asNativeWidget();
		if (childControl.getListeners(com.ashera.common.AnimationUtils.Animating).length == 0) {
			childControl.addListener(com.ashera.common.AnimationUtils.Animating, (e) -> {
				updateDrawerViewState(widget);
			});
		}
	
		com.ashera.common.AnimationUtils animationUtils = new com.ashera.common.AnimationUtils();
		animationUtils.animate(childControl, x, y, animationDurationInMs);
	}

	private com.ashera.common.BlurredPanel blurredPanel;
	private void createBlurredPanel(Object objValue) {
		IWidget blurredView = findWidgetById("@+id/blurredView");
		if (blurredView != null) {
			((View)blurredView.asWidget()).setVisibility(View.VISIBLE);
			if (blurredPanel == null || blurredPanel.getPanel().isDisposed()) {
				org.eclipse.swt.widgets.Composite blurredComposite = (org.eclipse.swt.widgets.Composite) blurredView.asNativeWidget();
				blurredPanel = new com.ashera.common.BlurredPanel(blurredComposite);
				blurredPanel.getCanvas().addListener(org.eclipse.swt.SWT.MouseUp, (e) -> {
					closeDrawer(objValue);
				});
			}
		}
	}
	
	private void hideBlurredPanel() {
		IWidget blurredView = findWidgetById("@+id/blurredView");
		if (blurredView != null) {
			org.eclipse.swt.widgets.Composite blurredComposite = (org.eclipse.swt.widgets.Composite) blurredView.asNativeWidget();
			blurredComposite.setVisible(false);
			((View)blurredView.asWidget()).setVisibility(View.INVISIBLE);

			if (blurredPanel != null) {
				blurredPanel.dispose();
				blurredPanel = null;
			}
		}
	}
	
	
	private boolean requiresCurrentXCorrection() {
		return true;
	}
	
	//start - animationduration
	private int animationDurationInMs = 200; 
	private void setAnimationDuration(Object objValue) {
		animationDurationInMs = (int) objValue;
		
	}
	//end - animationduration
}
