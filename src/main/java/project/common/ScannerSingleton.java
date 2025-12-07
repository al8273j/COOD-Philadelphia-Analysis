package project.common;

import java.util.Scanner;

public class ScannerSingleton {

    private static Scanner scanner = null;


    public static Scanner getScannerInstance(){
        if (scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
