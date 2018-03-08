package com.trustmobi.dalbum.applike;


import com.trustmobi.componentlib.applicationlike.IApplicationLike;
import com.trustmobi.componentlib.router.ui.UIRouter;

/**
 * Created by dds on 2017/6/15.
 */

public class AlbumLike implements IApplicationLike {

    UIRouter uiRouter = UIRouter.getInstance();
    AlbumUIRouter albumUIRouter = AlbumUIRouter.getInstance();

    @Override
    public void onCreate() {
        uiRouter.registerUI(albumUIRouter);
    }

    @Override
    public void onStop() {
        uiRouter.unregisterUI(albumUIRouter);
    }
}
