package pairmatching;

import pairmatching.controller.PairMatchingController;

public class Application {

    public static void main(String[] args) {
        PairMatchingController pairMatchingController = new PairMatchingController();

        try {
            pairMatchingController.start();
        } catch (IllegalArgumentException exception) {
            System.out.printf("[ERROR] %s", exception.getMessage());
        }
    }

}
