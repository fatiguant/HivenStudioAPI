package fr.hivenapi.hivenig;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface HivenFriend {

    boolean areFriends(UUID p1, UUID p2);

    List<String> namesFriendsList(UUID asking);

    List<UUID> uuidFriendsList(UUID asking);


    Map<UUID, String> associatedFriendsList(UUID asking);


    List<String> requests(UUID asking);


    List<String> sentRequests(UUID asking);


    boolean removeFriend(UUID asking, UUID target);
}