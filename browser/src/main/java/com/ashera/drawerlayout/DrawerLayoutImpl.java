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

import org.teavm.jso.dom.html.HTMLElement;

import static com.ashera.widget.IWidget.*;
//end - imports

import androidx.drawerlayout.widget.DrawerLayout;

public class DrawerLayoutImpl extends BaseHasWidgets {
	//start - body
	private HTMLElement htmlElement;
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
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("openDrawer").withType("gravity"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("closeDrawer").withType("gravity"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("scrimColor").withType("color"));
		WidgetFactory.registerAttribute(localName, new WidgetAttribute.Builder().withName("animationDurationInMs").withType("int"));
	
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
		return new DrawerLayoutImpl(groupName, localName);
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
		 nativeRemoveView(w);            
		return remove;
	}
	
	@Override
    public boolean remove(int index) {
		IWidget widget = widgets.get(index);
        boolean remove = super.remove(index);

        if (index + 1 <= drawerLayout.getChildCount()) {
            drawerLayout.removeViewAt(index);
            nativeRemoveView(widget);
        }    
        return remove;
    }

	private void nativeRemoveView(IWidget widget) {
		r.android.animation.LayoutTransition layoutTransition = drawerLayout.getLayoutTransition();
		if (layoutTransition != null && (
				layoutTransition.isTransitionTypeEnabled(r.android.animation.LayoutTransition.CHANGE_DISAPPEARING) ||
				layoutTransition.isTransitionTypeEnabled(r.android.animation.LayoutTransition.DISAPPEARING)
				)) {
			addToBufferedRunnables(() -> ViewGroupImpl.nativeRemoveView(widget));          
		} else {
			ViewGroupImpl.nativeRemoveView(widget);
		}
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
		public IWidget getWidget() {
			return DrawerLayoutImpl.this;
		}
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
        private Map<String, IWidget> templates;
    	@Override
    	public r.android.view.View inflateView(java.lang.String layout) {
    		if (templates == null) {
    			templates = new java.util.HashMap<String, IWidget>();
    		}
    		IWidget template = templates.get(layout);
    		if (template == null) {
    			template = (IWidget) quickConvert(layout, "template");
    			templates.put(layout, template);
    		}
    		IWidget widget = template.loadLazyWidgets(DrawerLayoutImpl.this.getParent());
    		return (View) widget.asWidget();
    	}        
        
    	@Override
		public void remeasure() {
			getFragment().remeasure();
		}
    	
        @Override
		public void removeFromParent() {
        	DrawerLayoutImpl.this.getParent().remove(DrawerLayoutImpl.this);
		}
        @Override
        public void getLocationOnScreen(int[] appScreenLocation) {
        	appScreenLocation[0] = htmlElement.getBoundingClientRect().getLeft();
        	appScreenLocation[1] = htmlElement.getBoundingClientRect().getTop();
        }
        @Override
        public void getWindowVisibleDisplayFrame(r.android.graphics.Rect displayFrame){
        	
        	org.teavm.jso.dom.html.TextRectangle boundingClientRect = htmlElement.getBoundingClientRect();
			displayFrame.top = boundingClientRect.getTop();
        	displayFrame.left = boundingClientRect.getLeft();
        	displayFrame.bottom = boundingClientRect.getBottom();
        	displayFrame.right = boundingClientRect.getRight();
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
		public void setMyAttribute(String name, Object value) {
			if (name.equals("state0")) {
				setState0(value);
				return;
			}
			if (name.equals("state1")) {
				setState1(value);
				return;
			}
			if (name.equals("state2")) {
				setState2(value);
				return;
			}
			if (name.equals("state3")) {
				setState3(value);
				return;
			}
			if (name.equals("state4")) {
				setState4(value);
				return;
			}
			DrawerLayoutImpl.this.setAttribute(name, value, true);
		}
        @Override
        public void setVisibility(int visibility) {
            super.setVisibility(visibility);
            ((HTMLElement)asNativeWidget()).getStyle().setProperty("display", visibility != View.VISIBLE ? "none" : "block");
            
        }
		 @Override
	        public void smoothSlideViewTo(r.android.view.View drawerView, int x, int y) {
			 DrawerLayoutImpl.this.smoothSlideViewTo(drawerView, x, y);
	        }
        
    	public void setState0(Object value) {
    		ViewImpl.setState(DrawerLayoutImpl.this, 0, value);
    	}
    	public void setState1(Object value) {
    		ViewImpl.setState(DrawerLayoutImpl.this, 1, value);
    	}
    	public void setState2(Object value) {
    		ViewImpl.setState(DrawerLayoutImpl.this, 2, value);
    	}
    	public void setState3(Object value) {
    		ViewImpl.setState(DrawerLayoutImpl.this, 3, value);
    	}
    	public void setState4(Object value) {
    		ViewImpl.setState(DrawerLayoutImpl.this, 4, value);
    	}
        	public void state0() {
        		ViewImpl.state(DrawerLayoutImpl.this, 0);
        	}
        	public void state1() {
        		ViewImpl.state(DrawerLayoutImpl.this, 1);
        	}
        	public void state2() {
        		ViewImpl.state(DrawerLayoutImpl.this, 2);
        	}
        	public void state3() {
        		ViewImpl.state(DrawerLayoutImpl.this, 3);
        	}
        	public void state4() {
        		ViewImpl.state(DrawerLayoutImpl.this, 4);
        	}
                        
        public void stateYes() {
        	ViewImpl.stateYes(DrawerLayoutImpl.this);
        	
        }
        
        public void stateNo() {
        	ViewImpl.stateNo(DrawerLayoutImpl.this);
        }
     
		@Override
		public void endViewTransition(r.android.view.View view) {
			super.endViewTransition(view);
			runBufferedRunnables();
		}
	}
	@Override
	public Class getViewClass() {
		return DrawerLayoutExt.class;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void setAttribute(WidgetAttribute key, String strValue, Object objValue, ILifeCycleDecorator decorator) {
		ViewGroupImpl.setAttribute(this, key, strValue, objValue, decorator);
		Object nativeWidget = asNativeWidget();
		switch (key.getAttributeName()) {
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
			case "openDrawer": {


		openDrawer(objValue);



			}
			break;
			case "closeDrawer": {


		closeDrawer(objValue);



			}
			break;
			case "scrimColor": {


		setScrimColor(objValue);



			}
			break;
			case "animationDurationInMs": {


		setAnimationDuration(objValue);



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
        return htmlElement;
    }
    private void nativeCreate(Map<String, Object> params) {
    	htmlElement = org.teavm.jso.dom.html.HTMLDocument.current().createElement("div");
    	htmlElement.getStyle().setProperty("box-sizing", "border-box");
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
				ViewImpl.translateWithAnimation(child.asNativeWidget(), x, y, animationDurationInMs, (currentX, currentY) -> {
					updateDrawerViewState(child, currentX);
				});
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
		if (Math.abs(contentViewWidth - ViewImpl.getX(nativeWidget)) >= view.getMeasuredWidth()/2) {
			openDrawer(gravity);
		} else {
			closeDrawer(gravity);
		}
	}

	private void handleLeftDrawerPanEnd(IWidget widget, View view) {
		Object nativeWidget = widget.asNativeWidget();
		int gravity = getGravity(view);

		if (Math.abs(ViewImpl.getX(nativeWidget)) <= view.getMeasuredWidth() / 2) {
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
						correction = ViewImpl.getX(widget.asNativeWidget());
					}
					int x = correction + (eventX - startX);
					if (x <= 0) {
						mouseMoved = true;
						updateX(widget.asNativeWidget(), x);
						drawerLayout.onViewPositionChanged(view, x, 0, 0, 0);
					}
				}
			}
			
			if (isRightDrawer(view)) {
				if (drawerLayout.getDrawerLockMode(view) != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {

					int initX = ((View) view.getParent()).getMeasuredWidth() - view.getMeasuredWidth();
					int correction = requiresCurrentXCorrection() ? ViewImpl.getX(widget.asNativeWidget()) : initX;
					int x = correction + (eventX - startX);
					
					if (x >= initX ) {
						mouseMoved = true;
						updateX(widget.asNativeWidget(), x);
						drawerLayout.onViewPositionChanged(view, x, 0, 0, 0);
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
								drawerLayout.onViewPositionChanged(view, x, 0, 0, 0);
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
								drawerLayout.onViewPositionChanged(view, x, 0, 0, 0);
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
	


	private int animationDurationInMs = 200; 
	private void setAnimationDuration(Object objValue) {
		animationDurationInMs = (int) objValue;
		
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
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, drawerView,slideOffset);
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
		if (strValue != null && !strValue.isEmpty() && !strValue.trim().startsWith("+")) {
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
    
    if (w.getComponentId() != null) {
    	obj.put("componentId", w.getComponentId());
    }
    
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
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, drawerView);
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
		if (strValue != null && !strValue.isEmpty() && !strValue.trim().startsWith("+")) {
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
    
    if (w.getComponentId() != null) {
    	obj.put("componentId", w.getComponentId());
    }
    
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
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, drawerView);
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
		if (strValue != null && !strValue.isEmpty() && !strValue.trim().startsWith("+")) {
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
    
    if (w.getComponentId() != null) {
    	obj.put("componentId", w.getComponentId());
    }
    
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
		    if (EventCommandFactory.hasCommand(commandName)) {
		    	 EventCommandFactory.getCommand(commandName).executeCommand(w, obj, newState);
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
		if (strValue != null && !strValue.isEmpty() && !strValue.trim().startsWith("+")) {
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
    
    if (w.getComponentId() != null) {
    	obj.put("componentId", w.getComponentId());
    }
    
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
			drawerLayout.setId((int) quickConvert(id, "id"));
		}
	}
	
    
    @Override
    public void setVisible(boolean b) {
        ((View)asWidget()).setVisibility(b ? View.VISIBLE : View.GONE);
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
public DrawerLayoutCommandBuilder setScrimColor(String value) {
	Map<String, Object> attrs = initCommand("scrimColor");
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
}
public class DrawerLayoutBean extends com.ashera.layout.ViewGroupImpl.ViewGroupBean{
		public DrawerLayoutBean() {
			super(DrawerLayoutImpl.this);
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

public void openDrawer(String value) {
	getBuilder().reset().openDrawer(value).execute(true);
}

public void closeDrawer(String value) {
	getBuilder().reset().closeDrawer(value).execute(true);
}

public void setScrimColor(String value) {
	getBuilder().reset().setScrimColor(value).execute(true);
}

public void setAnimationDurationInMs(int value) {
	getBuilder().reset().setAnimationDurationInMs(value).execute(true);
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

	private void setScrimColor(Object objValue) {
		IWidget blurredWidget = findWidgetById("@+id/blurredView");
		if (blurredWidget != null) {
			Object nativeWidget = blurredWidget.asNativeWidget();
			((HTMLElement) nativeWidget).getStyle().setProperty("background-color", (String) objValue);
		}
	}
	
	
	
	private void createBlurredPanel(Object objValue) {
		IWidget blurredWidget = findWidgetById("@+id/blurredView");
		if (blurredWidget != null) {
			Object nativeWidget = blurredWidget.asNativeWidget();
			ViewImpl.setVisibility(blurredWidget, nativeWidget, View.VISIBLE);
			ViewImpl.setOnClick(this, nativeWidget,  new View.OnClickListener() {
				@Override
				public void onClick(r.android.view.View v) {
					closeDrawer(objValue);
				}
			});
		}
	}
	
	private void hideBlurredPanel() {
		IWidget blurredWidget = findWidgetById("@+id/blurredView");
		if (blurredWidget != null) {
			ViewImpl.setVisibility(blurredWidget, blurredWidget.asNativeWidget(), View.INVISIBLE);
		}
	}

	private void updateX(Object nativeWidget, int x) {
		((HTMLElement) nativeWidget).getStyle().setProperty("left", x + "px");
	}
	
	public interface HtmlMouseEvent extends org.teavm.jso.dom.events.Event {
	    @org.teavm.jso.JSProperty
	    int getClientX();
	    
	    @org.teavm.jso.JSProperty
	    int getClientY();
	}
	private class MouseListener implements org.teavm.jso.dom.events.EventListener<HtmlMouseEvent> {
		@Override
		public void handleEvent(HtmlMouseEvent event) {
			int eventX = event.getClientX();
			switch (event.getType()) {
			case "mousedown":
				handlePanStart(eventX);
				break;
			case "mousemove":
				handlePanDrag(eventX);
				break;
			case "mouseup":
				handlePanEnd();
				break;

			default:
				break;
			}
		}
	}
	
	private class DrawerDragListener implements org.teavm.jso.dom.events.EventListener<HtmlMouseEvent> {
		private IWidget widget;
		public DrawerDragListener(IWidget widget) {
			this.widget = widget;
		}

		@Override
		public void handleEvent(HtmlMouseEvent event) {

			int eventX = event.getClientX();
			switch (event.getType()) {
			case "mousedown":
				handlePanStart(eventX);
				break;
			case "mousemove":
				handlePanDragOfDrawer(eventX, widget);
				break;
			case "mouseup":
				handlePanEndOfDrawer(widget);
				break;

			default:
				break;
			}

		}
	}
	@Override
	public void initialized() {
		super.initialized();
		MouseListener listener = new MouseListener();
		
		for (IWidget widget : widgets) {
			View view = (View) widget.asWidget();
			if ("@+id/blurredView".equals(widget.getId())) {
				htmlElement.addEventListener("mousemove", listener, false);
				htmlElement.addEventListener("mouseup", listener, false);
			}
			if (isLeftDrawer(view) || isRightDrawer(view)) {
				DrawerDragListener drawerDragListener = new DrawerDragListener(widget);
				HTMLElement htmlElement = (HTMLElement) widget.asNativeWidget();
				htmlElement.addEventListener("mousedown", drawerDragListener, false);
				htmlElement.addEventListener("mouseup", drawerDragListener, false);
				htmlElement.addEventListener("mousemove", drawerDragListener, false);
			}
			if (isContentView(widget)) {
				HTMLElement htmlElement = (HTMLElement) widget.asNativeWidget();
				htmlElement.addEventListener("mousedown", listener, false);
				htmlElement.addEventListener("mouseup", listener, false);
				htmlElement.addEventListener("mousemove", listener, false);
			}
		}
	}
	
	private boolean requiresCurrentXCorrection() {
		return false;
	}
}
