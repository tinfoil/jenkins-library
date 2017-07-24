// Adapted from http://blog.knapik.me/knapsack-with-jenkins-pipeline/
def split(Integer ci_node_total, Closure cl) {
  def nodes = [:]

  for(int i = 0; i < ci_node_total; i++) {
    def index = i; // i gets mutated
    nodes["ci_node_${i}"] = {
      withEnv(["CI_NODE_INDEX=$index", "CI_NODE_TOTAL=$ci_node_total"]) {
        cl()
      }
    }
  }

  return nodes
}
