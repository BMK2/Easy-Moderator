package easyModerator;

import bot.configuration.ConfigManager;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

    private ConfigManager cm = new ConfigManager();
    private final String deletionEmote = ":x:";
    //This should work if you use a custom emote or a unicode emoji
    //Custom emote: :leagueOfLegendsLogo:
    //Emoji: 😄 or :smiley:

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getMember().getUser().isBot()) return;
        if (!event.getGuild().getId().equals(cm.getGuildId())) return;
        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
        System.out.println(reactionEmote.toString());
    }
}
