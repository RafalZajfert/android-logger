/*
 * Copyright 2017 rSquared s.c. R. Orlik, R. Zajfert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package software.rsquared.androidlogger.toast;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

import software.rsquared.androidlogger.AppenderConfig;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

/**
 * @author Rafal Zajfert
 */
@SuppressWarnings("unused")
public class ToastAppenderConfig extends AppenderConfig<ToastAppenderConfig> {

    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {}

    @Duration
    private int duration = Toast.LENGTH_SHORT;

    ToastAppenderConfig() {
    }

    /**
     * How long to display the message.  Either {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     */
    public ToastAppenderConfig setDuration(@Duration int duration) {
        this.duration = duration;
        return this;
    }

    /**
     * How long to display the message.  Either {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}
     */
    @Duration
    public int getDuration() {
        return this.duration;
    }

    @Override
    protected void read(@NonNull Map<String, String> config) {
        super.read(config);

        if (config.containsKey("duration")) {
            switch (config.get("duration")){
                case "LENGTH_SHORT":
                    setDuration(LENGTH_SHORT);
                    break;
                case "LENGTH_LONG":
                    setDuration(LENGTH_LONG);
                    break;
                default:
                    throw new IllegalArgumentException("Illegal duration length. Must be one of: LENGTH_SHORT, LENGTH_LONG");
            }
        }
    }
}
