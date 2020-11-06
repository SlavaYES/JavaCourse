package ru.slava;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

@Service
public class UserService {

    private List<User> users;
    Vertx vertx = Vertx.vertx(
            new VertxOptions().setWorkerPoolSize(40)
    );

    HttpClientOptions  httpClientOptions = new HttpClientOptions()
            .setProtocolVersion(HttpVersion.HTTP_2)
            .setSsl(true)
            .setUseAlpn(true)
            .setTrustAll(true);

    HttpClient httpClient = vertx.createHttpClient(httpClientOptions);
    Integer user_id = 183150940;

    UserService() throws InterruptedException {
        users = new ArrayList<User>() {
            {
                AtomicReference<JsonObject> json = new AtomicReference<>(new JsonObject());
                httpClient.requestAbs(HttpMethod.GET,
                        "https://api.vk.com/method/friends.get?user_id=" + user_id + "&fields=domain&v=5.52&access_token=e794b816e794b816e794b816d4e7e449e5ee794e794b816b904a9717ecf54c9bbcff785",
                        result -> {
                            result.result().bodyHandler(body -> {
                                System.out.println(body.toString());
                                json.set(body.toJsonObject());
                            });
                        }).end();
                Thread.sleep(60_00);
                JsonObject jsonName = (JsonObject) json.get().getValue("response");
                JsonArray jsonItem = (JsonArray) jsonName.getValue("items");
//                jsonName.forEach(System.out::println);
//                jsonItem.forEach(System.out::println);

                for ( Object iter : jsonItem) {
                    JsonObject jsonObject = (JsonObject) iter;
                    System.out.println(jsonObject.getValue("first_name") +
                            " " + jsonObject.getValue("id") +
                            " " + jsonObject.getValue("domain"));
                    add( new User(jsonObject));
                }
            }
        };
    }

    Flux<User> getAll() {
        return Flux.fromIterable(users);
    }
}
