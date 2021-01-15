/**
 * This class has a single static method which is used by the supplied driver to create all the spaceship objects
 * according to the command line arguments.
 *
 * @author oop
 */


public class SpaceShipFactory {
    /**
     *
     * @param args an array of strings representing the willed space ship types, given as the command line arguments.
     * @return an array of said SpaceShips.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip[] spaceShips = new SpaceShip[args.length];
        for (int i=0; i<args.length; i++){
            if (args[i].equals("h")){
                spaceShips[i] = new Human();
            }else if (args[i].equals("a")){
                spaceShips[i] = new Aggressive();
            }else if (args[i].equals("b")){
                spaceShips[i] = new Basher();
            }else if (args[i].equals("d")) {
                spaceShips[i] = new Drunkard();
            }else if (args[i].equals("r")) {
                spaceShips[i] = new Runner();
            }else if (args[i].equals("s")) {
                spaceShips[i] = new Special();
            }
        }
        return spaceShips;
    }
}
