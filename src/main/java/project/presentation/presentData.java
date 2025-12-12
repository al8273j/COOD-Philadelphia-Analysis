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
            scanner.nextLine();
            if(selectedMenu == 1){
                System.out.println(processor.menuOne());
            }
            else if(selectedMenu == 2){
                processor.menuTwo().forEach((key, value) -> System.out.println(key + " " + value));
            }
            else if(selectedMenu == 3){
                System.out.println("Enter a zipcode:\n");
                String zipcode = scanner.nextLine();
                try {
                    Double.parseDouble(zipcode);
                    System.out.println(processor.menuThree(zipcode));
                }
                catch (Exception e) {
                    System.out.println("Invalid zipcode\n");
                    continue;
                }

            }

    
          

            else if(selectedMenu == 4){
                System.out.println("Enter a zipcode:\n");
                String zipcode = scanner.nextLine();
                System.out.println(processor.menuFour(zipcode));
            }
            else if(selectedMenu == 5){
                System.out.println("Enter a zipcode:\n");
                String zipcode = scanner.nextLine();
                System.out.println(processor.menuFive(zipcode));
            }
            else if(selectedMenu == 6){
                System.out.println("Enter a zipcode:\n");
                String zipcode = scanner.nextLine();
                System.out.println("Enter minimum market value:");
                int min = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter maximum market value:");
                int max = Integer.parseInt(scanner.nextLine());
                System.out.println(processor.menuSix(zipcode, min, max));
            }
          
            else if(selectedMenu == 7){
                System.out.println("Please enter a zipcode:\n");
                String zipcode=scanner.nextLine().trim();
                System.out.println(Arrays.toString(processor.menuSeven(zipcode)));
            }
        }
    }
}
