package com.suhongjin.karaokecreator.permissions;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.common.base.Preconditions;
import com.suhongjin.karaokecreator.R;

/**
 * Simple activity that is shown when a user chooses "never ask again" when asking for permissions.
 */
public final class NoPermissionsActivity extends AppCompatActivity {

  private boolean shouldShowRequestRationale;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle args = Preconditions.checkNotNull(getIntent().getExtras());
    shouldShowRequestRationale = args.getBoolean(PermissionHandler.SHOULD_SHOW_RATIONALE_EXTRA);

    setContentView(R.layout.activity_no_permissions);

    Button button = findViewById(R.id.settings_button);
    button.setOnClickListener(
        view -> {
          if (shouldShowRequestRationale) {
            retryPermissions();
          } else {
            openSettings();
          }
        });
  }

  private void openSettings() {
    Uri packageUri =
        new Uri.Builder()
            .scheme("package")
            .opaquePart(getPackageName())
            .build();
    Intent intent =
        new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(packageUri);
    startActivity(intent);
  }

  private void retryPermissions() {
    Intent returnIntent = new Intent();
    setResult(Activity.RESULT_OK, returnIntent);
    finish();
  }
}
