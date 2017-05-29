class Fox implements Package {
    int life;

    Fox() {
        this.life = 1;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    // Most probably a chicken
    public void eat(Package c) {
        c.setLife(0);
    }

    public boolean isAlive() {
        return this.life > 0;
    }

    public String toString() {
        return "FOX";
    }
}

