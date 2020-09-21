package com.github.duorourou.sidecar.contentservice;

public class ContentResponse {
    public final String name;
    public final String author;
    public final String type;
    public final String label;

    public ContentResponse(String name, String author, String type, String label) {
        this.name = name;
        this.author = author;
        this.type = type;
        this.label = label;
    }
}
