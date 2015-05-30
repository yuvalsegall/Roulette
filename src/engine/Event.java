package engine;

import engine.bets.Bettable;
import engine.bets.Bet;
import engine.bets.NumbersBet;
import java.math.BigInteger;

/**
 *
 * @author yuvalsegall
 */
public class Event {

    private String playerName;
    private int eventID;
    private static int eventCounter = 0;
    private EventType eventType;
    private Game game;
    private Bet playerBet;
    private PlayerAction playerAction;

    public Event(String playerName, EventType eventType, Game game, Bet playerBet) {
        if (eventType == EventType.NUMBER_RESULT) {
            this.playerName = playerName;
        }
        this.eventType = eventType;
        this.game = game;
        this.playerBet = playerBet;
        this.eventID = eventCounter++;
        this.playerAction = generatePlayerActionFromEventType(eventType);
    }

    public Event(String playerName, EventType eventType, Game game) {
        this(playerName, eventType, game, null);
    }

    public Event(EventType eventType, Game game) {
        this(null, eventType, game, null);
    }

    public static enum EventType {

        GAME_START, GAME_OVER, GAME_WINNER, NUMBER_RESULT, PLAYER_RESIGNED, PLAYER_BET, PLAYER_FINISHED_BETTING;
    }

    private PlayerAction generatePlayerActionFromEventType(EventType eventType) {
        switch (eventType) {
            case PLAYER_RESIGNED:
                return PlayerAction.RESIGNED;
            case PLAYER_BET:
                return PlayerAction.BET;
            case PLAYER_FINISHED_BETTING:
                return PlayerAction.FINISHED_BETTING;
            default:
                return null;
        }
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getEventID() {
        return eventID;
    }

    public EventType getEventType() {
        return eventType;
    }

    public int getTimeoutCount() {
        //TODO getTimeoutCount
        //will be 0 in case no timer is active
        return 0;
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public Bettable getBetType() {
        return (Bettable) playerBet;
    }

    public int[] getNumbers() {
        return ((NumbersBet) playerBet).getNumbers();
    }

    public BigInteger getAmount() {
        return playerBet.getBetAmount();
    }

    public int getWinningNumber() {
        return game.getTable().getCurrentBallPosition().getValue();
    }
}
