package com.example.taskscheduling.Strictmode;

public class StrictMod{
        public static void StrictMode() {
            android.os.StrictMode.ThreadPolicy policy = new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
            android.os.StrictMode.setThreadPolicy(policy);
        }

}