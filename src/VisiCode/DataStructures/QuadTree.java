package VisiCode.DataStructures;

import VisiCode.Internals.BoundingBox;
import VisiCode.Internals.Entity;

import java.util.List;

public interface QuadTree<T extends Entity> {
    //Insert: Inserts a node into the tree
    void Insert(T node);
    //Query: Returns a list of nodes that are within the BoundingBox
    List<T> Query(BoundingBox b);
}
