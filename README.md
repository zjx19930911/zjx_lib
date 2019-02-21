# baseLib
小西瓜的Android项目基础库



#### gradle引用
```
allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```

```
compile 'com.github.zjx19930911:zjx_base:54e0146971'

```	

#### 在application中初始化
```
   BaseConfig.init(getApplicationContext(),"主颜色");
  <style name="AppTheme" parent="QMUI.Compat">
```	
#### 主题更换
```

  <style name="AppTheme" parent="QMUI.Compat">
```
#### 在manifest添加
```
     <uses-permission android:name="android.permission.RECEIVE_USER_PRESENT" />
     <uses-permission android:name="android.permission.GET_TASKS" />
     <uses-permission android:name="android.permission.SET_DEBUG_APP" />
     <uses-permission android:name="android.permission.GET_ACCOUNTS" />
     <uses-permission android:name="android.permission.USE_CREDENTIALS" />
     <uses-permission android:name="android.permission.MANAGE_ACCOUNTS" />
 
 
     <uses-permission android:name="android.permission.WRITE_SETTINGS" />
     <uses-permission android:name="android.permission.VIBRATE" />
     <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
     <uses-permission android:name="android.permission.WAKE_LOCK" />
     <uses-permission android:name="android.permission.CAMERA" />
     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
     <uses-permission android:name="android.permission.CALL_PHONE" />
     <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
     <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
     <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     <uses-permission android:name="android.permission.INTERNET" />
     <uses-permission android:name="android.permission.READ_LOGS" />
     <uses-permission android:name="com.android.alarm.permission.SET_ALARM"/>
 
     <uses-permission android:name="android.permission.BLUETOOTH"/>
     <uses-permission android:name="android.permission.BLUETOOTH_ADMIN"/>
     <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
     <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
     <uses-permission android:name="android.permission.WRITE_CONTACTS" />
     <uses-permission android:name="android.permission.READ_CONTACTS" />
            
```
```
   <activity
            android:name="com.yalantis.ucrop.UCropActivity"
            android:screenOrientation="portrait"
            android:theme="@style/Theme.AppCompat.Light.NoActionBar" />
            
```
```
        <activity
            android:name="me.iwf.photopicker.PhotoPickerActivity"
            android:theme="@style/Theme.AppCompat.NoActionBar" />
            
```
```
       <activity android:name="me.iwf.photopicker.PhotoPagerActivity"
                  android:theme="@style/Theme.AppCompat.NoActionBar"/>
            
```



### 感谢

1. 小西瓜
2. 小西瓜
3. 小西瓜