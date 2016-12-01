package com.example.hyeonseok.gumpulza_v003;

public class Question {

    private byte[] QUESTION;
    private String ANSWER;

    public Question() {

        QUESTION = null;
        ANSWER = "";

    }

    public byte[] getQUESTION() { return QUESTION; }

    public void setQUESTION( byte[] QUESTION ) { this.QUESTION = QUESTION; }

    public String getANSWER() { return ANSWER; }

    public void setANSWER( String ANSWER ) { this.ANSWER = ANSWER; }

}
