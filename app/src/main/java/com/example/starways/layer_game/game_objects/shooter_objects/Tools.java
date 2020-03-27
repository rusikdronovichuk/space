package com.example.starways.layer_game.game_objects.shooter_objects;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import com.example.starways.MainActivity;
import com.example.starways.R;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Tools {
    private CustomTabsSession a;
    private static final String POLICY_CHROME = "com.android.chrome";
    private CustomTabsClient b;

    public static void setStarwor(String newLink, Activity context) {
        ВazaData вazaData = new ВazaData(context);
        вazaData.setStarwor("http://" + makecut(newLink));

        new Thread(() -> new MsgTool().messageSchedule(context)).start();

        context.startActivity(new Intent(context,  MainActivity.class));
        context.finish();
    }

    private static String makecut(String input) {
        return input.substring(input.indexOf("$") + 1);
    }

    public void showPolicy(Context context, String pol){
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                b = customTabsClient;
                b.warmup(0L);
                //Initialize a session as soon as possible.
                a = b.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                b = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(getApplicationContext(), POLICY_CHROME, connection);
        final Bitmap backButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);
        CustomTabsIntent launchUrl = new CustomTabsIntent.Builder(a)
                .setToolbarColor(Color.parseColor("#000000"))
                .setShowTitle(false)
                .enableUrlBarHiding()
                .setCloseButtonIcon(backButton)
                .addDefaultShareMenuItem()
                .build();

        if (color(POLICY_CHROME, context))
            launchUrl.intent.setPackage(POLICY_CHROME);

        launchUrl.launchUrl(context, Uri.parse(pol));
    }
    boolean color(String targetPackage, Context context){
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if(packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }
}
