package com.suhongjin.karaokecreator;

import java.util.Observable;

/**
 * Created by suhongjin on 3/31/19.
 */

public final class SessionState extends Observable {

  private static SessionState INSTANCE = null;

  private boolean isRecording;

  public static SessionState getSessionState() {
    if (INSTANCE == null) {
      INSTANCE = new SessionState();
    }
    return INSTANCE;
  }

  private SessionState() {}

  public boolean isRecording() {
    return isRecording;
  }

  public void setRecording(boolean isRecording) {
    this.isRecording = isRecording;

    setChanged();
    this.notifyObservers();
  }
}
