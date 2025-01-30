# Drawer

Drawer project which adds support for android drawer layout.

## Installation
To install the plugin use:

```
cordova plugin add https://github.com/AsheraCordova/Drawer.git
```

## Important Links
https://asheracordova.github.io/

https://asheracordova.github.io/doc/help-doc.html

https://asheracordova.github.io/doc/androidx/drawerlayout/widget/DrawerLayout.html

## Widgets
* androidx.drawerlayout.widget.DrawerLayout

## Custom Attributes

The following table lists the custom attributes used in widgets:
### androidx.drawerlayout.widget.DrawerLayout
Name                	| Description
-------------       	| -------------
animationDurationInMs | Controls the animation duration in milli seconds of opening and closing of the drawer. e.g 3000 (3 seconds).


The following shows an example of DrawerLayout:

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:ignore="MissingPrefix">

    <androidx.drawerlayout.widget.DrawerLayout
        android:id="@+id/drawer_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <LinearLayout
            swtAttachEventBubbler="mousedown,mouseup,mousemove"
            zIndex="0"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">
            <Button
                android:onClick="openDrawer"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Open Drawer"></Button>
        </LinearLayout>

        <View
            android:id="@+id/blurredView"
            zIndex="1"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:visibility="invisible" />

        <LinearLayout
            android:id="@+id/drawerlayoutstart"
            style="@style/drawer_view"
            zIndex="2"
            android:orientation="horizontal"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_gravity="start">
            <TextView
                style="@style/h2_white_bold"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Test"></TextView>

        </LinearLayout>
    </androidx.drawerlayout.widget.DrawerLayout>
</LinearLayout>
```

The following are some important points to be noted:
* blurredView - This is required for swt implementation where in a blurred view is put in background as SWT does not support transparency.
* swtAttachEventBubbler - As swt does not support event bubbling, manual event bubbling has to be configured.
* drawerlayoutstart and drawerlayoutend - the ids are hard coded in the implementation and should not be changed. This is the view which contains the navigation drawer view.
* zIndex - this should be configured so that it works properly across all platforms.
