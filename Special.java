/**
 * This class represents a Special type inherited Spaceship, which strives to be the smartest player in the game,
 * combining Aggressive and Runner attributes, depending on the situation.
 */
public class Special extends SpaceShip {
    /**
     * An override of the doAction function of Spaceship, to fit thought out, strategic activity.
     * For further detail see API of SpaceShip.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        int turn = STRAIGHT;
        boolean should_fire = false;
        shields_up = false;
        SpaceShip closest = game.getClosestShipTo(this);
        double directionOfClosest = ssp.angleTo(closest.getPhysics());
        if (closest.shields_up){
            if (directionOfClosest < 0){
                turn = LEFT;
            }else if (directionOfClosest > 0){
                turn = RIGHT;
            }else{
                teleport();
            }
        }else{
            if (Math.abs(game.getClosestShipTo(this).getPhysics().distanceFrom(getPhysics())) <= 0.20){
                should_fire = true;
            }
            if (directionOfClosest < 0){
                turn = RIGHT;
                if (should_fire) {
                    fire(game);
                }
            }else if (directionOfClosest > 0){
                turn = LEFT;
                if (should_fire) {
                    fire(game);
                }
            }else if (should_fire){
                fire(game);
            }
        }
        ssp.move(true, turn);
        super.doAction(game);
    }
}
