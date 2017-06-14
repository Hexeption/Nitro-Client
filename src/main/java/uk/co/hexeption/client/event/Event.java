package uk.co.hexeption.client.event;

public class Event {

    private Type type;

    private boolean cancelled;

    public Event(Type type) {

        this.type = type;
    }

    public Type getType() {

        return type;
    }

    public void setType(Type type) {

        this.type = type;
    }

    public boolean isCancelled() {

        return cancelled;
    }

    public void cancel() {

        cancelled = true;
    }

    public <T extends Event> T cast() {

        return (T) this;
    }

    public enum Type {
        PRE, POST
    }
}
