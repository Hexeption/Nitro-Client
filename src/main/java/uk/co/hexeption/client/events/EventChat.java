/*******************************************************************************
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *                     Version 2, December 2004
 *
 *  Copyright (C) 2004 Sam Hocevar <sam@hocevar.net>
 *
 *  Everyone is permitted to copy and distribute verbatim or modified
 *  copies of this license document, and changing it is allowed as long
 *  as the name is changed.
 *
 *             DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
 *    TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 *   0. You just DO WHAT THE FUCK YOU WANT TO.
 *
 ******************************************************************************/

package uk.co.hexeption.client.events;

import me.zero.alpine.type.Cancellable;
import net.minecraft.client.entity.EntityPlayerSP;

public class EventChat extends Cancellable {

    private String message;

    public EventChat(String message) {

        this.message = message;
    }

    public String getMessage() {

        return message;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public static class Send extends EventChat {

        private EntityPlayerSP entityPlayerSP;

        public Send(String message, EntityPlayerSP entityPlayerSP) {

            super(message);
            this.entityPlayerSP = entityPlayerSP;
        }

        public EntityPlayerSP getEntityPlayerSP() {

            return entityPlayerSP;
        }
    }

    public static class Recive extends EventChat {

        public Recive(String message) {

            super(message);
        }
    }
}
