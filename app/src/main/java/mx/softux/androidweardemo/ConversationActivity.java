package mx.softux.androidweardemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by juan on 7/7/15.
 */
public class ConversationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            ConversationFragment conversationFragment = ConversationFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.conversation_fragment, conversationFragment).commit();

            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }
}
