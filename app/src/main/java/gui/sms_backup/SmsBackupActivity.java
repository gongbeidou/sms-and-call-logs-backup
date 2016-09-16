package gui.sms_backup;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import gui.BaseActivity;
import in.softc.app.R;
import utils.DialogUtility;
import utils.ViewUtility;

public class SmsBackupActivity extends BaseActivity implements View.OnClickListener {

    private ConversationsListAdapter conversationsListAdapter;
    private boolean isSelectionButtonClicked = false;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_sms_backup;
    }


    @Override
    public void onInitialize(Bundle bundle) {
        ViewUtility.setViewOnClickListener(this, this, R.id.bnt_backup, R.id.bnt_select);

        ArrayList<Sms> allSms = SmsBrowser.getAllSms(this, null);
        ArrayList<Conversation> conversations = SmsBrowser.getAllConversations(allSms);

        ListView listView = (ListView) findViewById(R.id.list_sms_conversation);
        conversationsListAdapter = new ConversationsListAdapter(this, conversations);
        listView.setAdapter(conversationsListAdapter);

        loadBannerAd();
    }


    @Override
    public void onClosed() {
        finish();
    }


    public void onBackClicked(View view) {
        onClosed();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.bnt_backup) {
            ArrayList<Conversation> selectedConversations = conversationsListAdapter.getSelectedConversations();
            backupConversation(selectedConversations);

        } else if (view.getId() == R.id.bnt_select) {

            if (!isSelectionButtonClicked) {
                conversationsListAdapter.selectAllConversation(true);
                isSelectionButtonClicked = true;

            } else {
                conversationsListAdapter.selectAllConversation(false);
                isSelectionButtonClicked = false;
            }
            conversationsListAdapter.notifyDataSetChanged();
        }
    }


    private void backupConversation(ArrayList<Conversation> selectedConversations) {
        DialogUtility.generateNewDialog(this, R.layout.dialog_create_file_name);
    }

}
