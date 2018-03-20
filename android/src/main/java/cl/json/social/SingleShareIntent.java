package cl.json.social;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cl.json.ShareFile;

/**
 * Created by disenodosbbcl on 23-07-16.
 */
public abstract class SingleShareIntent extends ShareIntent {




    protected String playStoreURL = null;
    protected String appStoreURL = null;

    public SingleShareIntent(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public void open(ReadableMap options) throws ActivityNotFoundException {
        System.out.println(getPackage());
        //  check if package is installed
        if(getPackage() != null || getDefaultWebLink() != null || getPlayStoreLink() != null) {
            if(this.isPackageInstalled(getPackage(), reactContext)) {
                System.out.println("INSTALLED");
                this.getIntent().setPackage(getPackage());
                //  configure default
                super.open(options);
            } else {
                System.out.println("NOT INSTALLED");
                throw new ActivityNotFoundException("App is not installed");
            }
        } else {
            //  configure default
            super.open(options);
        }
    }
}
