/*******************************************************************************
 *     Nitro Client
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

package uk.co.hexeption.client.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import net.minecraft.util.Session;
import uk.co.hexeption.client.IMC;

import java.net.Proxy;

public class Auth implements IMC {

    private final YggdrasilUserAuthentication authentication;

    private Auth() {

        this.authentication = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
    }

    public static Auth INSTANCE() {

        return new Auth();
    }

    public final Auth username(String username) {

        authentication.setUsername(username);
        return this;
    }

    public final Auth password(String password) {

        authentication.setPassword(password);
        return this;
    }

    public final Session session() {

        try {
            authentication.logIn();
            GameProfile profile = authentication.getSelectedProfile();
            return new Session(profile.getName(), profile.getId().toString(), authentication.getAuthenticatedToken(), "MOJANG");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public final boolean login() {

        Session session = session();
        if (session == null) {
            return false;
        }

        mixMC.setSession(session);
        return true;
    }


}
