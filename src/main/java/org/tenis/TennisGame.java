package org.tenis;

public class TennisGame {
    private Score playerA = Score.ZERO;
    private Score playerB = Score.ZERO;
    private boolean advantagePlayerA = false;
    private boolean advantagePlayerB = false;
    private String winner = null;

    public String playTennis(String input) {
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == 'A') {
                winForA();
            } else if (c == 'B') {
                winForB();
            } else {
                throw new InvalidInputException("Invalid input. Only 'A' or 'B' are allowed.");
            }
            if (isGameOver()) {
                //System.out.println(winner + " wins the game");
                output.append(winner).append(" wins the game");
                break;
            }
            //System.out.println(printScore() + "\n");
            output.append(printScore()).append("\n");
        }
        return output.toString();
    }

    private void winForA() {
        if (isDeuce()) {
            handleDeuce(true);
        } else if (playerA == Score.FORTY) {
            endGame("Player A");
        } else {
            playerA = Score.next(playerA);
        }
    }

    private void winForB() {
        if (isDeuce()) {
            handleDeuce(false);
        } else if (playerB == Score.FORTY) {
            endGame("Player B");
        } else {
            playerB = Score.next(playerB);
        }
    }

    private void handleDeuce(boolean isPlayerA) {
        if (isPlayerA) {
            if (advantagePlayerA) {
                endGame("Player A");
            } else if (advantagePlayerB) {
                advantagePlayerB = false;
            } else {
                advantagePlayerA = true;
            }
        } else {
            if (advantagePlayerB) {
                endGame("Player B");
            } else if (advantagePlayerA) {
                advantagePlayerA = false;
            } else {
                advantagePlayerB = true;
            }
        }
    }

    private boolean isDeuce() {
        return playerA == Score.FORTY && playerB == Score.FORTY;
    }

    private boolean isGameOver() {
        return winner != null;
    }

    private void endGame(String winner) {
        this.winner = winner;
        playerA = null;
        playerB = null;
    }

    private String printScore() {
        if (advantagePlayerA) {
            return "Player A has advantage";
        }
        if (advantagePlayerB) {
            return "Player B has advantage";
        }
        return "Player A: " + playerA + " / Player B: " + playerB;
    }

    public static void main(String[] args) {
        TennisGame game = new TennisGame();
       System.out.println(  game.playTennis("ABABABABABBAABBB"));
    }
}
