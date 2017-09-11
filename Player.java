
public class Player {

    private String playerName;
    private Integer playerLife;

    public Player (String playerName, Integer playerLife) {
        this.playerName = playerName;
        this.playerLife = playerLife;
    }

    public void subtractPoints(Integer pointsToSubtract) {
        this.playerLife -= pointsToSubtract;
    }

    public Integer getPlayerLife() {
        return this.playerLife;
    }
}
