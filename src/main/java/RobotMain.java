import java.io.FileNotFoundException;

/**
 * The RobotProject program is an application which will move the robot across the map. RobotMain class contains the
 * main method.
 *
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */
public class RobotMain
{
    /**
     * This is the main method which will obtain the map from the map file and move the robot towards the end
     * position.
     *
     * @param args main arguments
     */
    public static void main(String[] args)
    {
        /*
          This is where the classes, as well as the main argument is initialized.
         */
        DrivingModule drm = new RobotInitialize();
        RobotMethods rm = new RobotMethods();
        String filePath = null;

        /*
          Main argument is checked for any issues. Possible issue: incorrect parameter.
          In case of incorrect parameter, the program will exit. Otherwise, the filepath will be read as a string
          in order to properly initialize the map later on.
         */
        if(args.length != 1)
        {
            System.out.println("Incorrect parameter! Usage: java pathname");
            System.exit(1);
        }
        else
        {
            filePath = args[0];
            System.out.println("File found at: " + filePath);
        }

        /*
          This is where the map is initialized. Possible issue: the filepath doesn't contain the proper file; invalid
          maze dimensions.
         */
        try
        {
            rm.initMap(filePath);
            rm.printMap();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("No input file found!");
            System.exit(-1);
        }

        /*
          This is where the robot will move on the map. There can either be two cases: the robot reached the end
          position, or the robot returned back to the start position due to a dead end. In both cases, the appropriate
          message will be shown.
         */
        if (rm.driveFromStartToEnd(drm) == Position.END)
        {
            System.out.println("Robot has reached the end!");
            System.exit(0);
        }
        else
        {
            System.out.println("Robot has returned to the entrance!");
            System.exit(0);
        }
    }
}
