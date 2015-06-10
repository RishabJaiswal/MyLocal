/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.example.Rishab.myapplication.backend2;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(name = "myApi", version = "v1")
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name)
    {

        MyBean respon = new MyBean();
        respon.setData("Hi," + name);

        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastore.beginTransaction();
        try {
            Key employeeKey = KeyFactory.createKey("Employee", "Joe");
            Entity employee = new Entity(employeeKey);
            employee.setProperty("vacationDays", 10);

            datastore.put(employee);

            txn.commit();
        } finally {
            if (txn.isActive()) {
                txn.rollback();
            }
        }

        return respon;
    }

    @ApiMethod(name = "storeData")
    public  MyBean storeData(@Named("eid")String eid, @Named("uname")String uname, @Named("password")String pass)
    {
        MyBean bean = new MyBean();
        bean.dataStored = true;
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastore.beginTransaction();
        bean.setDataStored(true);

        try{

            Key employeeKey = KeyFactory.createKey("Users", uname);
            Entity user = new Entity(employeeKey);
            user.setProperty("Username", uname);
            user.setProperty("Emailid", eid);
            user.setProperty("Password", pass);
            datastore.put(user);
            txn.commit();

        }finally {
            if (txn.isActive())
            {
                txn.rollback();
                bean.setDataStored(false);
            }
        }

        return bean;
    }
}

