package com.ouieat.models.notification;

import com.ouieat.models.Model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notifications")
public class Notification extends Model {

    private String senderName;

    private String senderId;

    private String senderProfilePictureLink;

    private String recipientId;

    private String recipientName;

    private String type;

    public Notification(
        String senderName,
        String senderId,
        String senderProfilePictureLink,
        String recipientId,
        String recipientName,
        String type
    ) {
        this.senderName = senderName;
        this.senderId = senderId;
        this.senderProfilePictureLink = senderProfilePictureLink;
        this.recipientId = recipientId;
        this.recipientName = recipientName;
        this.type = type;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderProfilePictureLink() {
        return senderProfilePictureLink;
    }

    public void setSenderProfilePictureLink(String senderProfilePictureLink) {
        this.senderProfilePictureLink = senderProfilePictureLink;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
