package com.mazhj.felix.forum.websocket.container;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author mazhj
 */
public class SensitiveWordsTire {

    private final static String REPLACE_CHAR = "**";

    private final TireNode root = new TireNode();

    private class TireNode{

        private final Map<Character,TireNode> childNodes = new HashMap<>();

        private void putChild(Character word,TireNode child){
            childNodes.put(word,child);
        }

        private TireNode getChild(Character word){
            return childNodes.get(word);
        }

        private Boolean isEnding(){
            return this.childNodes.isEmpty();
        }

    }

    public SensitiveWordsTire(Set<String> wordSet){
        for (String words : wordSet) {
            TireNode temp = root;
            char[] charArray = words.toCharArray();
            for (char word : charArray) {
                TireNode child = temp.getChild(word);
                if (child == null){
                    child = new TireNode();
                    temp.putChild(word,child);
                }
                temp = child;
            }
        }
    }

    public String filter(String words){
        if (words.isEmpty() || words.isBlank()){
            return null;
        }

        TireNode pointer = root;
        int left = 0;
        int right = 0;
        StringBuilder sb = new StringBuilder();

        while (left < words.length()){
            char word = words.charAt(right);
            pointer = pointer.getChild(word);
            if (pointer == null){
                sb.append(words.charAt(left));
                right = ++left;
                pointer = root;
            } else if (pointer.isEnding()){
                sb.append(REPLACE_CHAR);
                left = ++right;
                pointer = root;
            } else {
                if (right >= words.length() - 1){
                    break;
                }
            }
            right++;
            if (pointer.isEnding()){

            }

        }
        return sb.toString();
    }
}
