package com.example.datinghaui.model;

public class Message {
    private String Sender;
    private String Reciever;
    private String content;
    private String timeSend;

    public Message() {
    }

    public Message(String sender, String reciever, String content, String timeSend) {
        Sender = sender;
        Reciever = reciever;
        this.content = content;
        this.timeSend = timeSend;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReciever() {
        return Reciever;
    }

    public void setReciever(String reciever) {
        Reciever = reciever;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }
}
