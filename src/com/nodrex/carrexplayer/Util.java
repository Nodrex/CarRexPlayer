package com.nodrex.carrexplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * This class contains useful methods and utilities for Android.
 * 
 * @author Nodar Tchumbadze
 * @since 2014
 * @version 1.0
 */
public class Util {

	/**
	 * Shows toast with given text.
	 * @param activity
	 * @param text
	 */
	public static void toast(Activity activity, String text) {
		if (activity == null || text == null) return;
		Toast toast = Toast.makeText(activity, text, Toast.LENGTH_SHORT);
		toast.show();
	}

	/**
	 * Keeps screen active.
	 * @param activity
	 */
	public static void keepScreenOn(Activity activity) {
		if (activity == null) return;
		Window window = activity.getWindow();
		if (window == null) return;
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	/**
	 * Sets full screen and hides software navigation buttons if that one exists.
	 * @param window activity.getWindow()
	 */
	@SuppressLint("InlinedApi")
	public static void hideNavigationButtons(Window window){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			View decorView = window.getDecorView();
			if (decorView == null) return;
			decorView.setSystemUiVisibility(
				View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
	
	/**
	 * Hides status bar.
	 * @param window activity.getWindow()
	 */
	public static void hideStatusBar(Window window){
		if (window == null) return;
		WindowManager.LayoutParams attrs = window.getAttributes();
		if (attrs == null) return;
		attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		window.setAttributes(attrs);
	}
	
}
