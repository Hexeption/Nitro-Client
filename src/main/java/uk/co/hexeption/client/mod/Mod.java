/*******************************************************************************
 *     ITweaker-Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package uk.co.hexeption.client.mod;

import org.lwjgl.input.Keyboard;
import uk.co.hexeption.client.Client;
import uk.co.hexeption.client.IMC;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class Mod implements IMC {

    private String name = getClass().getAnnotation(ModInfo.class).name();

    private String description = getClass().getAnnotation(ModInfo.class).description();

    private Category category = getClass().getAnnotation(ModInfo.class).category();

    private int bind = getClass().getAnnotation(ModInfo.class).bind();

    private boolean state;


    public boolean getState() {

        return state;
    }

    public void setState(boolean state) {

        onToggle();
        if (state) {
            this.state = true;
            onEnable();
            Client.INSTANCE.eventBus.subscribe(this);
        } else {
            Client.INSTANCE.eventBus.unsubscribe(this);
            this.state = false;
            onDisable();
        }

    }

    public void toggle() {

        setState(!this.state);
    }

    public void onToggle() {

    }

    public void onDisable() {

    }

    public void onEnable() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {

        this.category = category;
    }

    public int getBind() {

        return bind;
    }

    public void setBind(int bind) {

        this.bind = bind;
    }

    public String getKeyName() {

        return getBind() == -1 ? "-1" : Keyboard.getKeyName(getBind());
    }

    public enum Category {

        COMBAT(0x2384542),
        PLAYER(0x2384542),
        MOVEMENT(0x2384542),
        RENDER(0x2384542),
        WORLD(0x2384542),
        BUILD(0x2384542),
        MISC(0x2384542),;

        public int color;

        Category(int color) {

            this.color = color;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface ModInfo {

        String name();

        String description();

        Category category();

        int bind() default Keyboard.KEY_NONE;
    }

}
