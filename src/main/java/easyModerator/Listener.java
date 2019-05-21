package easyModerator;

import bot.configuration.ConfigManager;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {

    private ConfigManager cm = new ConfigManager();
    private static final String deletionEmote = "❌";
    //This should work if you use a custom emote or a unicode emoji
    //Custom emote: :leagueOfLegendsLogo:
    //Emoji: 😄 or ❌ or :smiley:

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getMember().getUser().isBot()) return;
        if (!event.getGuild().getId().equals(cm.getGuildId())) return;
        MessageReaction.ReactionEmote reactionEmote = event.getReactionEmote();
        if (reactionEmote.getName().equals(deletionEmote)) {
            String messageID = event.getMessageId();
            Message message = event.getChannel().getMessageById(messageID).complete();
            message.delete().queue();
            event.getGuild().getTextChannelById("337763599835594752").sendMessage(message).queue();
        }
    }
}
