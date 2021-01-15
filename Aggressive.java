/**
 * This class represents an Aggressive type inherited Spaceship, who strives to constantly attack all space ships
 * near by.
 */
public class Aggressive extends SpaceShip {
    /**
     * An override of the doAction function of Spaceship, to fit an aggressive attack-mode activity.
     * For further detail see API of SpaceShip.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        int turn = STRAIGHT;
        shields_up = false;
        double directionOfClosest = ssp.angleTo(game.getClosestShipTo(this).getPhysics());
        if (directionOfClosest < 0){
            turn = RIGHT;
        }else if(directionOfClosest > 0){
            turn = LEFT;
        }
        if (Math.abs(directionOfClosest) <= 0.21){
            fire(game);
        }
        ssp.move(true, turn);
        super.doAction(game);
    }
}
