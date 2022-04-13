package com.trncic.dottedbarsample;

import com.trncic.dottedbarsample.slice.MainAbilitySlice;
import ohos.eventhandler.EventHandler;
import ohos.eventhandler.EventRunner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainAbilitySliceTest {

    private static final MainAbilitySlice getMainAbilitySlice() {
        return new MainAbilitySlice();
    }

    @Test
    public void startProgress() {
        new EventHandler(EventRunner.getMainEventRunner()).postTask(() -> {
            MainAbilitySlice mainAbilitySlice = getMainAbilitySlice();
            mainAbilitySlice.startProgress();
            assertEquals(true, mainAbilitySlice.getProgressStatus());
        });
    }

    @Test
    public void stopProgress() {
        new EventHandler(EventRunner.getMainEventRunner()).postTask(() -> {
            MainAbilitySlice mainAbilitySlice = getMainAbilitySlice();
            mainAbilitySlice.stopProgress();
            assertEquals(false, mainAbilitySlice.getProgressStatus());
        });
    }
}