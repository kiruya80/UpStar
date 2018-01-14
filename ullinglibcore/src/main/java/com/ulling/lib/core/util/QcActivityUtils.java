/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ulling.lib.core.util;

import android.support.annotation.AnimRes;
import android.support.annotation.AnimatorRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * This provides methods to help Activities load their UI.
 * https://github.com/googlesamples/android-architecture/tree/dev-todo-mvvm-rxjava/todoapp/app
 * /src/main/java/com/example/android/architecture/blueprints/todoapp/util
 */
public class QcActivityUtils {

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragment(@NonNull FragmentManager fragmentManager,
                                   @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commit();
    }

    public static void addBackStackFragment(@NonNull FragmentManager fragmentManager,
                                            @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public static void addFragment(@NonNull FragmentManager fragmentManager,
                                   @NonNull Fragment fragment, int frameId, String tag,
                                   @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit,
                                   @AnimatorRes @AnimRes int popEnter, @AnimatorRes @AnimRes int popExit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit, popEnter, popExit);
        transaction.add(frameId, fragment, tag);
        transaction.commit();
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, tag);
        transaction.commit();
    }

    public static void replaceFragment(@NonNull FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId, String tag,
                                       @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit,
                                       @AnimatorRes @AnimRes int popEnter, @AnimatorRes @AnimRes int popExit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit, popEnter, popExit);
        transaction.replace(frameId, fragment, tag);
        transaction.commit();
    }

    public static void replaceFragmentAddToBackStack(@NonNull FragmentManager fragmentManager,
                                                     @NonNull Fragment fragment, int frameId,
                                                     String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void removeFragment(@NonNull FragmentManager fragmentManager,
                                      @NonNull String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragmentManager.findFragmentByTag(tag));
        transaction.commit();
    }

    public static void removeFragment(@NonNull FragmentManager fragmentManager,
                                      @NonNull String tag,
                                      @AnimatorRes @AnimRes int enter, @AnimatorRes @AnimRes int exit,
                                      @AnimatorRes @AnimRes int popEnter, @AnimatorRes @AnimRes int popExit) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enter, exit, popEnter, popExit);
        transaction.remove(fragmentManager.findFragmentByTag(tag));
        transaction.commit();
    }

    public static void hideFragment(@NonNull FragmentManager fragmentManager,
                                    @NonNull String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.hide(fragmentManager.findFragmentByTag(tag));
        transaction.commit();
    }

    public static void showFragment(@NonNull FragmentManager fragmentManager, @NonNull String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragmentManager.findFragmentByTag(tag));
        transaction.commit();
    }

    public static void showFragment(@NonNull FragmentManager fragmentManager,
                                    @NonNull Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.show(fragment);
        transaction.commit();
    }

    public static boolean hasFragment(@NonNull FragmentManager fragmentManager,
                                      @NonNull String tag) {
        return fragmentManager.findFragmentByTag(tag) != null;
    }

}