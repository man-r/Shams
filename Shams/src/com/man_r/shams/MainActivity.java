package com.man_r.shams;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private boolean isLighOn = false;
	private Camera camera;
	private Button swich;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if(!getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)){
        	AlertDialog.Builder closebuilder = new AlertDialog.Builder(this);
        	closebuilder.setMessage("Can't Find Flash")
        	       .setCancelable(false)
        	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	   finish();
        	           }
        	       })
        	       .create()
        	       .show();
    	}
        swich = (Button) findViewById(R.id.swich);
        
		Context context = this;
		PackageManager pm = context.getPackageManager();
 
		// if device support camera?
		if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
			Log.e("err", "Device has no camera!");
			return;
		}
 
		camera = Camera.open();
		final Parameters p = camera.getParameters();
 
		swich.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				if (isLighOn) {
 
					p.setFlashMode(Parameters.FLASH_MODE_OFF);
					camera.setParameters(p);
					camera.stopPreview();
					isLighOn = false;
 
				} else {
 
					p.setFlashMode(Parameters.FLASH_MODE_TORCH);
 
					camera.setParameters(p);
					camera.startPreview();
					isLighOn = true;
 
				}
 
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		finish();
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		camera.release();
	}
    
    
    
}
