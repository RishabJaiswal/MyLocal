package com.example.Rishab.myapplication.backend2;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiReference;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

import javax.inject.Named;

/**
 * Created by Rishab on 23-02-2015.
 */

@ApiReference(MyEndpoint.class)
public class MyEndpoint2
{
    @ApiMethod(name = "storeDatas")
    public  MyBean storeDatas(@Named("eid")String eid, @Named("uname")String uname, @Named("password")String pass)
    {
        MyBean bean = new MyBean();
        bean.dataStored = true;
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Transaction txn = datastore.beginTransaction();

        try{

            Key employeeKey = KeyFactory.createKey("Users", uname);
            Entity user = new Entity(employeeKey);
            user.setProperty("Username", uname);
            user.setProperty("Email id", eid);
            user.setProperty("Password", pass);
            datastore.put(user);
            txn.commit();

        }finally {
            if (txn.isActive())
            {
                txn.rollback();
                bean.dataStored = false;
            }
        }

        return bean;
    }
}