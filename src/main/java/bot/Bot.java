/*
 * Copyright (c) 2018 Chris Robinson. All rights reserved.
 */

package bot;
import bot.basics.MainListener;
import bot.configuration.ConfigManager;
import bot.utilities.nowPlayingScheduler.Scheduler;
import easyModerator.Listener;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;


public class Bot {

    public static void main(String[] args) throws LoginException, InterruptedException {
        ConfigManager cm = new ConfigManager();
        JDA api = new JDABuilder(AccountType.BOT).setToken(cm.getBotToken()).build().awaitReady();
        api.addEventListener(new MainListener(api));
        api.addEventListener(new Listener());
        api.setAutoReconnect(true);
        Thread nowPlayingThread = new Thread(new Scheduler(api));
        nowPlayingThread.start();
    }
}