package com.juntcompany.godandgodsummer.Manager;

import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.juntcompany.godandgodsummer.Util.GodAndGod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by EOM on 2016-07-15.
 */
public class PropertyManager {

    private static PropertyManager instance;

    public static PropertyManager getInstance(){
        if(instance == null){
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private PropertyManager(){
        mPrefs = PreferenceManager.getDefaultSharedPreferences(GodAndGod.getContext());
        mEditor = mPrefs.edit();
    }

    private static final String FIELD_USER_EMAIL = "user_email";
    public void setUserEmail(String email){
        mEditor.putString(FIELD_USER_EMAIL, email);
        mEditor.commit();
    }
    public String getUserEmail(){
        return mPrefs.getString(FIELD_USER_EMAIL, "");
    }

    private static final String FIELD_USER_PHONE_NUMBER = "user_phone_number";
    public void setUserPhoneNumber(String phoneNumber){
        mEditor.putString(FIELD_USER_PHONE_NUMBER, phoneNumber);
        mEditor.commit();
    }
    public String getUserPhoneNumber(){
        return mPrefs.getString(FIELD_USER_PHONE_NUMBER, "");
    }

    private static final String FIELD_USER_PASSWORD = "user_password";
    public void setUserPassword(String password){
        mEditor.putString(FIELD_USER_PASSWORD, password);
        mEditor.commit();
    }
    public String getUserPassword(){
        return mPrefs.getString(FIELD_USER_PASSWORD, "");
    }

    private static final String FIELD_USER_ID = "user_id";
    public void setUserId(int userId){
        mEditor.putInt(FIELD_USER_ID, userId);
        mEditor.commit();
    }
    public int getUserId(){
        return mPrefs.getInt(FIELD_USER_ID, 0);
    }

    private static final String FIELD_USER_NAME = "user_name";
    public void setUserName(String userName){
        mEditor.putString(FIELD_USER_NAME, userName);
        mEditor.commit();
    }
    public String getUserName(){
        return mPrefs.getString(FIELD_USER_NAME, "");
    }



    private static final String FIELD_PROFILE_IMAGE = "profile_image";
    public void setProfileImage(String filePath){
        mEditor.putString(FIELD_PROFILE_IMAGE, filePath);
        mEditor.commit();
    }
    public String getProfileImage(){
        return mPrefs.getString(FIELD_PROFILE_IMAGE, "");
    }

    private static final String FIELD_USER_INTRODUCTION = "user_introduction";
    public void setUserIntroduction(String filePath){
        mEditor.putString(FIELD_PROFILE_IMAGE, filePath);
        mEditor.commit();
    }
    public String getUserIntroduction(){
        return mPrefs.getString(FIELD_USER_INTRODUCTION, "");
    }

    private static final String FIELD_CURRENT_TARGET_FAITH = "current_target_faith";
    public void setCurrentTargetFaith(int faith){
        mEditor.putInt(FIELD_CURRENT_TARGET_FAITH, faith);
        mEditor.commit();
    }
    public int getCurrentTargetFaith(){
        return mPrefs.getInt(FIELD_CURRENT_TARGET_FAITH, 0);
    }

    private static final String FIELD_CURRENT_TARGET_POPULAR = "current_target_popular";
    public void setCurrentTargetPopular(int popular){
        mEditor.putInt(FIELD_CURRENT_TARGET_POPULAR, popular);
        mEditor.commit();
    }
    public int getCurrentTargetPopular(){
        return mPrefs.getInt(FIELD_CURRENT_TARGET_POPULAR, 0);
    }

    private static final String FIELD_CURRENT_TARGET_DONATE = "current_target_donate";
    public void setCurrentTargetDonate(int donate){
        mEditor.putInt(FIELD_CURRENT_TARGET_DONATE, donate);
        mEditor.commit();
    }
    public int getCurrentTargetDonate(){
        return mPrefs.getInt(FIELD_CURRENT_TARGET_DONATE, 0);
    }

    private static final String FIELD_CURRENT_TARGET_FRIENDLY = "current_target_friendly";
    public void setCurrentTargetFriendly(
            int friendly){
        mEditor.putInt(FIELD_CURRENT_TARGET_FRIENDLY, friendly);
        mEditor.commit();
    }
    public int getCurrentTargetFriendly(){
        return mPrefs.getInt(FIELD_CURRENT_TARGET_FRIENDLY, 0);
    }

    private static final String FIELD_PREVIOUS_TARGET_FAITH = "previous_target_faith";
    public void setPreviousTargetFaith(int faith){
        mEditor.putInt(FIELD_PREVIOUS_TARGET_FAITH, faith);
        mEditor.commit();
    }
    public int getPreviousTargetFaith(){
        return mPrefs.getInt(FIELD_PREVIOUS_TARGET_FAITH, 0);
    }

    private static final String FIELD_PREVIOUS_TARGET_POPULAR = "previous_target_popular";
    public void setPreviousTargetPopular(int popular){
        mEditor.putInt(FIELD_PREVIOUS_TARGET_POPULAR, popular);
        mEditor.commit();
    }
    public int getPreviousTargetPopular(){
        return mPrefs.getInt(FIELD_PREVIOUS_TARGET_POPULAR, 0);
    }

    private static final String FIELD_PREVIOUS_TARGET_DONATE = "previous_target_donate";
    public void setPreviousTargetDonate(int donate){
        mEditor.putInt(FIELD_PREVIOUS_TARGET_DONATE, donate);
        mEditor.commit();
    }
    public int getPreviousTargetDonate(){
        return mPrefs.getInt(FIELD_PREVIOUS_TARGET_DONATE, 0);
    }

    private static final String FIELD_PREVIOUS_TARGET_FRIENDLY = "previous_target_friendly";
    public void setPreviousTargetFriendly(int friendly){
        mEditor.putInt(FIELD_PREVIOUS_TARGET_FRIENDLY, friendly);
        mEditor.commit();
    }
    public int getPreviousTargetFriendly(){
        return mPrefs.getInt(FIELD_PREVIOUS_TARGET_FRIENDLY, 0);
    }

    private static final String FIELD_LAST_ACCESS_TIME = "last_access_time";
    public void setLastAccessTime(String access_time){
        mEditor.putString(FIELD_LAST_ACCESS_TIME, access_time);
        mEditor.commit();
    }
    public String getLastAccessTime(){
        return mPrefs.getString(FIELD_LAST_ACCESS_TIME, "");
    }


    private static final String FIELD_ROOM_LIST = "room_list";
    public void setRoomList(String room_list){
        mEditor.putString(FIELD_ROOM_LIST, room_list);
        mEditor.commit();
    }
    public String getRoomList(){
        return mPrefs.getString(FIELD_ROOM_LIST, "");
    }

    private static final String REG_ID = "registration_id";
    public void setRegID(String regid){
        mEditor.putString(REG_ID, regid);
        mEditor.commit();
    }
    public String getRegID(){ return mPrefs.getString(REG_ID, ""); }

    private static final String INITIAL_FRAGMENT = "InitialFragmentOfMainActivity";
    public void setInitialFragmentOfMainActivity(int choice){
        mEditor.putInt(INITIAL_FRAGMENT, choice);
        mEditor.commit();
    }
    public int getInitialFragmentOfMainActivity(){ return mPrefs.getInt(INITIAL_FRAGMENT, 0); }

    public void setRoomNOP(String key, int nop){
        mEditor.putInt(key, nop);
        mEditor.commit();
    }
    public int getRoomNOP(String key){
        return mPrefs.getInt(key, 0);
    }
}
