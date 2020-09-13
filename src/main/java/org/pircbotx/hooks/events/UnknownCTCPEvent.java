/**
 * Copyright (C) 2010-2014 Leon Blakey <lord.quackstar at gmail.com>
 * <p>
 * This file is part of PircBotX.
 * <p>
 * PircBotX is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * <p>
 * PircBotX is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * PircBotX. If not, see <http://www.gnu.org/licenses/>.
 */
package org.pircbotx.hooks.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.User;
import org.pircbotx.UserHostmask;
import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.types.GenericChannelUserEvent;

import javax.annotation.Nullable;

/**
 * This event is dispatched whenever we receive an unknown CTCP request.
 *
 * @author covers1624
 */
@Data
@EqualsAndHashCode (callSuper = true)
public class UnknownCTCPEvent extends Event implements GenericChannelUserEvent {

    /**
     * The user hostmask that sent the CTCP request.
     */
    @Getter (onMethod = @__ (@Override))
    protected final UserHostmask userHostmask;
    /**
     * The user that sent the CTCP request.
     */
    @Getter (onMethod = @__ ({ @Override, @Nullable }))
    protected final User user;
    /**
     * The target channel of the CTCP request.
     */
    protected final Channel channel;

    /**
     * The CTCP request in question.
     */
    @Getter
    private final String request;

    public UnknownCTCPEvent(PircBotX bot, @NonNull UserHostmask userHostmask, User user, @Nullable Channel channel, @NonNull String request) {
        super(bot);
        this.request = request;
        this.userHostmask = userHostmask;
        this.user = user;
        this.channel = channel;
    }

    /**
     * Respond with a CTCP response to the user
     *
     * @param response The response to send
     */
    @Override
    public void respond(String response) {
        getUserHostmask().send().ctcpResponse(response);
    }
}
