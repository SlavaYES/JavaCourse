import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        HandlerFunction<ServerResponse> helloJava = request ->
                ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(new User(1, "900", "Ivan")));

        RouterFunction router = RouterFunctions
                .route(GET("/get_all"), helloJava);

        HttpHandler handler = RouterFunctions.toHttpHandler(router);
        HttpServer
                .create()
                .port(8080)
                .handle(new ReactorHttpHandlerAdapter(handler))
                .bindNow();

        Thread.currentThread().join();


    }
}
