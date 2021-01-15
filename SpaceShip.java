import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip{
    protected int health, max_energy, current_energy;
    protected boolean shields_up;
    protected SpaceShipPhysics ssp;
    protected int count = 0;

    /** The number representing a right turn. */
    protected static final int RIGHT = -1;

    /** The number representing continuing straight. */
    protected static final int STRAIGHT = 0;

    /** The number representing a left turn. */
    protected static final int LEFT = 1;

    /** The number of rounds that must pass between a shot can be fired.*/
    private static final int FIRE_COUNT = 7;

    /** The number representing the life span of a space ship.*/
    private static final int INITIAL_HEALTH = 25;

    /** The number representing the initial maximal energy level of a space ship.*/
    private static final int INITIAL_MAX_NRG = 250;

    /** The number representing the initial energy lever of a space ship.*/
    private static final int INITIAL_CUR_NRG = 200;

    /** The number a parameter in energy acceleration formula.*/
    private static final int MULTIPLIER = 2;

    /**
     * Creates a new space ship.
     */
    public SpaceShip(){

        this.reset();
    }

   
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        count +=1;
        if (count >= FIRE_COUNT){
            count = 0;
        }
        double adding = MULTIPLIER*ssp.getVelocity()/ssp.getMaxVelocity();
        current_energy += (int) adding + 1;
        if (current_energy > max_energy){
            current_energy = max_energy;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){
        if (!shields_up){
            health -= 1;
            if (current_energy >= 10) {
                current_energy -= 10;
            }
            if (max_energy >=5){
                max_energy -=5;
                if (current_energy > max_energy){
                    current_energy = max_energy;
                }
            }
        }
        else{
            if (current_energy >= 15) {
                current_energy -= 15;
                max_energy += 20;
            }else if (current_energy >= 10){
                current_energy -= 10;
                if (max_energy >= 5){
                    max_energy -= 5;
                    if (current_energy > max_energy){
                        current_energy = max_energy;
                    }
                }
            }
        }

    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        health = INITIAL_HEALTH;
        max_energy = INITIAL_MAX_NRG;
        current_energy = INITIAL_CUR_NRG;
        shields_up = false;
        ssp = new SpaceShipPhysics();

    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {

        if (health == 0) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {

        return ssp;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets shot at (with or without a shield).
     */
    public void gotShot() {
        if (shields_up){
            if (current_energy >= 2){
                current_energy -= 2;
            }else if (max_energy >= 5){
                max_energy -= 5;
                if (current_energy > max_energy){
                    current_energy = max_energy;
                }
            }
        }else{
            health -=1;
            if (current_energy >= 10) {
                current_energy -= 10;
            }
            if (max_energy >= 5){
                max_energy -= 5;
                if (current_energy > max_energy){
                    current_energy = max_energy;
                }

            }
        }

    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (!shields_up) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        }else{
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (count == 0 &&  current_energy >= 15){
            game.addShot(ssp);
            current_energy -= 15;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (current_energy >= 3 && !shields_up){
            current_energy -=3;
            shields_up = true;
        }
        
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (current_energy >= 100){
            ssp = new SpaceShipPhysics();
            current_energy -= 100;
        }
       
    }
    
}
