package edu.harding.androiddots;

import java.util.List;

public class Test {

    private void displayBoard(DotsGame game) {
        // Display color of dots
        for (int row = 0; row < DotsGame.NUM_CELLS; row++) {
            for (int col = 0; col < DotsGame.NUM_CELLS; col++) {
                Dot dot = game.getDot(row, col);
                System.out.print(dot.color + " ");
            }
            System.out.println();
        }
    }

    public static void Main() {

        DotsGame game = new DotsGame(DotsGame.GameTypes.Moves);

        // Generate a configuration where the two bottom-left colors are the same for testing
        int bottomRow = DotsGame.NUM_CELLS - 1;
        while (game.getDot(bottomRow, 0).color != game.getDot(bottomRow, 1).color) {
            game.newGame();
        }

        // Display color of dots
        displayBoard(game);

        // No score yet
        System.out.println("Score = " + game.getScore());

        // Connect two dots on bottom-left
        Dot dot = game.getDot(bottomRow, 0);
        DotsGame.AddDotStatus status = game.addDotToPath(dot);
        if (status == DotsGame.AddDotStatus.Added) {
            System.out.println("Added dot " + dot);
        }

        dot = game.getDot(bottomRow, 1);
        status = game.addDotToPath(dot);
        if (status == DotsGame.AddDotStatus.Added) {
            System.out.println("Added dot " + dot);

            // Show selected dots
            System.out.println("Path: ");
            List<Dot> path = game.getDotPath();
            for (Dot dotInPath: path) {
                System.out.println(dotInPath);
            }

            // Connected two dots, so remove dots and slide dots down
            game.finishMove();

            // Show new board
            displayBoard(game);

            // Score of 2
            System.out.println("Score = " + game.getScore());
        }
        else if (status == DotsGame.AddDotStatus.Rejected) {
            System.out.println("Rejected dot " + dot);
        }

        game.clearDotPath();
    }
}
