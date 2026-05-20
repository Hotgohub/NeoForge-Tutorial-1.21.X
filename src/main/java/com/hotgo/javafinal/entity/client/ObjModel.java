package com.hotgo.javafinal.entity.client;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.io.*;
import java.util.*;

public class ObjModel {

    public record Vertex(float x, float y, float z) {}
    public record TexCoord(float u, float v) {}
    public record Normal(float x, float y, float z) {}

    public record Triangle(
            Vertex v0, Vertex v1, Vertex v2,
            TexCoord t0, TexCoord t1, TexCoord t2,
            Normal n0,   Normal n1,   Normal n2
    ) {}

    public final Map<String, List<Triangle>> groups = new LinkedHashMap<>();

    public static ObjModel load(String objPath) {
        ObjModel model = new ObjModel();
        ResourceLocation loc = ResourceLocation.fromNamespaceAndPath("javafinal", objPath);

        try (InputStream is = Minecraft.getInstance().getResourceManager().open(loc);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            List<Vertex>   vertices  = new ArrayList<>();
            List<TexCoord> texCoords = new ArrayList<>();
            List<Normal>   normals   = new ArrayList<>();
            String currentMaterial = "default";

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] p = line.split("\\s+");
                switch (p[0]) {
                    case "v"      -> vertices.add(new Vertex(
                            Float.parseFloat(p[1]),
                            Float.parseFloat(p[2]),
                            Float.parseFloat(p[3])));
                    case "vt"     -> texCoords.add(new TexCoord(
                            Float.parseFloat(p[1]),
                            1.0f - Float.parseFloat(p[2]))); // 3ds Max W component ignored
                    case "vn"     -> normals.add(new Normal(
                            Float.parseFloat(p[1]),
                            Float.parseFloat(p[2]),
                            Float.parseFloat(p[3])));
                    case "usemtl" -> {
                        currentMaterial = p[1];
                        model.groups.putIfAbsent(currentMaterial, new ArrayList<>());
                    }
                    case "f" -> {
                        int[][] idx = new int[3][3];
                        for (int i = 0; i < 3; i++) {
                            String[] parts = p[i + 1].split("/");
                            idx[i][0] = Integer.parseInt(parts[0]) - 1;
                            idx[i][1] = Integer.parseInt(parts[1]) - 1;
                            idx[i][2] = Integer.parseInt(parts[2]) - 1;
                        }
                        model.groups.computeIfAbsent(currentMaterial, k -> new ArrayList<>())
                                .add(new Triangle(
                                        vertices.get(idx[0][0]),   vertices.get(idx[1][0]),   vertices.get(idx[2][0]),
                                        texCoords.get(idx[0][1]),  texCoords.get(idx[1][1]),  texCoords.get(idx[2][1]),
                                        normals.get(idx[0][2]),    normals.get(idx[1][2]),    normals.get(idx[2][2])));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load OBJ: " + loc, e);
        }

        return model;
    }
}