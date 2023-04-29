package me.azeniz.lplugin;

import me.azeniz.lplugin.armorstand.ArmorstandEvents;
import me.azeniz.lplugin.auth.AuthEvents;
import me.azeniz.lplugin.auth.UnLoggedPlayersManager;
import me.azeniz.lplugin.auth.commands.LoginCMD;
import me.azeniz.lplugin.auth.commands.RegisterCMD;
import me.azeniz.lplugin.commands.BowTntCMD;
import me.azeniz.lplugin.commands.BowtpCMD;
import me.azeniz.lplugin.commands.CordsCMD;
import me.azeniz.lplugin.commands.PushMessageCMD;
import me.azeniz.lplugin.events.Events;
import me.azeniz.lplugin.events.MotdEvents;
import me.azeniz.lplugin.events.TabListEvents;
import me.azeniz.lplugin.sit.SitCommand;
import me.azeniz.lplugin.sit.SitController;
import me.azeniz.lplugin.sit.SitEvents;
import me.azeniz.lplugin.sleep.SleepEvents;
import me.azeniz.lplugin.vanish.VanishCMD;
import me.azeniz.lplugin.vanish.VanishEvents;
import me.azeniz.lplugin.vanish.VanishPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class Plugin extends JavaPlugin {
    public static Plugin instance;
    public static UnLoggedPlayersManager unloggedPlayersManager;
    public static VanishPlayerManager vanishPlayerManager;
    public static SitController sitController;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        unloggedPlayersManager = new UnLoggedPlayersManager();
        vanishPlayerManager = new VanishPlayerManager();
        sitController = new SitController();
        getCommand("login").setExecutor(new LoginCMD());
        getCommand("bowtp").setExecutor(new BowtpCMD());
        getCommand("bowtnt").setExecutor(new BowTntCMD());
        getCommand("coords").setExecutor(new CordsCMD());
        getCommand("register").setExecutor(new RegisterCMD());
        getCommand("vanish").setExecutor(new VanishCMD());
        getCommand("pm").setExecutor(new PushMessageCMD());
        getCommand("sit").setExecutor(new SitCommand());
        //getCommand("spawnevoker").setExecutor(new SpawnEvokerCMD());
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new SleepEvents(), this);
        Bukkit.getPluginManager().registerEvents(new TabListEvents(), this);
        Bukkit.getPluginManager().registerEvents(new MotdEvents(), this);
        Bukkit.getPluginManager().registerEvents(new AuthEvents(), this);
        Bukkit.getPluginManager().registerEvents(new VanishEvents(), this);
        Bukkit.getPluginManager().registerEvents(new SitEvents(), this);
        Bukkit.getPluginManager().registerEvents(new ArmorstandEvents(), this);
    }

    @Override
    public void onDisable() {
    }
}
