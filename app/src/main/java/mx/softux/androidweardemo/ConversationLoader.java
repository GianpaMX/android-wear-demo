package mx.softux.androidweardemo;

import android.content.Context;
import android.support.v4.content.Loader;

import java.util.List;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationLoader extends Loader<List<ConversationMessage>> {
    private List<ConversationMessage> messages;

    public ConversationLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        if (messages != null) {
            deliverResult(messages);
        }
    }

    @Override
    protected void onReset() {
        messages = null;
    }
}
