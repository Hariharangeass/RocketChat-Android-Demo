package demo.rocketchat.example.application;

import android.app.Application;

import com.rocketchat.common.network.ReconnectionStrategy;
import com.rocketchat.common.utils.Logger;
import com.rocketchat.core.RocketChatClient;
import com.squareup.picasso.Picasso;

/**
 * Created by sachin on 13/8/17.
 */

public class RocketChatApplication extends Application {

    RocketChatClient client;

    private static String serverurl = "wss://demo.rocket.chat/websocket";
    private static String baseUrl = "https://demo.rocket.chat/api/v1/";

    @Override
    public void onCreate() {
        super.onCreate();
        client = new RocketChatClient.Builder()
                .websocketUrl(serverurl)
                .restBaseUrl(baseUrl)
                .logger(logger)
                .build();

        client.setReconnectionStrategy(new ReconnectionStrategy(20, 3000));


        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }

    public RocketChatClient getRocketChatAPI() {
        return client;
    }

    Logger logger = new Logger() {
        @Override
        public void info(String format, Object... args) {
            System.out.println(format + " " +  args);
        }

        @Override
        public void warning(String format, Object... args) {
            System.out.println(format + " " +  args);
        }

        @Override
        public void debug(String format, Object... args) {
            System.out.println(format + " " +  args);
        }
    };
}
