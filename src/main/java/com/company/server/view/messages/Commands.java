package com.company.server.view.messages;

public class Commands {
    public static final String MAIN_MENU_COMMANDS = """
            To login type <login>
            To register type <register>
            To exit menu type <exit>
            """;
    public static final String AUTHENTICATED_MENU_COMMANDS = """
            To sort an array type <sort array>
            To search an element in an array type <search array>
            To view history of algorithm runs type <view history>
            To delete your history type <delete history>
            To delete your account along with your history type <delete user>
            To exit menu type <exit>
            """;
    public static final String INPUT_TYPE_COMMANDS = """
            To manually give input type <manual input>
            To automatically give input type <automatic input>
            """;
    public static final String SORTING_COMMANDS = """
            To view chart with comparisons between sorting methods type <view chart>
            To run several sorting methods with custom input type <start sorting>
            """;
    public static final String SEARCHING_COMMANDS = """
            To view chart with comparisons between searching methods type <view chart>
            To run several searching methods with custom input type <start searching>
            """;
}
