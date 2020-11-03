import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.UnsupportedEncodingException;

import java.util.concurrent.atomic.AtomicReference;

public class Main extends NullPointerException {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        Vertx vertx = Vertx.vertx(
                new VertxOptions().setWorkerPoolSize(40)
        );

        HttpClientOptions httpClientOptions = new HttpClientOptions()
                .setProtocolVersion(HttpVersion.HTTP_2)
                .setSsl(true)
                .setUseAlpn(true)
                .setTrustAll(true);

        HttpClient httpClient = vertx.createHttpClient(httpClientOptions);

        Integer user_id = 184064789;
        String[] name = user_name(user_id, httpClient);

        System.out.println(name[0] + " " + name[1]);

        //find(user_id, star_id, httpClient, 0);

        // 135336811 Воля
        // 183150940 Я
        // 326285231 Дима
        // 173725684 Асмус
        // 173410808 Катя
        // 184064789 Лиза
        // 15301779 close
    }

    private static String[] user_name(Integer user_id, HttpClient httpClient) throws InterruptedException {
        AtomicReference<JsonObject> json = new AtomicReference<>(new JsonObject());
        httpClient.requestAbs(HttpMethod.GET, "https://api.vk.com/method/users.get?user_id=" + user_id + "&name_case=gen&v=5.52&access_token=cb6c579db3bdc7350af5f2b2435cd3098434d7cd8f2588d3be25f6d7cac46802325c1b38cccf7e66fd0fa",
                result -> result.result().bodyHandler(body -> {
                    System.out.println(body.toString());
                    json.set(body.toJsonObject());
                })
        ).end();

        Thread.sleep(60_00);
        JsonArray jsonName = (JsonArray) json.get().getValue("response");
        String[] name = new String[2];
        try {
            name[0] = (String) jsonName.getJsonObject(0).getValue("first_name");
            name[1] = (String) jsonName.getJsonObject(0).getValue("last_name");
        } catch (Exception e) {
            name = null;
        }
        return name;
    }

    private static Integer find(Integer user_id, Integer star_id, HttpClient httpClient, Integer count) throws InterruptedException {

        JsonArray jsonArray = getFriend(httpClient, user_id);
        if (jsonArray == null) {
            return null;
        }

        String all_friends_user = "";

        int i = 0;
        if (is_closed(httpClient, (Integer) jsonArray.getList().get(i))) {
        } else {
            all_friends_user += jsonArray.getList().get(i).toString();
        }

        do {
            i++;
            if (is_closed(httpClient, (Integer) jsonArray.getList().get(i))) {
            } else {
                all_friends_user += "," + jsonArray.getList().get(i).toString();
            }
        } while ( i < jsonArray.getList().size() - 1);
        System.out.println(all_friends_user);

        httpClient.requestAbs(HttpMethod.GET, "https://api.vk.com/method/friends.getMutual?source_uid=" + star_id + "&target_uids=" + all_friends_user + "&v=5.124&access_token=cb6c579db3bdc7350af5f2b2435cd3098434d7cd8f2588d3be25f6d7cac46802325c1b38cccf7e66fd0fa",
            result -> {
                    result.result().bodyHandler(body -> {
                        System.out.println(body.toString());
                    });
                }).end();
        return 0;
    }

    private static boolean is_closed(HttpClient httpClient, Integer user_id) throws InterruptedException {
        boolean is_close;
        AtomicReference<JsonObject> json = new AtomicReference<>(new JsonObject());
        httpClient.requestAbs(HttpMethod.GET, "https://api.vk.com/method/users.get?user_id=" + user_id + "&v=5.124&access_token=cb6c579db3bdc7350af5f2b2435cd3098434d7cd8f2588d3be25f6d7cac46802325c1b38cccf7e66fd0fa",
                result -> {
                    result.result().bodyHandler(body -> {
                        json.set(body.toJsonObject());
                    });
                }).end();
        Thread.sleep(400);
        JsonArray jsonName = (JsonArray) json.get().getValue("response");
        try {
            is_close = (boolean)jsonName.getJsonObject(0).getValue("is_closed");
        } catch (Exception ex) {
            is_close = true;
        }

        return is_close;
    }

    private static JsonArray getFriend(HttpClient httpClient, Integer user_id) throws InterruptedException {
        AtomicReference<JsonObject> json = new AtomicReference<>(new JsonObject());
        httpClient.requestAbs(HttpMethod.GET, "https://api.vk.com/method/friends.get?user_id=" + user_id + "&v=5.52&access_token=e794b816e794b816e794b816d4e7e449e5ee794e794b816b904a9717ecf54c9bbcff785",
                result -> {
                    result.result().bodyHandler(body -> {
                        json.set(body.toJsonObject());
                    });
                }).end();
        Thread.sleep(60_00);
        JsonObject jsonName = (JsonObject) json.get().getValue("response");
        JsonArray jsonArray;
        try {
            jsonArray = (JsonArray) jsonName.getValue("items");
        } catch (Exception ex) {
            jsonArray = null;
        }
        return jsonArray;
    }

    static String findUserId(HttpClient httpClient, String name) throws InterruptedException {
        AtomicReference<JsonObject> json = new AtomicReference<>(new JsonObject());
        httpClient.requestAbs(HttpMethod.GET, "https://api.vk.com/method/utils.resolveScreenName?screen_name=" + name + "&v=5.52&access_token=e794b816e794b816e794b816d4e7e449e5ee794e794b816b904a9717ecf54c9bbcff785",
                result -> {
                    if (result.succeeded()) {
                        result.result().bodyHandler(body -> {
                            json.set(body.toJsonObject());
                        });
                    }
                }).end();

        Thread.sleep(70_00);
        JsonObject jsonName = (JsonObject) json.get().getValue("response");
        String user_id = String.valueOf(jsonName.getValue("object_id"));
        System.out.println(user_id);
        return user_id;
    }
}
