package by.bsuir.laba4.controller.command;

import by.bsuir.laba4.controller.command.admin.AddRoomCommand;
import by.bsuir.laba4.controller.command.admin.DeoccupyRoomCommand;
import by.bsuir.laba4.controller.command.admin.ShowRoomsCommand;
import by.bsuir.laba4.controller.command.common.*;
import by.bsuir.laba4.controller.command.user.MainPageCommand;
import by.bsuir.laba4.controller.command.user.MakeOrderCommand;

/**
 * Command factory class
 */
public class CommandFactory {
    /**
     * Command factory instance
     */
    private static final CommandFactory INSTANCE = new CommandFactory();

    /**
     * Main page label
     */
    private static final String MAIN_PAGE = "mainPage";

    /**
     * Make order label
     */
    private static final String MAKE_ORDER = "makeOrder";

    /**
     * Login label
     */
    private static final String LOGIN = "login";

    /**
     * Show rooms label
     */
    private static final String SHOW_ROOMS = "showRooms";

    /**
     * Change language label
     */
    private static final String CHANGE_LANGUAGE = "changeLanguage";

    /**
     * Add room label
     */
    private static final String ADD_ROOM = "addRoom";

    /**
     * Deoccupy label
     */
    private static final String DEOCCUPY_ROOM = "deoccupyRoom";

    /**
     * Logout label
     */
    private static final String LOG_OUT = "signOut";

    /**
     * Register label
     */
    private static final String SIGN_UP = "signUp";

    /**
     * Start pae label
     */
    private static final String START_PAGE = "startPage";

    /**
     * Start login label
     */
    private static final String START_LOGIN = "startLogin";

    /**
     * Command factory
     */
    private CommandFactory() {
    }

    /**
     * Get command factory instance
     *
     * @return CommandFactory
     */
    public static CommandFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Get command
     *
     * @param command command type
     *
     * @return Command
     */
    public Command getCommand(String command) {
        switch (command) {
            case LOGIN:
                return new LoginCommand();
            case SHOW_ROOMS:
                return new ShowRoomsCommand();
            case MAIN_PAGE:
                return new MainPageCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case ADD_ROOM:
                return new AddRoomCommand();
            case MAKE_ORDER:
                return new MakeOrderCommand();
            case DEOCCUPY_ROOM:
                return new DeoccupyRoomCommand();
            case LOG_OUT:
                return new LogOutCommand();
            case START_PAGE:
                return new StartPageCommand();
            case SIGN_UP:
                return new SignUpCommand();
            case START_LOGIN:
                return new StartLoginCommand();
            default:
                throw new UnsupportedOperationException();
        }
    }
}
