package com.cloudrail.fileviewer;

import android.app.Activity;

import com.cloudrail.si.cloudStorage.Box;
import com.cloudrail.si.cloudStorage.CloudStorage;
import com.cloudrail.si.cloudStorage.Dropbox;
import com.cloudrail.si.cloudStorage.GoogleDrive;
import com.cloudrail.si.cloudStorage.OneDrive;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by patrick on 08.04.16.
 */
public class Services {
    private static Services ourInstance = new Services();

    private final AtomicReference<CloudStorage> dropbox = new AtomicReference<>();
    private final AtomicReference<CloudStorage> box = new AtomicReference<>();
    private final AtomicReference<CloudStorage> googledrive = new AtomicReference<>();
    private final AtomicReference<CloudStorage> onedrive = new AtomicReference<>();

    private Activity context = null;

    public static Services getInstance() {
        return ourInstance;
    }

    private Services() {
    }

    private void initDropbox() {
        dropbox.set(new Dropbox(context, "u4gevj9clhvdjug", "9ol49hdlk8by9v9"));
    }

    private void initBox() {
        box.set(new Box(context, "zqgl7zrzxei2c076ss5k9hxf2ivbppfa", "ueG5uWHUarWYQNgldCsCwUwGzvSWlR0Y"));
    }

    private void initGoogleDrive() {
        googledrive.set(new GoogleDrive(context, "638240013795-k6cavk4npp6gtqkpb56icpm0hm4uo6aq.apps.googleusercontent.com", "hhJG6zCn4F7ObJUzllL3BXoL"));
    }

    private void initOneDrive() {
        onedrive.set(new OneDrive(context, "000000004018F12F", "lGQPubehDO6eklir1GQmIuCPFfzwihMo"));
    }

    // --------- Public Methods -----------
    public void prepare(Activity context) {
        this.context = context;
        this.initDropbox();
        this.initBox();
        this.initGoogleDrive();
        this.initOneDrive();
    }

    public CloudStorage getService(int service) {
        AtomicReference<CloudStorage> ret = new AtomicReference<>();

        switch (service) {
            case 1:
                ret = this.dropbox;
                break;
            case 2:
                ret = this.box;
                break;
            case 3:
                ret = this.googledrive;
                break;
            case 4:
                ret = this.onedrive;
                break;
            default:
                throw new IllegalArgumentException("Unknown service!");
        }

        return ret.get();
    }
}