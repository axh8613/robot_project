/**
 * The DrivingModule interface that contains the empty go method.
 *
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */

public interface DrivingModule
{
    /**
     * This method is used to actually move the robot.
     * @param direction the enum of direction which the robot will go to
     * @return the enum of current position of the robot
     */
    Position go(Direction direction);
    /**
     * This method is used to transfer parameters from RobotMethods class to RobotInitialize class
     * @param position current position of the robot
     * @param i the row position of the robot
     * @param j the column position of the robot
     * @param map the map of the maze
     */
    void preparePose(Position position, int i, int j,  char[][] map);
}
