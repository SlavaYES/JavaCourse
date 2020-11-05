import com.google.gson.JsonObject;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.messages.*;
import com.vk.api.sdk.queries.messages.MessagesGetLongPollHistoryQuery;
import com.vk.api.sdk.objects.messages.Keyboard;
import com.vk.api.sdk.objects.messages.KeyboardButton;
import com.vk.api.sdk.objects.messages.KeyboardButtonAction;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public String[] genre = {"Драма", "Мелодрамма", "Боевик", "Комедия", "Фантастика" };

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException {
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);

        Random random = new Random();

        Keyboard keyboard = new Keyboard();
        List<List<KeyboardButton>> allKey = new ArrayList<>();
        List<KeyboardButton> line1 = new ArrayList<>();

        line1.add(new KeyboardButton()
                .setAction(new KeyboardButtonAction()
                        .setLabel("Привет")
                        .setType(KeyboardButtonActionType.TEXT))
                .setColor(KeyboardButtonColor.POSITIVE));

        allKey.add(line1);

        keyboard.setButtons(allKey);

        GroupActor actor = new GroupActor(199983533, "a5938e0ddc975175aab32747f4e2787b9c8863118585df92ebf0e502e353e4f5ec9b9181322b0862820");
        Integer ts = vk.messages().getLongPollServer(actor).execute().getTs(); // time computer message

        while (true) {
            MessagesGetLongPollHistoryQuery historyQuery = vk.messages().getLongPollHistory(actor).ts(ts); // for time equals ts

            List<Message> messages = historyQuery.execute().getMessages().getItems(); // pull all message in list messages
            if (!messages.isEmpty()) { // if message not empty
                messages.forEach(message -> { // move of messages
                    if (!message.isOut()) {
                        System.out.println(message.toString());
                        String text = message.getText();
                        PrintStream printStream = new PrintStream(System.out, true, StandardCharsets.UTF_8);
                        printStream.println(text);
                        String[] splitText = text.split(" ");
                        for (String iter : splitText) {
                            System.out.println(iter);
                        }
                        String response = "";
                        if (splitText.length > 0
                                && (splitText[0].equals("Драма")
                                || splitText[0].equals("Мелодрама")
                                || splitText[0].equals("Боевик")
                                || splitText[0].equals("Комедия")
                                || splitText[0].equals("Фантастика"))) {

                            switch (splitText[0]) {
                                case "Драма":
                                    response = "https://lord-filmzz1.lordfilm.link/films/f/p.cat=22/";
                                    break;
                                case "Мелодрама":
                                    response = "https://lord-filmzz1.lordfilm.link/films/f/p.cat=27/";
                                    break;
                                case "Боевик":
                                    response = "https://lord-filmzz1.lordfilm.link/films/f/p.cat=14/";
                                    break;
                                case "Комедия":
                                    response = "https://lord-filmzz1.lordfilm.link/films/f/p.cat=24/";
                                    break;
                                case "Фантастика":
                                    response = "https://lord-filmzz1.lordfilm.link/films/f/p.cat=40/";
                                    break;
                                case "Ужасы":
                                    response = "https://lord-filmzz1.lordfilm.link/films/f/p.cat=39/";
                                    break;
                                default:
                                    break;
                            }
                            if ((splitText.length == 2)
                                    && (Integer.parseInt(splitText[1]) >= 0 && Integer.parseInt(splitText[1]) <= 10)) {
                                response += "r.kp_raiting=" + (Integer.parseInt(splitText[1]) - 1) + ";" + splitText[1] + "/sort=date/order=desc/";
                            } else if ((splitText.length == 2)
                                    && (Integer.parseInt(splitText[1]) >= 1915 && Integer.parseInt(splitText[1]) <= 2019)) {
                                response += "r.god_vyhoda=" + (Integer.parseInt(splitText[1]) - 1) + ";" + splitText[1] + "/r.kp_raiting=0;10/sort=date/order=desc/";
                            }
                            if (splitText.length == 3
                                    && (Integer.parseInt(splitText[1]) >= 1915 && Integer.parseInt(splitText[1]) <= 2019)
                                    && (Integer.parseInt(splitText[2]) >= 1915 && Integer.parseInt(splitText[2]) <= 2019)) {
                                response += "r.god_vyhoda=" + splitText[1] + ";" + splitText[2] + "/r.kp_raiting=0;10/sort=date/order=desc/";
                            } else if (splitText.length == 3
                                    && (Integer.parseInt(splitText[1]) >= 0 && Integer.parseInt(splitText[1]) <= 10)
                                    && (Integer.parseInt(splitText[2]) >= 1915 && Integer.parseInt(splitText[2]) <= 2019)) {
                                response += "r.god_vyhoda=" + (Integer.parseInt(splitText[2]) - 1) + ";" + splitText[2] + "/r.kp_raiting=" + (Integer.parseInt(splitText[1]) - 1) + ";" + splitText[1] + "/sort=date/order=desc/";
                            }
                            if (splitText.length == 4
                                    && (Integer.parseInt(splitText[1]) >= 0 && Integer.parseInt(splitText[1]) <= 10)
                                    && (Integer.parseInt(splitText[2]) >= 1915 && Integer.parseInt(splitText[2]) <= 2019)
                                    && (Integer.parseInt(splitText[3]) >= 1915 && Integer.parseInt(splitText[3]) <= 2019)) {
                                response += "r.god_vyhoda=" + splitText[3] + ";" + splitText[2] + "/r.kp_raiting=" + (Integer.parseInt(splitText[1]) - 1) + ";" + splitText[1] + "/sort=date/order=desc/";
                            }
                            if (splitText.length == 5
                                    && (Integer.parseInt(splitText[1]) >= 0 && Integer.parseInt(splitText[1]) <= 10)
                                    && (Integer.parseInt(splitText[2]) >= 0 && Integer.parseInt(splitText[2]) <= 10)
                                    && (Integer.parseInt(splitText[3]) >= 1915 && Integer.parseInt(splitText[3]) <= 2019)
                                    && (Integer.parseInt(splitText[4]) >= 1915 && Integer.parseInt(splitText[4]) <= 2019)) {
                                response += "r.god_vyhoda=" + splitText[3] + ";" + splitText[4] + "/r.kp_raiting=" + splitText[1] + ";" + splitText[2] + "/sort=date/order=desc/";
                            }

                            try {
                                vk.messages().send(actor).message("Проходи по ссылки").userId(message.getFromId())
                                        .attachment(response)
                                        .randomId(random.nextInt(10000))
                                        .execute();
                            } catch (ApiException | ClientException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                vk.messages().send(actor).message("Введи [Жанр] [Рейтинг(0;10)] [Год(1915;2019)]").userId(message.getFromId())
                                        .randomId(random.nextInt(10000))
                                        .keyboard(keyboard)
                                        .execute();
                            } catch (ApiException | ClientException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            ts = vk.messages().getLongPollServer(actor).execute().getTs();
            Thread.sleep(500);
        }

    }
}