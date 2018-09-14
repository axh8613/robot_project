/**
 * The RobotInitialize class is the class which will actually move the robot.
 * It also implements the DrivingModule interface which contains the movement method.
 *
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */

public class RobotInitialize implements DrivingModule
{
    /* These parameters will contain the parameters imported from the previous class */
    private Position pose;
    private int poseI;
    private int poseJ;
    private char[][] robotMap;
    private Direction direct;

    /**
     * This method is used to prepare all of the parameters imported from the previous class
     *
     * @param position the current robot position
     * @param i the row position of robot
     * @param j the column position of robot
     * @param map 2D array that contains the map
     */
    public void preparePose(Position position, int i, int j, char[][] map)
    {
        pose = position;
        poseI = i;
        poseJ = j;
        robotMap = map;
    }
    /**
     * This method is used to move the robot
     *
     * @param direction the direction the robot will go at
     * @return the position of the robot
     */
    public Position go(Direction direction)
    {
        /* Direction is imported from previous class */
        direct = direction;
        /* This loop will ensure the robot is travelling until it finds either the starting or ending position */
        do
        {
            /* This is the movement in case the robot is in the normal unchanged position, aka the robot hit a wall */
            if(pose == Position.NORMAL_UNCHANGED)
            {
                /* The robot checks both the neighbours and dead ends */
                checkNeighbours(direct);
                deadCheck(direct);
                /* After check the robot returns to normal position which allows it to move */
                pose = Position.NORMAL;
            }
            /* This is the movement in case the robot can move in one direction continiously or if it is at the starting
             * position */
            else if(pose == Position.NORMAL || pose == Position.START)
            {
                /* The robot continues movement */
                continueMovement(direct);
            }
        }/* Robot is performing movement as long as it is on the floor. */
        while (robotMap[poseI][poseJ] == '.');
        /* The robot has to check whenever it reached the end position or it returned back to starting position */
        if(robotMap[poseI][poseJ] == 'E')
        {
            return Position.END;
        }
        else
        {
            return Position.START;
        }
    }

    /**
     * This method is used to continuously move the robot in case the position is normal
     *
     * @param dir the direction the robot will go at
     */
    public void continueMovement(Direction dir)
    {
        /* This is where method checks the current direction the robot is moving at*/
        switch (dir)
        {
            /* In case of robot walking to a spot where the wall is at at up direction */
            case UP: if(robotMap[poseI - 1][poseJ] == '*')
            {
                /* The robot is not changing the position, after this it will have to check the neighbouring positions
                 * and dead ends */
                pose = Position.NORMAL_UNCHANGED;
                break;
            }
            else
            {
                /* In case of robot walking to a spot where the floor is, it will continue moving that way */
                poseI = poseI - 1;
                System.out.println("Moving up... position: " + poseI + ", " + poseJ);
                break;
            }
            /* In case of robot walking to a spot where the wall is at at down direction */
            case DOWN: if(robotMap[poseI + 1][poseJ] == '*')
            {
                pose = Position.NORMAL_UNCHANGED;
                break;
            }
            else
            {
                poseI = poseI + 1;
                System.out.println("Moving down... position: " + poseI + ", " + poseJ);
                break;
            }
            /* In case of robot walking to a spot where the wall is at at left direction */
            case LEFT: if(robotMap[poseI][poseJ - 1] == '*')
            {
                pose = Position.NORMAL_UNCHANGED;
                break;
            }
            else
            {
                poseJ= poseJ - 1;
                System.out.println("Moving left... position: " + poseI + ", " + poseJ);
                break;
            }
            /* In case of robot walking to a spot where the wall is at at right direction */
            case RIGHT: if(robotMap[poseI][poseJ + 1] == '*')
            {
                pose = Position.NORMAL_UNCHANGED;
                break;
            }
            else
            {
                poseJ = poseJ + 1;
                System.out.println("Moving right... position: " + poseI + ", " + poseJ);
                break;
            }
            /* Default case which can't normally happen*/
            default: System.out.println("Invalid position! Normal movement case.");
                break;
        }
    }

    /**
     * This method is used to check the neighbouring directions in case of a normal unchanged position
     *
     * @param dir the direction the robot will go at
     */
    public void checkNeighbours(Direction dir)
    {
        /* This is where method checks the previous direction the robot was moving at*/
        switch (dir)
        {
            /* In case of the previous direction being either up or down, the method checks if either the left or right
             * directions are available to turn at */
            case UP:
            case DOWN: if(robotMap[poseI][poseJ - 1] == '.')
            {
                /* The robot will move left if there is a floor*/
                poseJ = poseJ - 1;
                System.out.println("Hit a wall! Turning left... position: " + poseI + ", " + poseJ);
                direct = Direction.LEFT;
                pose = Position.NORMAL;
                break;
            }
            else
            {
                /* The robot will move right if a wall is located on the left*/
                poseJ = poseJ + 1;
                System.out.println("Hit a wall! Turning right... position: " + poseI + ", " + poseJ);
                direct = Direction.RIGHT;
                pose = Position.NORMAL;
                break;
            }
            /* In case of the previous direction being either left or right, the method checks if either the up or down
             * directions are available to turn at */
            case LEFT:
            case RIGHT: if(robotMap[poseI - 1][poseJ] == '.')
            {
                /* The robot will move up if there is a floor*/
                poseI = poseI - 1;
                System.out.println("Hit a wall! Turning up... position: " + poseI + ", " + poseJ);
                direct = Direction.UP;
                pose = Position.NORMAL;
                break;
            }
            else
            {
                /* The robot will move down if a wall is located on the up*/
                poseI = poseI + 1;
                System.out.println("Hit a wall! Turning down... position: " + poseI + ", " + poseJ);
                direct = Direction.DOWN;
                pose = Position.NORMAL;
                break;
            }
            /* Default case which can't normally happen*/
            default: System.out.println("Invalid position! Neighbour check case.");
                break;
        }
    }

    /**
     * This method is used to check any dead ends in case of a normal unchanged position
     *
     * @param dir the direction the robot will go at
     */
    public void deadCheck(Direction dir)
    {
        /* This is where method checks the previous direction the robot was moving at*/
        switch(dir)
        {
            /* In case of previous direction being up, the method will check the remaining directions in case all of
             * them containing walls */
            case UP:
                /* Up */
                if(robotMap[poseI - 1][poseJ] == '*')
                {
                    /* Left */
                    if(robotMap[poseI][poseJ - 1] == '*')
                    {
                        /* Right */
                        if(robotMap[poseI][poseJ + 1] == '*')
                        {
                            poseI = poseI + 1;
                            System.out.println("Hit a dead end! Moving down... position: " + poseI + ", " + poseJ);
                            direct = Direction.DOWN;
                            pose = Position.NORMAL;
                            break;
                        }
                    }
                }
            /* In case of previous direction being down, the method will check the remaining directions in case all of
             * them containing walls */
            case DOWN:
                /* Down */
                if(robotMap[poseI + 1][poseJ] == '*')
                {
                    /* Left */
                    if(robotMap[poseI][poseJ - 1] == '*')
                    {
                        /* Right */
                        if(robotMap[poseI][poseJ + 1] == '*')
                        {
                            poseI = poseI - 1;
                            System.out.println("Hit a dead end! Moving up... position: " + poseI + ", " + poseJ);
                            direct = Direction.UP;
                            pose = Position.NORMAL;
                            break;
                        }
                    }
                }
            /* In case of previous direction being left, the method will check the remaining directions in case all of
             * them containing walls */
            case LEFT:
                /* Left */
                if(robotMap[poseI][poseJ - 1] == '*')
                {
                    /* Down */
                    if(robotMap[poseI + 1][poseJ] == '*')
                    {
                        /* Up */
                        if(robotMap[poseI - 1][poseJ] == '*')
                        {
                            poseJ = poseJ + 1;
                            System.out.println("Hit a dead end! Moving right... position: " + poseI + ", " + poseJ);
                            direct = Direction.RIGHT;
                            pose = Position.NORMAL;
                            break;
                        }
                    }
                }
            /* In case of previous direction being right, the method will check the remaining directions in case all of
             * them containing walls */
            case RIGHT:
                /* Right */
                if(robotMap[poseI][poseJ + 1] == '*')
                {
                    /* Down */
                    if(robotMap[poseI + 1][poseJ] == '*')
                    {
                        /* Up */
                        if(robotMap[poseI - 1][poseJ] == '*')
                        {
                            poseJ = poseJ - 1;
                            System.out.println("Hit a dead end! Moving left... position: " + poseI + ", " + poseJ);
                            direct = Direction.LEFT;
                            pose = Position.NORMAL;
                            break;
                        }
                    }
                }
            default:
                break;
        }
    }

    /**
     * This method is used to return the row position of the robot
     *
     * @return row position of the robot
     */
    public int getI()
    {
        return poseI;
    }

    /**
     * This method is used to return the row position of the robot
     *
     * @return column position of the robot
     */
    public int getJ()
    {
        return poseJ;
    }

}
