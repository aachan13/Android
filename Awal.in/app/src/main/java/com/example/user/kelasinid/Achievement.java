package com.example.user.kelasinid;

class Achievement {

    private int id;
    private String title, desc;
    private String rating;
    private int image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Achievement(int id, String title, String desc, String rating, int image) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.rating = rating;

        this.image = image;
    }


}
