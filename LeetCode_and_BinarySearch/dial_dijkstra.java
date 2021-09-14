import java.util.*;

// Dial Dijkstra algorithm, can be used in some cp questions.
// it's itself not a cp question
class DialDijkstra {
    static class Node implements Comparator<Node> {
        public int cost, node;
    
        public Node() {}
        public Node(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
    
        @Override public int compare(Node node1, Node node2){
            if (node1.cost < node2.cost)
                return -1;
    
            if (node1.cost > node2.cost)
                return 1;
    
            return 0;
        }
    }

	private Set<Integer> settled;
	public int dist[];
    private List<Integer> buckets[];

    private int n;
	List<List<Node> > adj;

	public DialDijkstra(int n){
		this.n = n;
		settled = new HashSet<Integer>();
		dist = new int[n];
        
        // buckets will be from 0 to n and each will contain some vertices which have that indexed weight
        buckets = new List[n+1];

        for(int i=0; i<=n; i++){
            buckets[i] = new ArrayList<>();
        }
	}

	// To process all the neighbours of the passed node
	private void e_Neighbours(int u){
		int newDistance = -1, edgeDistance = -1, i=0;

		// All the neighbors of u
		for (i = 0; i < adj.get(u).size(); i++) {
			Node v = adj.get(u).get(i);

			// If current node hasn't already been processed
			if (!settled.contains(v.node)) {
				edgeDistance = v.cost;
				newDistance = dist[u] + edgeDistance;

				// If new distance is cheaper in cost
				if (newDistance < dist[v.node]){
					dist[v.node] = newDistance;
                    
                    // remove v from its current bucket and add it to the updated bucket
                    for(int j=0; j<=n; j++){
                        if(buckets[j].contains(v.node)){
                            buckets[j].remove(Integer.getInteger(""+v.node));
                            break;
                        }
                    }

                    buckets[dist[v.node]].add(v.node);
                }
			}
		}
	}

    // Dial Dijkstra's Algorithm
	public void dialDijkstra(int source, List<List<Node>> adj){
		this.adj = adj;

		for (int i = 0; i < n; i++)
			dist[i] = Integer.MAX_VALUE;

		// Add source node to the buckets
        buckets[0].add(0);

		// Distance to the source is 0
		dist[source] = 0;

		while (settled.size() != n) {

            // find the first non-empty bucket
            int i=0;
            for(i=0; i<=n;i++){
                if(buckets[i].size() != 0){
                    break;
                }
            }

            int u = buckets[i].get(0);
            if(settled.contains(u)){
                continue;
            }

            // get the vertex number, mark it as settled
            settled.add(u);

            // get all its neighbours, and update their distances in dist and in buckets
            e_Neighbours(u);
            
            // remove the current vertex from the bucket
            buckets[i].remove(0); // removes the first element from buckets[i]
		}
	}
}
