/* * Copyright (C) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.trncic.dottedbarsample.slice;

import com.trncic.dottedbarsample.ResourceTable;
import com.trncic.dottedbarsample.util.LogUtil;
import com.trncic.library.DottedProgressBar;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;

public class MainAbilitySlice extends AbilitySlice {
    private final int delay = 100;

    private DottedProgressBar bar;
    private boolean isInProgress;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        bar = (DottedProgressBar) findComponentById(ResourceTable.Id_progress);
        if(bar == null) {
            LogUtil.error("MainAbilitySlice",
                    "DottedProgressBar activeDot and inactiveDot params not set for Component");
            return;
        }
        Runnable run = () -> startProgress();
        EventHandler han = new EventHandler(EventRunner.getMainEventRunner());
        han.postTask(run, delay);
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopProgress();
    }

    public void stopProgress() {
        isInProgress = false;
        bar.stopProgress();
    }

    public void startProgress() {
        isInProgress = true;
        bar.startProgress();
    }

    public boolean getProgressStatus() {
        return isInProgress;
    }
}