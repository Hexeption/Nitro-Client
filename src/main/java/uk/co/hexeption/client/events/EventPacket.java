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
