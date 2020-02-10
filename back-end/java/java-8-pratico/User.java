class User {
    private String name;
    private int points;
    private boolean isOwner;

    public User(String name, int points) {
        this.name = name;
        this.points = points;
        this.isOwner = false;
    }

    public String getName() {
        return this.name;
    }

    public int getPoints() {
        return this.points;
    }

    public boolean isOwner() {
        return this.isOwner;
    }

    public void defineForOwner() {
        this.isOwner = true;
    }
}