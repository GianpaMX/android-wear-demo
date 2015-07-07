package mx.softux.androidweardemo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationLoader extends AsyncTaskLoader<List<ConversationMessage>> {
    private static final String TAG = ConversationLoader.class.getSimpleName();

    private List<ConversationMessage> messages;
    private final AndroidWearDemoService service;

    public ConversationLoader(Context context) {
        super(context);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://192.168.56.1:1337")
                .setConverter(new GsonConverter(gson))
                .build();

        service = restAdapter.create(AndroidWearDemoService.class);
    }

    @Override
    public List<ConversationMessage> loadInBackground() {
        Log.i(TAG, "loadInBackground");
        return service.getMessages();
    }

    @Override
    public void deliverResult(List<ConversationMessage> newMessages) {
        Log.i(TAG, "deliverResult.newMessages = " + newMessages.size());

        if(isReset()) {
            messages = null;
            return;
        }

        List<ConversationMessage> oldMessages = messages;
        messages = newMessages;

        if(isStarted()) {
            super.deliverResult(newMessages);
        }

        oldMessages = null;
    }

    @Override
    protected void onStartLoading() {
        if (messages != null) {
            deliverResult(messages);
        }

        if (takeContentChanged() || messages == null) {
            forceLoad();
        }
    }

    @Override
    protected void onReset() {
        messages = null;
    }
}
