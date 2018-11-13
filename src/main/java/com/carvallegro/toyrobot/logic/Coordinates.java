package com.carvallegro.toyrobot.logic;

public class Coordinates {
    private Integer x;
    private Integer y;

    public Coordinates(Integer x, Integer y){
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    @Override
    protected Coordinates clone() throws CloneNotSupportedException {
        return new Coordinates(x, y);
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Coordinates)){
            return false;
        }

        Coordinates comparedTo = (Coordinates) object;
        return x == comparedTo.x && y == comparedTo.y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
