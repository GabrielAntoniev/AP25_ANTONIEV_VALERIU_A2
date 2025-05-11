package org.example;


import org.graph4j.*;

import java.util.stream.Collectors;
import java.util.*;
import java.util.Random;
import org.graph4j.Graph;
import org.graph4j.shortestpath.*;
import org.graph4j.util.Path;

class Main{
    public static void main(String[] args){

        compulsory();
        homework();
        bonus();
    }

    public static void compulsory(){

        System.out.println("*********COMPULSORY***************");
        Random random = new Random();
        int nrLocations = 10;
        List<Location> locationList = new ArrayList<>();
        for(int i = 0; i < nrLocations; i++){
            locationList.add(new Location().generateRandomLocation(random));
            System.out.println(locationList.get(i));
        }

        System.out.println("*******************");

        Set<Location> locationTreeSet = locationList.stream()
                .filter(loc -> loc.isFriendly())
                .collect(Collectors.toCollection(TreeSet::new));

        List<Location> locationLinkedList = locationList.stream()
                .filter(loc -> loc.isEnemy())
                .sorted(Comparator.comparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        for(var p : locationTreeSet){
            System.out.println(p);
        }

        for(var p : locationLinkedList){
            System.out.println(p);
        }
    }

    public static void homework(){

        System.out.println("*********HOMEWORK***************");

        Random random = new Random();

        int nrLocations = 2+ random.nextInt(100);
        int nrEdges = 1+ random.nextInt((nrLocations*(nrLocations-1))/2);

        System.out.println("Nr. nodes : " + nrLocations + "; Nr. edges : " + nrEdges);

        //Digraph digraph = new RandomGnpGraphGenerator(nrLocations,0.5).createDigraph();

        List<Location> locationList = new ArrayList<>();
        List<Link> edgeList = new ArrayList<>();
        System.out.println("Nodes: ");
        for(int i = 0; i < nrLocations; i++){
            locationList.add(new Location().generateRandomLocation(random));
            System.out.println(i + ": "+ locationList.get(i));
        }

        for(int i = 0; i < nrEdges; i++){
            //boolean randomBool = random.nextBoolean();
            int i1,i2;
            boolean exists = false;
            do{
                exists = false;
                i1 = random.nextInt(nrLocations);
                Location loc1 = locationList.get(i1);
                do{i2 = random.nextInt(nrLocations);}while(i2 == i1);
                Location loc2 = locationList.get(i2);
                for(var v : edgeList){
                    if ((v.getLocation1().equals(loc1) && v.getLocation2().equals(loc2)) ) {
                        exists = true;
                        break;
                    }
                }
            }while(exists);

            edgeList.add(new Link(  locationList.get(i1), locationList.get(i2), true, Math.floor(random.nextDouble(10) *100 )/100, random.nextDouble()  ));
        }

        edgeList.sort(Comparator.comparing(Link::getLocation1).thenComparing(Link::getLocation2));
        System.out.println("Edges: ");
        for(int i = 0; i < nrLocations; i++){
            System.out.print("Node " + i + " -> ");
            int pos1=-1, pos2=-1;
            for(int j = 0; j < nrEdges; j++) if(edgeList.get(j).getLocation1().equals(locationList.get(i))){pos1 = j; break;}
            for(int j = nrEdges-1; j >= 0; j--) if(edgeList.get(j).getLocation1().equals(locationList.get(i))){pos2 = j; break;}
            if(pos1==-1 || pos2 == -1) {System.out.print(" - \n"); continue;}
            for(int j = pos1; j < pos2; j++) System.out.print(locationList.indexOf(edgeList.get(j).getLocation2()) + "(cost = " + (edgeList.get(j).getTimeCost()) + "), ");
                                             System.out.print(locationList.indexOf(edgeList.get(pos2).getLocation2()) + "(cost = " + (edgeList.get(pos2).getTimeCost()) + ")");
            System.out.println();
        }

        Graph graph = GraphBuilder.vertices()
                .estimatedNumVertices(nrLocations)
                .estimatedNumEdges(nrEdges)
                .buildDigraph();

        for (int i = 0; i < nrLocations; i++) {
            graph.addLabeledVertex(i, locationList.get(i));
        }

        for(int i = 0; i < nrEdges; i++){
            graph.addEdge(graph.findVertex(edgeList.get(i).getLocation1()), graph.findVertex(edgeList.get(i).getLocation2()), edgeList.get(i).getTimeCost());
        }

        int start = random.nextInt(nrLocations);
        System.out.println("Start node : " + start);
        var alg = new DijkstraShortestPathHeap(graph, start);
        for(Location l : locationList){
            if(l.equals(locationList.get(start))) continue;
            if((graph.findVertex(l))!=-1){
                Path path=alg.computePath(graph.findVertex(l));//////////////////////////////////////
                System.out.println(path + " cost = " + path.computeEdgesWeight());}
            }
        System.out.println();

        System.out.println("Dijkstra for each location, grouped by type:\n***********************");
        Map<Type, List<Location>> locationsGroupedByType = locationList.stream().collect(Collectors.groupingBy(Location::getType));
        for(var k : Type.values()){

            System.out.println(k + " : ");
            if(locationsGroupedByType.get(k)==null){System.out.println("***********************"); continue;}
            for(int i =0; i < locationsGroupedByType.get(k).size(); i++){
                start = locationList.indexOf(locationsGroupedByType.get(k).get(i));
                System.out.println(k + " start = " + start + ": ");
                alg = null;
                alg = new DijkstraShortestPathHeap(graph, start);
                for(Location l : locationList){
                    if(l.equals(locationList.get(start))) continue;
                    if((graph.findVertex(l))!=-1) {
                        Path path = alg.computePath(graph.findVertex(l));///////////////////////////////
                        System.out.println(path + " cost = " + path.computeEdgesWeight());
                    }
                }
            }
            System.out.println("***********************");
        }
    }

    public static void bonus(){
        System.out.println("\n*********BONUS***************");


        Random random = new Random();

        int nrLocations = 2+ random.nextInt(20);
        int nrEdges = 1+ random.nextInt((nrLocations*(nrLocations-1))/2);

        System.out.println("Nr. nodes : " + nrLocations + "; Nr. edges : " + nrEdges);

        //Digraph digraph = new RandomGnpGraphGenerator(nrLocations,0.5).createDigraph();

        List<Location> locationList = new ArrayList<>();
        List<Link> edgeList = new ArrayList<>();
        System.out.println("Nodes: ");
        for(int i = 0; i < nrLocations; i++){
            locationList.add(new Location().generateRandomLocation(random));
            System.out.println(i + ": "+ locationList.get(i));
        }

        for(int i = 0; i < nrEdges; i++){
            //boolean randomBool = random.nextBoolean();
            int i1,i2;
            boolean exists = false;
            do{
                exists = false;
                i1 = random.nextInt(nrLocations);
                Location loc1 = locationList.get(i1);
                do{i2 = random.nextInt(nrLocations);}while(i2 == i1);
                Location loc2 = locationList.get(i2);
                for(var v : edgeList){
                    if ((v.getLocation1().equals(loc1) && v.getLocation2().equals(loc2)) ) {
                        exists = true;
                        break;
                    }
                }
            }while(exists);

            edgeList.add(new Link(  locationList.get(i1), locationList.get(i2), true, Math.floor(random.nextDouble(10) *100 )/100, Math.floor(random.nextDouble()*100)/100  ));
        }

        edgeList.sort(Comparator.comparing(Link::getLocation1).thenComparing(Link::getLocation2));
        System.out.println("Edges: ");
        for(int i = 0; i < nrLocations; i++){
            System.out.print("Node " + i + " -> ");
            int pos1=-1, pos2=-1;
            for(int j = 0; j < nrEdges; j++) if(edgeList.get(j).getLocation1().equals(locationList.get(i))){pos1 = j; break;}
            for(int j = nrEdges-1; j >= 0; j--) if(edgeList.get(j).getLocation1().equals(locationList.get(i))){pos2 = j; break;}
            if(pos1==-1 || pos2 == -1) {System.out.print(" - \n"); continue;}
            for(int j = pos1; j < pos2; j++) System.out.print(locationList.indexOf(edgeList.get(j).getLocation2()) + "(total safety = " + (edgeList.get(j).getSafeReachProbability()) + "), ");
            System.out.print(locationList.indexOf(edgeList.get(pos2).getLocation2()) + "(total safety = " + (edgeList.get(pos2).getSafeReachProbability()) + ")");
            System.out.println();
        }

        Graph graph = GraphBuilder.vertices()
                .estimatedNumVertices(nrLocations)
                .estimatedNumEdges(nrEdges)
                .buildDigraph();

        for (int i = 0; i < nrLocations; i++) {
            graph.addLabeledVertex(i, locationList.get(i));
        }

        for(int i = 0; i < nrEdges; i++)
            graph.addEdge(graph.findVertex(edgeList.get(i).getLocation1()), graph.findVertex(edgeList.get(i).getLocation2()), edgeList.get(i).getSafeReachProbability());

        System.out.println("cost matrix: ");
        var alg = new FloydWarshallShortestPath(graph);
        double[][] rez =alg.getPathWeights();
        for(int i = 0; i < rez.length;i++){
            for(int j = 0; j < rez[i].length; j++){
                System.out.print(rez[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("group by type: ");
        Path path;
        Map<Path, StringBuilder> nrLocationsOfEachTypeForEachRoute = new HashMap<>();
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < nrLocations; i++){
            for(int j = 0; j < nrLocations; j++){
                if(i==j)continue;;
                path = alg.findPath(i,j);
                if(path.isEmpty())continue;
                System.out.print(path + " nr types: ");
                int[] v = path.vertices();

                s.delete(0, s.length());
                int nf=0,nn=0,ne=0;
                for(int k =0; k < v.length; k++){
                    if(locationList.get(v[k]).getType().equals(Type.FRIENDLY))nf++;
                    else if(locationList.get(v[k]).getType().equals(Type.NEUTRAL))nn++;
                    else ne++;
                }
                s.append("friendly: ").append(nf).append(" neutral: ").append(nn).append(" enemy: ").append(ne);
                System.out.println(s);
                nrLocationsOfEachTypeForEachRoute.put(path,s);
                //List<Location> loc = Arrays.stream(v).mapToObj(locationList::get).collect(Collectors.toCollection(ArrayList::new));
                //Map<Path, StringBuilder> toAdd = Arrays.stream(v).map(node -> ())
                //Map<Type, List<Location>> locationsGroupedByType = locationList.stream().collect(Collectors.groupingBy(Location::getType));
            }
        }

        //for(var k : )
        //System.out.println(Arrays.deepToString(rez));
    }
}