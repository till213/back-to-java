package net.tillart.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class Vertex {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private boolean visited;
    @Getter
    private List<Vertex> adjacentList;

    public Vertex(String name) {
        super();
        this.name = name;
        adjacentList = new LinkedList<>();
    }

    void addNeightbour(Vertex vertex) {
        adjacentList.add(vertex);
    }

    @Override
    public String toString() {
        return name;
    }

}
