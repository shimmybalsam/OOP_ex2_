import oop.ex2.GameGUI;

import java.awt.*;

/**
 * This class represents a Human type inherited Spaceship, which is controlled by the user.
 */
public class Human extends SpaceShip {
    /**
     * An override of the doAction function of Spaceship, to fit human controlled activity.
     * For further detail see API of SpaceShip.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game){
        int turn = STRAIGHT;
        boolean accel = false;
        shields_up = false;
        if (game.getGUI().isTeleportPressed()){
            teleport();
        }
        else if (game.getGUI().isUpPressed()){
            accel = true;
            if (game.getGUI().isLeftPressed()){
                turn = RIGHT;
            }
            else if (game.getGUI().isRightPressed()){
                turn = LEFT;
            }
        }
        else if (game.getGUI().isLeftPressed()){
            turn = LEFT;
            if (game.getGUI().isUpPressed()){
                accel = true;
            }
        }
        else if (game.getGUI().isRightPressed()){
            turn = RIGHT;
            if (game.getGUI().isUpPressed()){
                accel = true;
            }
        }
        else if (game.getGUI().isShotPressed()){
            fire(game);
        }
        else if (game.getGUI().isShieldsPressed()){
            shieldOn();
        }
        ssp.move(accel, turn);
        super.doAction(game);
    }


    public Image getImage(){
        /**
         * An override of the getImage function of Spaceship, to fit human controlled activity.
         * For further detail see API of SpaceShip.
         * @param game the game object to which this ship belongs.
         */
        if (!shields_up) {
            return GameGUI.SPACESHIP_IMAGE;
        }else{
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
    }
}
