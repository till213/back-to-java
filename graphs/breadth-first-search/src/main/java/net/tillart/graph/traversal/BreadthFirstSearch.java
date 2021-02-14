package net.tillart.graph.traversal;


import net.tillart.graph.Vertex;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

    private Queue<Vertex> vertices;

    public BreadthFirstSearch() {
        vertices = new LinkedList<>();
    }

    private void visit(Vertex vertex) {
        System.out.println(vertex);
        vertex.setVisited(true);
    }

    public void traverse(Vertex root) {
        root.setVisited(true);
        visit(root);
        vertices.add(root);

        while (!vertices.isEmpty()) {
            Vertex vertex = vertices.remove();

            for (Vertex neighbour : vertex.getAdjacentList()) {
                if (!neighbour.isVisited()) {
                    visit(neighbour);
                    vertices.add(neighbour);
                }
            }
        }
    }

}
