package VisiCode.DataStructures;

import VisiCode.Internals.BoundingBox;
import VisiCode.Internals.Color;
import VisiCode.Internals.Entity;
import VisiCode.Internals.Graphics;
import VisiCode.Internals.Vector2;

import java.util.ArrayList;
import java.util.List;

/*
This class serves as a QuadTree class for entities. This structure act as a normal QuadTree, but supports objects with a BoundingBox.
 */
public class PointQuadTree<T extends Entity> implements QuadTree<T> {

    public BoundingBox boundingBox;
    private List<T> entities;
    private int capacity;
    public PointQuadTree<T> nw, ne, sw, se;
    private boolean divided;

    public PointQuadTree(Vector2 center, Vector2 halfSize, int capacity) {
        this.capacity = capacity;
        entities = new ArrayList<T>();
        this.boundingBox = new BoundingBox(center, halfSize);
        divided = false;
    }

    @Override
    public void Insert(T node) {
        if(this.divided) {
            //determine which subtree the node is contained in
            if(nw.boundingBox.ContainsPoint(node.position)) {nw.Insert(node);}
            else if(ne.boundingBox.ContainsPoint(node.position)) {ne.Insert(node);}
            else if(sw.boundingBox.ContainsPoint(node.position)) {sw.Insert(node);}
            else if(se.boundingBox.ContainsPoint(node.position)) {se.Insert(node);}
            else {
                System.out.println("Insertion Error");
            }
        }
        else if(this.entities.size() + 1 > this.capacity) {
            Subdivide();
            //determine which subtree the node is contained in
            if(nw.boundingBox.ContainsPoint(node.position)) {nw.Insert(node);}
            else if(ne.boundingBox.ContainsPoint(node.position)) {ne.Insert(node);}
            else if(sw.boundingBox.ContainsPoint(node.position)) {sw.Insert(node);}
            else if(se.boundingBox.ContainsPoint(node.position)) {se.Insert(node);}
            else {
                System.out.println("Insertion Error");
            }
        }
        else {
            entities.add(node);
        }
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

    public void Render(Graphics g, Color c) {
        g.DrawRect(this.boundingBox.center, this.boundingBox.halfSize, c); //draw box

        for(T nodes : entities) {
            g.DrawPoint(nodes.position.x, nodes.position.y, Color.GREEN);
        }

        if(nw != null) {nw.Render(g, c);}
        if(ne != null) {ne.Render(g, c);}
        if(sw != null) {sw.Render(g, c);}
        if(se != null) {se.Render(g, c);}
    }

    public void Subdivide() {
        this.divided = true;
        this.nw = new PointQuadTree<T>(new Vector2(this.boundingBox.center.x - this.boundingBox.halfSize.x/2f, this.boundingBox.center.y - this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
        this.ne = new PointQuadTree<T>(new Vector2(this.boundingBox.center.x + this.boundingBox.halfSize.x/2f, this.boundingBox.center.y - this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
        this.sw = new PointQuadTree<T>(new Vector2(this.boundingBox.center.x - this.boundingBox.halfSize.x/2f, this.boundingBox.center.y + this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
        this.se = new PointQuadTree<T>(new Vector2(this.boundingBox.center.x + this.boundingBox.halfSize.x/2f, this.boundingBox.center.y + this.boundingBox.halfSize.y/2f),
                new Vector2(this.boundingBox.halfSize.x/2f, this.boundingBox.halfSize.y/2f), this.capacity);
    }
}
