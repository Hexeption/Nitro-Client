package uk.co.hexeption.client.event.events;

import uk.co.hexeption.client.event.Event;

public class EventClick extends Event {

    private MouseButtons mouseButtons;

    public EventClick(Type type, MouseButtons mouseButtons) {

        super(type);
        this.mouseButtons = mouseButtons;
    }

    public MouseButtons getMouseButtons() {

        return mouseButtons;
    }

    public enum MouseButtons {
        LEFT(0), RIGHT(1), MIDDLE(2);

        private int clickID;

        MouseButtons(int clickID) {

            this.clickID = clickID;
        }

        public int getClickID() {

            return clickID;
        }
    }


}
