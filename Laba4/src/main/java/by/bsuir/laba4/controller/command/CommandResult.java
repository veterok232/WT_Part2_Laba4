package by.bsuir.laba4.controller.command;

/**
 * Command result class
 */
public class CommandResult {
    /**
     * Current page
     */
    private final String page;

    /**
     * Is redirect
     */
    private final boolean redirect;

    /**
     * Constructor
     *
     * @param page current page
     * @param redirect is redirect
     */
    public CommandResult(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    /**
     * Forward
     *
     * @param page page
     *
     * @return CommandResult
     */
    public static CommandResult forward(String page) {
        return new CommandResult(page, false);
    }

    /**
     * Redirect
     *
     * @param page page
     *
     * @return CommandResult
     */
    public static CommandResult redirect(String page) {
        return new CommandResult(page, true);
    }

    /**
     * Get current page
     *
     * @return String
     */
    public String getPage() {
        return page;
    }

    /**
     * Get is redirect
     *
     * @return boolean
     */
    public boolean isRedirect() {
        return redirect;
    }
}
