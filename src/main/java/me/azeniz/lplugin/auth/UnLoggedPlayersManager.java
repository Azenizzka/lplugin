package me.azeniz.lplugin.auth;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UnLoggedPlayersManager {
    private Set<UUID> unLoggedPlayers = new HashSet<>();

    public boolean checkUnLoggedPlayers(UUID uuid) {
        return unLoggedPlayers.contains(uuid);
    }

    public void addUnLoggedPlayers(UUID uuid) {
        this.unLoggedPlayers.add(uuid);
    }

    public void delUnLoggedPlayers(UUID uuid) {
        if (checkUnLoggedPlayers(uuid)) {
            this.unLoggedPlayers.remove(uuid);
        }
    }
}
