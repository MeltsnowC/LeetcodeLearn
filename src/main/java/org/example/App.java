package org.example;

import algorithem.listlearn.MyLinkList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        Logger logger = LoggerFactory.getLogger(App.class);

        MyLinkList ml = new MyLinkList();
        int i = ml.get(0);
        System.out.println("get:"+i);
        System.out.println( "Hello World!" );
    }
}
