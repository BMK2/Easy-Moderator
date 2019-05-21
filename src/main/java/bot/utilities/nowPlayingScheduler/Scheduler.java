/*
 * Copyright (c) 2018 Chris Robinson. All rights reserved.
 */

package bot.utilities.nowPlayingScheduler;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Game;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MINUTES;

public class Scheduler implements Runnable {

    private JDA api;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Integer counter;
    private ArrayList<String> nowPlayingList = new ArrayList<>();

    public Scheduler(JDA api) {
        this.api = api;
        this.counter = 0;
        nowPlayingList.add("With your messages");
    }

    @Override
    public void run() {
        playScheduler();
    }

    private void playScheduler() {
        Runnable changeGame = () -> {
            api.getPresence().setGame(Game.playing(nowPlayingList.get(counter)));
            counter += 1;
            if (counter == nowPlayingList.size()) {
                counter = 0;
            }
        };
        scheduler.scheduleAtFixedRate(changeGame, 0, 10, MINUTES);
    }

}
