package com.netease.nim.uikit.business.session.actions;

import android.util.Log;

import com.netease.nim.uikit.R;
import com.netease.nim.uikit.api.model.location.LocationProvider;
import com.netease.nim.uikit.impl.NimUIKitImpl;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.model.IMMessage;

/**
 * Created by hzxuwen on 2015/6/12.
 */
public class LocationAction extends BaseAction {
    private final static String TAG = "LocationAction";

    public LocationAction() {
        super(R.drawable.nim_message_plus_location_normal, R.string.input_panel_location);
    }

    @Override
    public void onClick() {

        if (NimUIKitImpl.getLocationProvider() != null) {
            NimUIKitImpl.getLocationProvider().requestLocation(getActivity(), new LocationProvider.Callback() {
                @Override
                public void onSuccess(double longitude, double latitude, String address) {
                    Log.i("地理位置"," 拼接：" + new StringBuffer(address).substring(0,address.lastIndexOf("区") + 1) + "|"
                            + new StringBuffer(address).substring(address.lastIndexOf("区") + 1));
                    String newAddress = new StringBuffer(address).substring(0,address.lastIndexOf("区") + 1) + "|"
                            + new StringBuffer(address).substring(address.lastIndexOf("区") + 1);
                    IMMessage message = MessageBuilder.createLocationMessage(getAccount(), getSessionType(), latitude, longitude,
                            newAddress);
                    sendMessage(message);
                }
            });
        }
    }
}
