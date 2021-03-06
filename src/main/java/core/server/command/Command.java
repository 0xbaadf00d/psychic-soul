package core.server.command;

import core.server.session.Session;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * {@code Command} interface describe methods that each commands
 * implementation must have.
 *
 * @author Thibault Meyer
 * @version 1.1.0
 * @since 1.0.0
 */
public interface Command {

    /**
     * Get the minimal number of arguments needed. The command OpCode is
     * included in the number of arguments.
     *
     * @return The minimal number of arguments needed
     * @since 1.0.0
     */
    int getMinimalArgsCountNeeded();

    /**
     * Get the maximal number of arguments needed. The command OpCode is
     * included in the number of arguments. If this method return -1, the
     * command can take any number of arguments.
     *
     * @return The maximal number of arguments needed
     * @since 1.0.0
     */
    int getMaximalArgsCountNeeded();

    /**
     * Get the type of this command.
     *
     * @return The command type
     * @since 1.0.0
     */
    CmdType getType();

    /**
     * Check if this command can by executed by this user session.
     *
     * @param usrSession The current user session
     * @return {@code true} is the command can be executed, otherwise, {@code false}
     * @since 1.1.0
     */
    boolean canExecute(final Session usrSession);

    /**
     * Execute the command. The first entry (0) of the payload always
     * contain the command OpCode.
     *
     * @param payload           The command arguments
     * @param usrSession        The user session who call this command
     * @param connectedSessions The collection of connected sessions
     * @param globalFollowers   The map of all followers
     * @throws IndexOutOfBoundsException if payload don't contain enough arguments
     * @since 1.0.0
     */
    void execute(final String[] payload, final Session usrSession, final Collection<Session> connectedSessions, final Map<String, List<Session>> globalFollowers) throws ArrayIndexOutOfBoundsException;

    /**
     * Possible types of command.
     *
     * @author Thibault Meyer
     * @version 1.0.0
     * @since 1.0.0
     */
    enum CmdType {

        /**
         * This method is used to authenticate user.
         *
         * @since 1.0.0
         */
        AUTHENTICATION,

        /**
         * Simple command.
         *
         * @since 1.0.0
         */
        COMMAND
    }
}
