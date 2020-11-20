package VisiCode.DataStructures;

import VisiCode.Internals.*;

import java.util.ArrayList;
import java.util.List;

public class BoundingBoxQuadTree<T extends Entity> implements QuadTree<T>{

    private BoundingBox boundingBox;
    private boolean divided;
    private BoundingBoxQuadTree<T> nw, ne, sw, se;
    private int capacity;
    private List<T> entities;

    public BoundingBoxQuadTree(Vector2 center, Vector2 halfSize, int capacity) {
        this.boundingBox = new BoundingBox(center, halfSize);
        this.divided = false;
        this.capacity = capacity;
        this.entities = new ArrayList<T>();
    }

    public BoundingBoxQuadTree(BoundingBox boundingBox, int capacity) {
        this.boundingBox = boundingBox;
        this.divided = false;
        this.capacity = capacity;
        this.entities = new ArrayList<T>();
    }

    @Override
    public void Insert(T node) {
        //works the same as PointQuadTree except the entity being inserted is sorted by the smallest quadtree that the entities BoundingBox fits into
        /*if(this.divided) {
            //determine which subtree the node is contained in
            if(nw.boundingBox.Intersects(node.boundingBox)) {nw.Insert(node);}
            else if(ne.boundingBox.Intersects(node.boundingBox)) {ne.Insert(node);}
            else if(sw.boundingBox.Intersects(node.boundingBox)) {sw.Insert(node);}
            else if(se.boundingBox.Intersects(node.boundingBox)) {se.Insert(node);}
            else {
                System.out.println("Insertion Error");
            }
        }
        else if(this.entities.size() + 1 > this.capacity) {
            Subdivide();
            //determine which subtree the node is contained in
            if(nw.boundingBox.Intersects(node.boundingBox)) {nw.Insert(node);}
            else if(ne.boundingBox.Intersects(node.boundingBox)) {ne.Insert(node);}
            else if(sw.boundingBox.Intersects(node.boundingBox)) {sw.Insert(node);}
            else if(se.boundingBox.Intersects(node.boundingBox)) {se.Insert(node);}
            else {
                System.out.println("Insertion Error");
            }
        }
        else {
            entities.add(node);
        }*/
    }

    @Override
    public List<T> Query(BoundingBox b) {
        //recursively check for all boundingboxes that intersect with b

        List<T> l = new ArrayList<T>();

        if(this.boundingBox.Intersects(b)) {

            for(T node : this.entities) {
                if(b.ContainsPoint(node.position)) {
                    l.add(node);
                }
            }

            if(divided) {
                if (nw.boundingBox.Intersects(b)) {
                    nw.Query_recurse(b, l);
                }
                if (ne.boundingBox.Intersects(b)) {
                    ne.Query_recurse(b, l);
                }
                if (sw.boundingBox.Intersects(b)) {
                    sw.Query_recurse(b, l);
                }
                if (se.boundingBox.Intersects(b)) {
                    se.Query_recurse(b, l);
                }
            }
        }
        return l;
    }

    private void Query_recurse(BoundingBox b, List<T> l) {
        for(T node : this.entities) {
            if(b.ContainsPoint(node.position)) {
                l.add(node);
            }
        }

        if(divided) {
            if (nw.boundingBox.Intersects(b)) {
                nw.Query_recurse(b, l);
            }
            if (ne.boundingBox.Intersects(b)) {
                ne.Query_recurse(b, l);
            }
            if (sw.boundingBox.Intersects(b)) {
                sw.Query_recurse(b, l);
            }
            if (se.boundingBox.Intersects(b)) {
                se.Query_recurse(b, l);
            }
        }
    }

    public void Subdivide() {
        this.divided = true;
        this.nw = new BoundingBoxQuadTree<T>(new Vector2(this.boundingBox.center.x - this.boundingBox.halfSize.x/2f, this.boundingBox.center.y - this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
        this.ne = new BoundingBoxQuadTree<T>(new Vector2(this.boundingBox.center.x + this.boundingBox.halfSize.x/2f, this.boundingBox.center.y - this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
        this.sw = new BoundingBoxQuadTree<T>(new Vector2(this.boundingBox.center.x - this.boundingBox.halfSize.x/2f, this.boundingBox.center.y + this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
        this.se = new BoundingBoxQuadTree<T>(new Vector2(this.boundingBox.center.x + this.boundingBox.halfSize.x/2f, this.boundingBox.center.y + this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
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
