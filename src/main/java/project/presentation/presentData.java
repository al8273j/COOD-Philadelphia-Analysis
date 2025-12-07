package project.presentation;

import project.common.ScannerSingleton;
import project.processor.processData;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class presentData {
    processData processor;
    Scanner scanner = ScannerSingleton.getScannerInstance();
    public presentData(processData processor) {
        this.processor = processor;

    }
    public void start() throws ExecutionException, InterruptedException {



        while(true){
            System.out.println("Select a menu item: 1,2,3,4,5,6,7\n");
            int selectedMenu = scanner.nextInt();
            if(selectedMenu == 1){
                processor.menuOne();
            }
            else if(selectedMenu == 2){
                processor.menuTwo();
            }
            else if(selectedMenu == 3){
                processor.menuThree();
            }
            /*
            else if(selectedMenu == 4){processor.menuFour();}
            else if(selectedMenu == 5){processor.menuFive();}
            else if(selectedMenu == 6){processor.menuSix();}
            else if(selectedMenu == 7){processor.menuSeven();}
            */

        }

    }
}