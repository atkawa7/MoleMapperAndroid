package org.researchstack.molemapper.bridge;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import org.researchstack.foundation.components.utils.LogExt;
import org.researchstack.molemapper.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by bradleymcdermott on 2/8/16.
 */
public class Info
{
    public static final int BRIDGE_PHONE_INFO_LIMIT = 48;

    private List<FileInfo> files;
    private String         item;
    private String         surveyGuid;
    private String         surveyCreatedOn;
    private int schemaRevision = 1;
    // since this buildconfig is in skin, this won't be correct
    private String appVersion;
    private String phoneInfo;

    public Info(Context context, String item, int schemaRevision)
    {
        this.item = item;
        this.schemaRevision = schemaRevision;
        this.files = new ArrayList<>();
        initDetails(context);
    }

    public Info(Context context, String surveyGuid, String surveyCreatedOn)
    {
        this.surveyGuid = surveyGuid;
        this.surveyCreatedOn = surveyCreatedOn;
        this.files = new ArrayList<>();
        initDetails(context);
    }

    private void initDetails(Context context)
    {
        String versionName;
        PackageManager manager = context.getPackageManager();

        try
        {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            versionName = info.versionName;
        }
        catch(PackageManager.NameNotFoundException e)
        {
            LogExt.e(getClass(), "Could not find package version info");
            versionName = context.getString(R.string.rss_settings_version_unknown);
        }

        String fullPhoneInfo = Build.MANUFACTURER + " " + Build.MODEL;
        phoneInfo = fullPhoneInfo.substring(0,
                Math.min(fullPhoneInfo.length(), BRIDGE_PHONE_INFO_LIMIT));
        appVersion = versionName;
    }

    public void addFileInfo(FileInfo fileInfo)
    {
        files.add(fileInfo);
    }

    public String getFileName()
    {
        return UUID.randomUUID().toString() + "_" + (item == null ? surveyGuid : item) + ".zip";
    }
}
