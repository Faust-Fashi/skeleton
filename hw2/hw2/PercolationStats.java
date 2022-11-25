package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    double[] Prob;
    int T;
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        pf = new PercolationFactory();
        Prob = new double[T];
        this.T = T;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            int randRow = StdRandom.uniform(N);
            int randCol = StdRandom.uniform(N);
            while (!p.percolates()) {
                p.open(randRow, randCol);
            }
            Prob[i] = (double) p.numberOfOpenSites() / (N * N);
        }
    }
    public double mean() {
        return StdStats.mean(Prob);
    }
    public double stddev() {
        return StdStats.stddev(Prob);
    }
    public double confidenceLow() {
        return mean()-1.96*stddev()/Math.sqrt(T);
    }
    public double confidenceHigh(){
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }
}
