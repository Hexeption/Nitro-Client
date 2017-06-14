package uk.co.hexeption.client.event;

public interface EventListener {

    default Priority getPriority() {

        return Priority.NORMAL;
    }

    void onEvent(Event event);

    enum Priority {
        LOW, NORMAL, HIGH
    }

}
