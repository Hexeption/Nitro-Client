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

package uk.co.hexeption.client.event.events;

import net.minecraft.network.Packet;
import uk.co.hexeption.client.event.Event;

public class EventPacket extends Event {

    private final Packet<?> packet;

    public EventPacket(Type type, Packet<?> packet) {

        super(type);
        this.packet = packet;
    }

    public <T extends Packet> T getPacketRaw() {

        return (T) packet;
    }

    public static class Send extends EventPacket {

        public Send(Type type, Packet<?> packet) {

            super(type, packet);
        }
    }

    public static class Receive extends EventPacket {

        public Receive(Type type, Packet<?> packet) {

            super(type, packet);
        }
    }
}
