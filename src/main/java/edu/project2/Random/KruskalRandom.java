package edu.project2.Random;

import edu.project2.MazeStructure.Edge;
import java.util.ArrayList;
import java.util.Collections;

public class KruskalRandom implements Shuffle {
    @Override
    public void shuffle(ArrayList<Edge> edges) {
        Collections.shuffle(edges);
    }
}
