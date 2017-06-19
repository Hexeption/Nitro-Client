/*******************************************************************************
 *     ITweaker-Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
