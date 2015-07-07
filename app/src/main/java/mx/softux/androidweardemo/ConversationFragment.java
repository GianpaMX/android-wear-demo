package mx.softux.androidweardemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationFragment extends ListFragment implements LoaderManager.LoaderCallbacks<List<ConversationMessage>> {
    ConversationAdapter conversationAdapter;

    public static ConversationFragment newInstance() {
        ConversationFragment fragment = new ConversationFragment();
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        conversationAdapter = new ConversationAdapter(getActivity());
        setListAdapter(conversationAdapter);

        getLoaderManager().initLoader(0, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_conversation, null);
        return view;
    }

    @Override
    public Loader<List<ConversationMessage>> onCreateLoader(int id, Bundle args) {
        return new ConversationLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<ConversationMessage>> loader, List<ConversationMessage> messages) {
        conversationAdapter.addMessages(messages);
    }

    @Override
    public void onLoaderReset(Loader<List<ConversationMessage>> loader) {
        conversationAdapter.removeMessages();
    }
}
