package stdlibport.client.tv.fungus.shows;

import stdlibport.client.tv.fungus.Show;

public class Batman extends Show {

    @Override
    public String[] getEpisodes() {
        return new String[] {
                "https://therokuchannel.roku.com/watch/736174f78ea153039175b5283ecdcbc4 = All Episodes on The Roku Channel",
                "https://therokuchannel.roku.com/watch/736174f78ea153039175b5283ecdcbc4 = S1 Ep1 - Hi Diddle Riddle",
                "https://therokuchannel.roku.com/watch/62ac0ccfc95d5b88a9edb505fc963cd8 = S1 Ep2 - Smack in the Middle",
        };
    }

}
