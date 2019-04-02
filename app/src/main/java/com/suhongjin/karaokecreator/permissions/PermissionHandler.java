package com.suhongjin.karaokecreator.permissions;

import android.content.Intent;
import androidx.annotation.NonNull;

/** Handles callbacks for permission and activity results specifically for permissions. */
public interface PermissionHandler {
  /** Used to pass {@code boolean} that determines if we should request rationale. */
  String SHOULD_SHOW_RATIONALE_EXTRA = "should_show_rationale_extra";
  /** Request code for permission request. */
  int PERMISSION_REQUEST_CODE = 200;
  /** Request code for activity result. */
  int NO_PERMISSIONS_REQUEST_CODE = 1;

  /** Handles permission result. */
  void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
  /**
   * Handles activity result from
   * {@link com.suhongjin.karaokecreator.permissions.NoPermissionsActivity}.
   */
  void onActivityResult(int requestCode, int resultCode, Intent data);
}
