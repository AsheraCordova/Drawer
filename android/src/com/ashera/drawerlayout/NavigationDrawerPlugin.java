package com.ashera.drawerlayout;

import com.ashera.widget.WidgetFactory;

public class NavigationDrawerPlugin  {
    public static void initPlugin() {
    	//start - widgets
        WidgetFactory.register(new com.ashera.drawerlayout.DrawerLayoutImpl());
        //end - widgets
    }
}
