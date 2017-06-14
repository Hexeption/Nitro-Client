package uk.co.hexeption.client.mod.mods;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.event.Event;
import uk.co.hexeption.client.event.events.EventClick;
import uk.co.hexeption.client.mod.Mod;

@Mod.ModInfo(name = "Test", description = "Test", category = Mod.Category.TEST, bind = Keyboard.KEY_N)
public class Test extends Mod {

    @Override
    public void onEvent(Event event) {

        if (getState() && event instanceof EventClick && event.getType() == Event.Type.PRE) {
//            if (((EventClick) event).getMouseButtons() == EventClick.MouseButtons.LEFT) {
//
//                System.out.println("Left");
//            }
//            if (((EventClick) event).getMouseButtons() == EventClick.MouseButtons.RIGHT) {
//
//                System.out.println("Right");
//            }
//            if (((EventClick) event).getMouseButtons() == EventClick.MouseButtons.MIDDLE) {
//
//                System.out.println("Middle");
//            }


        }
    }
}
