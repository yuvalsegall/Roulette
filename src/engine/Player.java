package engine;

import engine.bets.Bet;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author yuvalsegall
 */
public class Player implements Comparable<Player> {

    private final PlayerDetails playerDetails;
    private static int idCounter = 0;

    public Player(PlayerDetails playerDetials) {
        this.playerDetails = playerDetials;
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    @Override
    public int compareTo(Player o) {
        return this.getPlayerDetails().getMoney().compareTo(o.getPlayerDetails().getMoney());
    }

    public static class PlayerDetails {

        private final int playerID;
        private String name;
        private Boolean isHuman;
        private BigInteger money;
        private List<Bet> bets;
        private Boolean isActive;

        public PlayerDetails(String name, Boolean isHuman, BigInteger money) {
            this.playerID = idCounter++;
            this.name = name;
            this.isHuman = isHuman;
            this.money = money;
            this.isActive = true;
        }

        public PlayerDetails() {
            this.playerID = idCounter++;
            this.name = new String();
            this.isHuman = true;
            this.money = null;
            this.isActive = true;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setIsHuman(Boolean isHuman) {
            this.isHuman = isHuman;
        }

        public int getPlayerID() {
            return playerID;
        }

        public String getName() {
            return name;
        }

        public Boolean getIsHuman() {
            return isHuman;
        }

        public BigInteger getMoney() {
            return money;
        }

        public void setMoney(BigInteger money) {
            this.money = money;
        }

        public List<Bet> getBets() {
            return bets;
        }

        public void setBets(List<Bet> bets) {
            this.bets = bets;
        }

        public Boolean getIsActive() {
            return isActive;
        }

        public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
        }

    }
}
