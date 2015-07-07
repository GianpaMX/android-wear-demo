package mx.softux.androidweardemo;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationLoader extends AsyncTaskLoader<List<ConversationMessage>> {
    private List<ConversationMessage> messages;
    private final AndroidWearDemoService service;

    public ConversationLoader(Context context) {
        super(context);

        service = Api.getService();
    }

    @Override
    public List<ConversationMessage> loadInBackground() {
        return service.getMessages();
    }

    @Override
    public void deliverResult(List<ConversationMessage> newMessages) {
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
