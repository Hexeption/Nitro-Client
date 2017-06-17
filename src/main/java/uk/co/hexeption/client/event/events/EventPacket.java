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
