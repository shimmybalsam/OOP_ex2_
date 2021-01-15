import static oop.ex2.SpaceShipPhysics.randomGenerator;

/**
 * This class represents a Drunkard type inherited Spaceship, mimics a real life drunk pilot.
 */
public class Drunkard extends SpaceShip {
    /**
     * An override of the doAction function of Spaceship, to fit drunk-like activity.
     * For further detail see API of SpaceShip.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        int turn = STRAIGHT;
        shields_up = false;
        int rand_count = randomGenerator.nextInt();
        if (current_energy == max_energy) {
            if (rand_count <= 10) {
                fire(game);
            }else {
                teleport();
            }
        } else {
            int bla = randomGenerator.nextInt(10);
            if (bla < 5) {
                turn = LEFT;
            }else if (bla > 5){
                turn = RIGHT;
            }
        }
        ssp.move(true, turn);
        super.doAction(game);
    }
}
