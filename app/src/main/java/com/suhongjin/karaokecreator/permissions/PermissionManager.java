package com.suhongjin.karaokecreator.permissions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.common.base.Preconditions;
import com.suhongjin.karaokecreator.base.BaseActivity;

/** Manages logic that deals with permissions. */
public final class PermissionManager implements LifecycleObserver, PermissionHandler {

  private static final String RECORD_AUDIO_PERMISSION = Manifest.permission.RECORD_AUDIO;

  private final BaseActivity activity;
  private final Lifecycle lifecycle;

  public PermissionManager(BaseActivity activity, Lifecycle lifecycle) {
    this.activity = activity;
    this.lifecycle = lifecycle;
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  public void onCreate() {
    activity.setPermissionHandler(this);
    if (ContextCompat.checkSelfPermission(
        activity, RECORD_AUDIO_PERMISSION) == PackageManager.PERMISSION_DENIED) {
      requestRecordAudioPermission();
    }
  }

  private void requestRecordAudioPermission() {
    ActivityCompat.requestPermissions(
        activity, new String[] {RECORD_AUDIO_PERMISSION}, PERMISSION_REQUEST_CODE);
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    Preconditions.checkArgument(permissions.length > 0);
    Preconditions.checkArgument(grantResults.length == permissions.length);
    for (int i = 0; i < permissions.length; i++) {
      String permission = permissions[i];
      if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
        boolean shouldShowRationale = activity.shouldShowRequestPermissionRationale(permission);
        Intent intent = new Intent(activity, NoPermissionsActivity.class);
        intent.putExtra(SHOULD_SHOW_RATIONALE_EXTRA, shouldShowRationale);
        activity.startActivityForResult(intent, NO_PERMISSIONS_REQUEST_CODE);
      }
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data){
    requestRecordAudioPermission();
  }
}
