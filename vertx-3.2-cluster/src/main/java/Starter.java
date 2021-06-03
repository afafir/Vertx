import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;

public class Starter {
    public static void main(String[] args){
        JsonObject zkConfig = new JsonObject();
        zkConfig.put("zookeeperHosts", "127.0.0.1");
        zkConfig.put("rootPath", "data");
        zkConfig.put("retry", new JsonObject()
                .put("initialSleepTime", 3000)
                .put("maxTimes", 3));

        ClusterManager mgr = new ZookeeperClusterManager(zkConfig);
        VertxOptions options = new VertxOptions().setClusterManager(mgr);
        Vertx.clusteredVertx(options, cluster -> {
            if (cluster.succeeded()) {
                cluster.result().deployVerticle(new ClusteredConsumer(), res -> {
                    if(res.succeeded()){
                        System.out.println("Deployment succesfull: " + res.result());
                    } else {
                        System.out.println("Deployment failed!");
                    }
                });
            } else {
                System.out.println("Cluster up failed: " + cluster.cause());
            }
        });
    }
}
