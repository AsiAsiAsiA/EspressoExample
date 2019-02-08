package com.example.semen.espressoexample;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

public class SecondActivityTestRule extends ActivityTestRule<SecondActivity> {

    public SecondActivityTestRule() {
        super(SecondActivity.class);
    }

    @Override
    protected Intent getActivityIntent() {
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_ID, "3");
        intent.putExtra(Constants.EXTRA_NAME, "Name 3");
        return intent;
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        System.out.println("beforeActivityLaunched");
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();
        System.out.println("afterActivityLaunched");
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        System.out.println("afterActivityFinished");
    }
}