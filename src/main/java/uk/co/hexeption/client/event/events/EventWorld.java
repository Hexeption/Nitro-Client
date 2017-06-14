package uk.co.hexeption.client.event.events;

import net.minecraft.client.multiplayer.WorldClient;
import uk.co.hexeption.client.event.Event;

public class EventWorld extends Event {

    protected final WorldClient worldClient;

    public EventWorld(Type type, WorldClient worldClient) {

        super(type);
        this.worldClient = worldClient;
    }

    public static class Load extends EventWorld {

        public Load(Type type, WorldClient worldClient) {

            super(type, worldClient);
        }
    }

    public static class Unload extends EventWorld {

        public Unload(Type type, WorldClient worldClient) {

            super(type, worldClient);
        }
    }
}
