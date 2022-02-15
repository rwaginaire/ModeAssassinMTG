package fr.cours.modeassassinmtg.model;

import java.util.List;

public class Player {
    private final String mFirstName;
    private Player mTarget;

    public Player(String firstName) {
        mFirstName = firstName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public Player getTarget() {
        return mTarget;
    }

    public void setTarget(Player target) {
        mTarget = target;
    }
}
