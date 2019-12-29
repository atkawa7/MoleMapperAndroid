package org.researchstack.molemapper;

import android.app.Application;



import org.researchstack.skin.ResearchStack;



public class MoleMapperApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        //MultiDex.install(this);
        // TODO remove Fabric/Crashlytics after QA process
       // Fabric.with(this, new Crashlytics());

        ResearchStack.init(this, new MoleMapperResearchStack());
    }

}
