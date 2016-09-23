

package com.ibm.mobileappbuilder.localsell20160923170219;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import ibmmobileappbuilder.cloudant.factory.CloudantDatabaseSyncerFactory;
import java.net.URI;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        //Syncing cloudant ds
        CloudantDatabaseSyncerFactory.instanceFor(
            "products",
            URI.create("https://06810d66-5375-4fad-90b5-56a5007b6754-bluemix:b11241e1596018fcc4fe13b913aeebf106b5765f64d8c473425630675df9e3d4@06810d66-5375-4fad-90b5-56a5007b6754-bluemix.cloudant.com/products/")
        ).sync(null);
      }
}

