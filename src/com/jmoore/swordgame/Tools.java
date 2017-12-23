package com.jmoore.swordgame;

public class Tools {
    static String getSystemOsSimple() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.startsWith("windows")) return "windows";
        if(os.startsWith("linux")) return "linux";
        if(os.startsWith("mac")) return "mac";
        if(os.startsWith("sun")) return "err";
        if(os.startsWith("free")) return "err";
        return "err";
        //SunOS and FreeBSD are unsupported on this program.
    }
}
