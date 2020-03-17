package com.xcy.fzbcity.all.myim;

import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.msg.model.TeamMsgAckInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by winnie on 2018/3/17.
 */

public class AckMsgViewModel extends ViewModel {
    private LiveData<TeamMsgAckInfo> teamMsgAckInfo;
    private AckModelRepository ackModelRepository;

    public void init(IMMessage message) {
        if (this.teamMsgAckInfo  != null) {
            return;
        }
        ackModelRepository = new AckModelRepository();
        teamMsgAckInfo = ackModelRepository.getMsgAckInfo(message);
    }

    public LiveData<TeamMsgAckInfo> getTeamMsgAckInfo() {
        return teamMsgAckInfo;
    }
}

