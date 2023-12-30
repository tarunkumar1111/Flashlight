package com.example.flashlight;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    ImageButton imageButton;
    boolean state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButton=findViewById(R.id.btnlight);
        Dexter.withContext(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                runFlashLigght();

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(MainActivity.this,"camera permission is required",Toast.LENGTH_SHORT).toString();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

            }
        }).check();



    }

    private void runFlashLigght() {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!state){
                    CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);

                    try {

                            String Cameraid=cameraManager.getCameraIdList()[0];
                            cameraManager.setTorchMode(Cameraid,true);
                            state=true;


                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                }else {
                    CameraManager cameraManager=(CameraManager) getSystemService(Context.CAMERA_SERVICE);

                    try {

                        String Cameraid=cameraManager.getCameraIdList()[0];
                        cameraManager.setTorchMode(Cameraid,false);
                        state=false;

                        
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


}