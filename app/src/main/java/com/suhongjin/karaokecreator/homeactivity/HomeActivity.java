package com.suhongjin.karaokecreator.homeactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.suhongjin.karaokecreator.base.BaseActivity;
import com.suhongjin.karaokecreator.R;
import com.suhongjin.karaokecreator.permissions.PermissionHandler;
import com.suhongjin.karaokecreator.permissions.PermissionManager;

/** Main activity. */
public class HomeActivity extends BaseActivity implements LifecycleOwner {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    LifecycleObserver permissionManager = new PermissionManager(this, getLifecycle());
    getLifecycle().addObserver(permissionManager);
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (permissionHandler == null) {
      return;
    }
    permissionHandler.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data){
    if (requestCode == PermissionHandler.NO_PERMISSIONS_REQUEST_CODE) {
      permissionHandler.onActivityResult(requestCode, resultCode, data);
    }
  }
}
