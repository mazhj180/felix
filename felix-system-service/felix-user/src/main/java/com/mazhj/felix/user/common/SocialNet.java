package com.mazhj.felix.user.common;

import java.util.List;

/**
 * @author mazhj
 */
public class SocialNet {

    UserNode root;

    private class UserNode{

        private String userId;

        private List<UserNode> friends;

        private List<UserNode> fans;

        private List<UserNode> idols;
    }

    public SocialNet(){

    }
}
