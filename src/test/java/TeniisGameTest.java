import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tenis.InvalidInputException;
import org.tenis.TennisGame;

class TennisGameTest {

    private TennisGame tennisGame;

    @BeforeEach
    void setUp() {
        tennisGame = new TennisGame();
    }

    @Test
    void testInitialScore() {
        String result = tennisGame.playTennis("");
        assertTrue(result.isEmpty(), "Initial score should be empty when no input is provided.");
    }

    @Test
    void testScoreProgressionPlayerA() {
        String result = tennisGame.playTennis("A");
        assertTrue(result.contains("Player A: 15 / Player B: 0"));

        result = tennisGame.playTennis("A");
        assertTrue(result.contains("Player A: 30 / Player B: 0"));

        result = tennisGame.playTennis("A");
        assertTrue(result.contains("Player A: 40 / Player B: 0"));

        result = tennisGame.playTennis("A");
        assertTrue(result.contains("Player A wins the game"));
    }

    @Test
    void testScoreProgressionPlayerB() {
        String result = tennisGame.playTennis("B");
        assertTrue(result.contains("Player A: 0 / Player B: 15"));

        result = tennisGame.playTennis("B");
        assertTrue(result.contains("Player A: 0 / Player B: 30"));

        result = tennisGame.playTennis("B");
        assertTrue(result.contains("Player A: 0 / Player B: 40"));

        result = tennisGame.playTennis("B");
        assertTrue(result.contains("Player B wins the game"));
    }

    @Test
    void testDeuceScenario() {
        String result = tennisGame.playTennis("AAABBB");
        assertTrue(result.contains("Player A: 40 / Player B: 40"), "Game should reach deuce.");
    }

    @Test
    void testAdvantagePlayerA() {
        String result = tennisGame.playTennis("AAABBBA");
        assertTrue(result.contains("Player A has advantage"), "Player A should have advantage after deuce.");
    }

    @Test
    void testAdvantagePlayerB() {
        String result = tennisGame.playTennis("AAABBBB");
        assertTrue(result.contains("Player B has advantage"), "Player B should have advantage after deuce.");
    }

    @Test
    void testPlayerAWinsAfterAdvantage() {
        String result = tennisGame.playTennis("AAABBBAA");
        assertTrue(result.contains("Player A wins the game"), "Player A should win after having advantage and scoring again.");
    }

    @Test
    void testPlayerBWinsAfterAdvantage() {
        String result = tennisGame.playTennis("AAABBBBA");
        result = tennisGame.playTennis("BB");
        assertTrue(result.contains("Player B wins the game"), "Player B should win after having advantage and scoring again.");
    }

    @Test
    void testInvalidInput() {
        Exception exception = assertThrows(InvalidInputException.class, () -> tennisGame.playTennis("C"));
        assertEquals("Invalid input. Only 'A' or 'B' are allowed.", exception.getMessage());
    }

    @Test
    void testGameEndsOnceWinnerDeclared() {
        String result = tennisGame.playTennis("AAAAA");
        assertTrue(result.contains("Player A wins the game"));


        Exception exception = assertThrows(IllegalStateException.class, () -> tennisGame.playTennis("B"));
        assertEquals("Invalid score progression.", exception.getMessage());
    }

    @Test
    void testComplexGameSequence() {
        String result = tennisGame.playTennis("ABABABABABBAAA");
        assertTrue(result.contains("Player A wins the game"), "Player A should win in this complex sequence.");
    }
    @Test
    void testSwitchingAdvantage() {
        String result = tennisGame.playTennis("AAABBBAABB");
        assertTrue(result.contains("Player A: 40 / Player B: 40"), "Game should return to deuce after switching advantage.");
    }
    @Test
    void testEmptyInput() {
        String result = tennisGame.playTennis("");
        assertTrue(result.isEmpty(), "Output should be empty for no input.");
    }

}
