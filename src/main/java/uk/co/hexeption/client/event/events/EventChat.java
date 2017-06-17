package uk.co.hexeption.client.event.events;

import net.minecraft.client.entity.EntityPlayerSP;
import uk.co.hexeption.client.event.Event;

public class EventChat extends Event {

    private String message;

    public EventChat(Type type, String message) {

        super(type);
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

        public Send(Type type, String message, EntityPlayerSP entityPlayerSP) {

            super(type, message);
            this.entityPlayerSP = entityPlayerSP;
        }

        public EntityPlayerSP getEntityPlayerSP() {

            return entityPlayerSP;
        }
    }

    public static class Recive extends EventChat {

        public Recive(Type type, String message) {

            super(type, message);
        }
    }
}
