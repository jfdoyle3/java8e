package com.company;

import java.util.Random;
import Yahtzee.Hand;
import Yahtzee.YahtzeeConsole;

public class Yahtzee {

    public static void main(String[] args) {
        Random rand = new Random();
        YahtzeeConsole.welcome();
        Hand myHand = new Hand();
        myHand.roll(rand);
        YahtzeeConsole.displayHand(myHand.getDice());
        myHand.roll(rand, YahtzeeConsole.getChoices());
        YahtzeeConsole.displayHand(myHand.getDice());
        myHand.roll(rand, YahtzeeConsole.getChoices());
        YahtzeeConsole.displayHand(myHand.getDice());
    }
}
