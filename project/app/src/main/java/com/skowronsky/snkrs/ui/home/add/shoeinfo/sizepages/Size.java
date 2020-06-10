package com.skowronsky.snkrs.ui.home.add.shoeinfo.sizepages;

/**
 * Klasa która jest używana do przechowywania rozmiarów butów
 */
public class Size {
    private double eu;
    private double us;
    private double uk;

    public Size(double eu, double us, double uk){
        this.eu = eu;
        this.us = us;
        this.uk = uk;
    }
    public double getEu() {
        return eu;
    }

    public double getUs() {
        return us;
    }

    public double getUk() {
        return uk;
    }
}
