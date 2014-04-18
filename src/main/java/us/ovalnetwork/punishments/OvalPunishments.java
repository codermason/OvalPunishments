package us.ovalnetwork.punishments;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import us.ovalnetwork.api.OvalAPI;
import us.ovalnetwork.punishments.commands.*;
import us.ovalnetwork.punishments.commands.appeals.UnbanCommand;
import us.ovalnetwork.punishments.commands.appeals.UnmuteCommand;
import us.ovalnetwork.punishments.listeners.AppealEvent;
import us.ovalnetwork.punishments.listeners.ChatEvent;
import us.ovalnetwork.punishments.listeners.LoginEvent;
import us.ovalnetwork.punishments.listeners.PunishEvent;
import us.ovalnetwork.punishments.profiles.PunishmentProfileManager;

/**
 * created by codermason on 3/30/14
 */
public class OvalPunishments extends Plugin {

    private OvalAPI api;

    private Command[] commands = {new BanCommand(this), new MuteCommand(this), new TempbanCommand(this), new TempmuteCommand(this), new KickCommand(this), new UnbanCommand(this), new UnmuteCommand(this)};
    private Listener[] listeners = {new ChatEvent(this), new LoginEvent(this), new PunishEvent(this), new AppealEvent(this)};

    private PunishmentProfileManager manager;

    private static OvalPunishments instance;

    public void onEnable() {
        instance = this;

        if(hookAPI()) {
            log("Hooked into OvalAPI!");
        }else{
            log("Could not hook into OvalAPI! Shutting down...");
            this.getProxy().getPluginManager().getPlugin(this.getDescription().getName()).onDisable();
        }

        this.manager = new PunishmentProfileManager(getAPI());

        getAPI().registerProfileManager(this.manager);

        registerListeners();
        registerCommands();

    }

    public void onDisable() {}

    public PunishmentProfileManager getManager() {
        return this.manager;
    }

    public OvalAPI getAPI() {
        return this.api;
    }

    public static OvalPunishments get() {
        return instance;
    }

    public void log(Object o) {
        this.getLogger().info(o.toString());
    }

    private void registerCommands() {
        for(Command command : commands)
            this.getProxy().getPluginManager().registerCommand(this, command);
    }

    private void registerListeners() {
        for(Listener listener : listeners)
            this.getProxy().getPluginManager().registerListener(this, listener);
    }

    private boolean hookAPI() {
        api = (OvalAPI) this.getProxy().getPluginManager().getPlugin("OvalAPI");
        return api != null;
    }
}

