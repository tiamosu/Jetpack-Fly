package com.tiamosu.fly.kts

import android.app.*
import android.app.admin.DevicePolicyManager
import android.app.job.JobScheduler
import android.app.usage.UsageStatsManager
import android.appwidget.AppWidgetManager
import android.content.ClipboardManager
import android.hardware.SensorManager
import android.location.LocationManager
import android.media.AudioManager
import android.media.MediaRouter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.BatteryManager
import android.os.PowerManager
import android.os.Vibrator
import android.os.storage.StorageManager
import android.telecom.TelecomManager
import android.telephony.CarrierConfigManager
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.accessibility.AccessibilityManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService

/**
 * @author ti
 * @date 2022/7/6.
 */

inline fun <reified T> getSystemService(): T? = appContext.getSystemService()

val windowManager by lazy { getSystemService<WindowManager>() }
val clipboardManager by lazy { getSystemService<ClipboardManager>() }
val layoutInflater by lazy { getSystemService<LayoutInflater>() }
val activityManager by lazy { getSystemService<ActivityManager>() }
val powerManager by lazy { getSystemService<PowerManager>() }
val alarmManager by lazy { getSystemService<AlarmManager>() }
val notificationManager by lazy { getSystemService<NotificationManager>() }
val keyguardManager by lazy { getSystemService<KeyguardManager>() }
val locationManager by lazy { getSystemService<LocationManager>() }
val searchManager by lazy { getSystemService<SearchManager>() }
val storageManager by lazy { getSystemService<StorageManager>() }
val vibrator by lazy { getSystemService<Vibrator>() }
val connectivityManager by lazy { getSystemService<ConnectivityManager>() }
val wifiManager by lazy { getSystemService<WifiManager>() }
val audioManager by lazy { getSystemService<AudioManager>() }
val mediaRouter by lazy { getSystemService<MediaRouter>() }
val telephonyManager by lazy { getSystemService<TelephonyManager>() }
val sensorManager by lazy { getSystemService<SensorManager>() }
val subscriptionManager by lazy { getSystemService<SubscriptionManager>() }
val carrierConfigManager by lazy { getSystemService<CarrierConfigManager>() }
val inputMethodManager by lazy { getSystemService<InputMethodManager>() }
val uiModeManager by lazy { getSystemService<UiModeManager>() }
val downloadManager by lazy { getSystemService<DownloadManager>() }
val batteryManager by lazy { getSystemService<BatteryManager>() }
val jobScheduler by lazy { getSystemService<JobScheduler>() }
val accessibilityManager by lazy { getSystemService<AccessibilityManager>() }
val appWidgetManager by lazy { getSystemService<AppWidgetManager>() }
val telecomManager by lazy { getSystemService<TelecomManager>() }
val devicePolicyManager by lazy { getSystemService<DevicePolicyManager>() }
val usageStatsManager by lazy { getSystemService<UsageStatsManager>() }