import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests relating to the RobotInitialize class
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */
class RobotInitializeTest
{

    @Test
    /**
     * This test method will test if the robot can move continously in one direction
     */
    void continueMovement()
    {
        /* An example map is initialized */
        char[][] mapTest =
                {
                        {'*', '*','*'},
                        {'.', '.', '.'},
                        {'*', '*', '*'}
                };

        /* Starting position */
        int i = 1;
        int j = 1;

        /* The position which will make this test successful */
        int realI = 1;
        int realJ = 2;

        /* The robot was moving in the right direction and its position is normal*/
        Direction dir = Direction.RIGHT;

        Position pose = Position.NORMAL;

        RobotInitialize ri = new RobotInitialize();

        ri.preparePose(pose, i, j, mapTest);
        ri.continueMovement(dir);

        /* The comparisons for successful testing are here*/
        assertEquals(ri.getI(), realI);
        assertEquals(ri.getJ(), realJ);
    }

    @Test
    /**
     * This test method will test if the robot can check the neighbours and turn in the correct direction
     */
    void checkNeighbours()
    {
        char[][] mapTest =
                {
                        {'*', '*','*'},
                        {'.', '.', '*'},
                        {'*', '.', '*'}
                };

        int i = 1;
        int j = 1;

        int realI = 2;
        int realJ = 1;

        Direction dir = Direction.RIGHT;

        Position pose = Position.NORMAL;

        RobotInitialize ri = new RobotInitialize();

        ri.preparePose(pose, i, j, mapTest);
        ri.checkNeighbours(dir);

        assertEquals(ri.getI(), realI);
        assertEquals(ri.getJ(), realJ);
    }

    @Test
    /**
     * This test method will test if the robot can detect a dead end and turn back
     */
    void deadCheck()
    {
        char[][] mapTest =
                {
                        {'*', '*','*'},
                        {'.', '.', '*'},
                        {'*', '*', '*'}
                };

        int i = 1;
        int j = 1;

        int realI = 1;
        int realJ = 0;

        Direction dir = Direction.RIGHT;

        Position pose = Position.NORMAL;

        RobotInitialize ri = new RobotInitialize();

        ri.preparePose(pose, i, j, mapTest);
        ri.deadCheck(dir);

        assertEquals(ri.getI(), realI);
        assertEquals(ri.getJ(), realJ);
    }
}