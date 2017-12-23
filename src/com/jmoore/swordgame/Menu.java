package com.jmoore.swordgame;

import java.util.Scanner;

public class Menu {
    static int getMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Play game");
        System.out.println("2. Quit game");
        System.out.print("> ");
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
    static int getGameMenu() {
        System.out.println("1. Sword");
        System.out.println("2. Knife");
        System.out.println("3. Shield");
        System.out.println("4. Health Potion");
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }
}
