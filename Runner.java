/**
 * This class represents a Runner type inherited Spaceship, who's soul purpose is to run away from any near space
 * ship to him, so to prevent getting injured.
 */
public class Runner extends SpaceShip {
    /**
     * An override of the doAction function of Spaceship, to fit cowardly, playing it safe activity.
     * For further detail see API of SpaceShip.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        int turn = STRAIGHT;
        shields_up = false;
        double directionOfClosest = ssp.angleTo(game.getClosestShipTo(this).getPhysics());
        if (directionOfClosest < 0){
            turn = LEFT;
        }else if(directionOfClosest > 0){
            turn = RIGHT;
        }
        if (Math.abs(game.getClosestShipTo(this).getPhysics().distanceFrom(getPhysics())) <= 0.25 ||
                Math.abs(directionOfClosest) <= 0.23){
            teleport();
        }
        ssp.move(true, turn);
        super.doAction(game);
    }
}
