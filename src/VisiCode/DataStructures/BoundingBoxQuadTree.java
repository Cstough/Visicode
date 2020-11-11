package VisiCode.DataStructures;

import VisiCode.Internals.BoundingBox;
import VisiCode.Internals.Entity;

import java.util.ArrayList;
import java.util.List;

/*
This class serves as a QuadTree class for entities. This structure act as a normal QuadTree, but supports objects with a BoundingBox.
 */
public class BoundingBoxQuadTree<T extends Entity> implements QuadTree<T> {

    private List<T> entities;
    private BoundingBoxQuadTree<T> nw, ne, sw, se;
    private boolean divided;

    public BoundingBoxQuadTree() {
        this.divided = false;
        entities = new ArrayList<T>();
    }

    @Override
    public void Insert(T node) {

    }

    @Override
    public List<T> Query(BoundingBox b) {
        return null;
    }

    //Subdivide will divide this QuadTree bounds into 4 smaller QuadTrees using the nw, ne, sw, se references
    //TODO: Create a Subdivide function

    //Contains is used when inserting nodes with a bounding box into this QuadTree. When inserting, the entities BoundingBox will be used to determine the smallest QuadTree Bounds it fits into completely, i.e.
    //a giant entity will be inside of a higher tree since those bounds are larger, smaller entities will be (hopefully) inside smaller trees.
    //TODO: Create a Contains function
}
