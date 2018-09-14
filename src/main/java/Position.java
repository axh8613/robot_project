/**
 * The Position enum is a class that contains all of the position enums which determine the current status of the robot.
 *
 * @author  Ante Hakstok
 * @version 1.0
 * @since   2018-09-11
 */

public enum Position
{
    START /* This position indicates the robot is at the starting position*/,
    NORMAL /* This position indicates the robot is moving continiously*/,
    NORMAL_UNCHANGED  /* This position indicates the robot stopped in its tracks, but the direction is unchanged
    compared to the previous direction*/,
    END  /* This position indicates the robot is at the end position*/
}
