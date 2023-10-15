package fr.hivenapi.hivenig;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClanManager {
    private String name;
    private UUID leader;
    private List<UUID> members;

    public ClanManager(String name, UUID leader) {
        this.name = name;
        this.leader = leader;
        this.members = new ArrayList<>();
        this.members.add(leader);
    }

    public String getName() {
        return name;
    }

    public UUID getLeader() {
        return leader;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public void addMember(UUID member) {
        members.add(member);
    }

    public void removeMember(UUID member) {
        members.remove(member);
    }

    public boolean isMember(UUID member) {
        return members.contains(member);
    }
}