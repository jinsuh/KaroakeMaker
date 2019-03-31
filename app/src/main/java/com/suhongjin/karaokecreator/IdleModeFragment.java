package com.suhongjin.karaokecreator;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.Observer;

/** When a user is not in the middle of a recording session. */
public final class IdleModeFragment extends Fragment {

  private Button recordButton;
  private SessionState sessionState;

  private final Observer recordStateObserver = (observable, o) -> updateText();

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_idle, container, false);

    recordButton = rootView.findViewById(R.id.record_button);
    recordButton.setOnClickListener(view -> handleRecordButtonPress());

    sessionState = SessionState.getSessionState();

    sessionState.addObserver(recordStateObserver);
    return rootView;
  }

  @Override
  public void onStop() {
    super.onStop();
    sessionState.deleteObserver(recordStateObserver);
  }

  private void handleRecordButtonPress() {
    sessionState.setRecording(!sessionState.isRecording());
  }

  private void updateText() {
    recordButton.setText(
        sessionState.isRecording() ? R.string.stop_recording_button : R.string.record_button);
  }
}
