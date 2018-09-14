
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains tests relating to the RobotMethods class
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */
class RobotMethodsTest
{

    @Test
    /**
     * This test method will test if the map initializes correctly
     */
    void initMap()
    {
        boolean actual = true;
        RobotMethods rm = new RobotMethods();

        try
        {
            String path = "map.txt";
            rm.initMap(path);
            rm.printMap();
        }
        catch(FileNotFoundException fe)
        {
            System.out.println("Can't find the map!");
            actual = false;
        }

        assertEquals(actual, true);
    }
}