import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * The RobotMethods class is what contains all of the necessary methods used to get the map and move the robot.
 *
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */

public class RobotMethods
{
    /* These parameters are necessary in order to determine the status of robot and the direction where it will move
      to by using enums */
    private Position pose;
    private Direction dir;

    /* These parameters are necessary in order to properly import the map file */
    private int totalRow;
    private int totalColumn;
    private char[][] map;

    /* These parameters will depict the starting position of the robot */
    private int startPosI;
    private int startPosJ;

    /* These parameters will depict the movement of the robot with current position */
    private int currentI;
    private int currentJ;


    /**
     * This method is used to read the map from the map file into an array of char.
     *
     * @exception FileNotFoundException Exception in case of file missing.
     */
    public void initMap(String filePath) throws FileNotFoundException
    {
        /* Initializing the file and scanner classes in order to read the file */
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        /* Setting up the dimensions of the maze */
        totalRow = 15;
        totalColumn = 7;

        /* This is the 2D array that will contain the map */
        map = new char[totalRow][totalColumn];

        /* The process of importing the map from file into the 2D array */
        for (int row = 0; scanner.hasNextLine() && row < totalRow; row++)
        {
            char[] pos = scanner.nextLine().toCharArray();
            for (int i = 0; i < totalColumn && i < pos.length; i++)
            {
                map[row][i] = pos[i];
            }
        }
    }

    /**
     * This method is used to print out the map.
     */
    public void printMap()
    {
        for (char[] aMap : map) {
            for (char anAMap : aMap) {
                System.out.print(anAMap);
            }
            System.out.println();
        }
    }

    /**
     * This method is used to determine the starting position of the robot.
     */
    private void startPosition()
    {
        /* The array is scanned through in order to determine the location of the starting symbol */
        for (int i = 0; i < map.length; ++i)
        {
            for(int j = 0; j < map[i].length; ++j)
            {
                if(map[i][j] == 'S')
                {
                    System.out.println("Robot is at the start position: " + i + ", " + j);
                    startPosI = i;
                    startPosJ = j;
                }
            }
        }
        /* This is where the robot is placed onto the start position */
        currentI = startPosI;
        currentJ = startPosJ;
        dir = checkStartDir(startPosI, startPosJ);
    }

    /**
     * This method is used to determine the first step the robot will take from starting position
     * @param i the X position of the robot
     * @param j the Y position of the robot
     * @return the direction the robot moves onto
     */
    private Direction checkStartDir(int i, int j)
    {
        /* Each of four starting positions are determined by wherever they are on the edge of any of the four corners
        * of the map */
        if(i == 0)
        { dir = Direction.DOWN;
        currentI = currentI + 1;}
        else if(i == totalRow - 1)
        { dir = Direction.UP;
        currentI = currentI - 1;}
        else if(j == 0)
        { dir = Direction.RIGHT;
          currentJ = currentJ + 1;}
        else if(j == totalColumn - 1)
        { dir = Direction.LEFT;
          currentJ = currentJ - 1;}
        /* After determining the starting position, the robot will move according to the direction and the row and
         * column they have to move in */
          System.out.println("Robot started movement to position: " + dir + " -> " + currentI + ", " + currentJ);
        pose = Position.START;
        return dir;
    }

    /**
     * This method will perform the necessary movements to reach the end of the maze
     * @param drivingModule the implementation of the DrivingModule interface
     * @return the position of the robot after finished movement
     */
    public Position driveFromStartToEnd(DrivingModule drivingModule)
    {
        /* Starting position is determined */
        startPosition();
        /* All of the necessary parameters are transfered in order to be used by the movement method*/
        drivingModule.preparePose(pose, currentI, currentJ, map);
        /* The initialized movement method should return a position */
        return drivingModule.go(dir);
    }
}
