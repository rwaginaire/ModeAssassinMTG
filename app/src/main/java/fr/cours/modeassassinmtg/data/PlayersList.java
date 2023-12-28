package fr.cours.modeassassinmtg.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayersList {
    private List<Player> mPlayersList;

    public PlayersList(List<Player> players) {
        mPlayersList = players;
    }

    public void addPlayer(Player player) {
        mPlayersList.add(player);
    }

    public void targetsDraw() {
        Set<Player> playersWithoutTarget = new HashSet<Player>(mPlayersList);
        Set<Player> playersDrawn = null;
        Set<Player> playersRemainingToBeDrawn = new HashSet<Player>(mPlayersList);
        while (!playersWithoutTarget.isEmpty()) {
            Player currentPlayer = getNextPlayerWithoutTarget(playersWithoutTarget, playersDrawn);
            Player target = drawTargetForPlayer(currentPlayer, playersRemainingToBeDrawn);
            currentPlayer.setTarget(target);
            playersWithoutTarget.remove(currentPlayer);
            playersDrawn.add(target);
            playersRemainingToBeDrawn.remove(target);
        }
    }

    private Player drawTargetForPlayer(Player currentPlayer, Set<Player> playersRemainingToBeDrawn) {
        Set<Player> possibleTargetsSet = playersRemainingToBeDrawn;
        possibleTargetsSet.remove(currentPlayer);
        List<Player> possibleTargetsList = new ArrayList<>(possibleTargetsSet);
        Collections.shuffle(possibleTargetsList);
        return possibleTargetsList.get(0);
    }

    private Player getNextPlayerWithoutTarget(Set<Player> playersWithoutTarget, Set<Player> playersDrawn) {
        Set<Player> eligiblePlayers = playersWithoutTarget;
        eligiblePlayers.removeAll(playersDrawn);
        if (eligiblePlayers.isEmpty()) {
            eligiblePlayers = playersWithoutTarget;
        }
        return eligiblePlayers.iterator().next();
    }

    private List<String> getPlayersNames() {
        List<String> playersNames = null;
        for (Player p : mPlayersList) {
            playersNames.add(p.getFirstName());
        }
        return playersNames;
    }
}
