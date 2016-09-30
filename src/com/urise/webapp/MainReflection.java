package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Сергей on 28.09.2016.
 */
public class MainReflection {
    public static void main (String[] arg) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r=new Resume();
        Field f=r.getClass().getDeclaredFields()[0];
        f.setAccessible(true);
        System.out.println(f.getName());
        System.out.println(f.get(r));
        f.set(r,"new_uuid");
        //TODO invoke r.toString via(через) reflection (вызвать r.toString через отражение)
        Method newToString=r.getClass().getDeclaredMethod("toString");
        String s=(String)newToString.invoke(r);
        System.out.println(s);


    }
}
