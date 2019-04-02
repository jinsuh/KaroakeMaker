package com.suhongjin.karaokecreator.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.suhongjin.karaokecreator.permissions.PermissionHandler;

public abstract class BaseActivity extends AppCompatActivity {

  protected PermissionHandler permissionHandler;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    permissionHandler = null;
  }

  public void setPermissionHandler(PermissionHandler permissionHandler) {
    this.permissionHandler = permissionHandler;
  }
}
