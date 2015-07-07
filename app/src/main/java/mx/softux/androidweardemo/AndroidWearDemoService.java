package mx.softux.androidweardemo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by juan on 7/7/15.
 */
public interface AndroidWearDemoService {
    @GET("/message")
    List<ConversationMessage> getMessages();

    @POST("/message")
    void postMessage(@Body ConversationMessage message, Callback<ConversationMessage> conversationMessageCallback);
}
