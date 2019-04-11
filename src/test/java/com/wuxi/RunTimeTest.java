package com.wuxi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;

public class RunTimeTest {

    @Test
    public void run() {
        try {

            Process process = Runtime.getRuntime().exec(new String[]{"which", "javac"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String info = null;
            while ((info = reader.readLine()) != null) {
                System.out.println(info);
            }
            System.out.println(process.waitFor());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void systemValue() {
        System.out.println(System.getProperty("os.name"));
        Properties properties = System.getProperties();
        for (Object e : properties.keySet()) {
            System.out.println(e + ": " + properties.getProperty(e.toString()));
        }

    }
}
