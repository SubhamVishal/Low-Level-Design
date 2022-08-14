package Util;

import java.util.*;

public class HydrateNodes {
    public static int hydratedTheNode(int[] parent, int[] water, int oh, int uh) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            map.putIfAbsent(parent[i], new ArrayList<>());
            map.get(parent[i]).add(i);
        }
        int[][] waterLevel = new int[parent.length][2];
        for(int[] arr : waterLevel) {
            Arrays.fill(arr, -1);
        }
        dfs(0, waterLevel, water, map);
        int ifNoWater = waterLevel[0][1] * uh;
        int globalMin = Integer.MAX_VALUE;
        for (int i = 0; i < parent.length; i++) {
            globalMin = Math.min(globalMin, (ifNoWater - ((waterLevel[i][1] * uh) - waterLevel[i][0] * oh));
        }
    }

    private static int[] dfs(int node, int[][] waterLevel, int[] water, Map<Integer, List<Integer>> map) {
        int[] total = new int[2];
        total[0] = 0; // oh
        total[1] = 0; // uh
        if(water[node] > 0) {
            total[0] += 1;
        } else if(water[node] < 0) {
            total[1] += 1;
        }
        if(waterLevel[node][0] != -1) {
            return waterLevel[node];
        }
        if(map.get(node) != null) {
            for(int i : map.get(node)) {
                int[] temp = dfs(i, waterLevel, water, map);
                total[0] += temp[0];
                total[1] += temp[1];
            }
        }
        waterLevel[node] = total;
        return total;
    }
}
