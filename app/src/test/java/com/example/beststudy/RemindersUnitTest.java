package com.example.beststudy;

import org.junit.Test;

import com.example.beststudy.CalenderReminders;

import static org.junit.Assert.*;

/**
 *
 */
public class RemindersUnitTest {
    //Test that priority of 1 makes the text blue
    @Test
    public void priority1_isBlue() {

        CalenderReminders C = new CalenderReminders();
        String input = "test";
        String expected = "<font color=#0000ff>"+input+"</font>";
        String output = C.getPriorityColor("1", input);

        assertEquals(expected, output);
    }
    //Test that a priority of 10 makes the text red
    @Test
    public void priority10_isRed() {

        CalenderReminders C = new CalenderReminders();
        String input = "test";
        String expected = "<font color=#ff0000>"+input+"</font>";
        String output = C.getPriorityColor("10", input);

        assertEquals(expected, output);
    }
    //Test that any string outside of ["0" - "10"] leaves the string color unchanged
    @Test
    public void errorPriority_hasNoColor() {

        CalenderReminders C = new CalenderReminders();
        String input = "test";
        String expected = "<font color=#000000>"+input+"</font>";
        String output = C.getPriorityColor("error", input);

        assertEquals(expected, output);
    }



}