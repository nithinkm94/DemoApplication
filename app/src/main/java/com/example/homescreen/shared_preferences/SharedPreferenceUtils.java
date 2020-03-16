package com.example.homescreen.shared_preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class SharedPreferenceUtils {

	//	private static SharedPreferenceUtils mSharedPreferenceUtils;
	Context mContext;
	SharedPreferences mSharedPreferences;
	SharedPreferences.Editor mSharedPreferencesEditor;


	public SharedPreferenceUtils(Context context) {

		mContext = context;
		mSharedPreferences = context.getSharedPreferences(mContext.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
		mSharedPreferencesEditor = mSharedPreferences.edit();
	}

	/**
	 * Creates single instance of SharedPreferenceUtils
	 *
	 * @param context context of Activity or Service
	 * @return Returns instance of SharedPreferenceUtils
	//	 */
//	public static synchronized SharedPreferenceUtils getInstance(Context context) {
//
//		if (mSharedPreferenceUtils == null) {
//			mSharedPreferenceUtils = new SharedPreferenceUtils(context);
//		}
//		return mSharedPreferenceUtils;
//	}

	/**
	 * Stores String value in preference
	 *
	 * @param key   key of preference
	 * @param value value for that key
	 */
	public void setValue(String key, String value) {
		mSharedPreferencesEditor.putString(key, value);
//		mSharedPreferencesEditor.commit();
		mSharedPreferencesEditor.apply();
	}

	/**
	 * Stores int value in preference
	 *
	 * @param key   key of preference
	 * @param value value for that key
	 */
	public void setValue(String key, int value) {
		mSharedPreferencesEditor.putInt(key, value);
//		mSharedPreferencesEditor.commit();
		mSharedPreferencesEditor.apply();
	}

	/**
	 * Stores Double value in String format in preference
	 *
	 * @param key   key of preference
	 * @param value value for that key
	 */
	public void setValue(String key, double value) {
		setValue(key, Double.toString(value));
	}

	/**
	 * Stores long value in preference
	 *
	 * @param key   key of preference
	 * @param value value for that key
	 */
	public void setValue(String key, long value) {
		mSharedPreferencesEditor.putLong(key, value);
//		mSharedPreferencesEditor.commit();
		mSharedPreferencesEditor.apply();
	}

	/**
	 * Stores boolean value in preference
	 *
	 * @param key   key of preference
	 * @param value value for that key
	 */
	public void setValue(String key, boolean value) {
		mSharedPreferencesEditor.putBoolean(key, value);
//		mSharedPreferencesEditor.commit();
		mSharedPreferencesEditor.apply();
	}

	/**
	 * Retrieves String value from preference
	 *
	 * @param key          key of preference
	 * @param defaultValue default value if no key found
	 */
	public String getStringValue(String key, String defaultValue) {
		return mSharedPreferences.getString(key, defaultValue);
	}

	/**
	 * Retrieves int value from preference
	 *
	 * @param key          key of preference
	 * @param defaultValue default value if no key found
	 */
	public int getIntValue(String key, int defaultValue) {
		return mSharedPreferences.getInt(key, defaultValue);
	}

	/**
	 * Retrieves long value from preference
	 *
	 * @param key          key of preference
	 * @param defaultValue default value if no key found
	 */
	public long getLongValue(String key, long defaultValue) {
		return mSharedPreferences.getLong(key, defaultValue);
	}

	/**
	 * Retrieves boolean value from preference
	 *
	 * @param keyFlag      key of preference
	 * @param defaultValue default value if no key found
	 */
	public boolean getBoolanValue(String keyFlag, boolean defaultValue) {
		return mSharedPreferences.getBoolean(keyFlag, defaultValue);
	}

	/**
	 * Removes key from preference
	 *
	 * @param key key of preference that is to be deleted
	 */
	public void removeKey(String key) {
		if (mSharedPreferencesEditor != null) {
			mSharedPreferencesEditor.remove(key);
//			mSharedPreferencesEditor.commit();
			mSharedPreferencesEditor.apply();
		}
	}


	/**
	 * Clears all the preferences stored
	 */
	public void clear() {
		mSharedPreferencesEditor.clear().commit();
		mSharedPreferencesEditor.clear().apply();
	}


	public void setObjectValue(String key, Object object){

		Gson gson = new Gson();
		String json = gson.toJson(object);
		mSharedPreferencesEditor.putString(key, json);
//		mSharedPreferencesEditor.commit();
		mSharedPreferencesEditor.apply();
	}

	public Object getObject(String key){
		Gson gson = new Gson();
		String json = mSharedPreferences.getString(key, "");
		Object obj = gson.fromJson(json, Object.class);
		return obj;

	}

	public void saveArrayList(ArrayList<String> list, String key){
		Gson gson = new Gson();
		String json = gson.toJson(list);
		mSharedPreferencesEditor.putString(key, json);
		mSharedPreferencesEditor.apply();     // This line is IMPORTANT !!!
	}

	public ArrayList<String> getArrayList(String key){
		Gson gson = new Gson();
		String json = mSharedPreferences.getString(key, null);
		Type type = new TypeToken<ArrayList<String>>() {}.getType();
		return gson.fromJson(json, type);
	}

}
