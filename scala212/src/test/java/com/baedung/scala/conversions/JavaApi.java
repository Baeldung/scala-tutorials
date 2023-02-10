package com.baedung.scala.conversions;

import java.util.*;

public class JavaApi {
    public Iterator<Integer> getOneToFive() {
        List<Integer> myList = new ArrayList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        return myList.listIterator();
    }

    public List<String> getNames() {
        List<String> myList = new ArrayList();
        myList.add("Oscar");
        myList.add("Helga");
        myList.add("Faust");
        return myList;
    }

    public List<String> addExclamation(List<String> strList) {
        for (int i = 0; i < strList.size(); i++) {
            strList.set(i, strList.get(i) + "!");
        }
        return strList;
    }

    public String iteratorToString(Iterator<Integer> it) {
        List<String> myStrList = new ArrayList();
        for (; it.hasNext(); ) {
            myStrList.add(it.next().toString());
        }
        return myStrList.toString();
    }

    public Properties getConfig() {
        Properties myProps = new Properties();
        myProps.put("name", "Oscar");
        myProps.put("level", "hard");
        return myProps;
    }

    public <T> String collectionSize(Collection<T> col) {
        return "Collection of size: " + col.size();
    }
}
