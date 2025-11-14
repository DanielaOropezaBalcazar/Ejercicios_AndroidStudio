package com.example.demo_1;

public class Primo {
    int n;

    public Primo(int n) {
        this.n = n;
    }

    boolean esPrimo() {
        int c = 0;
        for (int d = 1; d <= n; d++) {
            if(n%d == 0) c++;
        }
        return c==2;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
}
