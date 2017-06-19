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

public class EventClick {

    private MouseButtons mouseButtons;

    public EventClick(MouseButtons mouseButtons) {

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
