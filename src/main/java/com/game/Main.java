package com.game;

import jline.console.ConsoleReader;
import jline.console.KeyMap;

import java.io.IOException;

public class Main {

    private static final String HIDE_CURSOR = "\u001B[?25l";
    private static final String KEY_UP = "\u001B[A";
    private static final String KEY_DOWN = "\u001B[B";
    private static final String KEY_LEFT = "\u001B[D";
    private static final String KEY_RIGHT = "\u001B[C";

    public static void main(String[] args) throws IOException {

        ConsoleReader console = new ConsoleReader();
        BattleField battleField = new BattleField(20, 20);
        Hero hero = new Hero(new Position(5,8), battleField);
        Monster monster1 = new Monster( new Position(10,15), battleField);
        Monster monster2 = new Monster(new Position(18,13), battleField);

        hideCursor(console);
        clearConsole(console);

        printBattleField(console, battleField);

        while (true) {

            Action action = readKeyboard(console);

            clearConsole(console);

            if (action == Action.Right) {

                hero.moveRight();
                monster1.moveRandom();
                monster2.moveRandom();

            } else if (action == Action.Left) {

                hero.moveLeft();
                monster1.moveRandom();
                monster2.moveRandom();

            } else if (action == Action.Up) {

                hero.moveUp();
                monster1.moveRandom();
                monster2.moveRandom();

            } else if (action == Action.Down) {

                hero.moveDown();
                monster1.moveRandom();
                monster2.moveRandom();
            }

            printBattleField(console, battleField);
        }
    }

    private static void hideCursor(ConsoleReader console) throws IOException {
        console.print(HIDE_CURSOR);
        console.flush();
    }

    private static Action readKeyboard(ConsoleReader console) throws IOException {
        KeyMap map = new KeyMap("");
        map.bind(KEY_UP, Action.Up);
        map.bind(KEY_DOWN, Action.Down);
        map.bind(KEY_LEFT, Action.Left);
        map.bind(KEY_RIGHT, Action.Right);
        return (Action) console.readBinding(map);
    }

    private static void clearConsole(ConsoleReader console) throws IOException {
        console.clearScreen();
        console.flush();
    }

    private static void printBattleField(ConsoleReader console,
            BattleField battleField) throws IOException {
        console.print(battleField.toString());
        console.flush();
    }
}
