class Grain implements Package {
    int life;

    Grain() {
        this.life = 1;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isAlive() {
        return this.life > 0;
    }

    public String toString() {
        return "GRAIN";
    }
}

