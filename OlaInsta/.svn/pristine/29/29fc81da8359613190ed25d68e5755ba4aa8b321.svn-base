package com.ola.insta.pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceManager {

	private Context contaxt;

	// Shared Preferences reference
	SharedPreferences pref;

	// Editor reference for Shared preferences
	Editor editor;

	// Context
	Context _context;

	// Shared pref mode
	int PRIVATE_MODE = 0;

	// Sharedpref file name
	private static final String PREFER_NAME = "com.ola.insta.pref.key";


	public PreferenceManager(Context context) {
		// TODO Auto-generated constructor stub
		this._context = context;
		pref = _context.getSharedPreferences(PREFER_NAME, PRIVATE_MODE);
		editor = pref.edit();
	}
	
	public boolean setStringData(String key, String data)
	{
		editor.putString(key, data);
		return editor.commit();
	}
	
	public boolean setBooleanData(String key, Boolean data)
	{
		editor.putBoolean(key, data);
		return editor.commit();
	}
	
	public boolean setIntData(String key, int data)
	{
		editor.putInt(key, data);
		return editor.commit();
	}
	
	public boolean getBooleanData(String key)
	{
		return pref.getBoolean(key, false);
	}
	
	public int getIntData(String key)
	{
		return pref.getInt(key, 0);
	}
	
	public String getStringData(String key)
	{
		return pref.getString(key, null);
	}

}
