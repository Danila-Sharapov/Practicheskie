package com.example.widgets;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

public class FlashClass {

    private boolean is_flash = false;
    private Context context;
    CameraManager cameraManager;

    public FlashClass(Context context) {
        this.context = context;
        cameraManager = (CameraManager)context.getSystemService(Context.CAMERA_SERVICE);
    }

    public void SvetOff(){
        try{
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, false);
            this.is_flash = false;
        }
        catch (CameraAccessException ex){
            ex.printStackTrace();
        }
    }


    public void SvetOn(){
        try{
            assert cameraManager != null;
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId, true);
            this.is_flash = true;
        }
        catch (CameraAccessException ex){
            ex.printStackTrace();
        }
    }



    public boolean isFlashh() {
        return is_flash;
    } //return is_flash
}
