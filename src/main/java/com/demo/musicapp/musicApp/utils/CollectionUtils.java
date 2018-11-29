package com.demo.musicapp.musicApp.utils;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionUtils
{
    public static <T> Collection<T> iterableToCollection(Iterable<T> iterable)
    {
        Collection<T> collection = new ArrayList<T>();
        for (T e : iterable) {
            collection.add(e);
        }
        return collection;
    }

}