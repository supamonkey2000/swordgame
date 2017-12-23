package com.jmoore.swordgame;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    void start() {
        setUserVariables();
        readData();
        play();
    }

    private void setUserVariables() {
        UserVariables.USER_OS = getOsInt();
        UserVariables.BASE_PATH = getBasePath();
    }

    private int getOsInt() {
        switch(Tools.getSystemOsSimple()) {
            case "windows":
                return 0;
            case "linux":
                return 1;
            case "mac":
                return 2;
            default:
                return -1;
        }
    }

    private File getBasePath() {
        switch(UserVariables.USER_OS) {
            case 0: //Windows
                return new File(System.getenv("SystemDrive") + "/Users/" + System.getProperty("user.name") + "/jmoore/sword/");
            case 1: //Linux
                return new File("/home/" + System.getProperty("user.name") + "/.jmoore/sword/");
            case 2: //Mac OS X
                return new File("/Users/" + System.getProperty("user.name") + "/.jmoore/sword/");
            default:
                System.err.println("ERROR: An unknown error occurred");
                return null;
        }
    }

    private void readData() {
        try {
            if (!UserVariables.BASE_PATH.exists()) UserVariables.BASE_PATH.mkdirs();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void play() {
        System.out.println("########################################");
        System.out.println("##                                    ##");
        System.out.println("##        Text-Based Sword Game       ##");
        System.out.println("##                                    ##");
        System.out.println("########################################\n\n");

        boolean selected = false;

        mainLoop:
        while(true) {
            while(!selected) {
                int option = Menu.getMainMenu();
                switch (option) {
                    case 1:
                        selected = true;
                        break;
                    case 2:
                        System.exit(0);
                        break mainLoop;
                    default:
                        System.out.println("Wrong option");
                }
            }

            System.out.println("");
            int pHealth = 50, bHealth = 50;

            //Game loop
            while(true) {
                System.out.println("\nPlayer has " + pHealth + " Health.\nComputer has " + bHealth + " Health.");
                int poption = Menu.getGameMenu();
                int boption = ThreadLocalRandom.current().nextInt(1,5);
                //printUse(poption, "Player");
                //printUse(boption, "Computer");
                Result result = getResult(poption, boption);
                pHealth += result.phealth;
                bHealth += result.bhealth;
                if(pHealth < 1 && bHealth > 1) {
                    System.out.println("Player has died! Computer has won the game!");
                    System.out.println("Better luck next time.");
                    break;
                } else if(bHealth < 1 && pHealth > 1) {
                    System.out.println("Computer has died! Player has won the game!");
                    System.out.println("Congratulations!");
                    break;
                } else if(pHealth < 1 && bHealth < 1) {
                    System.out.println("Player and Computer fought valiantly, but both died in the end.");
                    System.out.println("Try harder next time!");
                }
            }
        }
    }

    private void printUse(int option, String player) {
        switch(option) {
            case 1:
                System.out.println(player + " used Sword!");
                break;
            case 2:
                System.out.println(player + " used Knife!");
                break;
            case 3:
                System.out.println(player + " used Shield!");
                break;
            case 4:
                System.out.println(player + " used Health Potion!");
                break;
            default:
                System.out.println(player + " was stupid and selected an incorrect option!");
        }
    }

    private Result getResult(int p, int b) {
        if(ThreadLocalRandom.current().nextInt(0,11) == 5) p = 5;
        if(ThreadLocalRandom.current().nextInt(0,11) == 5) b = 5;

        if(p == 1 && b == 1) {
            System.out.println("Player and Computer both used their swords!");
            System.out.println("The swords clashed and nothing happened!");
            return new Result(0, 0);
        }
        if(p == 1 && b == 2) {
            System.out.println("Player attacked Computer with their sword!");
            System.out.println("Computer attacked Player with their knife!");
            System.out.println("Player lost 5 Health.\nComputer lost 10 Health.");
            return new Result(-5,-10);
        }
        if(p == 1 && b == 3) {
            System.out.println("Player attacked Computer with their sword!");
            System.out.println("Computer blocked Player's attack with their shield, and nothing happened!");
            return new Result(0,0);
        }
        if(p == 1 && b == 4) {
            System.out.println("Player attacked Computer with their sword!");
            System.out.println("Computer used a Health Potion.");
            System.out.println("Computer lost 10 Health, but regained it with their Potion!");
            return new Result(0,0);
        }
        if(p == 2 && b == 1) {
            System.out.println("Player attacked Computer with their knife!");
            System.out.println("Computer attacked Player with their sword!");
            System.out.println("Player lost 10 Health.\nComputer lost 5 Health.");
            return new Result(-10,-5);
        }
        if(p == 2 && b == 2) {
            System.out.println("Player and Computer both used their knives!");
            System.out.println("The knives clashed and nothing happened!");
            return new Result(0,0);
        }
        if(p == 2 && b == 3) {
            System.out.println("Player attacked Computer with their knife!");
            System.out.println("Computer blocked Player's attack with their shield, and nothing happened!");
            return new Result(0,0);
        }
        if(p == 2 && b == 4) {
            System.out.println("Player attacked Computer with their knife!");
            System.out.println("Computer used a Health Potion.");
            System.out.println("Computer lost 5 Health, but regained 10 with their Potion!");
            return new Result(0,5);
        }
        if(p == 3 && b == 1) {
            System.out.println("Computer attacked Player with their sword!");
            System.out.println("Player blocked Computer's attack with their shield, and nothing happened!");
            return new Result(0,0);
        }
        if(p == 3 && b == 2) {
            System.out.println("Computer attacked Player with their knife!");
            System.out.println("Player blocked Computer's attack with their shield, and nothing happened!");
            return new Result(0,0);
        }
        if(p == 3 && b == 3) {
            System.out.println("Both players used their shields! Nothing happened.");
            return new Result(0,0);
        }
        if(p == 3 && b == 4) {
            System.out.println("Player used their shield!");
            System.out.println("Computer used a Health Potion, and regained 10 Health.");
            return new Result(0,10);
        }
        if(p == 4 && b == 1) {
            System.out.println("Computer attacked Player with their sword!");
            System.out.println("Player used a Health Potion.");
            System.out.println("Player lost 10 Health, but regained it with their potion!");
            return new Result(0,0);
        }
        if(p == 4 && b == 2) {
            System.out.println("Computer attacked Player with their knife!");
            System.out.println("Player used a Health Potion.");
            System.out.println("Player lost 5 Health, but regained 10 with their potion!");
            return new Result(5,0);
        }
        if(p == 4 && b == 3) {
            System.out.println("Player used a Health Potion.");
            System.out.println("Computer used their shield!");
            System.out.println("Player regained 10 Health.");
            return new Result(10,0);
        }
        if(p == 4 && b == 4) {
            System.out.println("Player and Computer both regained 10 Health with Potions.");
            return new Result(10,10);
        }
        if(p == 1 && b == 5) {
            System.out.println("Player attacked Computer with their sword!");
            System.out.println("Computer slipped and messed up their turn!");
            System.out.println("Computer lost 10 Health!");
            return new  Result(0,-10);
        }
        if(p == 2 && b == 5) {
            System.out.println("Player attacked Computer with their knife!");
            System.out.println("Computer slipped and messed up their turn!");
            System.out.println("Computer lost 5 Health!");
            return new  Result(0,-5);
        }
        if(p == 3 && b == 5) {
            System.out.println("Player used their shield!");
            System.out.println("Computer slipped and messed up their turn!");
            System.out.println("Nothing happened!");
            return new  Result(0,0);
        }
        if(p == 4 && b == 5) {
            System.out.println("Player used a Health potion.");
            System.out.println("Computer slipped and messed up their turn!");
            System.out.println("Player regained 10 Health!");
            return new  Result(10,0);
        }
        if(p == 5 && b == 5) {
            System.out.println("Player and Computer both slipped and messed up their turns! Nothing happened!");
            return new  Result(0,0);
        }
        if(p == 5 && b == 1) {
            System.out.println("Computer attacked Player with their sword!");
            System.out.println("Player slipped and messed up their turn!");
            System.out.println("Player lost 10 Health!");
            return new  Result(-10,0);
        }
        if(p == 5 && b == 2) {
            System.out.println("Computer attacked Player with their knife!");
            System.out.println("Player slipped and messed up their turn!");
            System.out.println("Player lost 5 Health!");
            return new  Result(-5,0);
        }
        if(p == 5 && b == 3) {
            System.out.println("Computer used their shield!");
            System.out.println("Player slipped and messed up their turn!");
            System.out.println("Nothing happened!");
            return new  Result(0,0);
        }
        if(p == 5 && b == 4) {
            System.out.println("Computer used a Health Potion.");
            System.out.println("Player slipped and messed up their turn!");
            System.out.println("Computer regained 10 Health.");
            return new  Result(0,10);
        }
        if(p < 1 || p > 6) {
            System.out.println("Player was stupid and picked an incorrect option.");
            System.out.println("Computer shook their head in disappointment");
            return new Result(0,0);
        } else {
            return new Result(0,0);
        }
    }

    class Result {
        int phealth;
        int bhealth;
        Result(int phealth, int bhealth) {
            this.phealth = phealth;
            this.bhealth = bhealth;
        }
    }
}