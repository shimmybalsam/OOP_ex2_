/**
 * This class represents a Basher type inherited Spaceship, who's soul purpse is to collide with the near space
 * ships to him.
 */
public class Basher extends SpaceShip {
    /**
     * An override of the doAction function of Spaceship, to fit irrationally stubborn activity.
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
        if (Math.abs(game.getClosestShipTo(this).getPhysics().distanceFrom(getPhysics())) <= 0.19){
            shieldOn();
        }
        ssp.move(true, turn);
        super.doAction(game);
    }
}
