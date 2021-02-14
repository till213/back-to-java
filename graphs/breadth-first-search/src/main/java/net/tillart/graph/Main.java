package net.tillart.graph;

import net.tillart.graph.traversal.BreadthFirstSearch;

public class Main {

    public static void main(String[] args) {

        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");

        a.addNeightbour(b);
        a.addNeightbour(f);
        a.addNeightbour(g);

        b.addNeightbour(a);
        b.addNeightbour(c);
        b.addNeightbour(d);

        c.addNeightbour(b);

        d.addNeightbour(b);
        d.addNeightbour(e);

        f.addNeightbour(a);

        g.addNeightbour(a);
        g.addNeightbour(h);

        h.addNeightbour(g);

        var bfs = new BreadthFirstSearch();
        bfs.traverse(a);
    }
}
