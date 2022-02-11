package fr.cours.modeassassinmtg.model;

import java.util.List;

public class Player {
    private final String mFirstName;
    private String mTarget;

    public Player(String firstName) {
        mFirstName = firstName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getTarget() {
        return mTarget;
    }

    public void setTarget(String target) {
        mTarget = target;
    }
}
