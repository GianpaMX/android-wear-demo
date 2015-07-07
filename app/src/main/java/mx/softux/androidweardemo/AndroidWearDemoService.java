package mx.softux.androidweardemo;

import java.util.List;

import retrofit.http.GET;

/**
 * Created by juan on 7/7/15.
 */
public interface AndroidWearDemoService {
    @GET("/message")
    List<ConversationMessage> getMessages();
}
