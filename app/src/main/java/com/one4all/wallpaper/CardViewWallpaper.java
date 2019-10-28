package com.one4all.wallpaper;

public class CardViewWallpaper {
    private String imageUrl;
    private String author;
    private String likes;

    public CardViewWallpaper(String imageUrl, String author, String likes) {
        this.imageUrl = imageUrl;
        this.author = author;
        this.likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
