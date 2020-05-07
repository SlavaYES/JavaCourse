import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(
                new VertxOptions().setWorkerPoolSize(40)
        );

        // vk api 6 handle

    }
}


//https://api.vk.com/method/users.get?user_id=183150940&v=5.52&access_token=e794b816e794b816e794b816d4e7e449e5ee794e794b816b904a9717ecf54c9bbcff785