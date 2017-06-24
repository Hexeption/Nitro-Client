/*******************************************************************************
 *     Nitro Client
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
import net.minecraft.network.Packet;

public class EventPacket extends Cancellable {

    private Packet<?> packet;

    public EventPacket(Packet<?> packet) {

        this.packet = packet;
    }

    public final Packet<?> getPacket() {

        return this.packet;
    }


    public final Packet<?> setPacket(Packet<?> packet) {

        return this.packet = packet;
    }

    public static class Send extends EventPacket {

        public Send(Packet<?> packet) {

            super(packet);
        }
    }

    public static class Receive extends EventPacket {

        public Receive(Packet<?> packet) {

            super(packet);
        }
    }
}
