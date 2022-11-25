package hw2;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] sites;
    private boolean[][] blocked;
    private int N;
    private int openSize;
    private WeightedQuickUnionUF grid;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new java.lang.IllegalArgumentException();
        this.N = N;
        sites = new int[N][N];
        blocked = new boolean[N][N];
        openSize = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocked[i][j] = true;
            }
        }
        grid = new WeightedQuickUnionUF(xyTo1D(N, N));
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkBoundary(row, col, true);
        if (!blocked[row][col])
            return;
        blocked[row][col] = false;
        openSize++;
        if (checkBoundary(row, col - 1, false) && isOpen(row, col - 1)) {
            grid.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }if (checkBoundary(row, col + 1, false) && isOpen(row, col + 1)) {
            grid.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }if (checkBoundary(row - 1, col, false) && isOpen(row - 1, col)) {
            grid.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }if (checkBoundary(row + 1, col, false) && isOpen(row + 1, col)) {
            grid.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkBoundary(row, col, true);
        return !blocked[row][col];
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i < N; i++) {
            if (isOpen(row, col) && isOpen(0, i) &&
                grid.connected(xyTo1D(row, col), xyTo1D(0, i)) )
                return true;
        }
        return false;
    }
    // number of open sites
    public int numberOfOpenSites() {
        return openSize;
    }
    // check if index is out of boundary
    private boolean checkBoundary(int row, int col, boolean exception) {
        if (row >= N || col >= N || row < 0 || col < 0)
            if (exception)
                throw new java.lang.IndexOutOfBoundsException();
            else
                return false;
        return true;
    }
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }
    public boolean percolates() {
        for (int i = 0; i < N; i++) {
            if (isFull(N - 1, i))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
