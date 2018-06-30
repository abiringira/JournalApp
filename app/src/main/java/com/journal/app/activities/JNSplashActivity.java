package com.journal.app.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class JNSplashActivity extends JNBaseActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jn_activity_splash);
        if (!isNetworkAvailable()) {
            Log.e("cONNETING","Error Connecting to the server");
            return;

        } else {
            startTheApp();
        }
      /*  else {
            ERPPingResult pingResult = new ERPPingResult();
            serviceType = ERPClient.ERPServiceType.PING;
            ERPPingController controller = (ERPPingController) ERPControllerFactory.createController(ERPControllerFactory.ERPControllerType.PING_SERVER,this);
            if(controller != null) {
                controller.setActivity(this);
                controller.setServiceType(ERPClient.ERPServiceType.PING);
                controller.execute(pingResult);

            }*/


        }


    public void startTheApp() {
        Intent intent = new Intent(this, JNAppLauncherActivity.class);
        startActivity(intent);
    }






}
