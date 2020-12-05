
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import static io.vertx.core.http.HttpMethod.GET;

public class Main  extends NullPointerException {
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx(
                new VertxOptions().setWorkerPoolSize(40)
        );
        HttpClientOptions httpClientOptions = new HttpClientOptions()
                .setProtocolVersion(HttpVersion.HTTP_2)
                .setSsl(true)
                .setUseAlpn(true)
                .setTrustAll(true);
        HttpClient httpClient = vertx.createHttpClient(httpClientOptions);
        httpClient.requestAbs(GET, "https://ru.numberempire.com/primenumbers.php?number=12872033275667980547846774154183404719546181930291549053787830294920887719417318687025612357148072536084243369983623239278613957&action=check&_p1=2326",
                result -> {
                    result.result().bodyHandler(body -> {
                        System.out.println(body.toString());
                    });
                })
                .end();
    }
}
