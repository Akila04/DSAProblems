import java.util.*;

public class DisjointSets {
    public static void main(String args[]) {
        Integer numberOfNodes = 8;
        HashMap<Integer, Integer> edges = new HashMap<>();
        edges.put(1, 2);edges.put(2, 3);edges.put(3, 4);edges.put(4, 5);
        edges.put(3, 6);edges.put(2, 7);edges.put(1, 8);edges.put(8, 7);
        edges.put(6, 5);
        boolean loopExist = findLoopInGraph(numberOfNodes, edges);
        if(loopExist) {
            System.out.println("Loop Exist");
        } else {
            System.out.println("No Loops exist");
        }
    }

    private static boolean findLoopInGraph(Integer numberOfNodes, Map<Integer, Integer> edges) {
        System.out.println("LOOP");
        List<Integer> vertices = new ArrayList<>(Collections.nCopies(numberOfNodes+1, -1));
        for(Map.Entry<Integer, Integer> edge : edges.entrySet()) {
            Integer index1 = edge.getKey();
            Integer index2 = edge.getValue();
            Boolean loopExist = findLoopAndadjustVertices(index1, index2, vertices);
            if(loopExist) {
                System.out.println("Loop Exist :::: Edge : " + edge.getKey() + "  , " + edge.getValue());
            }
        }
        return false;
    }

    private static Boolean findLoopAndadjustVertices(Integer index1, Integer index2, List<Integer> vertices) {
        Integer index1Parent;
        Integer index2Parent;
        index1Parent=vertices.get(index1);
        while (index1Parent>0){
            index1Parent = vertices.get(index1Parent);
        }
        index2Parent=vertices.get(index2);
        while (index2Parent>0){
            index2Parent = vertices.get(index2Parent);
        }

        if(index1Parent == index2Parent && index1Parent!=-1) {
            return Boolean.TRUE;
        }


        if(index1Parent == -1 && index2Parent == -1) {
            vertices.set(index1, vertices.get(index1)+(-1));
            vertices.set(index2, index1);
        } else if(index1Parent == -1 && index2Parent >-1){
            vertices.set(index2, vertices.get(index2)+(-1) );
            vertices.set(index1, index2);
        } else if(index2Parent == -1 && index1Parent >-1){
            vertices.set(index1, vertices.get(index1)+(-1));
            vertices.set(index2, index1);
        } else {
            vertices.set(index1, vertices.get(index2)+(-1));
            vertices.set(index2, index1);
        }
        return Boolean.FALSE;
    }
}
