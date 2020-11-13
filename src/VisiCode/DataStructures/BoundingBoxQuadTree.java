package VisiCode.DataStructures;

import VisiCode.Internals.BoundingBox;
import VisiCode.Internals.Color;
import VisiCode.Internals.Entity;
import VisiCode.Internals.Graphics;

import java.util.ArrayList;
import java.util.List;

public class BoundingBoxQuadTree<T extends Entity> implements QuadTree<T>{

    private BoundingBox boundingBox;
    private boolean divided;
    private BoundingBoxQuadTree<T> nw, ne, sw, se;
    private int capacity;
    private List<T> entities;

    public BoundingBoxQuadTree(BoundingBox boundingBox, int capacity) {
        this.boundingBox = boundingBox;
        this.divided = false;
        this.capacity = capacity;
        this.entities = new ArrayList<T>();
    }

    @Override
    public void Insert(T node) {
        //works the same as PointQuadTree except the entity being inserted is sorted by the smallest quadtree that the entities BoundingBox fits into
        if(this.boundingBox.ContainsBox(node.boundingBox)) {

        }
    }

    @Override
    public List<T> Query(BoundingBox b) {
        return null;
    }

    public void Query_recurse(BoundingBox b, List<T> l) {

    }

    public void Subdivide() {

    }

    public void Render(Graphics g, Color c) {
        g.DrawRect(this.boundingBox.center, this.boundingBox.halfSize, c); //draw box

        for(T nodes : entities) {
            g.DrawRect(nodes.boundingBox.center, nodes.boundingBox.halfSize, Color.GREEN);
        }

        if(nw != null) {nw.Render(g, c);}
        if(ne != null) {ne.Render(g, c);}
        if(sw != null) {sw.Render(g, c);}
        if(se != null) {se.Render(g, c);}
    }
}
